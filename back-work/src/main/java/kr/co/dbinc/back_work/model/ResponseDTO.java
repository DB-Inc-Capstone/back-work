package kr.co.dbinc.back_work.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

	public String message;
	public boolean success;
	public WorkVO workinfo;
}
