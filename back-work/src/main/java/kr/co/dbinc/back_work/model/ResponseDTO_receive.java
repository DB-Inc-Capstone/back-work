package kr.co.dbinc.back_work.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO_receive {
	public String message;
	public WorkerDTO worker;
	public List<WorkerDTO> workers;
}