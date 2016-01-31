package com.usc.xiweiliu.myebaysearch;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiweiliu on 10/28/15.
 */
public class DataService {

    public List<Item> items;
    private JSONObject response;
    private String SERVER_URL = "http://android-ebaysearch-env.elasticbeanstalk.com/index.php?";
    private String PAGE_INFO = "perPage=5&curPageNum=1";

    DataService() {
        items = new ArrayList<Item>();
    }

    /*
     * Get items through backend PHP and eBay API
     */
    public boolean searchItems(SearchInput input) {
        // building REST url
        StringBuilder request = new StringBuilder();
        request.append(SERVER_URL)
                .append("keywords=" + input.getKeyword() + "&")
                .append("pricefrom=" + input.getPriceFrom() + "&")
                .append("priceto=" + input.getPriceTo() + "&")
                .append("sort=" + input.getSortBy() + "&")
                .append(PAGE_INFO);

        // send url to backend PHP in AWS
        // then backend PHP will communicate with eBay API, and return a JSON file
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(request.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder jsonSb = new StringBuilder();
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                jsonSb.append((temp)).append("\n");
            }

            response = new JSONObject(jsonSb.toString());
            if(!response.getString("ack").equals("Success")) {
                return false;
            }
        } catch (MalformedURLException e) {
            Log.e("Error", e.getMessage());
            return false;
        } catch (IOException e) {
            Log.e("Error", e.getMessage());
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
                ;
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /*
     * Parse the JSON response returned from backend php in AWS
     */
    public void parseResponse() {
        try {
            items.clear();

            int count = Integer.valueOf(response.getString("resultCount"));
//            Log.e("response count", count + "");
            for(int i = 0; i < 5 && i < count; i++) {
//                Log.e("response", "add item" + i);
                Item item = new Item(response.getJSONObject("item" + i));
                items.add(item);
            }
        } catch (JSONException e) {
            Log.e("Error", e.getMessage());
        }

    }

}
