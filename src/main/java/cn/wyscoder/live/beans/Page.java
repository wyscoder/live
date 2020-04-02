package cn.wyscoder.live.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 分页属性类
 * @author wys
 * @date 2019/11/1
 */
public class Page implements Serializable {

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 直播总数量
     */
    private Integer totalCount;

    /**
     * 当前直播页数
     */
    private Integer thePage;

    /**
     * 当前页直播所有房间
     */
    private List<Room> rooms;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getThePage() {
        return thePage;
    }

    public void setThePage(Integer thePage) {
        this.thePage = thePage;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", thePage=" + thePage +
                ", rooms=" + rooms +
                '}';
    }
}
