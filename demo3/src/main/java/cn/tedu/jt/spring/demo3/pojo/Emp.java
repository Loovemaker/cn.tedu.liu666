package cn.tedu.jt.spring.demo3.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable {

    private Integer empId;
    private String empName;

    private Dept dept;

    @Override
    public String toString() {
        return new StringJoiner(", ", Emp.class.getSimpleName() + "[", "]")
                .add("Name: " + empName)
                .add("Dept: " + (Objects.nonNull(dept) ? dept.getDeptName() : null))
                .toString();
    }

}
