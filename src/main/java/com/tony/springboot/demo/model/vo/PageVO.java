package com.tony.springboot.demo.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageVO {
    @ApiModelProperty(value = "页码", example = "1")
    private Integer number = 1;
    @ApiModelProperty(value = "页大小", example = "10")
    private Integer size = 10;
}
