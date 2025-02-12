package runner.components;

import static com.p3solutions.archon_report_utility.builder.CardBuilder.buildCard;
import static com.p3solutions.archon_report_utility.builder.DividerBeanBuilder.buildDividerInputBean;
import static com.p3solutions.archon_report_utility.builder.HeaderBuilder.getHeaderBean;
import static com.p3solutions.archon_report_utility.builder.SummaryBeanBuilder.buildSummaryBean;
import static com.p3solutions.archon_report_utility.builder.TableBuilder.getTableBean;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.*;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.DIVIDER_GREY_COLOR;
import static runner.builder.CardValueBuilder.buildParametersForCard;
import static runner.builder.TableValueBuilder.*;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.p3solutions.archon_report_utility.beans.FooterBean;
import com.p3solutions.archon_report_utility.core.Report;
import com.p3solutions.archon_report_utility.core.ReportBuilder;
import com.p3solutions.archon_report_utility.enums.CardType;
import com.p3solutions.archon_report_utility.enums.DividerType;
import com.p3solutions.archon_report_utility.enums.FontType;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;

import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.services.CommonRunner;

@Slf4j
public class ConsolidatedIngestionValidationRunner implements CommonRunner {
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
                      "src/main/resources/fonts/Roboto-Medium.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent dividerAfterObjective =
          ReportBuilder.addComponent(
              buildDividerInputBean(410L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      String reportDescription =
              "The Consolidated Ingestion Validation Report ensures the accuracy and completeness of data ingested into the system from "
                      + "various sources. It includes validation checks for data integrity, consistency, and alignment with predefined standards. This "
                      + "report helps identify and rectify discrepancies early, ensuring reliable data availability for downstream processes and analytics.";
      ReportComponent fileObjectiveValue =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  reportDescription,
                  OBJECTIVE_FONT_COLOR,
                  10,
                      "src/main/resources/fonts/Roboto-Regular.ttf",
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
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
              buildDividerInputBean(282L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));

      ReportComponent tableLevelMultiCardComponent = ReportBuilder.addComponent( buildCard(
              "ADS_DEMO_CHECK,_003_METADATA_ADS.ADS_CONTENT_FS",
              CardType.MULTIPLE_DETAILS_INFO,
              buildParametersForCard(reportNameConstants),
              new Date().toString(),
              ""));

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
      report.addComponent(tableLevelDetails);
      report.addComponent(tableLevelDetailsDivider);
      report.addComponent(tableLevelMultiCardComponent);
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
