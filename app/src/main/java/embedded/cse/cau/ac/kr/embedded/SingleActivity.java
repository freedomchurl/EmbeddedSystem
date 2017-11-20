package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by caucse on 2017-11-08.
 */

public class SingleActivity extends Activity {

    private String myName = null;
    private TextView nameText = null;
    private TextView scoreText = null;

    public int score = 0;
    public int combo = 0;

    public ImageView combo1 = null;
    public ImageView combo2 = null;
    public ImageView combo3 = null;
    public ImageView combo4 = null;
    public ImageView combo5 = null;
    public ImageView combo6 = null;
    public ImageView combo7 = null;
    public ImageView combo8 = null;
    public ImageView combo9 = null;
    public ImageView combo10 = null;
    public ImageView combo11 = null;
    public ImageView combo12 = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity);

        myName = (String) getIntent().getStringExtra("MYNAME");
        this.nameText = (TextView) findViewById(R.id.singleName);
        // 이름 TextView를 받아오고

        combo1 = (ImageView) findViewById(R.id.combo1);
        combo2 = (ImageView) findViewById(R.id.combo2);
        combo3 = (ImageView) findViewById(R.id.combo3);
        combo4 = (ImageView) findViewById(R.id.combo4);
        combo5 = (ImageView) findViewById(R.id.combo5);
        combo6 = (ImageView) findViewById(R.id.combo6);
        combo7 = (ImageView) findViewById(R.id.combo7);
        combo8 = (ImageView) findViewById(R.id.combo8);
        combo9 = (ImageView) findViewById(R.id.combo9);
        combo10 = (ImageView) findViewById(R.id.combo10);
        combo11 = (ImageView) findViewById(R.id.combo11);
        combo12 = (ImageView) findViewById(R.id.combo12);





        this.nameText.setText(myName);

        this.scoreText = (TextView) findViewById(R.id.singleScore);

        this.scoreText.setText("0 점");


        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);//객체참조
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(       //객체생성
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        CustomViewImage img = new CustomViewImage(SingleActivity.this, this);

        mainLayout.addView(img, params); // 리니어레이아웃에 포함시킴

        this.AddCombo(0);

    }

    public void onBackPressed()
    {
        Log.d("김난희바보","김유정바보");

        // 여기서 점수 업데이트 해야한다.

        WriteRank write = new WriteRank();

        write.execute(myName,String.valueOf(score));

        Toast.makeText(this,"점수가 등록되었습니다!",Toast.LENGTH_SHORT).show();

        super.onBackPressed();
    }

    public void AddScore(int num)
    {
        score = score + num + combo;

        this.scoreText.setText(String.valueOf(score) + " 점");
    }

    public void AddCombo(int modify)
    {
        combo1.setImageResource(android.R.color.transparent);
        combo2.setImageResource(android.R.color.transparent);
        combo3.setImageResource(android.R.color.transparent);
        combo4.setImageResource(android.R.color.transparent);
        combo5.setImageResource(android.R.color.transparent);
        combo6.setImageResource(android.R.color.transparent);
        combo7.setImageResource(android.R.color.transparent);
        combo8.setImageResource(android.R.color.transparent);
        combo9.setImageResource(android.R.color.transparent);
        combo10.setImageResource(android.R.color.transparent);
        combo11.setImageResource(android.R.color.transparent);
        combo12.setImageResource(android.R.color.transparent);

        combo+=modify; //

        if(combo <=0)
            combo = 0;
        if(combo >=12)
            combo = 12;

        if(combo>=1)
            combo1.setImageResource(R.color.combo1);
        if(combo>=2)
            combo2.setImageResource(R.color.combo2);
        if(combo>=3)
            combo3.setImageResource(R.color.combo3);
        if(combo>=4)
            combo4.setImageResource(R.color.combo4);
        if(combo>=5)
            combo5.setImageResource(R.color.combo5);
        if(combo>=6)
            combo6.setImageResource(R.color.combo6);
        if(combo>=7)
            combo7.setImageResource(R.color.combo7);
        if(combo>=8)
            combo8.setImageResource(R.color.combo8);
        if(combo>=9)
            combo9.setImageResource(R.color.combo9);
        if(combo>=10)
            combo10.setImageResource(R.color.combo10);
        if(combo>=11)
            combo11.setImageResource(R.color.combo11);
        if(combo>=12)
            combo12.setImageResource(R.color.combo12);
    }


    class WriteRank extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            try {

                String url = "http://35.201.221.215/write_ranking.php?name=" + strings[0] + "&score=" + strings[1];

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




