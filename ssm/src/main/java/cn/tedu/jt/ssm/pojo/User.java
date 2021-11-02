package cn.tedu.jt.ssm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}
