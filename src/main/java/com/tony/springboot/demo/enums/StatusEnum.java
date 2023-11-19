package com.tony.springboot.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    DISABLE(0),
    ENABLE(1),
    ;

    private Integer val;
}
