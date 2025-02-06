package runner.components;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.FooterBean;
import com.p3solutions.archon_report_utility.core.Report;
import com.p3solutions.archon_report_utility.core.ReportBuilder;
import com.p3solutions.archon_report_utility.enums.DividerType;
import com.p3solutions.archon_report_utility.enums.FontType;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.services.CommonRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import static com.p3solutions.archon_report_utility.builder.ChartCreationConfigUtil.buildChartCreationConfig;
import static com.p3solutions.archon_report_utility.builder.DividerBeanBuilder.buildDividerInputBean;
import static com.p3solutions.archon_report_utility.builder.GridTableBuilder.buildGridTableComponent;
import static com.p3solutions.archon_report_utility.builder.HeaderBuilder.getHeaderBean;
import static com.p3solutions.archon_report_utility.builder.SummaryBeanBuilder.buildSummaryBean;
import static com.p3solutions.archon_report_utility.builder.TableBuilder.getTableBean;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.*;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.DIVIDER_GREY_COLOR;
import static runner.builder.GridValueBuilder.buildGridValue;
import static runner.builder.TableValueBuilder.buildContentForJobSummary;
import static runner.builder.TableValueBuilder.headerTableParameters;

@Slf4j
public class IngestionRunner implements CommonRunner {
    @Override
    public void generateReport(String location, ReportNameConstants reportNameConstants) {
        try {

            Report report = new ReportBuilder(location, reportNameConstants.getFileName()).build();
            ReportComponent tableComponent =
                    ReportBuilder.addComponent(getTableBean(headerTableParameters(), TableType.HEADER));
            ReportComponent dividerComponent =
                    ReportBuilder.addComponent(
                            buildDividerInputBean(
                                    760L, 1L, HEADER_TABLE_DIVIDER_GREY_COLOR, 1, DividerType.PAGE_TO_PAGE));
            ReportComponent jobSummaryComponent =
                    ReportBuilder.addComponent(
                            buildSummaryBean(
                                    "Job Summary",
                                    HEADER_FONT_COLOR,
                                    13,
                                    FontType.HELVETICA_BOLD.getFontName(),
                                    TextAlignment.LEFT,
                                    VerticalAlignment.TOP));
            ReportComponent summaryDividerComponent =
                    ReportBuilder.addComponent(
                            buildDividerInputBean(735L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));

            ReportComponent jobStatusComponent =
                    ReportBuilder.addComponent(getTableBean(new LinkedHashMap<>() , TableType.JOB_STATUS));

            ReportComponent jobTableComponent =
                    ReportBuilder.addComponent(
                            getTableBean(buildContentForJobSummary(reportNameConstants), TableType.SUMMARY));
            ReportComponent objectiveHeaderComponent =
                    ReportBuilder.addComponent(
                            buildSummaryBean(
                                    "Objective",
                                    HEADER_FONT_COLOR,
                                    13,
                                    FontType.HELVETICA_BOLD.getFontName(),
                                    TextAlignment.LEFT,
                                    VerticalAlignment.TOP));
            ReportComponent dividerAfterObjective =
                    ReportBuilder.addComponent(
                            buildDividerInputBean(470L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
            String reportDescription =
                    "The ingestion report details the process of importing data from various sources into the system. It includes metrics on data "
                            + "volume, ingestion times, and success rates, as well as any errors or issues encountered. This report ensures that the data "
                            + "ingestion process is efficient, accurate, and aligned with organizational requirements, providing a foundation for reliable data "
                            + "analytics and operations.";
            ReportComponent fileObjectiveValue =
                    ReportBuilder.addComponent(
                            buildSummaryBean(
                                    reportDescription,
                                    OBJECTIVE_FONT_COLOR,
                                    10,
                                    FontType.HELVETICA.getFontName(),
                                    TextAlignment.LEFT,
                                    VerticalAlignment.TOP));
            ReportComponent sessionMetrics =
                    ReportBuilder.addComponent(
                            buildSummaryBean(
                                    "Session Metrics",
                                    HEADER_FONT_COLOR,
                                    13,
                                    FontType.HELVETICA_BOLD.getFontName(),
                                    TextAlignment.LEFT,
                                    VerticalAlignment.TOP));
            ReportComponent sessionMetricsDivider =
                    ReportBuilder.addComponent(
                            buildDividerInputBean(370L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));

            ReportComponent chartComponent = ReportBuilder.addComponent(buildChartCreationConfig(reportNameConstants));
            ReportComponent tableLevelDetails =
                    ReportBuilder.addComponent(
                            buildSummaryBean(
                                    "Table Level Details",
                                    HEADER_FONT_COLOR,
                                    13,
                                    FontType.HELVETICA_BOLD.getFontName(),
                                    TextAlignment.LEFT,
                                    VerticalAlignment.TOP));
            ReportComponent tableLevelDetailsDivider =
                    ReportBuilder.addComponent(
                            buildDividerInputBean(785L, 1L, DIVIDER_GREY_COLOR, 2, DividerType.CONTENT));
            ReportComponent tableLevelGridComponent =
                    ReportBuilder.addComponent(
                            buildGridTableComponent(
                                    buildGridValue(reportNameConstants), buildGridValue(reportNameConstants).size()));
            ReportComponent headerComponent =
                    ReportBuilder.addComponent(getHeaderBean(reportNameConstants.getReportName()));
            ReportComponent footerComponent = ReportBuilder.addComponent(new FooterBean());
            report.addComponent(headerComponent);
            report.addComponent(tableComponent);
            report.addComponent(dividerComponent);
            report.addComponent(jobSummaryComponent);
            report.addComponent(summaryDividerComponent);
            report.addComponent(jobStatusComponent);
            report.addComponent(jobTableComponent);
            report.addComponent(objectiveHeaderComponent);
            report.addComponent(dividerAfterObjective);
            report.addComponent(fileObjectiveValue);
            report.addComponent(sessionMetrics);
            report.addComponent(sessionMetricsDivider);
            report.addComponent(chartComponent);
            report.addComponent(tableLevelDetails);
            report.addComponent(tableLevelDetailsDivider);
            report.addComponent(tableLevelGridComponent);
            report.addComponent(footerComponent);
            report.render();
            report.close();

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
