package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/2/2
 */
@Data
@Accessors(chain = true)
@TableName("user")
public class User extends BasePojo{
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Boolean status;     // isEnabled
}
