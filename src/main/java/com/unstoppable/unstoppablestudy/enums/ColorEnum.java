package com.unstoppable.unstoppablestudy.enums;

import lombok.Getter;
import lombok.Setter;


@Getter
public enum ColorEnum {
    RED(200,"red"),
    WHITE(201,"WHITE"),
    ;
    private Integer code;
    private String name;

    ColorEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public static ColorEnum getByCode(int code,ColorEnum colorEnum){
        for (ColorEnum value : ColorEnum.values()) {
            if(value.getCode()==code){
                colorEnum = value;
            }
        }
        return colorEnum;
    }
}
