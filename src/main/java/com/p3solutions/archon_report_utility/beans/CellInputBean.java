package com.p3solutions.archon_report_utility.beans;

import com.p3solutions.archon_report_utility.CellConfigBean;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CellInputBean {

  @Builder.Default private CellConfigBean cellConfigBean = new CellConfigBean();

  @Builder.Default private AlignmentBean alignmentBean = new AlignmentBean();

  @Builder.Default private BorderBean borderBean = new BorderBean();

  @Builder.Default private MarginBean marginBean = new MarginBean();

  @Builder.Default private FontConfigBean fontConfigBean = new FontConfigBean();

  @Builder.Default private PaddingInputBean paddingInputBean = new PaddingInputBean();
}
