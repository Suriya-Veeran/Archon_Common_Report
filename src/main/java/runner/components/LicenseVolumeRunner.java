package runner.components;

import static com.p3solutions.archon_report_utility.builder.ChartCreationConfigUtil.buildChartCreationConfig;
import static com.p3solutions.archon_report_utility.builder.DividerBeanBuilder.buildDividerInputBean;
import static com.p3solutions.archon_report_utility.builder.HeaderBuilder.getHeaderBean;
import static com.p3solutions.archon_report_utility.builder.SummaryBeanBuilder.buildSummaryBean;
import static com.p3solutions.archon_report_utility.builder.TableBuilder.getTableBean;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.*;
import static runner.builder.TableValueBuilder.buildContentForJobSummary;
import static runner.builder.TableValueBuilder.headerTableParameters;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.FooterBean;
import com.p3solutions.archon_report_utility.core.Report;
import com.p3solutions.archon_report_utility.core.ReportBuilder;
import com.p3solutions.archon_report_utility.enums.DividerType;
import com.p3solutions.archon_report_utility.enums.FontType;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.services.CommonRunner;

@Slf4j
public class LicenseVolumeRunner implements CommonRunner {

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
                      "src/main/resources/fonts/Roboto-Medium.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent summaryDividerComponent =
          ReportBuilder.addComponent(
              buildDividerInputBean(718L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));

      ReportComponent jobStatusComponent =
              ReportBuilder.addComponent(getTableBean(new LinkedHashMap<>(), TableType.JOB_STATUS));

      ReportComponent jobTableComponent =
          ReportBuilder.addComponent(
              getTableBean(buildContentForJobSummary(reportNameConstants), TableType.SUMMARY));
      ReportComponent objectiveHeaderComponent =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "Objective",
                  HEADER_FONT_COLOR,
                  13,
                      "src/main/resources/fonts/Roboto-Medium.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent dividerAfterObjective =
          ReportBuilder.addComponent(
              buildDividerInputBean(560L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      String reportDescription =
          "The License Volume Statistics Report provides detailed insights into the utilization of software licenses within an organization. "
              + "It includes metrics on the total number of licenses available, the number of licenses currently in use, and any remaining or unused licenses. "
              + "This report helps organizations manage compliance, optimize license allocation, and plan for future licensing needs, ensuring cost-effective use of software resources and adherence to licensing agreements.";
      ReportComponent fileObjectiveValue =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  reportDescription,
                  OBJECTIVE_FONT_COLOR,
                  10,
                      "src/main/resources/fonts/Roboto-Regular.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent licenseVolumeMetrics =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "License Volume Metrics",
                  HEADER_FONT_COLOR,
                  13,
                      "src/main/resources/fonts/Roboto-Medium.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent licenseVolumeMetricDivider =
          ReportBuilder.addComponent(
              buildDividerInputBean(415L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      ReportComponent headerComponent =
          ReportBuilder.addComponent(getHeaderBean(reportNameConstants.getReportName()));
      ReportComponent footerComponent = ReportBuilder.addComponent(new FooterBean());
      ReportComponent chartComponent = ReportBuilder.addComponent(buildChartCreationConfig(reportNameConstants));

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
      report.addComponent(licenseVolumeMetrics);
      report.addComponent(licenseVolumeMetricDivider);
      report.addComponent(chartComponent);
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
