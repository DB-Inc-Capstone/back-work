package kr.co.dbinc.back_work.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.dbinc.back_work.model.WorkVO;

@Service
public interface WorkService {
	List<WorkVO> selectWorkList();

	int insertWork(WorkVO workVO);

}
