package com.rini.cricketscores;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import com.rini.cricketscores.Utils.CricData;

public class MatchCalActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        new MatchCalActivity.MatchCalTask().execute();
    }

    class MatchCalTask extends AsyncTask<String, Void, JSONObject> {

        protected JSONObject doInBackground(String... urls) {

            JSONObject matchObj = new JSONObject();
            try
            {
                CricData cd = new CricData();
                matchObj.put("s","s");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return matchObj;
        }
        protected void onPostExecute(JSONObject matchObj) {

            TextView matchCalView = (TextView)findViewById(R.id.matchCalData);

            if(matchObj != null)
            {
                try
                {
                    matchCalView.setText("Hello");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    matchCalView.setText("NOTHING HERE!");
                }
            }
            else {
                matchCalView.setText("NOTHING HERE!");
            }
        }
    }
}
