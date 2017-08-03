package com.module.product;


import com.module.product.orm.model.AuthorityOperation;
import com.module.product.service.AuthorityOperationService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by on 2017/5/11.
 */
public class AbstractServiceTest extends ApplicationTest {
    @Resource
    private AuthorityOperationService operationService;

    @Test
    public  void testLogicDeleteById(){
        final Integer id = 1;
        Assert.assertTrue(operationService.findById(id).getDeleted() ==  0);
        operationService.logicDeleteById(id);
        Assert.assertTrue(operationService.findById(id).getDeleted() ==  1);
    }
    @Test
    public  void testFindBy(){
        String value = "operManage";
        AuthorityOperation operation = operationService.findBy("code", value);
        Assert.assertTrue(value.equals(operation.getCode()));
    }
}
