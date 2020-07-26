package indi.uhyils.outapi.tencent;

import com.alibaba.fastjson.JSON;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.*;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月26日 12时48分
 */
public class CloudDemo {
    private static String appId = "xxx";
    private static String secretId = "xxx";
    private static String secretKey = "xxx";

    public static void main(String[] args) {
        /*语音文件识别*/
//        Long aLong = asrCommitFile("D:\\share\\ideaSrc\\speechRecognition\\data\\data_thchs30\\data\\A2_0.wav");
//        asrGetFileResult(841329195L);

        /*一句话语音识别*/
        getOneVoiceAsr("D:\\share\\ideaSrc\\speechRecognition\\data\\data_thchs30\\data\\A2_0.wav");
    }

    private static void getOneVoiceAsr(String filePath) {
        //采用本地语音上传方式调用
        try {
            //重要，此处<Your SecretId><Your SecretKey>需要替换成客户自己的账号信息，获取方法：
            //https://cloud.tencent.com/document/product/441/6203
            //具体路径：点控制台右上角您的账号-->选：访问管理-->点左边菜单的：访问密钥-->API 密钥管理
            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("asr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            clientProfile.setSignMethod("TC3-HMAC-SHA256");
            AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"ProjectId\":0,\"SubServiceType\":2,\"EngSerViceType\":\"16k\",\"SourceType\":1,\"Url\":\"\",\"VoiceFormat\":\"wav\",\"UsrAudioKey\":\"session-123\"}";
            SentenceRecognitionRequest req = SentenceRecognitionRequest.fromJsonString(params, SentenceRecognitionRequest.class);

            File file = new File(filePath);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            req.setDataLen(file.length());
            inputFile.read(buffer);
            inputFile.close();
            String encodeData = Base64.getEncoder().encodeToString(buffer);
            req.setData(encodeData);

            SentenceRecognitionResponse resp = client.SentenceRecognition(req);

            System.out.println(SentenceRecognitionRequest.toJsonString(resp));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static void asrGetFileResult(Long taskId) {
        try {
            //重要，此处<Your SecretId><Your SecretKey>需要替换成客户自己的账号信息，获取方法：
            //请参考接口说明（https://cloud.tencent.com/document/product/1093/37139）中的使用步骤 1 进行获取。
            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("asr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"TaskId\":" + taskId + "}";
            DescribeTaskStatusRequest req = DescribeTaskStatusRequest.fromJsonString(params, DescribeTaskStatusRequest.class);

            DescribeTaskStatusResponse resp = client.DescribeTaskStatus(req);

            System.out.println(DescribeTaskStatusRequest.toJsonString(resp));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private static Long asrCommitFile(String filePath) {
        try {
            //重要，此处<Your SecretId><Your SecretKey>需要替换成客户自己的账号信息，获取方法：
            //请参考接口说明（https://cloud.tencent.com/document/product/1093/37139）中的使用步骤 1 进行获取。
            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("asr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            AsrClient client = new AsrClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"EngineModelType\":\"16k_0\",\"ChannelNum\":1,\"ResTextFormat\":0,\"SourceType\":1}";
            CreateRecTaskRequest req = CreateRecTaskRequest.fromJsonString(params, CreateRecTaskRequest.class);

            File file = new File(filePath);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            req.setDataLen(file.length());
            inputFile.read(buffer);
            inputFile.close();
            String encodeData = Base64.getEncoder().encodeToString(buffer);
            req.setData(encodeData);
            CreateRecTaskResponse resp = client.CreateRecTask(req);
            String s = CreateRecTaskRequest.toJsonString(resp);
            RasResponse rasResponse = JSON.parseObject(s, RasResponse.class);
            Long taskId = rasResponse.getData().getTaskId();
            return taskId;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    private static class RasResponse {
        private Data Data;

        private String RequestId;

        public CloudDemo.Data getData() {
            return Data;
        }

        public void setData(CloudDemo.Data data) {
            Data = data;
        }

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String requestId) {
            RequestId = requestId;
        }
    }

    private static class Data {
        private Long TaskId;

        public Long getTaskId() {
            return TaskId;
        }

        public void setTaskId(Long taskId) {
            TaskId = taskId;
        }
    }
}
