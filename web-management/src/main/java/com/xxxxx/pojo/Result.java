package com.xxxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//後端統一回應結果
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code; //1:成功, 0:失敗
    private String msg;   //錯誤提示訊息
    private Object data;  //資料

    public static Result success(){
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object object){
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.data = object;
        return result;
    }

    public static Result error(String msg){
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }

}
