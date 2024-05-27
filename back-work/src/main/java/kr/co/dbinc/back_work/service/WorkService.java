package kr.co.dbinc.back_work.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.dbinc.back_work.model.IssueVO;
import kr.co.dbinc.back_work.model.WorkVO;
import kr.co.dbinc.back_work.model.Work_RelVO;

@Service
public interface WorkService {
   List<WorkVO> selectWorkList();
   
   List<IssueVO> selectIssueList();
   
   WorkVO selectWorkById(int workID);
   
   List<WorkVO> selectWork_Rel_List();

   IssueVO selectIssueById(int issueID);
   
   int insertWork(WorkVO workVO);
   
   int insertIssue(IssueVO issueVO);
   
   int insertWork_rel(Work_RelVO work_rel);
   
   int updateWork(WorkVO workVO);
   
   int updateIssue(IssueVO issueVO);
   
   int updateIssueState(IssueVO issueVO);
   
   int updateWorkState(WorkVO workVO);
   
   int deleteWorkById(int workID);
   
   int deleteIssueById(int issueID);
   

   

}