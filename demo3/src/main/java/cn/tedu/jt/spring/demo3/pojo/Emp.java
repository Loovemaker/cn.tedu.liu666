package cn.tedu.jt.spring.demo3.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable {
    private Integer empId;
    private String empName;

    private Dept dept;
}
