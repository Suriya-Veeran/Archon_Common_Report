package com.p3solutions.archon_report_utility.builder;

import com.p3solutions.archon_report_utility.beans.ChartCreationConfig;
import com.p3solutions.archon_report_utility.beans.charts.DataInfoBean;
import com.p3solutions.archon_report_utility.beans.charts.HtmlCreationInfoBean;
import com.p3solutions.archon_report_utility.enums.FormatTypes;
import com.p3solutions.archon_report_utility.exception.EnumNotFound;
import lombok.experimental.UtilityClass;
import runner.enums.ReportNameConstants;

import java.util.ArrayList;
import java.util.List;

import static com.p3solutions.archon_report_utility.builder.ChartBeanUtils.*;

@UtilityClass
public class ChartCreationConfigUtil {
    public static ChartCreationConfig buildChartCreationConfig(ReportNameConstants reportNameConstants) {

        ChartCreationConfig chartCreationConfig = ChartCreationConfig.
                builder()
                .fitHeight(400)
                .fitWeight(400).build();

        switch (reportNameConstants){
            case INGESTION_REPORT :
            case SOURCE_TO_TARGET_VALIDATION_REPORT:
                chartCreationConfig.setNumberOfRows(2);
                chartCreationConfig.setHtmlCreationInfoBean(buildSourceToValidationReport());
             break;
            case LICENSE_VOLUME_STATISTICS_REPORT:
                chartCreationConfig.setNumberOfRows(2);
                chartCreationConfig.setHtmlCreationInfoBean(buildLicenseStatisticsInfoBean());
            break;
            default:
                throw new EnumNotFound("Unsupported report type " + reportNameConstants.getReportName());
        }

        return chartCreationConfig;

    }

    public static List<HtmlCreationInfoBean> buildSourceToValidationReport(){
        List<HtmlCreationInfoBean> htmlCreationInfoBeans = new ArrayList<>();
        htmlCreationInfoBeans.add(createPieChartForSourceToValidation("Table"));
        htmlCreationInfoBeans.add(createPieChartForSourceToValidation("Files"));
        return htmlCreationInfoBeans;
    }

    private static HtmlCreationInfoBean createPieChartForSourceToValidation(String title) {

        List<String> pieData = List.of("Success", "Failed");
        List<DataInfoBean> pieDataInfoList = List.of(
                buildDataInfoBean("Success", 6, FormatTypes.MB, "#4169E1"),
                buildDataInfoBean("Failed", 1, FormatTypes.MB, "#FF0000")
        );

        return ChartBeanUtils.createChartConfig(
                ChartBeanUtils.createChartBasicInfo("500px", "400px", "pie"),
                ChartBeanUtils.createTitleConfig(title, 16, "Arial", "bold", "#333"),
                ChartBeanUtils.createLegendInfoBean(pieData),
                ChartBeanUtils.createSeriesInfoBean(pieDataInfoList)
        );
    }

    public static List<HtmlCreationInfoBean> buildLicenseStatisticsInfoBean() {
        List<HtmlCreationInfoBean> htmlCreationInfoBeans = new ArrayList<>();
        htmlCreationInfoBeans.add(createPieChartConfig());
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
