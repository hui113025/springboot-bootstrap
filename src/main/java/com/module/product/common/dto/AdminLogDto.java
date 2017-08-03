package com.module.product.common.dto;

import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;
import com.module.product.orm.model.AdminLog;

public class AdminLogDto extends AdminLog {

     private String operLevelDto;//操作级别
    private String operTypeDto;//操作类型

    public String getOperLevelDto() {
        return operLevelDto;
    }

    public void setOperLevelDto(String operLevelDto) {

        for (OperLevel operLevelTemp : OperLevel.values()) {
                if (Integer.parseInt(operLevelDto) == operLevelTemp.getValue()) {
                    this.operLevelDto = operLevelTemp.getName();
                }
        }
    }

    public String getOperTypeDto() {
        return operTypeDto;
    }

    public void setOperTypeDto(String operTypeDto) {

        for (OperType operTypeTemp : OperType.values()) {
            if (operTypeDto.equals(operTypeTemp.getValue().toLowerCase())) {
                this.operTypeDto = operTypeTemp.getName();
            }
        }
    }
}
