package com.bite.book.enums;

import lombok.Data;


public enum BookStatus {
    DELETE(0, "删除"),
    NORMAL(1, "可借阅"),
    FORBIDDEN(2, "不可借阅"),
    ;
    BookStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;
    /**
     * 根据Code, 返回描述信息
     */
    public static BookStatus getDescByCode(Integer code){
        switch (code){
            case 0: return DELETE;
            case 1: return NORMAL;
            case 2:
            default:
                return FORBIDDEN;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
