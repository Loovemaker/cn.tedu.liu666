package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageResult {
    private String  query;
    private Integer pageNum;
    private Integer pageSize;
    private Long    total;
    private Object  rows;
}
