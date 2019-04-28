package com.mytaxi.android_demo.helper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class JSONReader {
    // helper from stack over flow
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    // helper from stack over flow
    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    // my own helper
    public static String readJSONElementFromURL(String sURL , String JSONtag) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl(sURL);
        JSONArray resultsArr = json.getJSONArray("results");
        JSONObject arr = resultsArr.getJSONObject(0).getJSONObject("login");
        return arr.getString(JSONtag);
    }
}