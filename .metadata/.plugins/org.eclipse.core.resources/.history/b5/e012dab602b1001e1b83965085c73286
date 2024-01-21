package kr.co.dbinc.back_work.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dbinc.back_work.model.ResponseDTO;
import kr.co.dbinc.back_work.model.WorkVO;
import kr.co.dbinc.back_work.service.WorkService;

@RestController
@RequestMapping("/work")
public class WorkController {

	@Autowired
	private WorkService workService;

	public WorkController(WorkService ws) {
		this.workService = ws;
	}

	
	@PostMapping
	public ResponseEntity<ResponseDTO> createWork(@Valid @RequestBody WorkVO workVO) {
		ResponseDTO response = new ResponseDTO();

		// 작업상태초기화 진행중
		workVO.setWorkState(1);

		Integer result = workService.insertWork(workVO);

		if (result != 0) {
			response.success = true;
			response.message = "작업이 생성되었습니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.success = false;
			response.message = "서버 오류로 작업이 생성되지 않았습니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

}
