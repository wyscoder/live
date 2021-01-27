package org.wys.live.domain.po;

/**
 * 返回对象实体
 */
public class RetResult<T> {
    /**
     * 返回的状态码
     */
    private int code;

    /**
     * 返回的响应信息
     */
    private String msg;

    /**
     * 返回的数据类型
     */
    private T data;

    public RetResult<T> setCode(RetCode retCode) {
        this.code = retCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public RetResult<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RetResult<T> setData(T data) {
        this.data = data;
        return this;
    }
}
