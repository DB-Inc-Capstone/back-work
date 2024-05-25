package kr.co.dbinc.back_work.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dbinc.back_work.model.IssueVO;
import kr.co.dbinc.back_work.model.ResponseDTO;
import kr.co.dbinc.back_work.model.WorkVO;
import kr.co.dbinc.back_work.service.WorkService;


@RequestMapping("/work")
@RestController
public class WorkController {


   private final WorkService workService;

   public WorkController(WorkService ws) {
      this.workService = ws;
   }

   
   @PostMapping
   public ResponseEntity<ResponseDTO> createWork(@Valid @RequestBody WorkVO workVO) {
      ResponseDTO response = new ResponseDTO();

      // 작업 상태 초기화
      //workVO.setWorkState(1);
      
      int result = workService.insertWork(workVO);

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
   
   @PostMapping("/issue")
   	public ResponseEntity<ResponseDTO> createIssue(@Valid @RequestBody IssueVO issueVO) {
	   ResponseDTO response = new ResponseDTO();
	   int workID = issueVO.getWorkID();
	   WorkVO existwork = workService.selectWorkById(workID);
	   
	   if (existwork != null) {
		   //issueVO.setIssueState(1);
		   //issueVO.setWorkID(workID);
		   
		   int result = workService.insertIssue(issueVO);
		   
		   if (result != 0) {
			   response.success = true;
		       response.message = "이슈가 생성되었습니다.";
		       return new ResponseEntity<>(response, HttpStatus.OK);
		   } 
		   else {
		       response.success = false;
		       response.message = "서버 오류로 이슈가 생성되지 않았습니다.";
		       return new ResponseEntity<>(response, HttpStatus.OK);
		   }
	   }
	   else {
		   response.success = false;
		   response.message = "상위 작업이 존재하지 않습니다.";
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   }
   }
	   
	   
   @PostMapping("/{workID}/sub")
   public ResponseEntity<ResponseDTO> createSubWork(@PathVariable int workID, @Valid @RequestBody WorkVO workVO){
	   ResponseDTO response = new ResponseDTO();
	   
	   WorkVO existwork = workService.selectWorkById(workID);
	   
	   if (existwork != null) {
		   //workVO.setWorkState(1);
		   workVO.setParentID(workID);
		   
		   int result = workService.insertWork(workVO);
		   
		   if (result != 0) {
			   response.success = true;
		       response.message = "작업이 생성되었습니다.";
		       return new ResponseEntity<>(response, HttpStatus.OK);
		   } 
		   else {
		       response.success = false;
		       response.message = "서버 오류로 작업이 생성되지 않았습니다.";
		       return new ResponseEntity<>(response, HttpStatus.OK);
		   }
	   }
	   else {
		   response.success = false;
		   response.message = "상위 작업이 존재하지 않습니다.";
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   }
   }

   @GetMapping
   public ResponseEntity<ResponseDTO> getWorks() {
	   ResponseDTO response = new ResponseDTO();
	   
	   List<WorkVO> works = workService.selectWorkList();
	   
	   if(works != null) {
		   response.success = true;
		   response.message = "조회 결과입니다.";
		   response.workinfos = works;
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   } else {
		   response.success = false;
		   response.message = "작업 조회에 실패하였습니다.";
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   }
   }
   
   @GetMapping("/issue")
   public ResponseEntity<ResponseDTO> getIssue() {
	   ResponseDTO response = new ResponseDTO();
	   
	   List<IssueVO> issues =  workService.selectIssueList();

	   if(issues != null) {
		   response.success = true;
		   response.message = "조회 결과입니다.";
		   response.issueinfos = issues;
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   } else {
		   response.success = false;
		   response.message = "작업 조회에 실패하였습니다.";
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   }
	   
   }
   
   @GetMapping("/{workID}")
   public ResponseEntity<ResponseDTO> searchWork(@PathVariable int workID) {
      ResponseDTO response = new ResponseDTO();
      
      WorkVO work = workService.selectWorkById(workID);
      
      if(work != null) {
         response.success = true;
         response.message = "조회 결과입니다.";
         response.workinfo = work;
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
      else {
         response.success = false;
         response.message = "해당 작업이 존재하지 않습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
   }
   
   @GetMapping("/{workID}/issue/{issueID}")
   public ResponseEntity<ResponseDTO> searchIssue(@PathVariable int workID,@PathVariable int issueID) {
	   ResponseDTO response = new ResponseDTO();
	   
	   WorkVO existwork = workService.selectWorkById(workID);
	   
	   if(existwork != null) {
		   IssueVO issue = workService.selectIssueById(issueID);
		   
		   if(issue != null) {
			   response.success = true;
			   response.message = "조회 결과입니다.";
			   response.workinfo = existwork;
			   response.issueinfo = issue;
			   return new ResponseEntity<>(response, HttpStatus.OK);
		   }
		   else {
			   response.success = false;
			   response.message = "해당 이슈가 존재하지 않습니다.";
			   return new ResponseEntity<>(response, HttpStatus.OK);
		   }
	   }
	   else {
		   response.success = false;
		   response.message = "상위 작업이 존재하지 않습니다.";
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   }
   }
   
   
   
   
   @PutMapping("/{workID}")
   public ResponseEntity<ResponseDTO> updateWork(@PathVariable int workID, @Valid @RequestBody WorkVO workVO) {
      ResponseDTO response = new ResponseDTO();
      
      WorkVO existwork = workService.selectWorkById(workID);
      
      if (existwork != null) {
    	  workVO.setWorkID(workID);
          int result = workService.updateWork(workVO);
          
          if(result != 0) {
             response.success = true;
             response.message = "작업이 수정되었습니다.";
             return new ResponseEntity<>(response, HttpStatus.OK);
          }
          else {
             response.success = false;
             response.message = "서버 오류로 작업이 수정되지 않았습니다.";
             return new ResponseEntity<>(response, HttpStatus.OK);
          }
      }
      else {
    	  response.success = false;
    	  response.message = "상위 작업이 존재하지 않습니다.";
    	  return new ResponseEntity<>(response, HttpStatus.OK);
      }
   }
   
   @PutMapping("/issue/{issueID}")
   public ResponseEntity<ResponseDTO> updateIssue(@PathVariable int issueID, @RequestBody IssueVO issueVO) {
	   ResponseDTO response = new ResponseDTO();
	   
	   int workID = issueVO.getWorkID();
	   WorkVO existwork = workService.selectWorkById(workID);
	      
	   if (existwork != null) {
		   	IssueVO existissue = workService.selectIssueById(issueID);
		   if(existissue != null) {
			   //issueVO.setWorkID(workID);
			   //issueVO.setIssueID(issueID);
		       int result = workService.updateIssue(issueVO);
		       
		       if(result != 0) {
		    	   response.success = true;
			       response.message = "이슈가 수정되었습니다.";
			       return new ResponseEntity<>(response, HttpStatus.OK);
		       }
		       else {
		    	   response.success = false;
		    	   response.message = "서버 오류로 이슈가 수정되지 않았습니다.";
		    	   return new ResponseEntity<>(response, HttpStatus.OK);
		       }
		   }
		   else {
			   response.success = false;
			   response.message = "해당 이슈가 존재하지 않습니다.";
			   return new ResponseEntity<>(response, HttpStatus.OK);
		   }
	   }
	   else {
		   	response.success = false;
		   	response.message = "해당 작업이 존재하지 않습니다.";
		   	return new ResponseEntity<>(response, HttpStatus.OK);
	   }
   }
   /*
   @PatchMapping("/{workID}")
   public ResponseEntity<ResponseDTO> updateWorkState(@PathVariable int workID, @Valid @RequestBody WorkVO workVO){
      ResponseDTO response = new ResponseDTO();
      
      WorkVO existwork = workService.selectWorkById(workID);
      
      if(existwork != null) {
    	  workVO.setWorkID(workID);
          int result = workService.updateWorkState(workVO);
          
          if(result != 0) {
        	  response.success = true;
        	  response.message = "상태 변경을 성공하였습니다.";
        	  return new ResponseEntity<>(response, HttpStatus.OK);
          }
          else {
        	  response.success = false;
        	  response.message = "서버 오류로 상태를 변경하지 못하였습니다.";
        	  return new ResponseEntity<>(response, HttpStatus.OK);
          }
      }
      else {
    	  response.success = false;
    	  response.message = "해당 작업이 존재하지 않습니다.";
    	  return new ResponseEntity<>(response, HttpStatus.OK);
      }   
   }
   
   @PatchMapping("/{workID}/issue/{issueID}")
   public ResponseEntity<ResponseDTO> updateIssueState(@PathVariable int workID, @PathVariable int issueID, @Valid @RequestBody IssueVO issueVO) {
	   ResponseDTO response = new ResponseDTO();
	   WorkVO existwork = workService.selectWorkById(workID);
	      
	   if (existwork != null) {
		   	IssueVO existissue = workService.selectIssueById(issueID);
		   if(existissue != null) {
			
		       int result = workService.updateIssueState(issueVO);
		       
		       if(result != 0) {
		    	   response.success = true;
			       response.message = "상태가 수정되었습니다.";
			       return new ResponseEntity<>(response, HttpStatus.OK);
		       }
		       else {
		    	   response.success = false;
		    	   response.message = "서버 오류로 상태가 변경되지 않았습니다.";
		    	   return new ResponseEntity<>(response, HttpStatus.OK);
		       }
		   }
		   else {
			   response.success = false;
			   response.message = "해당 이슈가 존재하지 않습니다.";
			   return new ResponseEntity<>(response, HttpStatus.OK);
		   }
	   }
	   else {
	    	  response.success = false;
	    	  response.message = "해당 작업이 존재하지 않습니다.";
	    	  return new ResponseEntity<>(response, HttpStatus.OK);
	      }   
	   
   }
   */
   
   @DeleteMapping("/{workID}")
   public ResponseEntity<ResponseDTO> deleteWork(@PathVariable int workID){
      ResponseDTO response = new ResponseDTO();
      
      WorkVO work = workService.selectWorkById(workID);
      int result = workService.deleteWorkById(workID);
      
      if(result != 0) {
         response.success = true;
         response.message = "작업이 삭제되었습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
      else if(work == null) {
         response.success = false;
         response.message = "해당 작업이 존재하지 않습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
      else {
         response.success = false;
         response.message = "서버 오류로 작업이 삭제되지 않았습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
   }
   
   @DeleteMapping("/{workID}/issue/{issueID}")
   public ResponseEntity<ResponseDTO> deleteIssue(@PathVariable int workID, @PathVariable int issueID) {
	   ResponseDTO response = new ResponseDTO();
	   IssueVO existissue = workService.selectIssueById(issueID);
	   int result = workService.deleteIssueById(issueID);
	      
	   if(result != 0) {
	      response.success = true;
	      response.message = "이슈가 삭제되었습니다.";
	      return new ResponseEntity<>(response, HttpStatus.OK);
	   }
	   else if(existissue == null) {
	      response.success = false;
	      response.message = "해당 이슈가 존재하지 않습니다.";
	      return new ResponseEntity<>(response, HttpStatus.OK);
	   }
	   else {
	      response.success = false;
	      response.message = "서버 오류로 이슈가 삭제되지 않았습니다.";
	      return new ResponseEntity<>(response, HttpStatus.OK);
	   }
	}
   

}