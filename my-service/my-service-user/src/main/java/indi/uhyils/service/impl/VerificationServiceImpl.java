package indi.uhyils.service.impl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.DTO.request.VerificationCommand;
import indi.uhyils.pojo.DTO.response.VerificationGetDTO;
import indi.uhyils.pojo.cqe.DefaultCQE;
import indi.uhyils.pojo.entity.Verification;
import indi.uhyils.repository.VerificationRepository;
import indi.uhyils.service.VerificationService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时28分
 */
@Service
@ReadWriteMark
public class VerificationServiceImpl implements VerificationService {


    @Autowired
    private VerificationRepository rep;

    @Override
    public VerificationGetDTO getVerification(DefaultCQE request) throws IOException {
        Verification verification = new Verification(160, 60, 10);
        // 产生图片
        verification.makeImg();
        // 保存到缓存中
        verification.saveCodeToCache(rep);
        // 获取base64
        String base64 = verification.findVerificationBase64();
        // 获取对应的key
        String key = verification.findKey();
        return VerificationGetDTO.build(base64, key);
    }

    @Override
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public Boolean verification(VerificationCommand request) {
        Verification verification = new Verification(request.getKey(), request.getCode());
        // 验证
        Boolean result = verification.verification(rep);
        // 一个验证码只有一次有效
        if (result) {
            verification.cleanCache(rep);
        }
        return result;
    }
}
