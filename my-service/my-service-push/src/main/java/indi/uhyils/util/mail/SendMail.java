package indi.uhyils.util.mail;


import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import indi.uhyils.util.LogUtil;

import java.util.ArrayList;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月22日 16时06分
 */
public class SendMail {

    private static final String HOST = "smtp.qq.com";
    private static final String PORT = "465";
    /**
    * stmp密码
    */
    private static final String PASS = "*************";
    private static final String USER = "uhyils";
    private static final String FROM_USER = "uhyils@qq.com";

    public static Boolean send(String mail, String title, String content) {
        EmailVo emailVo = new EmailVo();
        EmailConfig emailConfig = new EmailConfig();

        emailConfig.setHost(HOST);
        emailConfig.setPort(PORT);
        emailConfig.setPass(PASS);
        emailConfig.setUser(USER);
        emailConfig.setFromUser(FROM_USER);

        emailVo.setContent(content);
        ArrayList<String> tos = new ArrayList<>();
        tos.add(mail);
        emailVo.setTos(tos);
        emailVo.setSubject(title);

        // 封装
        MailAccount account = new MailAccount();
        account.setHost(emailConfig.getHost());
        account.setPort(Integer.parseInt(emailConfig.getPort()));
        account.setAuth(true);
        try {
            // 对称解密
            account.setPass(emailConfig.getPass());
        } catch (Exception e) {
            LogUtil.error(SendMail.class, e);
        }
        account.setFrom(emailConfig.getUser() + "<" + emailConfig.getFromUser() + ">");
        // ssl方式发送
        account.setSslEnable(true);
        // 发送
        try {
            int size = emailVo.getTos().size();
            Mail.create(account)
                    .setTos(emailVo.getTos().toArray(new String[size]))
                    .setTitle(emailVo.getSubject())
                    .setContent(emailVo.getContent())
                    //抄送给自己防止邮箱发送出现554
                    .setCcs(emailConfig.getFromUser())
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
            return true;
        } catch (Exception e) {
            LogUtil.error(SendMail.class, e);
        }
        return false;
    }
}
