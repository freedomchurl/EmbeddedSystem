package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by caucse on 2017-11-08.
 */

public class GetNameActivity extends Activity {

    private Button start = null;
    private EditText name = null;
    private String myName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getname_activity);

        start = (Button) findViewById(R.id.start_button);
        name = (EditText) findViewById(R.id.name_text);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    myName = name.getText().toString();

                    if(myName.equals(""))
                    {
                        Log.d("Name","Name is empty!");
                        Toast.makeText(GetNameActivity.this,"Please input your Name!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent i = new Intent(GetNameActivity.this,IntroActivity.class);
                        i.putExtra("MYNAME",myName);
                        startActivity(i);
                        finish();
                    }
            }
        });

    }
}
