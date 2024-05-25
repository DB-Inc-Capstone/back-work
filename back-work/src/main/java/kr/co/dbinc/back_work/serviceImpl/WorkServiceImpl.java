package kr.co.dbinc.back_work.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import kr.co.dbinc.back_work.mapper.WorkMapper;
import kr.co.dbinc.back_work.model.IssueVO;
import kr.co.dbinc.back_work.model.WorkVO;
import kr.co.dbinc.back_work.model.WorkerVO;
import kr.co.dbinc.back_work.service.WorkService;

@Service
@Primary
@Repository
public class WorkServiceImpl implements WorkService {
   private final SqlSession sqlSession;
   private final RestTemplate restTemplate;
   
   @Autowired
   public WorkServiceImpl(SqlSession ss, RestTemplate rt) {
      this.sqlSession = ss;
      this.restTemplate = rt;
   }

   @Transactional(readOnly = true)
   @Override
   public List<WorkVO> selectWorkList() {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.selectWorkList();
   }
   
   @Transactional(readOnly = true)
   @Override
   public List<IssueVO> selectIssueList() {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.selectIssueList();
   }
   
   @Transactional(readOnly = true)
   @Override
   public WorkVO selectWorkById(int workID) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.selectWorkById(workID);
   }
   
   @Transactional(readOnly = true)
   @Override
   public IssueVO selectIssueById(int issueID) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.selectIssueById(issueID);
   }


   @Transactional
   @Override
   public int insertWork(WorkVO workVO) {
	  
	  // workerID ø‰√ª
	  String wokerIDUrl = "http://ec2-43-203-124-16.ap-northeast-2.compute.amazonaws.com:9001/worker/{workerID}";
	  WorkerVO worker = restTemplate.getForObject(wokerIDUrl, WorkerVO.class,workVO.getWorkerID());
	  
	  //Long workerID = worker.getId();
	
	  workVO.setWorkerID(worker.getId());
	  
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.insertWork(workVO);
   }
   
   @Transactional
   @Override
   public int insertIssue(IssueVO issueVO) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.insertIssue(issueVO);
   }
   
   @Transactional
   @Override
   public int updateWork(WorkVO workVO) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.updateWork(workVO);
   }
   
   @Transactional
   @Override
   public int updateIssue(IssueVO issueVO) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.updateIssue(issueVO);
   }
   
   @Transactional
   @Override
   public int updateWorkState(WorkVO workVO) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.updateWorkState(workVO);
   }
   
   @Transactional
   @Override
   public int updateIssueState(IssueVO issueVO) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.updateIssueState(issueVO);
   }
   
   @Transactional
   @Override
   public int deleteWorkById(int workID) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.deleteWorkById(workID);
   }
   
   @Transactional
   @Override
   public int deleteIssueById(int issueID) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.deleteIssueById(issueID);
   }
   
   
   
   
}