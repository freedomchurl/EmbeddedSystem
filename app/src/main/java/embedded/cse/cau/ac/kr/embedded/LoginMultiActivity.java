package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by churl on 2017-11-21.
 */

public class LoginMultiActivity extends Activity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longin_activity);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginMultiActivity.this,"여인희지각쟁이",Toast.LENGTH_SHORT).show();
                        }
                    });
                    Socket sock = new Socket("clee94.iptime.org", 7222);

                    OutputStream out = sock.getOutputStream();
                    InputStream in = sock.getInputStream();

                    DataInputStream dis = new DataInputStream(in);
                    //BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    DataOutputStream dos = new DataOutputStream(out);

                    String sending = "LeeCheol";

                    final String echo = dis.readUTF() + " dddd";

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginMultiActivity.this,echo,Toast.LENGTH_SHORT).show();
                        }
                    });

                    dos.writeUTF(sending);

                    sock.close();
                    dis.close();


                }catch (Exception e){}
            }
        }).start();
    }
}
