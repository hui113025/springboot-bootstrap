package com.module.product.common.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.product.common.annotation.AdminLogAnn;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.model.AdminLog;
import com.module.product.service.AdminLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by on 2017/5/10.
 */
@Aspect
@Component
public class LogAspect {
    @Resource
    private AdminLogService adminLogService;

    @AfterReturning("@annotation(logAnn)")
    @Async
    public void saveLog(JoinPoint point, AdminLogAnn logAnn) throws JsonProcessingException {
        if (logAnn.enable()) {
            AdminLog adminLog = new AdminLog();
            adminLog.setUserId(ShiroUtils.getSessionUser().getId());
            adminLog.setUserName(ShiroUtils.getSessionUser().getName());
            adminLog.setOperName(point.getSignature().toString());
            String param = convertArgs(point.getArgs());
            adminLog.setOperParam(param);
            adminLog.setOperDescribe(logAnn.operDescribe());
            adminLog.setOperType(logAnn.operType().getValue());
            adminLog.setOperLevel(logAnn.operLevel().getValue());
            adminLog.setOperTime(new Date());
            this.adminLogService.save(adminLog);
        }
    }

    private String convertArgs(Object[] objs) throws JsonProcessingException {
        String param = "";
        if (null != objs && objs.length > 0) {
            List para = new ArrayList<>();
            for (int i = 0; i < objs.length; i++) {
                Object o = objs[i];
                if (HttpServletRequest.class.isInstance(o) ||
                        HttpServletResponse.class.isInstance(o) ||
                        HttpSession.class.isInstance(o) || Model.class.isInstance(o)) {
                    continue;
                }
                para.add(o);
            }

            ObjectMapper json = new ObjectMapper();
            param = json.writeValueAsString(para);
        }
        return param;
    }
}
