package com.rini.cricketscores.Utils;

/**
 * Created by Rini on 24-Mar-18.
 */

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExternalFunctions {

    public HttpURLConnection getConnectionFromUrl(String urlString) throws Exception
    {
        String APIKey = "OxotZc86TJexD2YJFyaKTaUZ3oN2";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setReadTimeout(10000);
        con.setConnectTimeout(15000);
        con.setDoOutput(true);
        con.setRequestProperty("apikey",APIKey);
        return con;
    }

    public JSONObject getObjectFromUrl(HttpURLConnection con) throws Exception
    {
        JSONObject resultObj = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str = null;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        String resultStr = sb.toString();
        Log.w("MATCHES DATA","RESULTSTR::: " + resultStr);
        resultObj = new JSONObject(resultStr);
        return resultObj;
    }
}
