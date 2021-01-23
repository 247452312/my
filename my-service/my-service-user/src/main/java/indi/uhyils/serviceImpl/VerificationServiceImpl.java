package indi.uhyils.serviceImpl;

import indi.uhyils.annotation.NoToken;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.enum_.ReadWriteTypeEnum;
import indi.uhyils.pojo.request.VerificationRequest;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.response.VerificationGetResponse;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.redis.RedisPoolHandle;
import indi.uhyils.redis.Redisable;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年09月14日 06时34分
 */
@RpcService
@ReadWriteMark
public class VerificationServiceImpl implements VerificationService {

    /**
     * 验证码有效期
     */
    private static final Integer VERIFICATION_TIME_OUT = 30;
    @Autowired
    private RedisPoolHandle redisPool;

    @Override
    @NoToken
    public ServiceResult<VerificationGetResponse> getVerification(DefaultRequest request) throws IOException {
        int width = 160;
        int height = 60;
        int lines = 10;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 设置字体
        g.setFont(new Font("cmr10", Font.BOLD, (int) (height / 1.2)));
        // 随机数字
        Random r = new Random(System.currentTimeMillis());
        String code = "";
        for (int i = 0; i < 4; i++) {
            int a = r.nextInt(10);
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawString("" + a, 5 + i * width / 4, (int) (height / 1.3) + r.nextInt(height / 8));
            code = code + a;
        }
        // 干扰线
        for (int i = 0; i < lines; i++) {
            Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            g.setColor(c);
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        }
        g.dispose();// 类似于流中的close()带动flush()---把数据刷到img对象当中
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", outputStream);
        byte[] base64Img = Base64Utils.encode(outputStream.toByteArray());
        String value = "data:image/jpeg;base64," + new String(base64Img).replaceAll("\n", "");
        String key = "Verification" + UUID.randomUUID().toString().replaceAll("-", "");
        VerificationGetResponse build = VerificationGetResponse.build(value, key);
        Redisable jedis = redisPool.getJedis();

        try {
            jedis.set(key, code);
            jedis.expire(key, VERIFICATION_TIME_OUT);
            return ServiceResult.buildSuccessResult(build, request);
        } finally {
            jedis.close();
        }
    }

    @Override
    @NoToken
    @ReadWriteMark(type = ReadWriteTypeEnum.WRITE)
    public ServiceResult<Boolean> verification(VerificationRequest request) {
        String key = request.getKey();
        String code = request.getCode();
        Redisable jedis = redisPool.getJedis();

        try {
            String realCode = jedis.get(key);
            if (code.equals(realCode)) {
                jedis.del(key);
                return ServiceResult.buildSuccessResult(true, request);
            }
            return ServiceResult.buildSuccessResult(false, request);
        } finally {
            jedis.close();
        }
    }
}
