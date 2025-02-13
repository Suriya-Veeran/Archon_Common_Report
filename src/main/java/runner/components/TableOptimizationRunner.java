package runner.components;

import static com.p3solutions.archon_report_utility.builder.DividerBeanBuilder.buildDividerInputBean;
import static com.p3solutions.archon_report_utility.builder.GridTableBuilder.buildGridTableComponent;
import static com.p3solutions.archon_report_utility.builder.HeaderBuilder.getHeaderBean;
import static com.p3solutions.archon_report_utility.builder.SummaryBeanBuilder.buildSummaryBean;
import static com.p3solutions.archon_report_utility.builder.TableBuilder.getTableBean;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.*;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.DIVIDER_GREY_COLOR;
import static runner.builder.GridValueBuilder.buildGridValue;
import static runner.builder.TableValueBuilder.*;
import static runner.constants.RobotoFontConstants.ROBOTO_MEDIUM;
import static runner.constants.RobotoFontConstants.ROBOTO_REGULAR;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.FooterBean;
import com.p3solutions.archon_report_utility.core.Report;
import com.p3solutions.archon_report_utility.core.ReportBuilder;
import com.p3solutions.archon_report_utility.enums.DividerType;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.services.CommonRunner;

@Slf4j
public class TableOptimizationRunner implements CommonRunner {

  @Override
  public void generateReport(String location, ReportNameConstants reportNameConstants) {

    try {
      Report report = new ReportBuilder(location, reportNameConstants.getFileName()).build();
      ReportComponent tableComponent =
          ReportBuilder.addComponent(getTableBean(headerTableParameters(reportNameConstants), TableType.HEADER));
      ReportComponent dividerComponent =
          ReportBuilder.addComponent(
              buildDividerInputBean(
                  755L, 0.75f, HEADER_TABLE_DIVIDER_GREY_COLOR, 1, DividerType.PAGE_TO_PAGE));
      ReportComponent jobSummaryComponent =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "Job Summary",
                  HEADER_FONT_COLOR,
                  13,
                      ROBOTO_MEDIUM,
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent summaryDividerComponent =
          ReportBuilder.addComponent(
              buildDividerInputBean(726L, 0.75f, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));

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
                      ROBOTO_MEDIUM,
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent dividerAfterObjective =
          ReportBuilder.addComponent(
              buildDividerInputBean(510L, 0.75f, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      String reportDescription =
              "This report shows the storage optimisation achieved after the process run. "
                      + "Table data optimisation jobs identify scope of optimising the way this data is stored "
                      + "using all the data and metadata information available for that table. "
                      + "This involves techniques like merging data sets, compressing data, cleaning up fragmented storage, etc.";
      ReportComponent fileObjectiveValue =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  reportDescription,
                  OBJECTIVE_FONT_COLOR,
                  10,
                      ROBOTO_REGULAR,
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent optimizationStatistics =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "Optimization Statistics",
                  HEADER_FONT_COLOR,
                  13,
                      ROBOTO_MEDIUM,
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent optimizationStatisticsDivider =
          ReportBuilder.addComponent(
              buildDividerInputBean(425L, 0.75f, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      ReportComponent optimizationGridComponent =
          ReportBuilder.addComponent(buildGridTableComponent(buildGridValue(reportNameConstants),
                  buildGridValue(reportNameConstants).size()));
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
      report.addComponent(optimizationStatistics);
      report.addComponent(optimizationStatisticsDivider);
      report.addComponent(optimizationGridComponent);
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
