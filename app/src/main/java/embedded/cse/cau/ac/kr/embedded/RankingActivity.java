package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by caucse on 2017-11-08.
 */

// Ranking show View
public class RankingActivity extends Activity {

    ArrayList<RankingData> myRank = new ArrayList<RankingData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        GetRank getRank = new GetRank();
        getRank.execute();

    }



    class GetRank extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                JSONArray jsonArray = new JSONObject(result).getJSONArray("data");

                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jObject = jsonArray.getJSONObject(i);
                    // get i, object data

                    int score = jObject.getInt("score");
                    String name = jObject.getString("name");

                    Log.d("Score and name",score + " " + name);

                    myRank.add(new RankingData(score,name,i+1));
                }


            }catch (Exception e){}
        }

        @Override
        protected String doInBackground(Void... strings) {

            try {


                String url = "http://35.201.149.34/read_ranking.php";

                URL myUrl = new URL(url);

                HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
                // URL connection

                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                conn.setDefaultUseCaches(false);

                InputStream is = conn.getInputStream();

                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));

                String line;

                while((line = reader.readLine())!=null)
                {
                    builder.append(line + "\n");
                }

                String result = builder.toString();

                return result;
            }catch (Exception e){}

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
