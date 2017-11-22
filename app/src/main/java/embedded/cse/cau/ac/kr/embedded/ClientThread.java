package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.util.Log;
import android.view.View;
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
    String oppoName;

    boolean isLeader = false;

    int roomnum = -1;

    boolean Running = true;
    boolean isPlaying = false;
    ClientThread(Activity runningActivity,String name, boolean isLeader,int roomnum) {
        this.parents = runningActivity;
        this.name = name;
        this.isLeader = isLeader;
        this.roomnum = roomnum;


    }

    public void Write(String input)
    {
        Log.d("출력해보자",input);
        try {
            this.dos.writeUTF(input);
            Log.d("왜 안될까?",input);
        }catch (Exception e){}
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

                if (echo.startsWith("ROOMNUM///")) {

                    String [] splitData = echo.split("///");

                    this.roomnum = Integer.valueOf(splitData[1]);
                    // 그리고 roomnum이 들어가게 된다.


                    parents.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(parents, roomnum + " 번 게임방을 개설하였습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
            else
            {
                // 방에 접속해야한다.
                String message = "JOINROOM///" + name + "///" + roomnum; // 이름을 보낸다.

                dos.writeUTF(message);

            }

            while(Running)
            {

                if(isPlaying==false) {
                    String input = dis.readUTF();

                    if (input.startsWith("LETSPLAY///")) // 시작 신호를 받을 경우.
                    {
                        String[] splitData = input.split("///");
                        oppoName = splitData[1];
                        parents.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MultiActivity) parents).oppName.setText(oppoName);
                            }
                        });


                        parents.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MultiActivity) parents).message.setText(String.valueOf(5));
                            }
                        });
                        Thread.sleep(1000);

                        parents.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MultiActivity) parents).message.setText(String.valueOf(4));
                            }
                        });
                        Thread.sleep(1000);

                        parents.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MultiActivity) parents).message.setText(String.valueOf(3));
                            }
                        });
                        Thread.sleep(1000);

                        parents.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MultiActivity) parents).message.setText(String.valueOf(2));
                            }
                        });
                        Thread.sleep(1000);

                        parents.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MultiActivity) parents).message.setText(String.valueOf(1));
                            }
                        });
                        Thread.sleep(1000);

                        parents.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MultiActivity) parents).message.setText("Start");
                                ((MultiActivity) parents).waitingIcon.setImageResource(R.drawable.play);
                            }
                        });
                        Thread.sleep(1000);

                        parents.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MultiActivity) parents).StartGame();
                            }
                        });

                        isPlaying = true;

                    }
                }
                else {
                    String input = dis.readUTF();

                    if (input.startsWith("GAMEDATA///")) {
                        Log.d("게임데이터", input);
                    }
                }

            }

            sock.close();
            dis.close();
            dos.close();
        }catch (Exception e){}

    }
}

