import java.net.*;
import java.io.*;
import com.google.gson.Gson;

public class test {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://interface.sina.cn/app.news/24hours_news.d.json?conf=page&page=1&pageType=kangYiNewsFlash");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            Gson gson = new Gson();
            Object json = gson.fromJson(sb.toString(), Object.class);
            System.out.println(json.getClass());
            System.out.println(gson.toJson(json));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}