package com.p3solutions.archon_report_utility.beans;

import com.p3solutions.archon_report_utility.beans.charts.HtmlCreationInfoBean;
import com.p3solutions.archon_report_utility.interfaces.ReportBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartCreationConfig implements ReportBean {

    @Builder.Default
    private List<HtmlCreationInfoBean> htmlCreationInfoBean = new ArrayList<>();

    @Builder.Default
    private int numberOfRows = 2;

    @Builder.Default
    private float fitWeight = 300;

    @Builder.Default
    private float fitHeight = 300;


}
