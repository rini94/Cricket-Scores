package com.rini.cricketscores.Utils;

/**
 * Created by Rini on 24-Mar-18.
 */

import android.util.Log;

import org.json.JSONObject;

import java.net.HttpURLConnection;

public class CricData {

    public JSONObject getMatches()
    {
        String urlStr = "http://cricapi.com/api/matches/";
        JSONObject matchesObj = null;
        ExternalFunctions extFn = new ExternalFunctions();
        try
        {
            HttpURLConnection con = extFn.getConnectionFromUrl(urlStr);
            con.connect();
            matchesObj = extFn.getObjectFromUrl(con);
        }
        catch(Exception e)
        {
            Log.w("MATCHES",e);
            e.printStackTrace();
        }
        return matchesObj;
    }
    public JSONObject getScores(Integer uid)
    {
        String url = "http://cricapi.com/api/cricketScore/";
        JSONObject scoresObj = null;
        ExternalFunctions extFn = new ExternalFunctions();
        try
        {
            HttpURLConnection con = extFn.getConnectionFromUrl(url);
            con.setRequestProperty("unique_id",uid.toString());
            con.connect();
            scoresObj = extFn.getObjectFromUrl(con);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return scoresObj; //data - team-1, team-2, innings-requirement, score
    }
    public JSONObject getOldMatchesList()
    {
        String url = "http://cricapi.com/api/cricket";
        JSONObject scoresObj = null;
        ExternalFunctions extFn = new ExternalFunctions();
        try {
            HttpURLConnection con = extFn.getConnectionFromUrl(url);
            con.connect();
            scoresObj = extFn.getObjectFromUrl(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoresObj;
    } // unique_id, title, description

    //http://cricapi.com/api/matchCalendar
    //data - unique_id(opt), date, name


}
