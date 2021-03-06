package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Inhee on 2017-11-18.
 */


/*
1. 최초 1회 띄우고나서 (딜레이) 샤샤샥 옮겨야한다.
    1) (딜레이) 내리고
2. 옮겼을때, 일단 옮기고 (즉시)
    1) 검사하고 맞으면 지우고 (딜레이) 내리고
    2) 검사하고 안맞으면 (딜레이) 다시 원래대로 옮긴다
 */


public class CustomOppositeMultiViewImage extends View {

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


    public CustomOppositeMultiViewImage(Context context, Activity Single) {
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

        for(int i=0;i<7;i++) {
            for (int j = 0; j < 7; j++) {


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
                arr[i][j]= (int) (-1); //6개냐 7개냐 여기서 바꿔줌
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

           // if(isFirst)
           // {
               // UpDateAll();

           // }
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


}
