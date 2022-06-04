package io.github.xingchuan.query.api.domain.error;

import cn.hutool.core.util.StrUtil;

import java.util.Map;

/**
 * 框架的错误
 *
 * @author xingchuan.qxc
 * @date 2022/6/4
 */
public class CommonError extends Error {


    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    public static CommonError NOT_SUPPORT_OPERATION() {
        return new CommonError("J0000001", "不支持的操作,{operation}");
    }

    ;

    public CommonError(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 创建运行时异常
     *
     * @return
     */
    public JevinRuntimeException newException() {

        return new JevinRuntimeException(this.errorCode, this);
    }

    /**
     * 转换错误提示
     *
     * @param params
     * @return
     */
    public CommonError parseErrorMsg(Map<String, Object> params) {
        String content = StrUtil.format(this.errorMsg, params);
        this.setErrorMsg(content);
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
