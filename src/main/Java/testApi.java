import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import utils.JsonReader;
import utils.JsonToObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/testApi")
public class testApi extends HttpServlet {
    public testApi(){
       super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("do Get");
//            AuthService.getAuth();
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("do Post");

        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

//        log("JSON ------------->" + sb.toString());
        //将json字符串转换为json对象       此处获取image的base64编码String
        String imgBase64 = new JsonParser().parse(sb.toString()).getAsJsonObject().getAsJsonObject("params").get("image").toString();
        log("image.substring(0, 30) ------------->" + imgBase64.substring(0, 30));


        try {
//            String imgPath = "/Users/wangzhenning/IdeaProjects/VRcurr/res/1.jpeg";

//            String classPath = this.getClass().getClassLoader().getResource("/").getPath();// /Users/wangzhenning/IdeaProjects/VRcurr/target/VRcurr/WEB-INF/classes/
//            String imgPath =classPath + "../1.jpeg";
//            log("classPath is -------------" + classPath);
//            log("imgPath is -------------" + imgPath);
            // 调用服务，从百度API获取返回值
            String result = CurRecService.getCurrencyRecognitionResult(imgBase64);
            JSONObject jsonObject = new JSONObject(result);

            resp.getWriter().write(result);
        } catch (IOException e){
            log("IOException!");
        }

    }

//    public static void main(String[] args) {
//        String jsonStr = "{   \"params\" : {     \"image\" : \"\\/9j\\/4AAQSkZJhYJ\\/B9+l\\/s6w\\/551y\\/wCrGB\\/kD+2cQf\\/Z\"   },   \"method\" : \"imageClassify\",   \"header\" : {     \"device\" : \"iPhone 6s Plus<iPhone8,2>\",     \"cipherType\" : \"0\",     \"platform\" : \"iOS 12.1\",     \"local\" : \"zh_CN\",     \"uuid\" : \"15440891069786007\",     \"ext\" : \"8\",     \"version\" : \"3.1.9\",     \"plugins\" : \"5\",     \"page\" : \"6\",     \"agent\" : \"iOS 12.1\"   } }";
////        ReqPO reqPO = gson.fromJson(jsonStr, ReqPO.class);
//        System.out.println(new JsonParser().parse(jsonStr).getAsJsonObject().getAsJsonObject("params").get("image"));
//    }

}
