package com.p3solutions.archon_report_utility.components;

import com.itextpdf.layout.Document;
import com.p3solutions.archon_report_utility.beans.TableBean;
import com.p3solutions.archon_report_utility.exception.EnumNotFound;
import com.p3solutions.archon_report_utility.helpers.TableHelper;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TableComponent implements ReportComponent {


    private TableBean inputBean;

    private TableHelper tableHelper;


    public void render(Document document) throws IOException {

        switch (inputBean.getTableConfigBean().getTableType()) {
            case HEADER:
                break;
            case SUMMARY:
                break;
            case JOB_STATUS:
                break;
            default:
                throw new EnumNotFound("Illegal table type : " + inputBean.getTableConfigBean().getTableType());
        }

    }


}
