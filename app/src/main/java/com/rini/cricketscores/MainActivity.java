package com.rini.cricketscores;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rini.cricketscores.Utils.CricData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MatchesTask().execute();
    }

    Context context = this;

    class MatchesTask extends AsyncTask<String, Void, JSONArray> {

        protected JSONArray doInBackground(String... urls) {
            JSONArray matchesArray = null;

            try
            {
                CricData cd = new CricData();
                JSONObject cricObj = cd.getMatches();

                if(cricObj != null) {
                    matchesArray = (JSONArray) cricObj.get("matches");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return matchesArray;
        }

        protected void onPostExecute(JSONArray data) {

            TextView matchView = (TextView)findViewById(R.id.cricData);
            ListView matchesLv = (ListView)findViewById(R.id.matchesList);

            Integer uid = 0;
            if(data != null) {
                JSONObject matchObj = null;
                int i=0;
                //Integer uid = 0;
                String team1 = null;
                String team2 = null;
                String dateGmt = null;
                boolean matchStarted = false;
                String type = null;
                boolean squad = false;
                String winner_team = null;
                String toss_winner_team = null;
                ArrayList matchList = new ArrayList();
                String match = "";
                //SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm Z");

                try {
                    for (i = 0; i < data.length(); i++) {
                        matchObj = (JSONObject) data.get(i);
                        uid = (Integer) matchObj.get("unique_id");
                        team1 = (String) matchObj.get("team-1");
                        team2 = (String) matchObj.get("team-2");
                        dateGmt = (String) matchObj.get("dateTimeGMT");
                        matchStarted = (boolean) matchObj.get("matchStarted");
                        type = (String) matchObj.get("type");
                        squad = (boolean) matchObj.get("squad");
                        if(matchObj.has("winner-team")) {
                            winner_team = (String) matchObj.get("winner-team");
                        }
                        if(matchObj.has("toss_winner-team")) {
                            toss_winner_team = (String) matchObj.get("toss_winner-team");
                        }
                        Log.w("MATCHES DATA","DATA::: " + data);
                        match = team1 + " vs. " + team2 + "\n" + dateGmt;
                        matchList.add(match);
                    }
                    matchView.setText("");

                    matchesLv.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, matchList));

                    matchesLv.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.w("SSSS",view.toString());
                            Intent intent = new Intent(MainActivity.this, ScoresActivity.class);
                            String message = "aaa";
                            intent.putExtra("uidForScore", message);
                            startActivity(intent);
                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    matchView.setText("NOTHING HERE!");
                }
            }
            else {
                matchView.setText("NOTHING HERE!");
            }
        }
    }
}
