package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by churl on 2017-11-22.
 */

public class ClientThread extends Thread{

    Activity parents = null;
    OutputStream out = null;
    InputStream in = null;
    Socket sock = null;

    DataOutputStream dos = null;
    DataInputStream dis = null;

    String name;

    ClientThread(Activity runningActivity,String name) {
        this.parents = runningActivity;
        this.name = name;

    }


    public void run()
    {
        try{

            sock = new Socket("10.210.60.231", 7676);

            out = sock.getOutputStream();
            in = sock.getInputStream();

            dis = new DataInputStream(in);
            //BufferedReader br = new BufferedReader(new InputStreamReader(in));
            dos = new DataOutputStream(out);

            String message = "CONNECTED/" + name; // 이름을 보낸다.

            dos.writeUTF(message);

            String echo = dis.readUTF();

            if(echo.equals("CONNECTED_SUCCESS")) {
                parents.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(parents, "서버에 접속했습니다", Toast.LENGTH_SHORT).show();
                    }
                });
            }


            dos.writeUTF("REQUEST_ROOM"); // 방 정보를 요청한다.




            sock.close();
            dis.close();
            dos.close();
        }catch (Exception e){}

    }
}
