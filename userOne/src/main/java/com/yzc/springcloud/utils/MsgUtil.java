package com.yzc.springcloud.utils;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzc.springcloud.entity.SystemCode;
import com.yzc.springcloud.service.SystemCodeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
@Slf4j
public class MsgUtil {
    @Autowired
    private SystemCodeService systemCodeService;


    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public  com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    //手机短信
    public void sendSms(String phone,String code)  throws Exception{
        SystemCode smsSystemOne = systemCodeService.getOne(new QueryWrapper<SystemCode>().eq("key_name", "sms"));
        SystemCode smsSystemTwo = systemCodeService.getOne(new QueryWrapper<SystemCode>().eq("key_name", "sms_parameter"));
        if(null == smsSystemOne || null == smsSystemTwo) {
            return;
        }
        com.aliyun.dysmsapi20170525.Client client = createClient(smsSystemOne.getSystemKey(), smsSystemOne.getSystemValue());
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName(smsSystemTwo.getSystemKey())
                .setTemplateCode(smsSystemTwo.getSystemValue())
                .setTemplateParam("{\"code\":"+"\"" + code +"\""+ "}");
        // 复制代码运行请自行打印 API 的返回值  //打印个鸡儿 还是得自己来
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        log.info(JSONUtil.toJsonStr(sendSmsResponse));

    }

    public void sendEmail(String email,String code) {
        SystemCode smsSystemOne = systemCodeService.getOne(new QueryWrapper<SystemCode>().eq("key_name", "email"));
        if(null == smsSystemOne) {
            return;
        }
        Properties props = new Properties();//手动配置
        props.put("mail.smtp.host", "smtp.126.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {  //邮箱账号 和密码
                        return new PasswordAuthentication(smsSystemOne.getSystemKey(), smsSystemOne.getSystemValue());
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addHeaderLine("method=REQUEST");
            message.addHeaderLine("charset=UTF-8");
            message.addHeaderLine("component=VEVENT");
            message.setFrom(new InternetAddress(smsSystemOne.getSystemKey()));
            message.addRecipients(Message.RecipientType.TO, email);//发送给谁
            BodyPart bodyPart = new MimeBodyPart();
            message.setSubject("验证码", "UTF-8");
            bodyPart.setContent("测试阶段验证码："+code, "text/html;charset=utf-8");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);   // html正文bodypart
            message.setContent(multipart);
            Transport.send(message);
            // 发送
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
