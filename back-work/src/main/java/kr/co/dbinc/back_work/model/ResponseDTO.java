package kr.co.dbinc.back_work.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

	public List<WorkVO> workinfo;
	public String message;
	public boolean success;
}
