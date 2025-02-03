package com.p3solutions.archon_report_utility.components;

import com.itextpdf.layout.Document;
import com.p3solutions.archon_report_utility.beans.CardBean;
import com.p3solutions.archon_report_utility.interfaces.ReportComponent;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardComponent implements ReportComponent {

  private CardBean inputBean;

  @Override
  public void render(Document document) throws IOException {

  }
}
