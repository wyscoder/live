package cn.wys.live.beans;

/**
 * @author wys
 * @date 2019/10/31
 * 房间信息
 */
public class Room {

    /**
     * 房间名称
     */
    private String roomName;
    /**
     * 主播名称
     */
    private String anchorName;
    /**
     * 房间id号
     */
    private String roomId;


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomName='" + roomName + '\'' +
                ", anchorName='" + anchorName + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
