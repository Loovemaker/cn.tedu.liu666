package com.jt.vo;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.jt.pojo.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


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
