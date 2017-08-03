package com.ruhang.hf.web;

import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.search.${modelNameUpperCamel}SearchBean;
import com.module.product.common.util.ResponseGenerator;
import com.module.product.orm.model.${modelNameUpperCamel};
import com.module.product.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;

/**
 * Created by ${author} on ${date}.
 */
@Controller
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PermissionAnn(menuCode = "X", msg = "你没有进入X管理页面的权限！")
    @RequestMapping("/manager")
    public String manager() {

        return "${viewName}";
    }

    @RequestMapping("/list")
    @ResponseBody
    public DataTablePageModel list(${modelNameUpperCamel}SearchBean searchBean) {
        return ${modelNameLowerCamel}Service.buildDataTablePageModel(searchBean);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseJsonModel add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResponseGenerator.genSuccess();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResponseJsonModel delete(Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResponseGenerator.genSuccess();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseJsonModel update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResponseGenerator.genSuccess();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public ResponseJsonModel detail(Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResponseGenerator.genSuccess(${modelNameLowerCamel});
    }
}
