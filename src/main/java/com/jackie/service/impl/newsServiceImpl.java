package com.jackie.service.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.jackie.domain.ChinaDaily;
import com.jackie.domain.News;
import com.jackie.service.newsService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service("newsService")
public class newsServiceImpl implements newsService
{
    @Override
    public ChinaDaily getTodayData()
    {
        ChinaDaily chinaDaily = new ChinaDaily();
        StringBuilder json = new StringBuilder();
        try
        {
            URL urlObject = new URL("https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5");
            URLConnection uc = urlObject.openConnection();
            BufferedReader bin = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = bin.readLine()) != null) {
                json.append(inputLine);
            }
            bin.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(String.valueOf(json));
        String data = "data";
        if (element.isJsonObject()) {
            JsonObject object = element.getAsJsonObject();
            data = object.get("data").getAsString();
        }
        element = parser.parse(data);
        if (element.isJsonObject())
        {
            JsonObject object = element.getAsJsonObject();
            JsonObject chinaTotal = object.get("chinaTotal").getAsJsonObject();
            int confirm=chinaTotal.get("confirm").getAsInt();
            int nowConfirm=chinaTotal.get("nowConfirm").getAsInt();
            int death=chinaTotal.get("dead").getAsInt();
            chinaDaily.setNewCase(nowConfirm);
            chinaDaily.setDeath(death);
            chinaDaily.setComulative(confirm);
        }
        return chinaDaily;
    }

    @Override
    public List<News> getNews()
    {
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
        return newsList;
    }
}
