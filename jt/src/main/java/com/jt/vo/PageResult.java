package com.jt.vo;


import lombok.*;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private String  query;
    private Integer pageNum;
    private Integer pageSize;
    private Long    total;
    private Object  rows;
}
