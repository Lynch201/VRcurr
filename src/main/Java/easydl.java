


//import java.util.*;

import utils.Base64Util;
import utils.GsonUtils;

import java.io.*;
import java.util.HashMap;
import utils.HttpUtil;
import java.util.Map;

/**
 * easydl
 */
public class easydl {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String easydl() {
        // 请求url
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/currencyRecognition";
        String imgPath = "/Users/wangzhenning/IdeaProjects/testWebApp/res/1.jpeg";

        try {
            Map<String, Object> map = new HashMap<>();

            map.put("image", readImg2Base64(imgPath));
            map.put("top_num", "5");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.e84df0c713d1ab1cc35f03b0e9b07c00.2592000.1547778365.282335-15056054]";

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String readImg2Base64(String imgpath) throws Exception {
        FileInputStream ips = null;

        //获取图片存放路径
        ips = new FileInputStream(new File(imgpath));
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(ips);

        return Base64Util.encode(data);
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    public static void main(String[] args) {
        easydl.easydl();
    }
}