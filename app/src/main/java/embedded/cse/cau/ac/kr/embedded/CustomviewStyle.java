package embedded.cse.cau.ac.kr.embedded;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Inhee on 2017-11-18.
 */

public class CustomviewStyle extends View {
    private Paint paint;

    public CustomviewStyle(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.RED);
    }
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawRect(100,100,200,200,paint);
    }
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Toast.makeText(super.getContext(), "MotionEvent.ACTION_DOWN : "+event.getX() + ", "+event.getY(),Toast.LENGTH_LONG).show();
        }
        return super.onTouchEvent(event);
    }
}
