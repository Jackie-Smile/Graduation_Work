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
            URL urlObject = new URL("http://111.231.75.86:8000/api/provinces/CHN/");
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
        System.out.println(element);
        element = element.getAsJsonArray();
        System.out.println(element);
        for(int i=0;i<element.getAsJsonArray().size();i++){
            ChinaProvince chinaProvince=new ChinaProvince();
            JsonObject current=element.getAsJsonArray().get(i).getAsJsonObject();
            System.out.println(current);
            String name=current.get("provinceName").getAsString();
            System.out.println(name);
            int confirm=current.get("currentConfirmedCount").getAsInt();
            System.out.println(confirm);
            chinaProvince.setName(name);
            chinaProvince.setValue(confirm);
            chinaProvinceList.add(chinaProvince);
            System.out.println(chinaProvince);
        }
//        String data = "data";
//        if (element.isJsonObject()) {
//            JsonObject object = element.getAsJsonObject();
//            data = object.get("data").getAsString();
//        }
//        element = parser.parse(data);
//        if (element.isJsonObject())
//        {
//            JsonObject object = element.getAsJsonObject();
//            JsonArray areaTree = object.get("areaTree").getAsJsonArray();
//            JsonObject china=areaTree.get(0).getAsJsonObject();
//            JsonArray children=china.get("children").getAsJsonArray();
//            for (int i = 0; i < children.size(); i++)
//            {
//                ChinaProvince chinaProvince=new ChinaProvince();
//                JsonObject current=children.get(i).getAsJsonObject();
//                System.out.println(current);
//                String name=current.get("provinceName").getAsString();
//                JsonObject total=current.get("total").getAsJsonObject();
//                int confirm=current.get("currentConfirmedCount").getAsInt();
//                chinaProvince.setName(name);
//                chinaProvince.setValue(confirm);
//                chinaProvinceList.add(chinaProvince);
//            }
//        }
        Collections.sort(chinaProvinceList);
//        return chinaProvinceList;
    }
}