package kr.co.dbinc.back_work.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import kr.co.dbinc.back_work.mapper.WorkMapper;
import kr.co.dbinc.back_work.model.IssueVO;
import kr.co.dbinc.back_work.model.ResponseDTO;
import kr.co.dbinc.back_work.model.ResponseDTO_receive;
import kr.co.dbinc.back_work.model.WorkVO;
import kr.co.dbinc.back_work.model.Work_RelVO;
import kr.co.dbinc.back_work.model.WorkerDTO;
import kr.co.dbinc.back_work.service.WorkService;

@Service
@Primary
@Repository
public class WorkServiceImpl implements WorkService {
   private final SqlSession sqlSession;
   private final RestTemplate restTemplate;
   
   @Value("${API_GATEWAY_URL}")
   private String url;
   
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
   public List<WorkVO> selectWork_Rel_List() {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.selectWork_Rel_List();
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
	  
	  // workerID
	  String wokerIDUrl = url + "/worker/{workerID}";
	  ResponseDTO_receive response = restTemplate.getForObject(wokerIDUrl, ResponseDTO_receive.class,workVO.getWorkerID());
	  
	  WorkerDTO worker = response != null ? response.getWorker() : null;
	  Long workerID = worker != null ? worker.getId() : null;
	  workVO.setWorkerID(workerID);

      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.insertWork(workVO);
   }
   
   @Transactional
   @Override
   public int insertIssue(IssueVO issueVO) {
	// workerID
	   String wokerIDUrl = url + "/worker/{workerID}";
	   ResponseDTO_receive response = restTemplate.getForObject(wokerIDUrl, ResponseDTO_receive.class,issueVO.getWorkerID());
		  
	   WorkerDTO worker = response != null ? response.getWorker() : null;
	   Long workerID = worker != null ? worker.getId() : null;
	   issueVO.setWorkerID(workerID);

	   WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
	   return workmapper.insertIssue(issueVO);
   }
   
   @Transactional
   @Override
   public int insertWork_rel(Work_RelVO work_rel) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.insertWork_rel(work_rel);
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
   public int deleteAllWork() {
	   WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
	   return workmapper.deleteAllWork();
   }
   
   @Transactional
   @Override
   public int deleteIssueById(int issueID) {
      WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
      return workmapper.deleteIssueById(issueID);
   }
   
   @Transactional
   @Override
   public int deleteAllIssue() {
	   WorkMapper workmapper = sqlSession.getMapper(WorkMapper.class);
	   return workmapper.deleteAllIssue();
   }
   
   
}