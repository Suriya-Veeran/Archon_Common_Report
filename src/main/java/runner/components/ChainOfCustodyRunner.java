package runner.components;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.FooterBean;
import com.p3solutions.archon_report_utility.core.Report;
import com.p3solutions.archon_report_utility.core.ReportBuilder;
import com.p3solutions.archon_report_utility.enums.DividerType;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.services.CommonRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import static com.p3solutions.archon_report_utility.builder.DividerBeanBuilder.buildDividerInputBean;
import static com.p3solutions.archon_report_utility.builder.GridTableBuilder.buildGridTableComponent;
import static com.p3solutions.archon_report_utility.builder.HeaderBuilder.getHeaderBean;
import static com.p3solutions.archon_report_utility.builder.SummaryBeanBuilder.buildSummaryBean;
import static com.p3solutions.archon_report_utility.builder.TableBuilder.getTableBean;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.*;
import static runner.builder.GridValueBuilder.buildGridValue;
import static runner.builder.TableValueBuilder.buildContentForJobSummary;
import static runner.builder.TableValueBuilder.headerTableParameters;

@Slf4j
public class ChainOfCustodyRunner implements CommonRunner {
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
              buildDividerInputBean(720L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));

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
              buildDividerInputBean(510L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      String reportDescription =
          "This report contains details of the end-to-end data and metadata track of the data object from the point of extraction to "
              + "ingestion into Archon Data Store. Chain of custody checks are run for each object, and their status is listed alongside. "
              + "Go through them to ensure your data has been mapped without errors.";
      ReportComponent fileObjectiveValue =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  reportDescription,
                  OBJECTIVE_FONT_COLOR,
                  10,
                      "src/main/resources/fonts/Roboto-Regular.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent chainOfCustodySummary =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "Chain of Custody Summary",
                  HEADER_FONT_COLOR,
                  13,
                      "src/main/resources/fonts/Roboto-Medium.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent chainOfCustodySummaryDivider =
          ReportBuilder.addComponent(
              buildDividerInputBean(410L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));

      ReportComponent schemaLevelDetails =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "Schema Level Details",
                  HEADER_FONT_COLOR,
                  13,
                      "src/main/resources/fonts/Roboto-Medium.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));

      ReportComponent schemaLevelDetailDivider =
          ReportBuilder.addComponent(
              buildDividerInputBean(370L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));

      ReportComponent schemaLevelGrid =
          ReportBuilder.addComponent(
              buildGridTableComponent(
                  buildGridValue(reportNameConstants, "Schema"), buildGridValue(reportNameConstants, "Schema").size()));

      ReportComponent tableLevelDetails =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "Table Level Details",
                  HEADER_FONT_COLOR,
                  13,
                      "src/main/resources/fonts/Roboto-Medium.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent tableLevelDetailsDivider =
          ReportBuilder.addComponent(
              buildDividerInputBean(265L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      ReportComponent tableLevelGridComponent =
          ReportBuilder.addComponent(
              buildGridTableComponent(
                  buildGridValue(reportNameConstants, "Table"), buildGridValue(reportNameConstants, "Table").size()));
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
      report.addComponent(chainOfCustodySummary);
      report.addComponent(chainOfCustodySummaryDivider);
      report.addComponent(schemaLevelDetails);
      report.addComponent(schemaLevelDetailDivider);
      report.addComponent(schemaLevelGrid);
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
