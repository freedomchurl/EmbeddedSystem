package embedded.cse.cau.ac.kr.embedded;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * Created by caucse on 2017-11-08.
 */

public class SingleActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_activity);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);//객체참조
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(       //객체생성
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        CustomViewImage img = new CustomViewImage(SingleActivity.this);

        mainLayout.addView(img, params); // 리니어레이아웃에 포함시킴


    }


    }




