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
import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.services.CommonRunner;

@Slf4j
public class PurgeReportRunner implements CommonRunner {

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
              buildDividerInputBean(628L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      String reportDescription =
          "The purge report use case involves identifying and removing outdated or irrelevant data from the system to maintain database "
              + "efficiency and integrity. It includes setting criteria for data to be purged, executing the purge process, and verifying successful "
              + "deletion. This ensures optimized performance and compliance with data retention policies.";
      ReportComponent fileObjectiveValue =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  reportDescription,
                  OBJECTIVE_FONT_COLOR,
                  10,
                  FontType.HELVETICA.getFontName(),
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent approvalDetails =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "Approval Details",
                  HEADER_FONT_COLOR,
                  13,
                  FontType.HELVETICA_BOLD.getFontName(),
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent approvalDetailsDivider =
          ReportBuilder.addComponent(
              buildDividerInputBean(540L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      ReportComponent approvalDetailsTableComponent =
          ReportBuilder.addComponent(
              getTableBean(approvalDetailTableParameters(), TableType.SUMMARY));
      ReportComponent recordGroupDetails =
          ReportBuilder.addComponent(
              buildSummaryBean(
                  "Record / Group Details",
                  HEADER_FONT_COLOR,
                  13,
                  FontType.HELVETICA_BOLD.getFontName(),
                  TextAlignment.LEFT,
                  VerticalAlignment.TOP));
      ReportComponent recordGroupDetailDivider =
          ReportBuilder.addComponent(
              buildDividerInputBean(430L, 1L, DIVIDER_GREY_COLOR, 1, DividerType.CONTENT));
      ReportComponent recordGroupTableGridComponent =
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
      report.addComponent(jobTableComponent);
      report.addComponent(objectiveHeaderComponent);
      report.addComponent(dividerAfterObjective);
      report.addComponent(fileObjectiveValue);
      report.addComponent(approvalDetails);
      report.addComponent(approvalDetailsDivider);
      report.addComponent(approvalDetailsTableComponent);
      report.addComponent(recordGroupDetails);
      report.addComponent(recordGroupDetailDivider);
      report.addComponent(recordGroupTableGridComponent);
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
