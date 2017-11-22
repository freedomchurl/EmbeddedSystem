package embedded.cse.cau.ac.kr.embedded;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by churl on 2017-11-21.
 */

public class LoginMultiActivity extends Activity {



    private FloatingActionButton fab;

    RecyclerView roomView = null;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    ArrayList<RoomData> myRoom = new ArrayList<RoomData>();

    ImageView refreshBtn = null;
    String myName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longin_activity);

        refreshBtn = (ImageView) findViewById(R.id.refresh_button);
        fab = (FloatingActionButton) findViewById(R.id.float_add);

        myName = getIntent().getStringExtra("MYNAME");

        //myRoom.add(new RoomData(3,"김유정",1,false));

        roomView = (RecyclerView) findViewById(R.id.room_recycle);

        roomView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        roomView.setLayoutManager(layoutManager);
        adapter = new MyAdpater(myRoom,getApplicationContext());

        roomView.setAdapter(adapter);

        GetRoom myRoom = new GetRoom();
        myRoom.execute();

        refreshBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GetRoom getRoom = new GetRoom();
                getRoom.execute();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginMultiActivity.this,MultiActivity.class);
                i.putExtra("MYNAME",myName);

                // 여기다가 서버에 방을 만들어줘야하고, 여기서부터는 연결을 아예 해야한다.
                i.putExtra("ISLEADER",true);
                // 방을 만드는 사람

                startActivity(i);
                finish();

            }
        });

    }


    class GetRoom extends AsyncTask<Void,Void,String>
    {
        int myport = 8686;

        ServerSocket server = null;
        OutputStream out = null;
        InputStream in = null;
        Socket sock = null;

        DataOutputStream dos = null;
        DataInputStream dis = null;

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                String [] splitData = result.split("\n"); // 줄바꿈으로 Split

                myRoom.clear();
                for(int i=0;i<splitData.length;i++)
                {
                    Log.d("Cheol","짱짱");
                    String [] RoomData = splitData[i].split("///");

                    String name = RoomData[0];
                    int id = Integer.valueOf(RoomData[1]);
                    int num = Integer.valueOf(RoomData[2]);
                    boolean state;

                    if(RoomData[3].equals("0")) // 플레이중
                        state = true; // true면 플레이
                    else // 대기중
                        state = false; // false면 대기중

                    myRoom.add(new RoomData(id,name,num,state));
                }

                adapter.notifyDataSetChanged();


            }catch (Exception e){}
        }

        @Override
        protected String doInBackground(Void... strings) {

            String result = null;

            try {
                sock = new Socket("10.210.60.231", 7676);
                // 이게 쓰기용
                out = sock.getOutputStream();
                in = sock.getInputStream();

                dis = new DataInputStream(in);
                //BufferedReader br = new BufferedReader(new InputStreamReader(in));
                dos = new DataOutputStream(out);

                String message = "REQUEST_ROOM_LIST"; // 방 이름을 요청한다.

                dos.writeUTF(message);

                result = dis.readUTF();

                Log.d("ROOMDATA",result);


                sock.close();
                dis.close();
                dos.close();


                return result;
            }catch (Exception e){}

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }






    class MyAdpater extends RecyclerView.Adapter {
        private Context context;
        private ArrayList<RoomData> mItems;

        // Allows to remember the last item shown on screen
        private int lastPosition = -1;

        public MyAdpater(ArrayList<RoomData> items, Context mContext) {
            mItems = items;
            context = mContext;
        }

        @Override
        public MyAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_cardview, parent, false);
            MyAdpater.ViewHolder holder = new MyAdpater.ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(mItems.get(position).state==true)
                ((MyAdpater.ViewHolder)holder).imageView.setImageResource(R.drawable.play);
            else
                ((MyAdpater.ViewHolder)holder).imageView.setImageResource(R.drawable.hourglass);
            ((MyAdpater.ViewHolder)holder).nameView.setText(mItems.get(position).getName());
            ((MyAdpater.ViewHolder)holder).numView.setText(String.valueOf(mItems.get(position).getNum()) + "/2");

            setAnimation(((MyAdpater.ViewHolder)holder).imageView, position);

        }



        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView imageView;
            public TextView nameView;
            public TextView numView;


            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.state_image);
                nameView = (TextView) view.findViewById(R.id.opposite_name);
                numView = (TextView) view.findViewById(R.id.opposite_num);


                view.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v)
                    {
                        // 그 채팅방으로 들어가는 기능이 필요하다.
                        Intent i = new Intent(LoginMultiActivity.this,MultiActivity.class);

                        int pos = getPosition();
                        i.putExtra("MYNAME",myName);
                        i.putExtra("ISLEADER",false);
                        i.putExtra("ROOMNUM",mItems.get(pos).getRoomID());
                        Log.d("몇번째방이지?",String.valueOf(mItems.get(pos).getRoomID()));
                        startActivity(i);
                        finish();


                    }
                });
                // 이 부분에서, 드래그 삭제 기능을 넣어야한다.
            }

        }

        private void setAnimation(View viewToAnimate, int position) {
            // 새로 보여지는 뷰라면 애니메이션을 해줍니다
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        }


    }
}
