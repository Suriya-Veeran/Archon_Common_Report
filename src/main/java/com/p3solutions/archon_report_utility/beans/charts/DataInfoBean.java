package com.p3solutions.archon_report_utility.beans.charts;

import com.p3solutions.archon_report_utility.enums.FormatTypes;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataInfoBean {
    @Builder.Default
    private String name = "Default";
    @Builder.Default
    private int value = 0;
    @Builder.Default
    private FormatTypes format = FormatTypes.KB;
    @Builder.Default
    private ItemStyle itemStyle = new ItemStyle();
}
