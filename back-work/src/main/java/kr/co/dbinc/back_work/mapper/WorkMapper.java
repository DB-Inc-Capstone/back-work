package kr.co.dbinc.back_work.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.dbinc.back_work.model.WorkVO;

@Repository
public interface WorkMapper {
	List<WorkVO> selectWorkList();

	int insertWork(WorkVO workVO);

}
