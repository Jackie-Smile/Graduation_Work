import java.lang.reflect.Array;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jackie.domain.News;

public class test {
    public static void main(String[] args) {
//        String url = "https://lab.isaaclin.cn/nCoV/api/news";
        String url = "https://interface.sina.cn/app.news/24hours_news.d.json?conf=page&page=1&pageType=kangYiNewsFlash";
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String info=json.toString();
        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(info).getAsJsonObject();
        JsonArray ja = jo.get("data").
                getAsJsonObject().get("components").
                getAsJsonArray().get(1).
                getAsJsonObject().get("data").getAsJsonArray();
        List newsList = new ArrayList<News>(20);
        System.out.println(newsList);
        for(int i = 0; i < ja.size(); i++) {
            News news = new News();
            news.setTime(ja.get(i).getAsJsonObject().get("title").getAsString());
            news.setTitle(ja.get(i).getAsJsonObject().
                    get("item").getAsJsonObject().
                    get("info").getAsJsonObject().get("interactionInfo").
                    getAsJsonObject().get("shareInfo").
                    getAsJsonObject().get("title").getAsString());
            news.setTitleUrl(ja.get(i).getAsJsonObject().
                    get("item").getAsJsonObject().
                    get("info").getAsJsonObject().get("interactionInfo").
                    getAsJsonObject().get("shareInfo").
                    getAsJsonObject().get("link").getAsString());
            news.setSource(ja.get(i).getAsJsonObject().
                    get("item").getAsJsonObject().
                    get("info").getAsJsonObject().get("mediaInfo").
                    getAsJsonObject().get("name").getAsString());
            news.setSourceUrl("");
            newsList.add(i,news);
//            System.out.println(newsList.get(i));
        }
//        System.out.println(ja);
        if(!jo.get("msg").getAsString().equals("success"))
        {
            System.out.println("获取新闻信息失败");
//            return null;
        }
        Gson gson = new Gson();
        System.out.println("获取新闻信息成功");
//        return gson.fromJson(jo.get("results").getAsJsonArray(), new TypeToken<List<News>>(){}.getType());

    }
}