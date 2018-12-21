import utils.Base64Util;
import utils.GsonUtils;
import utils.HttpUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CurRecService {
    public static String getCurrencyRecognitionResult() {
        String imgPath = "/Users/wangzhenning/IdeaProjects/VRcurr/res/1.jpeg";
//        String imgPath = "../webapp/WEB-INF/1.jpeg";

        return getCurrencyRecognitionResult(readImg2Base64(imgPath));
    }

    public static String getCurrencyRecognitionResult(String imgBase64){
        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/currencyRecognition";

        // 注意这里仅为了简化编码使用了一个请求回来的token，这一个月不会过期 at 2018-12-19 14:21:44
//        String accessToken = "24.e84df0c713d1ab1cc35f03b0e9b07c00.2592000.1547778365.282335-15056054]";
        // 注意这里仅为了简化编码每次请求都获取了access_token
        String accessToken = AuthService.getAuth();

        try {
            Map<String, String> map = new LinkedHashMap<>();

            map.put("image", imgBase64);
            map.put("top_num", "5");

            String param = GsonUtils.toJson(map);


            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static String readImg2Base64(String imgpath) {
        FileInputStream ips = null;
        try {
            //获取图片存放路径
            ips = new FileInputStream(new File(imgpath));
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(ips);
            return Base64Util.encode(data);
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
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

}

