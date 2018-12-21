package utils;


import com.google.gson.Gson;

public class JsonToObject {

    /**
     * json to javabean
     *
     * @param json
     */
    public static Object jsonToJavaBean(String json) {
        Gson gson = new Gson();
        Object object = gson.fromJson(json, Object.class);//对于javabean直接给出class实例
//        System.out.println(object.toString());
        return object;
    }

}
