package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by caucse on 2017-11-08.
 */

public class MultiActivity extends Activity {

    public String myName;
    public boolean isLeader;
    public int roomnum = -1;

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

    public Button readyButton = null;

    public TextView message = null;
    public ImageView waitingIcon = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_activity);

        this.isLeader = getIntent().getBooleanExtra("ISLEADER",false);
        this.myName = getIntent().getStringExtra("MYNAME");

        if(isLeader == false)
        {
            this.roomnum = getIntent().getIntExtra("ROOMNUM",-1);
        }

        message = (TextView) findViewById(R.id.textMessage);
        waitingIcon = (ImageView) findViewById(R.id.waitingIcon);

        combo1 = (ImageView) findViewById(R.id.mcombo1);
        combo2 = (ImageView) findViewById(R.id.mcombo2);
        combo3 = (ImageView) findViewById(R.id.mcombo3);
        combo4 = (ImageView) findViewById(R.id.mcombo4);
        combo5 = (ImageView) findViewById(R.id.mcombo5);
        combo6 = (ImageView) findViewById(R.id.mcombo6);
        combo7 = (ImageView) findViewById(R.id.mcombo7);
        combo8 = (ImageView) findViewById(R.id.mcombo8);
        combo9 = (ImageView) findViewById(R.id.mcombo9);
        combo10 = (ImageView) findViewById(R.id.mcombo10);
        combo11 = (ImageView) findViewById(R.id.mcombo11);
        combo12 = (ImageView) findViewById(R.id.mcombo12);

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

        LinearLayout mainLayout2 = (LinearLayout) findViewById(R.id.oppositeLayout);//객체참조

        mainLayout2.removeAllViewsInLayout();

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(       //객체생성
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout2.setOrientation(LinearLayout.VERTICAL);

        CustomOppositeMultiViewImage img2 = new CustomOppositeMultiViewImage(MultiActivity.this,this);

        mainLayout2.addView(img2, params2);



        ClientThread myClient = new ClientThread(this,myName,isLeader,roomnum);
        myClient.start();
    }

    public void StartGame()
    {
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mutiLayout);//객체참조

        mainLayout.removeAllViewsInLayout();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(       //객체생성
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        CustomMultiViewImage img = new CustomMultiViewImage(MultiActivity.this,this);

        mainLayout.addView(img, params); // 리니어레이아웃에 포함시킴


    }


    public void AddScore(int num)
    {
        score = score + num + combo;

        //this.scoreText.setText(String.valueOf(score) + " 점");
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
}
