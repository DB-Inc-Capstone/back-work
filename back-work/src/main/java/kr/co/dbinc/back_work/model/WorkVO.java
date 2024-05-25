package kr.co.dbinc.back_work.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkVO {

	private Integer workID;
	private Integer parentID;
	private String workTitle;
	private String workContent;
	private Integer workState;
	private Long workerID;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	private Date finishDate;	
}
