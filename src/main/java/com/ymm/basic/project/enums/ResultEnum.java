package com.ymm.basic.project.enums;


import lombok.Getter;

/**
 * @author y
 * Date: Created in 18/8/29 上午9:54
 * Utils: Intellij Idea
 * Description: 返回状态枚举类
 */
@Getter
public enum ResultEnum {

    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(10000, "未知异常"),

    /**
     * 格式错误
     */
    FORMAT_ERROR(10001, "参数格式错误"),

    /**
     * 超时
     */
    TIME_OUT(10002, "超时"),

    /**
     * 添加失败
     */
    ADD_ERROR(10003, "添加失败"),

    /**
     * 更新失败
     */
    UPDATE_ERROR(10004, "更新失败"),

    /**
     * 删除失败
     */
    DELETE_ERROR(10005, "删除失败"),

    /**
     * 查找失败
     */
    GET_ERROR(10006, "查找失败"),

    /**
     * 参数类型不匹配
     */
    ARGUMENT_TYPE_MISMATCH(10007, "参数类型不匹配"),

    /**
     * 请求方式不支持
     */
    REQ_METHOD_NOT_SUPPORT(10010,"请求方式不支持"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过状态码获取枚举对象
     * @param code 状态码
     * @return 枚举对象
     */
    public static ResultEnum getByCode(int code){
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if(code == resultEnum.getCode()){
                return resultEnum;
            }
        }
        return null;
    }

}

