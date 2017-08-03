package com.ruhang.hf.service.impl;

import com.module.product.common.bean.DataTablePageModel;
import com.module.product.orm.mapper.${modelNameUpperCamel}Mapper;
import com.module.product.orm.model.${modelNameUpperCamel};
import com.module.product.service.${modelNameUpperCamel}Service;
import com.module.product.service.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    //连表的DataTable查询覆写该方法
    @Override
    public DataTablePageModel buildDataTablePageModel(DataTablePageModel page) {
         return super.buildDataTablePageModel(page);
    }
}
