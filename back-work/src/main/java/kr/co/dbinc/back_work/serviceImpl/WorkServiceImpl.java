package kr.co.dbinc.back_work.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dbinc.back_work.mapper.WorkMapper;
import kr.co.dbinc.back_work.model.WorkVO;
import kr.co.dbinc.back_work.service.WorkService;

@Service
@Primary
@Repository
public class WorkServiceImpl implements WorkService {
   private final SqlSession sqlSession;

   public WorkServiceImpl(SqlSession ss) {
      this.sqlSession = ss;
   }

   @Transactional(readOnly = true)
   @Override
   public List<WorkVO> selectWorkList() {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.selectWorkList();
   }
   
   @Transactional(readOnly = true)
   @Override
   public List<WorkVO> selectWorkById(int workID) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.selectWorkById(workID);
   }


   @Transactional
   @Override
   public int insertWork(WorkVO workVO) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.insertWork(workVO);
   }
   
   @Transactional
   @Override
   public int updateWork(WorkVO workVO) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.updateWork(workVO);
   }
   
   @Transactional
   @Override
   public int updateWorkState(WorkVO workVO) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.updateWorkState(workVO);
   }
   
   @Transactional
   @Override
   public int deleteWorkById(int workID) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.deleteWorkById(workID);
   }
   
   
   
   
}