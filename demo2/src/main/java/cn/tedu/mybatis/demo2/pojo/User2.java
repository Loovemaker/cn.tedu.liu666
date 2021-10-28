package cn.tedu.mybatis.demo2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User2 implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}
