package cn.edu.qlu.exception;

/**
 * <描述信息>
 */
public class BizException extends RuntimeException {

    public BizException(String message) {
        this(message, false);
    }

    public BizException(String message, boolean writableStackTrace) {
        this(message, null, false, writableStackTrace);
    }

    protected BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
