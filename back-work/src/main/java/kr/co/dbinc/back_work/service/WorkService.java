package kr.co.dbinc.back_work.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.dbinc.back_work.model.IssueVO;
import kr.co.dbinc.back_work.model.WorkVO;

@Service
public interface WorkService {
   List<WorkVO> selectWorkList();
   
   List<IssueVO> selectIssueList();
   
   WorkVO selectWorkById(int workID);

   IssueVO selectIssueById(int issueID);
   
   int insertWork(WorkVO workVO);
   
   int insertIssue(IssueVO issueVO);
   
   int updateWork(WorkVO workVO);
   
   int updateIssue(IssueVO issueVO);
   
   int updateIssueState(IssueVO issueVO);
   
   int updateWorkState(WorkVO workVO);
   
   int deleteWorkById(int workID);
   
   int deleteIssueById(int issueID);
   

   

}