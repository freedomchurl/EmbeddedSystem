package embedded.cse.cau.ac.kr.embedded;

/**
 * Created by churl on 2017-11-22.
 */

public class RoomData {

    int roomID;
    String name;
    int num;
    boolean state;

    RoomData(int roomID,String name,int num,boolean state)
    {
        this.name = name;
        this.roomID = roomID;
        this.num = num;
        this.state = state;

        // true면 플레이 false면 대기중
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public boolean getState()
    {
        return this.state;
    }

    public void setState(boolean state) {
        this.state = state;
    }


}
