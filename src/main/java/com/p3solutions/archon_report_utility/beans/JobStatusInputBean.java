package com.p3solutions.archon_report_utility.beans;

import com.p3solutions.archon_report_utility.enums.JobStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobStatusInputBean {

  @Builder.Default private JobStatusEnum jobStatus = JobStatusEnum.SUCCESS;

  @Builder.Default private String errorMessage = "Schema Ads is not found";

}
