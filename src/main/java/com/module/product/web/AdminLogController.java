package com.module.product.web;


import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;
import com.module.product.common.search.AdminLogSearchBean;
import com.module.product.service.AdminLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adminLog")
public class AdminLogController {
    @Resource
    private AdminLogService adminLogService;

    @PermissionAnn(menuCode = "adminLog", msg = "你没有进入操作日志列表页面的权限!")
    @RequestMapping(value = "/list")
    public String forwardLogListManager(Model model) {
        List operTypelList = new ArrayList();
        for (OperType operType : OperType.values()) {
            operTypelList.add(operType);
        }

        List operLevelList = new ArrayList();
        for (OperLevel operLevel : OperLevel.values()) {
            operLevelList.add(operLevel);
        }
        model.addAttribute("operTypelList", operTypelList);
        model.addAttribute("operLevelList", operLevelList);
        return "log-manager";
    }

    @PermissionAnn(menuCode = "adminLog", operCode = "find", msg = "你没有操作日志的查询权限!")
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public DataTablePageModel searchEmpInfo(AdminLogSearchBean adminLogSearchBean) {
        DataTablePageModel pageModel = adminLogService.buildDataTablePageModel(adminLogSearchBean);
        return pageModel;
    }
}
