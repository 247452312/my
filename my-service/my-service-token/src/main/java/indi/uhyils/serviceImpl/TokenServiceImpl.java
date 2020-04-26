package indi.uhyils.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import indi.uhyils.request.DefaultRequest;
import indi.uhyils.request.IdRequest;
import indi.uhyils.response.ServiceResult;
import indi.uhyils.service.TokenService;
import indi.uhyils.service.indi.uhyils.model.TokenInfo;
import indi.uhyils.util.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月26日 09时41分
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Value("${token.salt}")
    private String salt;

    @Value("${token.encodeRules}")
    private String encodeRules;

    @Override
    public ServiceResult<String> getToken(IdRequest userId) {
        StringBuilder sb = new StringBuilder();

        //生成日期部分
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddhhmmss");
        String format = localDateTime.format(dateTimeFormatter);
        sb.append(format);

        sb.append(userId.getId());
        sb.append(salt);

        String token = AESUtil.AESEncode(encodeRules, sb.toString());

        return ServiceResult.buildSuccessResult("token生成成功", token, userId);
    }

    @Override
    public ServiceResult<TokenInfo> getTokenInfoByToken(DefaultRequest defaultRequest) {
        String token = defaultRequest.getToken();

        String tokenInfo_ = AESUtil.AESDecode(encodeRules, token);
        String year = tokenInfo_.substring(0, 2);
        String month = tokenInfo_.substring(2, 4);
        String day = tokenInfo_.substring(4, 6);
        String hour = tokenInfo_.substring(6, 8);
        String mon = tokenInfo_.substring(8, 10);
        String sec = tokenInfo_.substring(10, 12);
        String userId = tokenInfo_.substring(12, tokenInfo_.length() - 1 - salt.length());

        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setYear(Integer.parseInt(year));
        tokenInfo.setMonth(Integer.parseInt(month));
        tokenInfo.setDay(Integer.parseInt(day));
        tokenInfo.setHour(Integer.parseInt(hour));
        tokenInfo.setMin(Integer.parseInt(mon));
        tokenInfo.setSec(Integer.parseInt(sec));
        tokenInfo.setUserId(userId);


        return ServiceResult.buildSuccessResult("解密成功", tokenInfo, defaultRequest);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEncodeRules() {
        return encodeRules;
    }

    public void setEncodeRules(String encodeRules) {
        this.encodeRules = encodeRules;
    }

    public static void main(String[] args) {
        TokenServiceImpl tokenService = new TokenServiceImpl();
        ServiceResult<String> token = tokenService.getToken(IdRequest.build("9c458f04afb1c6fe"));
    }
}
