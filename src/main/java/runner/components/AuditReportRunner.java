package runner.components;

import static com.p3solutions.archon_report_utility.builder.CardBuilder.buildCard;
import static com.p3solutions.archon_report_utility.builder.DividerBeanBuilder.buildDividerInputBean;
import static com.p3solutions.archon_report_utility.builder.HeaderBuilder.getHeaderBean;
import static com.p3solutions.archon_report_utility.builder.TableBuilder.getTableBean;
import static com.p3solutions.archon_report_utility.constants.ColorConstants.*;
import static runner.builder.TableValueBuilder.*;

import com.p3solutions.archon_report_utility.beans.FooterBean;
import com.p3solutions.archon_report_utility.core.Report;
import com.p3solutions.archon_report_utility.core.ReportBuilder;
import com.p3solutions.archon_report_utility.enums.CardType;
import com.p3solutions.archon_report_utility.enums.DividerType;
import com.p3solutions.archon_report_utility.enums.TableType;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import runner.enums.ReportNameConstants;
import runner.services.CommonRunner;

@Slf4j
public class AuditReportRunner implements CommonRunner {

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

      String firstHeaderValue =
          "User sysadmin (sysadmin@ads.com) exported the audit events for date range 2023-11-07 - 2024-06-12 as CSV";
      String firstHeader = " System | Management | Audit | Export";

      String secondHeaderValue =
          "Output of Searches job (Job_name_1717412150060) processed for search "
              + "'PartSupp_Supplier_Lineitem_Search_CustomTemp' under application "
              + "'TST_CUSTOMTEMP_TPCH_DATASET' was downloaded by user sysadmin (sysadmin@ads.com).";

      String secondHeader = "System | Processing | Background Jobs | Download";

      ReportComponent firstSingleCardComponent =
          ReportBuilder.addComponent(
              buildCard(
                  firstHeader,
                  CardType.SINGLE_DETAILS_INFO,
                  null,
                  new Date().toString(),
                  firstHeaderValue));

      ReportComponent secondSingleCardComponent =
          ReportBuilder.addComponent(
              buildCard(
                  secondHeader,
                  CardType.SINGLE_DETAILS_INFO,
                  null,
                  new Date().toString(),
                  secondHeaderValue));

      ReportComponent thirdSingleCardComponent =
          ReportBuilder.addComponent(
              buildCard(
                  firstHeader,
                  CardType.SINGLE_DETAILS_INFO,
                  null,
                  new Date().toString(),
                  firstHeaderValue));

      ReportComponent fourthSingleCardComponent =
          ReportBuilder.addComponent(
              buildCard(
                  secondHeader,
                  CardType.SINGLE_DETAILS_INFO,
                  null,
                  new Date().toString(),
                  secondHeaderValue));

      ReportComponent headerComponent =
          ReportBuilder.addComponent(getHeaderBean(reportNameConstants.getReportName()));
      ReportComponent footerComponent = ReportBuilder.addComponent(new FooterBean());

      report.addComponent(headerComponent);
      report.addComponent(tableComponent);
      report.addComponent(dividerComponent);
      report.addComponent(firstSingleCardComponent);
      report.addComponent(secondSingleCardComponent);
      report.addComponent(thirdSingleCardComponent);
      report.addComponent(fourthSingleCardComponent);
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
