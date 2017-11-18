package embedded.cse.cau.ac.kr.embedded;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Inhee on 2017-11-18.
 */

public class BitmapStyle extends View {
    private Bitmap cacheBitmap;
    private Canvas cacheCanvas;
    private Paint mPaint;

    public BitmapStyle(Context context){
        super(context);
        mPaint = new Paint();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        createCacheBitmap(w, h);
        testDrawing();
    }

    private void createCacheBitmap(int w, int h){
        cacheBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);
    }

    private void testDrawing(){
        cacheCanvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.RED);
        cacheCanvas.drawRect(100,100,200,200,mPaint);
    }

    protected void onDraw(Canvas canvas){
        if(cacheBitmap != null){
            canvas.drawBitmap(cacheBitmap, 0, 0, null);
        }
    }
}
