package com.rini.cricketscores;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rini.cricketscores.Utils.CricData;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        new ScoresTask().execute();
    }

    class ScoresTask extends AsyncTask<String, Void, JSONArray> {

        protected JSONArray doInBackground(String... urls) {

            JSONObject matchObj = new JSONObject();
            JSONArray dataArray = new JSONArray();

            try
            {
                CricData cd = new CricData();
                String something = getIntent().getExtras().getString("uidForScore");
                android.util.Log.w("MMM",something);
                Integer uid = 1123499;
                matchObj = cd.getScores(uid);
                if(matchObj != null) {
                    dataArray = (JSONArray) matchObj.get("data");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return dataArray;
        }
        protected void onPostExecute(JSONArray data) {

            TextView scoresView = (TextView)findViewById(R.id.matchData);
            TextView matchTitle = (TextView)findViewById(R.id.matchTitle);
            TextView inningsReq = (TextView)findViewById(R.id.inningsReq);
            TextView scores = (TextView)findViewById(R.id.score);

            if(data != null)
            {
                JSONObject matchObj = null;
                int i = 0;
                try {

                    for (i = 0; i < data.length(); i++) {
                        matchObj = (JSONObject) data.get(i);
                        String team1 = (String) matchObj.get("team-1");
                        String team2 = (String) matchObj.get("team-2");
                        String innReq = (String) matchObj.get("innings-requirement");
                        String score = (String) matchObj.get("score");
                        String title = team1 + " vs. " + team2;
                        scoresView.setText("");
                        matchTitle.setText(title);
                        inningsReq.setText(innReq);
                        scores.setText(score);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    scoresView.setText("NOTHING HERE!");
                }
            }
            else
            {
                scoresView.setText("NOTHING HERE!");
            }
        }
    }
}