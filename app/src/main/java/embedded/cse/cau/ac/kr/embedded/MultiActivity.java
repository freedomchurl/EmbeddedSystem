package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
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
    CustomMultiViewImage img = null;
    CustomOppositeMultiViewImage img2 = null;
    ClientThread myClient = null;
    public TextView oppName = null;

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
        oppName = (TextView) findViewById(R.id.oppName);
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

        img2 = new CustomOppositeMultiViewImage(MultiActivity.this,this);

        mainLayout2.addView(img2, params2);

        myClient = new ClientThread(this,myName,isLeader,roomnum);
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

        img = new CustomMultiViewImage(MultiActivity.this,this);

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

    class CustomMultiViewImage extends View {

        String sendingData = "";

        private Bitmap cacheBitmap;
        private Canvas cacheCanvas;
        private Paint mPaint;
        public int [][] arr = null; // 현재 상태를 저장하기 위함이다.

        int addScore = 0;
        public Bitmap burger,pizza, champagne, spaghetti, beer, chicken,cake;

        private int widthSize = 0;
        private int heightSize = 0;

        private int first_selectedRow, first_selectedCol;
        private int second_selectedRow, second_selectedCol;
        private boolean isSelected = false;

        public long t = 0;

        private boolean isFirst = true;

        public boolean[][] checkArr = null;

        private MultiActivity singleActivityCt = null;


        public CustomMultiViewImage(Context context, Activity Single) {
            super(context);

            this.singleActivityCt = (MultiActivity) Single;
            mPaint = new Paint();

            // Combo 부분 수정해야한다.
        }

        protected  void onSizeChanged(int w, int h, int oldw, int oldh){
            createCachebitmap(w,h);
            InitialDraw(6); //6 or 7
            UpdateDraw();
        }

        private void createCachebitmap(int w, int h){
            cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            cacheCanvas = new Canvas();
            cacheCanvas.setBitmap(cacheBitmap);
        }

        private void UpdateDraw()
        {
            cacheCanvas.drawColor(Color.WHITE);
            sendingData = "";
            for(int i=0;i<7;i++) {
                for (int j = 0; j < 7; j++) {

                    sendingData = sendingData + String.valueOf(arr[i][j]);

                    switch (arr[i][j]){
                        case 1:
                            cacheCanvas.drawBitmap(burger,cacheCanvas.getWidth()/7 +(cacheCanvas.getWidth()/7*(j-1)),
                                    cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(i-1)), mPaint);
                            break;
                        case 2:
                            cacheCanvas.drawBitmap(champagne, cacheCanvas.getWidth()/7 +(cacheCanvas.getWidth()/7*(j-1)),
                                    cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(i-1)), mPaint);
                            break;
                        case 3:
                            cacheCanvas.drawBitmap(pizza, cacheCanvas.getWidth()/7 +(cacheCanvas.getWidth()/7*(j-1)),
                                    cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(i-1)), mPaint);
                            break;
                        case 4:
                            cacheCanvas.drawBitmap(cake, cacheCanvas.getWidth()/7 +(cacheCanvas.getWidth()/7*(j-1)),
                                    cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(i-1)), mPaint);
                            break;
                        case 5:
                            cacheCanvas.drawBitmap(spaghetti, cacheCanvas.getWidth()/7 +(cacheCanvas.getWidth()/7*(j-1)),
                                    cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(i-1)), mPaint);
                            break;
                        case 6:
                            cacheCanvas.drawBitmap(beer, cacheCanvas.getWidth()/7 +(cacheCanvas.getWidth()/7*(j-1)),
                                    cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(i-1)), mPaint);
                            break;
                        case -1: // -1일경우 터지는 것으로 둔다.
                            break;
                        default:
                            cacheCanvas.drawBitmap(chicken, cacheCanvas.getWidth()/7 +(cacheCanvas.getWidth()/7*(j-1)),
                                    cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(i-1)), mPaint);
                            break;
                    }

                }
            }
            int lefttime = 1;
            String finalData = "GAMEDATA///" + String.valueOf(lefttime) + "///" + sendingData + "///" + score;
            Log.d("GAMEDATA!!!",finalData);
            try {
                myClient.dos.writeUTF(finalData);
            }catch (Exception e){}

            mPaint.setColor(Color.LTGRAY);
            //cacheCanvas.drawRect(0,0,480,800,mPaint);
            mPaint.setStrokeWidth(cacheCanvas.getWidth()/8-cacheCanvas.getWidth()/9);

            for (int j = 0; j < 8; j++) {
                cacheCanvas.drawLine(cacheCanvas.getWidth() / 7 + (cacheCanvas.getWidth() / 7 * (j - 1)), 0,
                        cacheCanvas.getWidth() / 7 + (cacheCanvas.getWidth() / 7 * (j - 1)), cacheCanvas.getHeight(), mPaint);
            }
            for (int j = 0; j < 8; j++) {
                cacheCanvas.drawLine(0,cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(j-1)),
                        cacheCanvas.getWidth(),cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(j-1)),mPaint);
            }

        }

        private void InitialDraw(int num){
            cacheCanvas.drawColor(Color.WHITE);


            this.widthSize = cacheCanvas.getWidth() / 7;
            this.heightSize = cacheCanvas.getHeight() / 7;

            // 간격을 저장하고.

            checkArr = new boolean[7][7];

            arr = new int [7][7];
            for(int i=0;i<7;i++){
                for(int j=0;j<7;j++){
                    arr[i][j]= (int) ((Math.random()*100)%num+1); //6개냐 7개냐 여기서 바꿔줌
                    checkArr[i][j] = false;
                }
            }

            burger = BitmapFactory.decodeResource(getResources(),R.drawable.a);
            //burger = Bitmap.createScaledBitmap(burger, 50, burger.getHeight()/(burger.getWidth()/50),true);
            burger = Bitmap.createScaledBitmap(burger, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);


            champagne = BitmapFactory.decodeResource(getResources(),R.drawable.b);
            champagne = Bitmap.createScaledBitmap(champagne, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

            pizza = BitmapFactory.decodeResource(getResources(),R.drawable.c);
            pizza = Bitmap.createScaledBitmap(pizza, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

            cake = BitmapFactory.decodeResource(getResources(),R.drawable.d);
            cake = Bitmap.createScaledBitmap(cake, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

            spaghetti = BitmapFactory.decodeResource(getResources(),R.drawable.e);
            spaghetti = Bitmap.createScaledBitmap(spaghetti, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

            beer = BitmapFactory.decodeResource(getResources(),R.drawable.f);
            beer = Bitmap.createScaledBitmap(beer, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

            chicken = BitmapFactory.decodeResource(getResources(),R.drawable.g);
            chicken = Bitmap.createScaledBitmap(chicken, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

        }

        protected  void onDraw(Canvas canvas){
            if(cacheBitmap!=null){

                canvas.drawBitmap(cacheBitmap,0,0,null);
                Log.d("CANVASE _REFREST","Ddd");

                if(isFirst)
                {
                    UpDateAll();

                }
            }
        }



        // 완성된 블록들을 확인하는 과정
        // checkArr가 true면 터져야할 아이들
        private boolean CheckPang()
        {
            InitPang(); // checkArr를 초기화 한다.

            int totalCount = 0;
            int currentType = -9999; // -9999로 초기화 한다.
            int beginRow=0,beginCol=0, endRow=0, endCol=0;
            int count = 1;
            // 자기자신이 있으므로 1부터 검사해야한다.
            // 가로 먼저 검사한다.
            for(int i=0;i<7;i++)
            {
                currentType = -9999;
                count = 1;

                beginRow = i;
                endRow = i;

                for(int j=0;j<7;j++)
                {
                    if(currentType == -9999) // 일단 지금 시작해야 하는 경우
                    {
                        currentType = arr[i][j]; // 일단 현재 포지션을 넣는다.
                        beginCol = j; // Col 값을 넣는다.
                        //beginRow = i; // Row 값도 넣어준다.
                        count = 1;
                    }
                    else // 비교를 들어가야하는 경우
                    {
                        if(currentType == arr[i][j]) // 같은 경우
                        {
                            endCol = j;
                            endRow = i;
                            // 거기까지의 값을 넣는다.
                            count++;

                            if(j==6 && count >=3) // 끝일경우
                            {
                                // 여기서 설정해주어야한다.
                                for(int l=beginCol;l<=endCol;l++)
                                {
                                    checkArr[beginRow][l] = true;
                                }
                                totalCount++;
                            }
                        }
                        else // 다른 경우
                        {
                            if((count>=3) && (beginRow==endRow)) // 3이넘고 같은 Row일경우
                            {
                                // 여기서 설정해주어야한다.
                                for(int l=beginCol;l<=endCol;l++)
                                {
                                    checkArr[beginRow][l] = true;
                                }
                                totalCount++;
                            }
                            currentType = arr[i][j];
                            beginCol = j;
                            count = 1;
                        }
                    }
                }
            }


            for(int i=0;i<7;i++)
            {
                currentType = -9999;
                count = 1;

                beginCol = i;
                endCol = i;

                for(int j=0;j<7;j++)
                {
                    if(currentType == -9999) // 일단 지금 시작해야 하는 경우
                    {
                        currentType = arr[j][i]; // 일단 현재 포지션을 넣는다.
                        //beginCol = i; // Col 값을 넣는다.
                        beginRow = j; // Row 값도 넣어준다.
                        count = 1;
                    }
                    else // 비교를 들어가야하는 경우
                    {
                        if(currentType == arr[j][i]) // 같은 경우
                        {
                            endCol = i;
                            endRow = j;
                            // 거기까지의 값을 넣는다.
                            count++;

                            if(j==6 && count>=3)
                            {
                                for(int l=beginRow;l<=endRow;l++)
                                {
                                    checkArr[l][beginCol] = true;
                                }
                                totalCount++;
                            }
                        }
                        else // 다른 경우
                        {
                            if((count>=3) && (beginCol==endCol)) // 3이넘고 같은 Row일경우
                            {
                                // 여기서 설정해주어야한다.
                                for(int l=beginRow;l<=endRow;l++)
                                {
                                    checkArr[l][beginCol] = true;
                                }
                                totalCount++;
                            }
                            currentType = arr[j][i];
                            beginRow = j;
                            count = 1;
                        }
                    }
                }
            }

            for(int i=0;i<7;i++)
            {
                for(int j=0;j<7;j++)
                {
                    if(checkArr[i][j]== true)
                    {
                        arr[i][j]= -1;

                    }
                }
            }


            if(totalCount!=0)
                return true;
            return false;

        }

        private void InitPang()
        {
            for(int i=0;i<7;i++)
            {
                for(int j = 0;j<7;j++)
                {
                    this.checkArr[i][j] = false;
                }
            }
        }

        private void MoveToDown()
        {
            for(int i=0;i<7;i++)
            {
                for(int j=6;j>=1;j--)
                {
                    if(arr[j][i]==-1) // 빈 공간이면
                    {
                        // 맨 처음 빈공간이 아닌곳까지 올라가서 Swap.
                        int current = j; // 현재 여기!

                        while(arr[current][i]==-1 && current>0)
                        {
                            current--;
                        }


                        int temp = arr[current][i];
                        arr[current][i] = -1;
                        arr[j][i] = temp;
                        Log.d("Where",current + " " + i + " " + j + "Temp : " + temp);
                    }
                }
            }
        }

        private void FillUpdate()
        {
            addScore = 0;
            for(int i=0;i<7;i++)
            {
                for(int j=0;j<7;j++)
                {
                    if(arr[i][j]==-1)
                    {
                        addScore++;
                        arr[i][j] = (int)(Math.random()*6)+1;
                    }
                }
            }


        }

        public void InitClear()
        {
            while(CheckPang())
            {
                UpDateAll();
            }
        }

        private void UpDateAll()
        {

            final android.os.Handler mHandler = new android.os.Handler();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("얼마나","찍히나");
                    if(CheckPang()) {
                        UpdateDraw();
                        postInvalidate();

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MoveToDown();
                                FillUpdate();
                                UpdateDraw();
                                postInvalidate();

                                if(isFirst==false){
                                    singleActivityCt.AddScore(addScore);
                                    if(t==0)
                                    {
                                        t = System.currentTimeMillis();
                                    }
                                    else
                                    {
                                        long current = System.currentTimeMillis();
                                        long term = current - t;
                                        t = current;

                                        Log.d("시간간격",String.valueOf(term));

                                        if(term<4000)
                                        {
                                            singleActivityCt.AddCombo(1);
                                        }
                                        else
                                        {
                                            singleActivityCt.AddCombo(-(int)term/3000);
                                        }

                                    }
                                }
                            }
                        }, 600);



                        postDelayed(this,900);
                    }
                    else
                    {

                    /*
                    여기에서 플레이 진행 가능 여부 체크하는 것도 좋을 것 같다.
                     */
                    }

                }
            },600);


        }

        @Override
        public boolean onTouchEvent(MotionEvent event){

            int x = (int) event.getX();
            int y = (int) event.getY();

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    int col = (int) x / widthSize;
                    int row = (int) y / heightSize;
                    Log.d("CANVAS2",row + " " + col + " " + "ACTION_DOWN");

                    if(isSelected == false) // 아직 아무것도 선택이 안되어있는 상태라면
                    {
                        this.first_selectedCol = col;
                        this.first_selectedRow = row;
                        // 선택되는 것들을 저장한다.
                        this.isSelected = true;
                    }
                    else if(isSelected == true) // 하나 눌린상태라면
                    {
                    /*
                    1. 상하좌우가 아닐경우 예외처리
                     */
                        this.isSelected = false;
                        this.second_selectedCol = col;
                        this.second_selectedRow = row;
                        int Val = (int)Math.abs(second_selectedCol-first_selectedCol) + (int) Math.abs(second_selectedRow-first_selectedRow);
                        // 상하좌우라면, 2개의 좌표의 row의 차 + col의 차가 무조건 1이어야 한다.
                        if(Val==1)
                        {
                            if(isFirst==true)
                            {
                                isFirst= false;
                            }
                            int temp = arr[this.first_selectedRow][this.first_selectedCol];

                            arr[this.first_selectedRow][this.first_selectedCol] = arr[second_selectedRow][second_selectedCol];

                            arr[second_selectedRow][second_selectedCol] = temp;

                            if(CheckPang()) {
                                UpDateAll();
                            }
                            else
                            {
                                temp = arr[second_selectedRow][second_selectedCol];
                                arr[second_selectedRow][second_selectedCol] = arr[first_selectedRow][first_selectedCol];
                                arr[first_selectedRow][first_selectedCol] = temp;
                            }

                        }


                        // 다시 초기화 한다.
                    }

                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("CANVAS",x + " " + y + " " + "ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d("CANVAS",x + " " + y + " " + "ACTION_UP");
                    this.postInvalidate();
                    break;
            }

            return true;
        }
    }
}
