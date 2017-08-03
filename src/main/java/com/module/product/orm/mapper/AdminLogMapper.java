package com.module.product.orm.mapper;


import com.module.product.common.dto.AdminLogDto;
import com.module.product.orm.model.AdminLog;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.dto.AdminLogDto;
import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.AdminLog;

import java.util.List;

public interface AdminLogMapper extends Mapper<AdminLog> {
    List<AdminLogDto> select4DataTable(DataTablePageModel search);
}