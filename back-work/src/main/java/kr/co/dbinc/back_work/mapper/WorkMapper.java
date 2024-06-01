package kr.co.dbinc.back_work.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.dbinc.back_work.model.IssueVO;
import kr.co.dbinc.back_work.model.WorkVO;
import kr.co.dbinc.back_work.model.Work_RelVO;


@Repository
public interface WorkMapper {
   List<WorkVO> selectWorkList();
   
   WorkVO selectWorkById(int workID);
   
   List<WorkVO> selectWork_Rel_List();
   
   List<IssueVO> selectIssueList();
   
   IssueVO selectIssueById(int issueID);

   int insertWork(WorkVO workVO);
   
   int insertIssue(IssueVO issueVO);
   
   int insertWork_rel(Work_RelVO work_rel);
   
   int updateIssue(IssueVO issueVO);
   
   int updateIssueState(IssueVO issueVO);
   
   int updateWork(WorkVO workVO);
   
   int updateWorkState(WorkVO workVO);
   
   int deleteWorkById(int workID);
   
   int deleteAllWork();
   
   int deleteIssueById(int issueID);
   
   int deleteAllIssue();

}