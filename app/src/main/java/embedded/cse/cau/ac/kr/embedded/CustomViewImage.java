package embedded.cse.cau.ac.kr.embedded;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Inhee on 2017-11-18.
 */

public class CustomViewImage extends View {

    private Bitmap cacheBitmap;
    private Canvas cacheCanvas;
    private Paint mPaint;

    public CustomViewImage(Context context) {
        super(context);
        mPaint = new Paint();
    }

    protected  void onSizeChanged(int w, int h, int oldw, int oldh){
        createCachebitmap(w,h);
        testdrawing(7); //6 or 7
    }

    private void createCachebitmap(int w, int h){
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);
    }

    private void testdrawing(int num){
        cacheCanvas.drawColor(Color.WHITE);

        //mPaint.setColor(Color.RED);
        //cacheCanvas.drawRect(100,100,200,200,mPaint);


        int [][]arr = new int [7][7];
        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                arr[i][j]= (int) ((Math.random()*100)%num+1); //6개냐 7개냐 여기서 바꿔줌
            }
        }

        Bitmap burger = BitmapFactory.decodeResource(getResources(),R.drawable.burger);
        //burger = Bitmap.createScaledBitmap(burger, 50, burger.getHeight()/(burger.getWidth()/50),true);
        burger = Bitmap.createScaledBitmap(burger, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);


        Bitmap champagne = BitmapFactory.decodeResource(getResources(),R.drawable.champagne);
        champagne = Bitmap.createScaledBitmap(champagne, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

        Bitmap pizza = BitmapFactory.decodeResource(getResources(),R.drawable.pizza);
        pizza = Bitmap.createScaledBitmap(pizza, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

        Bitmap cake = BitmapFactory.decodeResource(getResources(),R.drawable.birthday_cake);
        cake = Bitmap.createScaledBitmap(cake, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

        Bitmap spaghetti = BitmapFactory.decodeResource(getResources(),R.drawable.spaghetti);
        spaghetti = Bitmap.createScaledBitmap(spaghetti, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

        Bitmap beer = BitmapFactory.decodeResource(getResources(),R.drawable.beer);
        beer = Bitmap.createScaledBitmap(beer, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

        Bitmap chicken = BitmapFactory.decodeResource(getResources(),R.drawable.roast_chicken);
        chicken = Bitmap.createScaledBitmap(chicken, cacheCanvas.getWidth()/7, cacheCanvas.getHeight()/7,true);

        mPaint.setColor(Color.LTGRAY);
        //cacheCanvas.drawRect(0,0,480,800,mPaint);
        mPaint.setStrokeWidth(cacheCanvas.getWidth()/7-cacheCanvas.getWidth()/8);



        for (int j = 0; j < 8; j++) {
            cacheCanvas.drawLine(cacheCanvas.getWidth() / 7 + (cacheCanvas.getWidth() / 7 * (j - 1)), 0,
                    cacheCanvas.getWidth() / 7 + (cacheCanvas.getWidth() / 7 * (j - 1)), cacheCanvas.getHeight(), mPaint);
        }
        for (int j = 0; j < 8; j++) {
            cacheCanvas.drawLine(0,cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(j-1)),
                    cacheCanvas.getWidth(),cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(j-1)),mPaint);
        }



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
                    default:
                        cacheCanvas.drawBitmap(chicken, cacheCanvas.getWidth()/7 +(cacheCanvas.getWidth()/7*(j-1)),
                                cacheCanvas.getHeight()/7 +(cacheCanvas.getHeight()/7*(i-1)), mPaint);
                        break;
                }

            }
        }
        /*Matrix horInverseMatrix = new Matrix();
        horInverseMatrix.setScale(-1,1);    //좌우대칭
        Bitmap horInverseImg = Bitmap.createBitmap(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), horInverseMatrix, false);
        cacheCanvas.drawBitmap(horInverseImg, 30, 130, mPaint);

        Matrix verInverseMatrix = new Matrix();
        verInverseMatrix.setScale(1, -1);   //상하대칭
        Bitmap verInverseImg = Bitmap.createBitmap(srcImg, 0, 0, srcImg.getWidth(), srcImg.getHeight(), verInverseMatrix, false);
        cacheCanvas.drawBitmap(verInverseImg, 30 ,230, mPaint);*/
    }

    protected  void onDraw(Canvas canvas){
        if(cacheBitmap!=null){
            canvas.drawBitmap(cacheBitmap,0,0,null);
        }
    }
}
