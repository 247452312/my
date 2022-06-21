package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.base.AbstractEntity;
import indi.uhyils.repository.VerificationRepository;
import indi.uhyils.util.Asserts;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.util.Base64Utils;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 15时54分
 */
public class Verification extends AbstractEntity {

    /**
     * 验证码有效期
     */
    private static final Integer VERIFICATION_TIME_OUT = 30;

    private Integer width;

    private Integer height;

    private Integer lines;

    private byte[] img;

    /**
     * img编译后的base64
     */
    private String base64;

    /**
     * 字体
     */
    private Font font;

    private String code;

    private String key;

    public Verification(Integer width, Integer height, Integer lines) {
        this(width, height, lines, new Font("cmr10", Font.BOLD, (int) (height / 1.2)));
    }

    public Verification(String key, String code) {
        this.key = key;
        this.code = code;
    }


    public Verification(Integer width, Integer height, Integer lines, Font font) {
        this.width = width;
        this.height = height;
        this.lines = lines;
        this.font = font;
    }

    public void makeImg() throws IOException {
        Asserts.assertTrue(width != null, "宽度不能为null");
        Asserts.assertTrue(height != null, "长度不能为null");
        Asserts.assertTrue(lines != null, "干扰线数量不能为null");
        Asserts.assertTrue(font != null, "字体不能为null");

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 设置字体
        g.setFont(font);
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
        this.img = Base64Utils.encode(outputStream.toByteArray());
        this.code = code;
    }

    public void saveCodeToCache(VerificationRepository rep) {
        this.saveCodeToCache(rep, VERIFICATION_TIME_OUT);
    }

    public void saveCodeToCache(VerificationRepository rep, Integer time) {
        String key = findKey();
        rep.saveVerificationToCache(key, code, time);
    }

    public String findVerificationBase64() {
        Asserts.assertTrue(img != null, "img没有初始化,请执行:makeImg");
        if (base64 != null) {
            return base64;
        }

        return this.base64 = "data:image/jpeg;base64," + new String(img).replaceAll("\n", "");
    }

    public String findKey() {
        if (this.key != null) {
            return this.key;
        }
        return this.key = "Verification" + UUID.randomUUID().toString().replaceAll("-", "");
    }

    public Boolean verification(VerificationRepository rep) {
        Asserts.assertTrue(code != null, "code不能为空");
        Asserts.assertTrue(key != null, "key不能为空");
        return rep.verification(this);
    }

    public String key() {
        return key;
    }

    public String code() {
        return code;
    }

    public void cleanCache(VerificationRepository rep) {
        rep.cleanCache(this);
    }
}
