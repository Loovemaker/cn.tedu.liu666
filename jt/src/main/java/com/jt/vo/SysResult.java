package com.jt.vo;


import com.jt.misc.Codes;
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
public class SysResult {

    private Integer status;
    private String msg;
    private Object data;

    public static SysResult failed() {
        return new SysResult(Codes.Http.failed, "failed", null);
    }

    public static SysResult success(String msg, Object data) {
        return new SysResult(Codes.Http.success, msg, data);
    }
    public static SysResult success(Object data) {
        return success("success", data);
    }
    public static SysResult success() {
        return success(null);
    }
}

