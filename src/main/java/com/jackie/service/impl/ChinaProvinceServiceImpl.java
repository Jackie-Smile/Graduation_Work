package com.jackie.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jackie.domain.ChinaProvince;
import com.jackie.service.ChinaProvinceService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("ChinaProvinceService")
public class ChinaProvinceServiceImpl implements ChinaProvinceService
{
    @Override
    public List<ChinaProvince> getnewconfirm()
    {
        {

            List<ChinaProvince> chinaProvinceList=new ArrayList<ChinaProvince>();
            StringBuilder json = new StringBuilder();
            try
            {
                URL urlObject = new URL("https://c.m.163.com/ug/api/wuhan/app/data/list-total");
                URLConnection uc = urlObject.openConnection();
                uc.setRequestProperty("User-Agent", "Mozilla/4.76");
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
//        System.out.println(element.getAsJsonObject().get("data"));
//        String data = "data";
//        if (element.isJsonObject()) {
//            JsonObject object = element.getAsJsonObject();
//            data = object.get("data").getAsString();
//        }
            element = element.getAsJsonObject().get("data");
            System.out.println(element);
            if (element.isJsonObject())
            {
                JsonObject object = element.getAsJsonObject();
                JsonArray areaTree = object.get("areaTree").getAsJsonArray();
                JsonObject china=areaTree.get(2).getAsJsonObject();
                JsonArray children=china.get("children").getAsJsonArray();
                for (int i = 0; i < children.size(); i++)
                {
                    ChinaProvince chinaProvince=new ChinaProvince();
                    JsonObject current=children.get(i).getAsJsonObject();
                    String name=current.get("name").getAsString();
                    JsonObject today=current.get("today").getAsJsonObject();
                    int confirm=today.get("confirm").getAsInt();
                    chinaProvince.setName(name);
                    chinaProvince.setValue(confirm);
                    chinaProvinceList.add(chinaProvince);
                }
            }
            return chinaProvinceList;
        }
    }

    @Override
    public List<ChinaProvince> getconfirm()
    {
        List<ChinaProvince> chinaProvinceList=new ArrayList<ChinaProvince>();
        StringBuilder json = new StringBuilder();
        try
        {
            URL urlObject = new URL("https://c.m.163.com/ug/api/wuhan/app/data/list-total");
            URLConnection uc = urlObject.openConnection();
            uc.setRequestProperty("User-Agent", "Mozilla/4.76");
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
//        System.out.println(element.getAsJsonObject().get("data"));
//        String data = "data";
//        if (element.isJsonObject()) {
//            JsonObject object = element.getAsJsonObject();
//            data = object.get("data").getAsString();
//        }
        element = element.getAsJsonObject().get("data");
        System.out.println(element);
        if (element.isJsonObject())
        {
            JsonObject object = element.getAsJsonObject();
            JsonArray areaTree = object.get("areaTree").getAsJsonArray();
            JsonObject china=areaTree.get(2).getAsJsonObject();
            JsonArray children=china.get("children").getAsJsonArray();
            for (int i = 0; i < children.size(); i++)
            {
                ChinaProvince chinaProvince=new ChinaProvince();
                JsonObject current=children.get(i).getAsJsonObject();
                String name=current.get("name").getAsString();
                JsonObject today=current.get("total").getAsJsonObject();
                int confirm=today.get("confirm").getAsInt();
                chinaProvince.setName(name);
                chinaProvince.setValue(confirm);
                chinaProvinceList.add(chinaProvince);
            }
        }
        Collections.sort(chinaProvinceList);
        return chinaProvinceList;
    }
}
