import java.lang.reflect.Array;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.jackie.domain.ChinaProvince;
import com.jackie.domain.News;

public class test {
    public static void main(String[] args) {

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
//        return chinaProvinceList;
    }
}