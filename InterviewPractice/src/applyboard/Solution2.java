package applyboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.*;

/**
 * Created by Dinesh on 10/22/2019.
 */
public class Solution2 {

    static String[] getMovieTitles(String substr) {
        try {
            return sendGetForTotal(substr).toArray(new String[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> sendGetForTotal(String substr) throws Exception {

        Integer pageNum = 1;
        boolean allPagesComplete = false;

        List<String> titles = new ArrayList<>();

        while (!allPagesComplete) {
            URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr +  "&page=" + pageNum);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            int status = con.getResponseCode();
            System.out.println("status: " + status);

            StringBuffer callContent = getCallOutput(con);
            System.out.println("output: " + callContent);

            JsonObject rootNode = getParsedResponseObject(callContent.toString());
            titles.addAll(parseTopResponse(rootNode));

            Integer currPage = Integer.parseInt(rootNode.get("page").getAsString());
            if (currPage == rootNode.get("total_pages").getAsInt()) {
                allPagesComplete = true;
            } else {
                pageNum = currPage + 1;
            }
        }

        System.out.println("Titles size: " + titles.size() + ", all titles: " + titles);

        Collections.sort(titles);

        return titles;
    }

    private static List<String> parseTopResponse(JsonObject rootNode) {
        JsonArray data = rootNode.get("data").getAsJsonArray();
        List <String> titles = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            titles.add(data.get(i).getAsJsonObject().get("Title").getAsString());
        }

        return titles;
    }

    private static JsonObject getParsedResponseObject(String responsse) {
        JsonParser parser = new JsonParser();
        JsonElement rootNode = parser.parse(responsse);

        return rootNode.getAsJsonObject();
    }

    private static StringBuffer getCallOutput(HttpURLConnection con) throws Exception {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        return content;
    }

    public  static void main (String args[]) throws Exception {
        sendGetForTotal("spiderman");
    }
}
