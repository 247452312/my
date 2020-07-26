package indi.uhyils.outapi.tencent;

import com.tencent.cloud.asr.realtime.sdk.config.AsrInternalConfig;
import com.tencent.cloud.asr.realtime.sdk.config.AsrPersonalConfig;
import com.tencent.cloud.asr.realtime.sdk.http.synchronize.RequestSender;
import com.tencent.cloud.asr.realtime.sdk.model.enums.EngineModelType;
import com.tencent.cloud.asr.realtime.sdk.model.enums.ResponseEncode;
import com.tencent.cloud.asr.realtime.sdk.model.enums.SdkRole;
import com.tencent.cloud.asr.realtime.sdk.model.enums.VoiceFormat;
import com.tencent.cloud.asr.realtime.sdk.model.response.VoiceResponse;
import com.tencent.cloud.asr.realtime.sdk.utils.ByteUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月26日 13时18分
 */
public class RasrRequestSample {
    static {
        initBaseParameters();
    }

    public static void main(String[] args) {
        RasrRequestSample rasrRequestSample = new RasrRequestSample();
        rasrRequestSample.start();
    }

    private void start() {
        this.sendBytesRequest();
        System.exit(0);
    }

    /**
     * 从字节数组读取语音数据，发送请求。
     */
    private void sendBytesRequest() {
        RequestSender requestSender = new RequestSender();
        byte[] content = ByteUtils.inputStream2ByteArray("D:\\share\\ideaSrc\\speechRecognition\\data\\data_thchs30\\data\\A2_0.wav");
        VoiceResponse voiceResponse = requestSender.sendFromBytes(content);
        printReponse(voiceResponse);
    }

    private void printReponse(VoiceResponse voiceResponse) {
        if (voiceResponse != null)
            System.out.println("Result is: " + voiceResponse.getOriginalText());
        else
            System.out.println("Result is null.");
    }

    /**
     * 初始化基础参数, 请将下面的参数值配置成您自己的值。
     * <p>
     * 参数获取方法可参考： <a href="https://cloud.tencent.com/document/product/441/6203">签名鉴权 获取签名所需信息</a>
     */
    private static void initBaseParameters() {
        AsrInternalConfig.setSdkRole(SdkRole.ONLINE); // VAD版用户请务必赋值为 SdkRole.VAD
        AsrPersonalConfig.responseEncode = ResponseEncode.UTF_8;
        AsrPersonalConfig.engineModelType = EngineModelType._16k_0.getName();
        AsrPersonalConfig.voiceFormat = VoiceFormat.wav;
    }
}
