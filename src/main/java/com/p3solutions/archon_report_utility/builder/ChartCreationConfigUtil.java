package com.p3solutions.archon_report_utility.builder;

import com.p3solutions.archon_report_utility.beans.ChartCreationConfig;
import com.p3solutions.archon_report_utility.beans.charts.DataInfoBean;
import com.p3solutions.archon_report_utility.beans.charts.HtmlCreationInfoBean;
import com.p3solutions.archon_report_utility.enums.FormatTypes;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static com.p3solutions.archon_report_utility.builder.ChartBeanUtils.*;

@UtilityClass
public class ChartCreationConfigUtil {
    public static ChartCreationConfig buildChartCreationConfig() {
        return ChartCreationConfig.
                builder()
                .fitHeight(300)
                .fitWeight(300)
                .numberOfRows(2)
                .htmlCreationInfoBean(buildHtmlCreationInfoBeans())
                .build();

    }

    public static List<HtmlCreationInfoBean> buildHtmlCreationInfoBeans() {
        List<HtmlCreationInfoBean> htmlCreationInfoBeans = new ArrayList<>();

        // Add Pie chart
        htmlCreationInfoBeans.add(createPieChartConfig());

        // Add Doughnut chart
        htmlCreationInfoBeans.add(createDoughnutChartConfig());

        return htmlCreationInfoBeans;
    }

    // Method to create a Pie chart configuration
    private static HtmlCreationInfoBean createPieChartConfig() {
        List<String> pieData = List.of("350 GB", "650 GB");
        List<DataInfoBean> pieDataInfoList = List.of(
                buildDataInfoBean("350 GB", 350, FormatTypes.GB, "#397EE3"),
                buildDataInfoBean("650 GB", 650, FormatTypes.GB, "#9AC2FC")
        );

        return ChartBeanUtils.createChartConfig(
                ChartBeanUtils.createChartBasicInfo("500px", "400px", "pie"),
                ChartBeanUtils.createTitleConfig("Volume", 16, "Arial", "bold", "#333"),
                ChartBeanUtils.createLegendInfoBean(pieData),
                ChartBeanUtils.createSeriesInfoBean(pieDataInfoList)
        );
    }

    // Method to create a Doughnut chart configuration
    private static HtmlCreationInfoBean createDoughnutChartConfig() {
        List<String> doughnutData = List.of("Structured", "Unstructured", "Compliance", "Disposed");
        List<DataInfoBean> doughnutDataInfoList = List.of(
                buildDataInfoBean("Structured", 347, FormatTypes.GB, "#397EE3"),
                buildDataInfoBean("Unstructured", 100, FormatTypes.GB, "#406292"),
                buildDataInfoBean("Compliance", 32, FormatTypes.GB, "#697A91"),
                buildDataInfoBean("Disposed", 512, FormatTypes.MB, "#9AC2FC")
        );

        return ChartBeanUtils.createChartConfig(
                ChartBeanUtils.createChartBasicInfo("500px", "400px", "doughnut"),
                ChartBeanUtils.createTitleConfig("Consumption", 16, "Arial", "bold", "#333"),
                ChartBeanUtils.createLegendInfoBean(doughnutData),
                ChartBeanUtils.createSeriesInfoBean(doughnutDataInfoList)
        );
    }

}
