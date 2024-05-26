package kr.co.dbinc.back_work.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IssueVO {

	private Integer issueID;
	private Integer workID;
	private String issueTitle;
	private String issueContent;
	private Integer issueState;	
	private Long workerID;
}
