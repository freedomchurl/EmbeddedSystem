package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

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


    RecyclerView singleRank = null;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;


    ArrayList<RankingData> myRank = new ArrayList<RankingData>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ranking_activity);

        singleRank = (RecyclerView) findViewById(R.id.recycle_single);

        singleRank.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        singleRank.setLayoutManager(layoutManager);
        adapter = new MyAdpater(myRank,getApplicationContext());

        singleRank.setAdapter(adapter);

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

                adapter.notifyDataSetChanged();


            }catch (Exception e){}
        }

        @Override
        protected String doInBackground(Void... strings) {

            try {


                String url = "http://35.201.221.215/read_ranking.php";

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


    class MyAdpater extends RecyclerView.Adapter {
        private Context context;
        private ArrayList<RankingData> mItems;

        // Allows to remember the last item shown on screen
        private int lastPosition = -1;

        public MyAdpater(ArrayList<RankingData> items, Context mContext) {
            mItems = items;
            context = mContext;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ViewHolder)holder).imageView.setImageResource(R.drawable.crown);
            ((ViewHolder)holder).textView.setText(mItems.get(position).getName());
            ((ViewHolder)holder).rankView.setText(String.valueOf(mItems.get(position).getRank()) + "등");
            ((ViewHolder)holder).scoreView.setText(String.valueOf(mItems.get(position).getScore()) + "점");

            setAnimation(((ViewHolder)holder).imageView, position);

        }



        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView imageView;
            public TextView textView;
            public TextView rankView;
            public TextView scoreView;

            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.image);
                textView = (TextView) view.findViewById(R.id.name);
                rankView = (TextView) view.findViewById(R.id.rank);
                scoreView = (TextView) view.findViewById(R.id.score);

                // 이 부분에서, 드래그 삭제 기능을 넣어야한다.
            }

        }

        private void setAnimation(View viewToAnimate, int position) {
            // 새로 보여지는 뷰라면 애니메이션을 해줍니다
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }


    }
}
