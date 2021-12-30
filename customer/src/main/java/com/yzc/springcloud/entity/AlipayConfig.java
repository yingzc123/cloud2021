package com.yzc.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String appId ="2021000118613606";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCc6Hiuf/7IiepFF7rzrKccfWFZ7M+vfY+jHMhuBUokHQFl5OuBOCQhFWdRKBlMM63nu0mQV3514jOAPe9BSrqloV3zPzzN7spQyyLZBMEgenJfqu4TutO5WPiBxvt2F5nSR7+XGjzI3VH3F7LtcZiekIFeeCnw5Tp5W3TE8FHuN0mmc9vXlT0VpHkO/ILQKkhKCSmRwPtYPcsoZMLWUJrmNVwhzHaKD13C/VAUIQWkknUctULqa/9YR7Q6e60gHtQyldWmb4uhKd+ET0xtTtbCFIauqlSwQKoepT1JBXwbbUnBlGlNX2qvPcQzNokv24ueL5is9RXX2BkE+bMxSW5lAgMBAAECggEAL7dai+aMuruGLMDMr7G/mCBg7JHhjsKmXboxEZQ2OclAVZZKMB+8jPsPUlTZbJ0Cs8f21Rhmcr75XnR3P+nT3O63vXNrsEx/5hYAYPLbMF9MTN+USxezr1fpKr4yVnVS5+m9z9vAxGzZskTYuvCiswXK8cmlJ4buQ12WoxVaNRDNItiftYyqT/08+edX0AOrVCl72OAjfI/CHBImzSy1VHZ8BE4Z7ipQQ01/oH+vvyVN6zGgD6d2UHXm1okxzTRp/ABBkfXh3lEo9lVnJD1PTVGCOQuVUKmcnaHQpN90xHcMY4WYZ4W13S4UMyexudPTqn5wciYNxDc7Nt9vAK2WgQKBgQDR/15UYc1qLY/83qz/3h1PsR6jyTRWX8iu+aYoIWRJRAEEaovRGnloUPuuSKEMXJzLT4jITgYGs5cAXtYg4FwYbaoqWXI15T4WAVlu+wFg5iuLyTS7QKpUZLKsgbSg7KMNXc/JiCPEbcevWWJ2jGYyiqQ6WXr3lf3miwL5lY/f0QKBgQC/R9rLfBhwydK03ybs0mhIDvNpQ1JZbuXNmW5wyhkQcXkbvtRcN1jwkbX2mOzewicuBWOee7oUmE4P82I8ZlGHR4Znrp5IzzoFrxC3vQDIKVQ9ySbNw1mjjQKk1WSDenmPAEwCymu/s/ZBo2SROAdm0PsBN+Ev7a3qQr9xIre+VQKBgG84Ryd93SN9GiOddBbX9sI8VrP8XoHYnRs/Eadzd//tynQiUgbfdKmxKns18EXxzwFHEHS2Di8Cctk5YUNsUJqHinZ/hSPSVD2nXUyQGDzP6wRP/mOmbg/gfVksIRSSABGWO/TK69afJgFS3/lVhtMB5tUYiy4R/LVbrqmtctPhAoGAR7Gy/FoC/yNmaozJljI3C+suqOGm2AaM88byciwNwPAHKLMZ/YZxO5ohzc/72HKFJOa4NArD8Dw15hyfUWw4WrAgXHRCt5+Ua9T2OygIRv3WqWHfvXfe/UI5H3csOWuKbGL8Gn+sayoUvv1zEEsj2xGxQv+i08gOS6PBBbKxlBECgYBHAFoog81biS4JaXZNBzw73EKLGGyyX+wNEvTTCnGxFXoBO2E09s0hzY28WbI02oAUw423wYMbyGJ3YMMohUoahaOkIAovh4PpOvzKu0iM8og5Ojm3G4/IRRFEwuP7DD6laNEeD1o2Xow2pyk9BLhtYti6SIZQVSPldfo7zeDF1A==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipayPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnOh4rn/+yInqRRe686ynHH1hWezPr32PoxzIbgVKJB0BZeTrgTgkIRVnUSgZTDOt57tJkFd+deIzgD3vQUq6paFd8z88ze7KUMsi2QTBIHpyX6ruE7rTuVj4gcb7dheZ0ke/lxo8yN1R9xey7XGYnpCBXngp8OU6eVt0xPBR7jdJpnPb15U9FaR5DvyC0CpISgkpkcD7WD3LKGTC1lCa5jVcIcx2ig9dwv1QFCEFpJJ1HLVC6mv/WEe0OnutIB7UMpXVpm+LoSnfhE9MbU7WwhSGrqpUsECqHqU9SQV8G21JwZRpTV9qrz3EMzaJL9uLni+YrPUV19gZBPmzMUluZQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String notifyUrl;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String return_url = "http://localhost:9090/order/ali_pay_return";

    // 签名方式
    public static String signType = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
//    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
//    public static void logResult(String sWord) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
//            writer.write(sWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
