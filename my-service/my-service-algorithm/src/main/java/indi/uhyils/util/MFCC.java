package indi.uhyils.util;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 14时53分
 */
public class MFCC {
    /**
     * parameter USEPOWER in HTK, where default is false
     */
    private static final boolean M_OUSE_POWER_INSTEAD_OF_MAGNITUDE = false;


    // Number of MFCCs per speech frame.
    private final int m_nnumberOfParameters;
    /**
     * Sampling frequency.
     */
    private final double m_dsamplingFrequency;
    /**
     * Number of filter in mel filter bank.
     */
    private final int m_nnumberOfFilters;
    /**
     * Number of FFT points.
     */
    private final int m_nFFTLength;
    /**
     * Coefficient of filtering performing in cepstral domain (called
     * 'liftering' operation). It is not used if m_oisLifteringEnabled is false.
     */
    private final int m_nlifteringCoefficient;
    /**
     * True enables liftering.
     */
    private final boolean m_oisLifteringEnabled;
    /**
     * Minimum value of filter output, otherwise the log is not calculated and
     * m_dlogFilterOutputFloor is adopted. ISIP implementation assumes
     * m_dminimumFilterOutput = 1 and this value is used here.
     */
    private final double m_dminimumFilterOutput = 1.0;


    /**
     * True if the zero'th MFCC should be calculated.
     */
    private final boolean m_oisZeroThCepstralCoefficientCalculated;


    /**
     * Floor value for filter output in log domain. ISIP implementation assumes
     * m_dlogFilterOutputFloor = 0 and this value is used here.
     */
    private final double m_dlogFilterOutputFloor = 0.0;
    private final double[] m_nlifteringMultiplicationFactor;
    // things to be calculated just once:
    private final double m_dscalingFactor;
    private int[][] m_nboundariesDFTBins;
    private double[][] m_dweights;
    private FFT m_fft;
    private double[][] m_ddCTMatrix;
    private double[] m_dfilterOutput;


    /**
     * The 0-th coefficient is included in nnumberOfParameters. So, if one wants
     * 12 MFCC's and additionally the 0-th coefficient, one should call the
     * constructor with nnumberOfParameters = 13 and
     * oisZeroThCepstralCoefficientCalculated = true
     *
     * @param nnumberOfParameters                    输出参数数量
     * @param dsamplingFrequency                     采样频率
     * @param nnumberofFilters                       过滤器数量
     * @param nFFTLength                             FFT长度
     * @param oisLifteringEnabled                    true 提升系数
     * @param nlifteringCoefficient                  滤波器个数
     * @param oisZeroThCepstralCoefficientCalculated false 是否计算第零个倒谱mel系数
     */
    public MFCC(int nnumberOfParameters, double dsamplingFrequency,
                int nnumberofFilters, int nFFTLength, boolean oisLifteringEnabled,
                int nlifteringCoefficient,
                boolean oisZeroThCepstralCoefficientCalculated) {


        m_oisZeroThCepstralCoefficientCalculated = oisZeroThCepstralCoefficientCalculated;
        if (m_oisZeroThCepstralCoefficientCalculated) {
            // the user shouldn't notice that nnumberOfParameters was
            // decremented internally
            m_nnumberOfParameters = nnumberOfParameters - 1;
        } else {
            m_nnumberOfParameters = nnumberOfParameters;
        }


        m_dsamplingFrequency = dsamplingFrequency;
        m_nnumberOfFilters = nnumberofFilters;
        m_nFFTLength = nFFTLength;


        // the filter bank weights, FFT's cosines and sines
        // and DCT matrix are initialized once to save computations.


        // initializes the mel-based filter bank structure
        calculateMelBasedFilterBank(dsamplingFrequency, nnumberofFilters,
                nFFTLength);
        m_fft = new FFT(m_nFFTLength); // initialize FFT
        initializeDCTMatrix();
        m_nlifteringCoefficient = nlifteringCoefficient;
        m_oisLifteringEnabled = oisLifteringEnabled;


        // avoid allocating RAM space repeatedly, m_dfilterOutput is
        // going to be used in method getParameters()
        m_dfilterOutput = new double[m_nnumberOfFilters];


        // needed in method getParameters()
        // m_dscalingFactor shouldn't be necessary because it's only
        // a scaling factor, but I'll implement it
        // for the sake of getting the same numbers ISIP gets
        m_dscalingFactor = Math.sqrt(2.0 / m_nnumberOfFilters);


        // for liftering method
        if (m_oisLifteringEnabled) {
            // note that:
            int nnumberOfCoefficientsToLift = m_nnumberOfParameters;
            // even when m_oisZeroThCepstralCoefficientCalculated is true
            // because if 0-th cepstral coefficient is included,
            // it is not liftered
            m_nlifteringMultiplicationFactor = new double[m_nlifteringCoefficient];
            double dfactor = m_nlifteringCoefficient / 2.0;
            double dfactor2 = Math.PI / m_nlifteringCoefficient;
            for (int i = 0; i < m_nlifteringCoefficient; i++) {
                m_nlifteringMultiplicationFactor[i] = 1.0 + dfactor
                        * Math.sin(dfactor2 * (i + 1));
            }
            if (m_nnumberOfParameters > m_nlifteringCoefficient) {
                new Error(
                        "Liftering is enabled and the number "
                                + "of parameters = "
                                + m_nnumberOfParameters
                                + ", while "
                                + "the liftering coefficient is "
                                + m_nlifteringCoefficient
                                + ". In this case some cepstrum coefficients would be made "
                                + "equal to zero due to liftering, what does not make much "
                                + "sense in a speech recognition system. You may want to "
                                + "increase the liftering coefficient or decrease the number "
                                + "of MFCC parameters.");
            }
        } else {
            m_nlifteringMultiplicationFactor = null;
        }
    }

    /**
     * Converts frequencies in Hz to mel scale according to mel frequency = 2595
     * log(1 + (f/700)), where log is base 10 and f is the frequency in Hz.
     */
    public static double[] convertHzToMel(double[] dhzFrequencies,
                                          double dsamplingFrequency) {
        double[] dmelFrequencies = new double[dhzFrequencies.length];
        for (int k = 0; k < dhzFrequencies.length; k++) {
            dmelFrequencies[k] = 2595.0 * (Math
                    .log(1.0 + (dhzFrequencies[k] / 700.0)) / Math.log(10));
        }
        return dmelFrequencies;
    }

    /**
     * Initializes the DCT matrix.
     */
    private void initializeDCTMatrix() {
        m_ddCTMatrix = new double[m_nnumberOfParameters][m_nnumberOfFilters];
        for (int i = 0; i < m_nnumberOfParameters; i++) {
            for (int j = 0; j < m_nnumberOfFilters; j++) {
                m_ddCTMatrix[i][j] = Math.cos((i + 1.0) * (j + 1.0 - 0.5)
                        * (Math.PI / m_nnumberOfFilters));
            }
        }
    }

    /**
     * Calculates triangular filters. 三角带通滤波器（Triangular Bandpass
     * Filters）：将能量频谱能量乘以一组 20 个三角带通滤波器，求得每一个滤波器输出的对数能量（Log
     * Energy），共20个。必须注意的是：这 20 个三角带通滤波器在「梅尔频率」（Mel Frequency）上是平均分布的，而梅尔频率和一般频率
     * f 的关系式如下： mel(f)=2595*log10(1+f/700) 或是 mel(f)=1125*ln(1+f/700)
     * 梅尔频率代表一般人耳对于频率的感受度，由此也可以看出人耳对于频率 f 的感受是呈对数变化的： 在低频部分，人耳感受是比较敏锐 。
     * 在高频部分，人耳的感受就会越来越粗糙 。 三角带通滤波器有两个主要目的： 对频谱进行平滑化，并消除谐波的作用，突显原先语音的共振峰。
     * （因此一段语音的音调或音高，是不会呈现在 MFCC 参数内，换句话说，以 MFCC为特征的语音辨识系统，并不会受到输入语音的音调不同而有所影响。）
     */
    @SuppressWarnings("static-access")
    private void calculateMelBasedFilterBank(double dsamplingFrequency,
                                             int nnumberofFilters, int nfftLength) {


        // frequencies for each triangular filter
        @SuppressWarnings("unused")
        double[][] dfrequenciesInMelScale = new double[nnumberofFilters][3];
        // the +1 below is due to the sample of frequency pi (or fs/2)
        double[] dfftFrequenciesInHz = new double[nfftLength / 2 + 1];
        // compute the frequency of each FFT sample (in Hz):
        double ddeltaFrequency = dsamplingFrequency / nfftLength;
        for (int i = 0; i < dfftFrequenciesInHz.length; i++) {
            dfftFrequenciesInHz[i] = i * ddeltaFrequency;
        }
        // convert Hz to Mel
        double[] dfftFrequenciesInMel = convertHzToMel(
                dfftFrequenciesInHz, dsamplingFrequency);


        // compute the center frequencies. Notice that 2 filters are
        // "artificially" created in the endpoints of the frequency
        // scale, correspondent to 0 and fs/2 Hz.
        double[] dfilterCenterFrequencies = new double[nnumberofFilters + 2];
        // implicitly: dfilterCenterFrequencies[0] = 0.0;
        ddeltaFrequency = dfftFrequenciesInMel[dfftFrequenciesInMel.length - 1]
                / (nnumberofFilters + 1);
        for (int i = 1; i < dfilterCenterFrequencies.length; i++) {
            dfilterCenterFrequencies[i] = i * ddeltaFrequency;
        }


        // initialize member variables
        m_nboundariesDFTBins = new int[m_nnumberOfFilters][2];
        m_dweights = new double[m_nnumberOfFilters][];


        // notice the loop starts from the filter i=1 because i=0 is the one
        // centered at DC
        for (int i = 1; i <= nnumberofFilters; i++) {
            m_nboundariesDFTBins[i - 1][0] = Integer.MAX_VALUE;
            // notice the loop below doesn't include the first and last FFT
            // samples
            for (int j = 1; j < dfftFrequenciesInMel.length - 1; j++) {
                // see if frequency j is inside the bandwidth of filter i
                if ((dfftFrequenciesInMel[j] >= dfilterCenterFrequencies[i - 1])
                        & (dfftFrequenciesInMel[j] <= dfilterCenterFrequencies[i + 1])) {
                    // the i-1 below is due to the fact that we discard the
                    // first filter i=0
                    // look for the first DFT sample for this filter
                    if (j < m_nboundariesDFTBins[i - 1][0]) {
                        m_nboundariesDFTBins[i - 1][0] = j;
                    }
                    // look for the last DFT sample for this filter
                    if (j > m_nboundariesDFTBins[i - 1][1]) {
                        m_nboundariesDFTBins[i - 1][1] = j;
                    }
                }
            }
        }
        // check for consistency. The problem below would happen just
        // in case of a big number of MFCC parameters for a small DFT length.
        for (int i = 0; i < nnumberofFilters; i++) {
            if (m_nboundariesDFTBins[i][0] == m_nboundariesDFTBins[i][1]) {
                new Error(
                        "Error in MFCC filter bank. In filter "
                                + i
                                + " the first sample is equal to the last sample !"
                                + " Try changing some parameters, for example, decreasing the number of filters.");
            }
        }


        // allocate space
        for (int i = 0; i < nnumberofFilters; i++) {
            m_dweights[i] = new double[m_nboundariesDFTBins[i][1]
                    - m_nboundariesDFTBins[i][0] + 1];
        }


        // calculate the weights
        for (int i = 1; i <= nnumberofFilters; i++) {
            for (int j = m_nboundariesDFTBins[i - 1][0], k = 0; j <= m_nboundariesDFTBins[i - 1][1]; j++, k++) {
                if (dfftFrequenciesInMel[j] < dfilterCenterFrequencies[i]) {
                    m_dweights[i - 1][k] = (dfftFrequenciesInMel[j] - dfilterCenterFrequencies[i - 1])
                            / (dfilterCenterFrequencies[i] - dfilterCenterFrequencies[i - 1]);
                } else {
                    m_dweights[i - 1][k] = 1.0 - ((dfftFrequenciesInMel[j] - dfilterCenterFrequencies[i]) / (dfilterCenterFrequencies[i + 1] - dfilterCenterFrequencies[i]));
                }
            }
        }
    }


    /**
     * Returns the MFCC coefficients for the given speech frame. If calculated,
     * the 0-th coefficient is added to the end of the vector (for compatibility
     * with HTK). The order of an output vector x with 3 MFCC's, including the
     * 0-th, would be: x = {MFCC1, MFCC2, MFCC0}
     */
    public double[] getParameters(double[] fspeechFrame) {


        // use mel filter bank
        for (int i = 0; i < m_nnumberOfFilters; i++) {
            m_dfilterOutput[i] = 0.0;
            // Notice that the FFT samples at 0 (DC) and fs/2 are not considered
            // on this calculation
            if (M_OUSE_POWER_INSTEAD_OF_MAGNITUDE) {
                double[] fpowerSpectrum = m_fft.calculateFFTPower(fspeechFrame);
                for (int j = m_nboundariesDFTBins[i][0], k = 0; j <= m_nboundariesDFTBins[i][1]; j++, k++) {
                    m_dfilterOutput[i] += fpowerSpectrum[j] * m_dweights[i][k];
                }
            } else {
                double[] fmagnitudeSpectrum = m_fft
                        .calculateFFTMagnitude(fspeechFrame);
                for (int j = m_nboundariesDFTBins[i][0], k = 0; j <= m_nboundariesDFTBins[i][1]; j++, k++) {
                    m_dfilterOutput[i] += fmagnitudeSpectrum[j]
                            * m_dweights[i][k];
                }
            }


            // ISIP (Mississipi univ.) implementation
            if (m_dfilterOutput[i] > m_dminimumFilterOutput) {// floor power to
                // avoid log(0)
                m_dfilterOutput[i] = Math.log(m_dfilterOutput[i]); // using ln
            } else {
                m_dfilterOutput[i] = m_dlogFilterOutputFloor;
            }
        }


        // need to allocate space for output array
        // because it allows the user to call this method
        // many times, without having to do a deep copy
        // of the output vector
        double[] dMFCCParameters = null;
        if (m_oisZeroThCepstralCoefficientCalculated) {
            dMFCCParameters = new double[m_nnumberOfParameters + 1];
            // calculates zero'th cepstral coefficient and pack it
            // after the MFCC parameters of each frame for the sake
            // of compatibility with HTK
            double dzeroThCepstralCoefficient = 0.0;
            for (int j = 0; j < m_nnumberOfFilters; j++) {
                dzeroThCepstralCoefficient += m_dfilterOutput[j];
            }
            dzeroThCepstralCoefficient *= m_dscalingFactor;
            dMFCCParameters[dMFCCParameters.length - 1] = dzeroThCepstralCoefficient;
        } else {
            // allocate space
            dMFCCParameters = new double[m_nnumberOfParameters];
        }


        /*
         * cosine transform
         */
        for (int i = 0; i < m_nnumberOfParameters; i++) {
            for (int j = 0; j < m_nnumberOfFilters; j++) {
                dMFCCParameters[i] += m_dfilterOutput[j] * m_ddCTMatrix[i][j];
                // the original equations have the first index as 1
            }
            // could potentially incorporate liftering factor and
            // factor below to save multiplications, but will not
            // do it for the sake of clarity
            dMFCCParameters[i] *= m_dscalingFactor;
        }



        if (m_oisLifteringEnabled) {
            // Implements liftering to smooth the cepstral coefficients
            // according to
            // [1] Rabiner, Juang, Fundamentals of Speech Recognition, pp. 169,
            // [2] The HTK Book, pp 68 and
            // [3] ISIP package - Mississipi Univ. Picone's group.
            // if 0-th coefficient is included, it is not liftered
            for (int i = 0; i < m_nnumberOfParameters; i++) {
                dMFCCParameters[i] *= m_nlifteringMultiplicationFactor[i];
            }
        }


        return dMFCCParameters;
    } // end method


    /**
     * Returns the sampling frequency.
     */
    public double getSamplingFrequency() {
        return this.m_dsamplingFrequency;
    }


    /**
     * Returns the number of points of the Fast Fourier Transform (FFT) used in
     * the calculation of this MFCC.
     */
    public int getFFTLength() {
        return m_nFFTLength;
    }


    /**
     * Returns the number of MFCC coefficients, including the 0-th if required
     * by user in the object construction.
     */
    public int getNumberOfCoefficients() {
        return (m_oisZeroThCepstralCoefficientCalculated ? (m_nnumberOfParameters + 1)
                : m_nnumberOfParameters);
    }


    /**
     * Return a string with all important parameters of this object.
     */
    @Override
    public String toString() {
        return "MFCC.nnumberOfParameters = "
                + (m_oisZeroThCepstralCoefficientCalculated ? (m_nnumberOfParameters + 1)
                : m_nnumberOfParameters) + "\n"
                + "MFCC.nnumberOfFilters = " + m_nnumberOfFilters + "\n"
                + "MFCC.nFFTLength = " + m_nFFTLength + "\n"
                + "MFCC.dsamplingFrequency = " + m_dsamplingFrequency + "\n"
                + "MFCC.nlifteringCoefficient = " + m_nlifteringCoefficient
                + "\n" + "MFCC.oisLifteringEnabled = " + m_oisLifteringEnabled
                + "\n" + "MFCC.oisZeroThCepstralCoefficientCalculated = "
                + m_oisZeroThCepstralCoefficientCalculated;
    }


    public double[] getFilterBankOutputs(double[] fspeechFrame) {
        // use mel filter bank
        double[] dfilterOutput = new double[m_nnumberOfFilters];
        for (int i = 0; i < m_nnumberOfFilters; i++) {
            // Notice that the FFT samples at 0 (DC) and fs/2 are not considered
            // on this calculation
            if (M_OUSE_POWER_INSTEAD_OF_MAGNITUDE) {
                double[] fpowerSpectrum = m_fft.calculateFFTPower(fspeechFrame);
                for (int j = m_nboundariesDFTBins[i][0], k = 0; j <= m_nboundariesDFTBins[i][1]; j++, k++) {
                    dfilterOutput[i] += fpowerSpectrum[j] * m_dweights[i][k];
                }
            } else {
                double[] fmagnitudeSpectrum = m_fft
                        .calculateFFTMagnitude(fspeechFrame);
                for (int j = m_nboundariesDFTBins[i][0], k = 0; j <= m_nboundariesDFTBins[i][1]; j++, k++) {
                    dfilterOutput[i] += fmagnitudeSpectrum[j]
                            * m_dweights[i][k];
                }
            }


            // ISIP (Mississipi univ.) implementation
            if (dfilterOutput[i] > m_dminimumFilterOutput) {// floor power to
                // avoid log(0)
                dfilterOutput[i] = Math.log(dfilterOutput[i]); // using ln
            } else {
                dfilterOutput[i] = m_dlogFilterOutputFloor;
            }
        }
        return dfilterOutput;
    }
}
