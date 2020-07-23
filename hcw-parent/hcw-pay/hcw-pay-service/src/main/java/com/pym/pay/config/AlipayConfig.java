package com.pym.pay.config;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Component
public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102700769294";
	
	// 商户私钥，您的PKCS8格式RSA2私钥";
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxS+Ly08r7y4fRJLwJaAI8mHfKVs+M906SgsY259J07df38TkTeZdY2/GxifZkecD8ZEBi/AT8DkkgoER9Mqv0cz3XmQsIVQ3dJfo2agFwYFjPU7Cs2enhSBsirdtBLGWzq6iBTvXnsOTT38nd0RBcHrC8AG0U5CpCL6UkylCSN8ezzgREpzaBS7++Cpiw2AviqBwuZN7ub0fiRkK9wRBO8KFf2XgW97TIlnu3U6b3g+XWTSyrrA2+9oSIDaMHAbBj061yjcZreSjERqRgyi5cj9oJZn07Ko4pIr8U7g1sZTB45+KwCa8YlOPso7M2jyh43KC2R53p1taAQZOAeTIbAgMBAAECggEAaRkD+lAEFojLBPZSpPzLvqHe+MnHFNgQ8pak6y02u2u2R1ruK5Y7WkhfGJAgx+TIupSu+j5MsiHBxZ5bmx+rgSfDNAeb25+XrU1y6kiie7wRSS1iIMS4eHvm4gLPYu3vh7ctFyH4cRSSQOp63oust/7VY0yZXPX3+u4FZi6r3lBHgOnfyVE/mdDOgf7gsCi11ksAUzWeVqvysGZYR/syanqut7uS3+sY1yEpeBxk9AG+zB2Ys3vIQLDw4+pAIcdZKrBGN+q/ClRtyIEFdv5CwZcD9buWpvo6Bhur1wYW78h0TW6PCCQ8njHUiIZ6iZc9u/guwKKgvUVap0MwCwAM4QKBgQDiH5kP1KDjMRv0B0tlPBGlXtACfiu+vI7RcFMr7FvCTdZiDhahr/a1hPzxSDyWIXsw79v00X1QE+R8427IzdbQNsqv39k3jO70NfLwYEL+bBiG/fnCghDW0GDByaxMNJ+Ol+8aNweGQPqXRLBOcPh9rE/qM7LO9O6LnltY3FEtiwKBgQDIuMN4btTuIsZkiPJOORIJPS2cFsII2+0yseos7IkC3yOVYjTz1b4Z1qdUMWmFbhrFI00l16LEGLoZzGNO8Dr+hwbPWixYKxutSpZFikyKlJoN/LMtKvM2izrKZdVPSMOYeDYEcK8ZWN/3dpFulwO8grGcKn7RAAexSZ712dq/sQKBgQC3yp8KMvUiNIRX8KXjftOI6mt/2zWLLo+WxAmWJeLfJ40c3f6fJPsNxy4gn2o/TK9os0zAzJViRQjuSZ1+MXBshFxyXyua7z452K/BmTxat1moPz6GTlmJgI1kdZtJxWv65ps9X4UKJj6IPRhgaYpP+4JpBeRMOuC5MtlkfI6uyQKBgDdB9j4fdOsiE6/1q48xUu/MMdQ4x64crY45QJihfY6W03r6kE1YWH9QgrxA5iXfi+sdrs1uMdb+X61egTBoHjWYAp3u2ypio7nvQLNgQXYdNtQG72TN4Crx5Xt20WSESaSd43ERfILj3rSHpb3mQRcSe8bcRBFRisyZ0fMoyHnhAoGAdBDqvnk++NG0SZcXohhXdHRm/RBKMSvU3TYNDTFRynl/T/19zaXNM9fOwD2wEVLAm5ETb3qJHpYJoMkpeeCQMJxMJk/nFCpjPZhLBB76b5/jKq+XEFHUIhoxSmSWE/n8Vxi0UbKliXlteCoSRs2XtghmkfWBbS+bNPR6kOTw8as=";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmM0rSvDLK3A7mCBmtB/4AB6XEcH1NLmUV1+vLeQROxlcgp1b1N9l6SsaY535yyTAXcGXv0M+2zRumn9I9cNoPBJxbpQ6mBBIm6b+XnNH0C3+zpXGlurslPYMluzG1FQMo6JtLQtRsPF1qQgPkyEQ6Y9WIT1ao9iZslMAHmSLvEVaWiodih+/54KYlGGX35AlUEHXEAyu8rgK8TS1qneW/7r0r8rEnhiyIDeIX+obh7vuiv/s7ieMXyPx+kHONcajyp0SdDuEvFm8qZx8kU+jj36+jMxULqPCLW9yjlAOs0FrqXIgI9WWTEX7br+FpK4xOUsKS6JWuiRnI/ILbekZMwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://b0fd82168951.ngrok.io/alipay/notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://b0fd82168951.ngrok.io/alipay/return";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

