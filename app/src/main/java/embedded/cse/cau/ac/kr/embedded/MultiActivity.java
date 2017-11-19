package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

/**
 * Created by caucse on 2017-11-08.
 */

public class MultiActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_activity);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mutiLayout);//객체참조
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(       //객체생성
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        CustomViewImage img = new CustomViewImage(MultiActivity.this);

        mainLayout.addView(img, params); // 리니어레이아웃에 포함시킴

        LinearLayout mainLayout2 = (LinearLayout) findViewById(R.id.oppositeLayout);//객체참조
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(       //객체생성
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayout2.setOrientation(LinearLayout.VERTICAL);

        CustomViewImage img2 = new CustomViewImage(MultiActivity.this);

        mainLayout2.addView(img2, params2);
    }
}
