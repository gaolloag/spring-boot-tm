package com.ym.ms.commons.enums;

/**
 * 是否删除枚举
 *
 * @author sys
 * @date 9.5
 */
public enum IsDeleteEnum {
    DELETED(1, "删除"),
    NORMAL(0, "有效");

    private int key;
    private String description;

    IsDeleteEnum(int key, String description) {
        this.key = key;
        this.description = description;
    }

    public static IsDeleteEnum get(int key) {
        IsDeleteEnum re = null;
        for (IsDeleteEnum e : IsDeleteEnum.values()) {
            if (e.getKey() == key) {
                re = e;
                break;
            }
        }
        return re;
    }

    public int getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

}
