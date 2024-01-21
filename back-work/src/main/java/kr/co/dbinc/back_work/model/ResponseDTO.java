package kr.co.dbinc.back_work.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

	public List<WorkVO> workinfo;
	public String message;
	public boolean success;
}
