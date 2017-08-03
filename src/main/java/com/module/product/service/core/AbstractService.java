package com.module.product.service.core;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.exception.ServiceException;
import com.module.product.orm.core.Mapper;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.exception.ServiceException;
import com.module.product.orm.core.Mapper;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by on 2016/11/17.
 * <p>
 * AbstractService  Implement By MyBatis
 * <p>
 * 基于通用Mapper的MyBatis来实现Service接口的抽象Service
 */
public abstract class AbstractService<T> implements Service<T> {

    @Autowired
    protected Mapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T model) {
        mapper.insertSelective(model);
    }

    public void save(List<T> models) {
        mapper.insertList(models);
    }

    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public void logicDeleteById(Integer id) {
        try {
            T model = modelClass.newInstance();
            Field idField = modelClass.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(model, id);
            Field deletedField = modelClass.getDeclaredField("deleted");
            deletedField.setAccessible(true);
            deletedField.set(model,id);
            mapper.updateByPrimaryKeySelective(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String property, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(property);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return mapper.selectAll();
    }
    /**
     * 连表的、需要自定义SQL的DataTable查询请覆写该方法，选择自己的Mapper查询方法。
     */
    @Override
    public DataTablePageModel buildDataTablePageModel(DataTablePageModel page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        List<T> data = mapper.selectAll();
        page.setData(data);
        long total = ((Page) (data)).getTotal();
        page.setRecordsTotal(total);
        page.setRecordsFiltered(total);
        return page;
    }
}
