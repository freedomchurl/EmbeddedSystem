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

    boolean isLeader = false;

    ClientThread(Activity runningActivity,String name, boolean isLeader) {
        this.parents = runningActivity;
        this.name = name;
        this.isLeader = isLeader;

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

            if(isLeader==true) {
                String message = "MAKEROOM///" + name; // 이름을 보낸다.

                dos.writeUTF(message);

                String echo = dis.readUTF();

                if (echo.equals("CONNECTED_SUCCESS")) {
                    parents.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(parents, "게임방을 개설하였습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }


            sock.close();
            dis.close();
            dos.close();
        }catch (Exception e){}

    }
}
