package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

/*
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
*/
public class RankingActivity extends AppCompatActivity {

    Context mContext;

    RecyclerView recyclerView;
    RecyclerView.Adapter Adapter;
    RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.RankingActivity);

        mContext = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        // Item 리스트에 아이템 객체 넣기
        ArrayList items = new ArrayList<>();

        items.add(new Item(R.drawable.beer, "미키마우스"));
        items.add(new Item(R.drawable.burger, "인어공주"));
        items.add(new Item(R.drawable.birthday_cake, "디즈니공주"));
        items.add(new Item(R.drawable.pizza, "토이스토리"));
        items.add(new Item(R.drawable.roast_chicken, "니모를 찾아서"));

        // StaggeredGrid 레이아웃을 사용한다
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //layoutManager = new LinearLayoutManager(this);
        //layoutManager = new GridLayoutManager(this,3);

        // 지정된 레이아웃매니저를 RecyclerView에 Set 해주어야한다.
        recyclerView.setLayoutManager(layoutManager);

        Adapter = new MyAdpater(items, mContext);
        recyclerView.setAdapter(Adapter);

    }

    class MyAdpater extends RecyclerView.Adapter
    {
        private Context context;
        private ArrayList mItems;

        // Allows to remember the last item shown on screen
        private int lastPosition = -1;

        public MyAdpater(ArrayList items, Context mContext)
        {
            mItems = items;
            context = mContext;
        }

        // 필수로 Generate 되어야 하는 메소드 1 : 새로운 뷰 생성
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            // 새로운 뷰를 만든다
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent,false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        // 필수로 Generate 되어야 하는 메소드 2 : ListView의 getView 부분을 담당하는 메소드
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.imageView.setImageResource(mItems.get(position).image);
            holder.textView.setText(mItems.get(position).imagetitle);

            setAnimation(holder.imageView, position);
        }

        // // 필수로 Generate 되어야 하는 메소드 3
        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public class ViewHolder  extends RecyclerView.ViewHolder {

            public ImageView imageView;
            public TextView textView;

            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.image);
                textView = (TextView) view.findViewById(R.id.imagetitle);
            }
        }

        private void setAnimation(View viewToAnimate, int position)
        {
            // 새로 보여지는 뷰라면 애니메이션을 해줍니다
            if (position > lastPosition)
            {
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }

    }
}

