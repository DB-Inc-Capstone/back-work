package kr.co.dbinc.back_work.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dbinc.back_work.mapper.WorkMapper;
import kr.co.dbinc.back_work.model.WorkVO;
import kr.co.dbinc.back_work.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {
	private final SqlSession sqlSession;

	public WorkServiceImpl(SqlSession ss) {
		this.sqlSession = ss;
	}

	@Transactional(readOnly = true)
	@Override
	public List<WorkVO> selectWorkList() {
		WorkMapper workmapper = this.sqlSession.getMapper(WorkMapper.class);
		return workmapper.selectWorkList();
	}

	@Transactional
	@Override
	public int insertWork(WorkVO workVO) {
		WorkMapper workmapper = this.sqlSession.getMapper(WorkMapper.class);
		return workmapper.insertWork(workVO);
	}
}
