package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by caucse on 2017-11-07.
 */

public class IntroActivity extends Activity {

    private Button singleButton = null;
    private Button multiButton = null;
    private Button rankingButton =  null;

    private String myName = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);

        // Intro page. We need to choose, Single Mode or, Multi Mode

        singleButton = (Button) findViewById(R.id.single_play);
        multiButton = (Button) findViewById(R.id.multi_play);
        rankingButton = (Button) findViewById(R.id.ranking);

        myName = getIntent().getStringExtra("MYNAME"); // get String Data from tag "MYNAME"

        // Mapping Button by ID

        rankingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Log.d("Move","Go to Ranking Page");
                // Go to Ranking page
                Intent intent = new Intent(IntroActivity.this,RankingActivity.class);
                intent.putExtra("MYNAME",myName);
                startActivity(intent);
            }
        });

        singleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("Move","Go to Single Page");
                // Go to Ranking page
                Intent intent = new Intent(IntroActivity.this,SingleActivity.class);
                intent.putExtra("MYNAME",myName);
                startActivity(intent);
            }
        });

        multiButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("Move","Go to Multi Page");
                // Go to Ranking page
                Intent intent = new Intent(IntroActivity.this,MultiActivity.class);
                intent.putExtra("MYNAME",myName);
                startActivity(intent);
            }
        });



    }
}
