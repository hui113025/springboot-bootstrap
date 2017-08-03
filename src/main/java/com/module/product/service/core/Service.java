package com.module.product.service.core;

import com.module.product.common.bean.DataTablePageModel;
import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Created by on 2016/6/20.
 * <p>
 * Service 层 基础接口，其他Service 接口 请继承该接口
 *
 * @param <T> the model type parameter
 */
public interface Service<T> {
    void save(T model);//持久化
    void save(List<T> models);//批量持久化
    void deleteById(Integer id);//通过主鍵刪除
    void deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”
    void logicDeleteById(Integer id);//通过主键逻辑删除
    void update(T model);//更新
    T findById(Integer id);//通过ID查找
    T findBy(String property, Object value) throws TooManyResultsException; //通过某个成员属性查找,value需符合unique约束
    List<T> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”
    List<T> findByCondition(Condition condition);//根据条件查找
    List<T> findAll();//获取所有
    DataTablePageModel buildDataTablePageModel(DataTablePageModel dataTablePageModel);

}
