package org.wys.live.web.response;

/**
 * 转换返回类型
 */
public class RetResponse {

    /**
     * 返回成功信息
     */
    private final static String SUCCESS = "success";

    /**
     * @param <T>
     * @return
     */
    public static <T> RetResult<T> makeOKRsp() {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> RetResult<T> makeOKRsp(T data) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> RetResult<T> makeErrRsp(String message) {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(message);
    }

    public static <T> RetResult<T> makeRsp(int code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> RetResult<T> makeRsp(int code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }

}
