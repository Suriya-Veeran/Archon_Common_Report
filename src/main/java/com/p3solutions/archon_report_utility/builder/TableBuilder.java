package com.p3solutions.archon_report_utility.builder;

import com.itextpdf.layout.borders.Border;
import com.p3solutions.archon_report_utility.beans.TableBean;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.enums.TableTypeEnum;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TableBuilder {
    public static TableBean getTableBean(Map<String, String> parameters,
                                         TableType tableType) {

        return TableBean.builder()
                .numberOfColumns(3)
                .pointColumnWidth(new float[]{450L, 450L, 450L})
                .width(100)
                .border(Border.NO_BORDER)
                .keepTogether(true)
                .parameters(parameters)
                .tableType(tableType)
                .type(TableTypeEnum.POINT_COLUMN_WIDTH)
                .build();
    }
}
