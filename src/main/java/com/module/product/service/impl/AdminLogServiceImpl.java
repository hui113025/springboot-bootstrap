package com.module.product.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.dto.AdminLogDto;
import com.module.product.orm.mapper.AdminLogMapper;
import com.module.product.orm.model.AdminLog;
import com.module.product.service.AdminLogService;
import com.module.product.service.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by on 2017/5/9.
 */
@Service
@Transactional
public class AdminLogServiceImpl extends AbstractService<AdminLog> implements AdminLogService {

    @Resource
    private AdminLogMapper adminLogMapper;

    @Override
    public DataTablePageModel buildDataTablePageModel(DataTablePageModel page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        List<AdminLogDto> logList = adminLogMapper.select4DataTable(page);
        page.setData(logList);
        long total = ((Page) (logList)).getTotal();
        page.setRecordsTotal(total);
        page.setRecordsFiltered(total);
        return page;
    }

}
