package com.module.product.common.util;


import com.module.product.common.constant.ResponseCodeConstant;
import com.module.product.common.bean.ResponseJsonModel;

import static com.module.product.common.constant.ResponseCodeConstant.ERROR_CODE;

/**
 * Created by  on 2017/5/10.
 */
public abstract class ResponseGenerator {

    public static ResponseJsonModel genSuccess(){
        ResponseJsonModel response = new ResponseJsonModel();
        return response;
   }

    public static ResponseJsonModel genSuccess(Object result){
        ResponseJsonModel response = new ResponseJsonModel();
        response.setResult(result);
        return response;
    }
    public static ResponseJsonModel genFail(String message) {
        ResponseJsonModel response = new ResponseJsonModel();
        response.setCode(ResponseCodeConstant.ERROR_CODE);
        response.setMsg(message);
        return response;
    }
}
