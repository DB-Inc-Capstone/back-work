package kr.co.dbinc.back_work.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import kr.co.dbinc.back_work.model.Work_RelVO;
import kr.co.dbinc.back_work.service.WorkService;


@RequestMapping("/work")
@RestController
public class WorkController {


   private final WorkService workService;
   
   private final Logger logger = LogManager.getFormatterLogger(this.getClass());

   public WorkController(WorkService ws) {
      this.workService = ws;
   }

   
   @PostMapping
   public ResponseEntity<ResponseDTO> createWork(@Valid @RequestBody WorkVO workVO) {
	   
      logger.info("createWork 호출");

      ResponseDTO response = new ResponseDTO();
      
      int result = workService.insertWork(workVO);
      
     // Work_RelVO work_rel = null;
     // work_rel.setWorkerID(workVO.getWorkerID());

      if (result != 0) {
    	// workService.insertWork_rel(work_rel);
         response.success = true;
         response.message = "작업이 생성되었습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
         response.success = false;
         response.message = "서버 오류로 작업이 생성되지 않았습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
   }
   
   /**
    * 테스트 용도의 work 및 issue 생성 진행
    * @throws ParseException 
    */
   @GetMapping("/test")
   public ResponseEntity<ResponseDTO> createTestWorkAndIssue() throws ParseException {
	   
      logger.info("createTestWorkAndIssue 호출");
      
      // 초기화 후 진행
      resetTestIssue(); 
      resetTestWork();
      
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
      Integer pk;
      List<Integer> list_of_pk = new ArrayList<Integer>();
      
      /**
       * 작업 추가하기
       */
      WorkVO workVO;
      
      workVO = WorkVO.builder()
    		     .workID(null)
    		  	 .workTitle("API 문서 작성")
    		  	 .workContent("작업을 새로 추가하는 API 문서 작성")
    		  	 .workState(2)
    		  	 .startDate(formatter.parse("2024-05-20"))
    		  	 .finishDate(formatter.parse("2024-05-25"))
    		  	 .build();
      pk = workService.insertWork(workVO);
      list_of_pk.add(pk);
      
      workVO = WorkVO.builder()
		  		 .workTitle("데이터베이스 스키마 설계")
		  		 .workContent("웹 애플리케이션을 위한 데이터베이스 스키마 설계")
		  		 .workState(0)
		  		 .startDate(formatter.parse("2024-06-10"))
   		  	     .finishDate(formatter.parse("2024-06-15"))
		  		 .build();
      pk = workService.insertWork(workVO);
      list_of_pk.add(pk);
      
      workVO = WorkVO.builder()
		  		 .workTitle("REST API 엔드포인트 구현")
		  		 .workContent("사용자 관리 및 인증을 위한 REST API 엔드포인트 구현")
		  		 .workState(0)
		  		 .startDate(formatter.parse("2024-06-01"))
   		  	     .finishDate(formatter.parse("2024-09-01"))
		  		 .build();
      pk = workService.insertWork(workVO);
      list_of_pk.add(pk);
      
      workVO = WorkVO.builder()
		  		 .workTitle("프론트엔드와 백엔드 연동")
		  		 .workContent("프론트엔드 애플리케이션과 백엔드 API 서버 간의 연동 작업")
		  		 .workState(2)
		  		 .startDate(formatter.parse("2024-05-15"))
   		  	     .finishDate(formatter.parse("2024-05-20"))
		  		 .build();
      pk = workService.insertWork(workVO);
      list_of_pk.add(pk);
      
      workVO = WorkVO.builder()
		  		 .workTitle("사용자 인증 및 권한 관리")
		  		 .workContent("JWT 기반 사용자 인증 및 권한 관리 시스템 구현")
		  		 .workState(1)
		  		 .startDate(formatter.parse("2024-05-20"))
		  	     .finishDate(formatter.parse("2024-06-15"))
		  		 .build();
      pk = workService.insertWork(workVO);
      list_of_pk.add(pk);
      
      workVO = WorkVO.builder()
		  		 .workTitle("AWS 인프라 구축")
		  		 .workContent("웹 애플리케이션을 위한 AWS 클라우드 인프라 구축 및 설정")
		  		 .workState(0)
		  		 .startDate(formatter.parse("2024-06-01"))
		  	     .finishDate(formatter.parse("2024-06-23"))
		  		 .build();
      pk = workService.insertWork(workVO);
      list_of_pk.add(pk);
      
      workVO = WorkVO.builder()
		  		 .workTitle("CI/CD 파이프라인 설정")
		  		 .workContent("Jenkins를 사용한 지속적 통합 및 배포 파이프라인 설정")
		  		 .workState(0)
		  		 .startDate(formatter.parse("2024-06-10"))
		  	     .finishDate(formatter.parse("2024-06-20"))
		  		 .build();
      pk = workService.insertWork(workVO);
      list_of_pk.add(pk);
      
      workVO = WorkVO.builder()
		  		 .workTitle("서버 성능 최적화")
		  		 .workContent("서버 리소스 사용량 최적화를 위한 튜닝 작업")
		  		 .workState(2)
		  		 .startDate(formatter.parse("2024-05-15"))
		  	     .finishDate(formatter.parse("2024-05-25"))
		  		 .build();
      pk = workService.insertWork(workVO);
      list_of_pk.add(pk);
      
      /**
       * 이슈 추가하기
       */
      IssueVO issueVO;
      
      issueVO = IssueVO.builder()
    		  	.issueTitle("API 문서 작성")
    		  	.issueContent("작업을 새로 추가하는 API 문서 작성")
    		  	.issueState(1)
    		  	.workID(list_of_pk.get(0))
    		  	.build();
      createIssue(issueVO);
      
      issueVO = IssueVO.builder()
	  		   .issueTitle("DB 스키마 설계 오류")
	  		   .issueContent("사용자 테이블의 중복된 필드로 인해 데이터가 제대로 저장되지 않음")
	  		   .issueState(2)
	  		   .workID(list_of_pk.get(1))
	  		   .build();
      createIssue(issueVO);
      
      issueVO = IssueVO.builder()
	  		   .issueTitle("REST API 인증 문제")
	  		   .issueContent("사용자 관리 및 인증을 위한 REST API가 일부 사용자에 대해 인증 실패함")
	  		   .issueState(0)
	  		   .workID(list_of_pk.get(2))
	  		   .build();
      createIssue(issueVO);
      
      issueVO = IssueVO.builder()
	  		   .issueTitle("프론트엔드와 백엔드 연동 시 CORS 오류")
	  		   .issueContent("프론트엔드 애플리케이션과 백엔드 API 서버 간의 연동 시 CORS 정책 오류 발생")
	  		   .issueState(2)
	  		   .workID(list_of_pk.get(3))
	  		   .build();
      createIssue(issueVO);
      
      issueVO = IssueVO.builder()
	  		   .issueTitle("JWT 토큰 만료 문제")
	  		   .issueContent("JWT 기반 사용자 인증 시스템에서 토큰 만료 시간이 잘못 설정됨")
	  		   .issueState(0)
	  		   .workID(list_of_pk.get(4))
	  		   .build();
      createIssue(issueVO);
      
      issueVO = IssueVO.builder()
	  		   .issueTitle("AWS 인프라 구축 중 권한 오류")
	  		   .issueContent("AWS 클라우드 인프라 구축 시 S3 버킷 접근 권한이 제대로 설정되지 않음")
	  		   .issueState(0)
	  		   .workID(list_of_pk.get(5))
	  		   .build();
      createIssue(issueVO);
      
      issueVO = IssueVO.builder()
	  		   .issueTitle("CI/CD 파이프라인 빌드 실패")
	  		   .issueContent("Jenkins를 사용한 지속적 통합 및 배포 파이프라인 설정 중 빌드 오류 발생")
	  		   .issueState(1)
	  		   .workID(list_of_pk.get(6))
	  		   .build();
      createIssue(issueVO);
      
      issueVO = IssueVO.builder()
	  		   .issueTitle("서버 성능 최적화 중 리소스 누수")
	  		   .issueContent("서버 리소스 사용량 최적화를 위한 튜닝 작업 중 메모리 누수 현상 발생")
	  		   .issueState(0)
	  		   .workID(list_of_pk.get(7))
	  		   .build();
      createIssue(issueVO);
     
      /**
       * 결과 반환
       */
      ResponseDTO response = new ResponseDTO();
      response.success = true;
      response.message = "테스트 용도의 work-set 과 issue-set 을 생성하였습니다.";
      
      return new ResponseEntity<>(response, HttpStatus.OK);
   }
   
   
   @PostMapping("/issue")
   	public ResponseEntity<ResponseDTO> createIssue(@Valid @RequestBody IssueVO issueVO) {
	   
	   logger.info("createIssue 호출");
	   
	   ResponseDTO response = new ResponseDTO();
	   int workID = issueVO.getWorkID();
	   WorkVO existwork = workService.selectWorkById(workID);
	   
	   if (existwork != null) {
		   
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
	   
	   logger.info("createSubWork 호출");
	   
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
	   
	   logger.info("getWorks 호출");
	   
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
   public ResponseEntity<ResponseDTO> getIssues() {
	   
	   logger.info("getIssues 호출");
	   
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
	   
	  logger.info("searchWork 호출");
	   
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
   
   @GetMapping("/worker")
   public ResponseEntity<ResponseDTO> searchWork_Rel_List() {
	   ResponseDTO response = new ResponseDTO();
	   
	   List<WorkVO> works_rel = workService.selectWork_Rel_List();
	   
	   if(works_rel != null) {
		   response.success = true;
		   response.message = "작업 및 작업자 명단입니다.";
		   response.workinfos = works_rel;
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   } else {
		   response.success = false;
		   response.message = "작업 조회에 실패하였습니다.";
		   return new ResponseEntity<>(response, HttpStatus.OK);
	   }
   }	   
	   
   
   
   @GetMapping("/{workID}/issue/{issueID}")
   public ResponseEntity<ResponseDTO> searchIssue(@PathVariable int workID,@PathVariable int issueID) {
	   
	   logger.info("searchIssue 호출");
	   
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
	   
	  logger.info("updateWork 호출");
	   
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
	
	   logger.info("updateIssue 호출");
	   
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
	   
	  logger.info("updateWorkState 호출");
	   
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
	   
	   logger.info("updateIssueState 호출");
	   
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
	   
	  logger.info("deleteWork 호출");
	   
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

	   logger.info("deleteIssue 호출");
	   
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
    
    
 
    /**
     *  테스트 용도의 work 초기화 진행
     *
     */
    @GetMapping("/reset")
    public ResponseEntity<ResponseDTO> resetTestWork() {
	   
	    logger.info("resetTestWork 호출");
	   
	    workService.deleteAllWork();
 	   
	    ResponseDTO response = new ResponseDTO();
	    response.success = true;
	    response.message = "모든 work-set 을 초기화하였습니다.";
	      
	    return new ResponseEntity<>(response, HttpStatus.OK);
    }
   
    /**
     * 테스트 용도의 work 초기화 진행
     */
    @GetMapping("/issue/reset")
    public ResponseEntity<ResponseDTO> resetTestIssue() {
	   
	    logger.info("resetTestIssue 호출");
	   
	    workService.deleteAllIssue();
	   
	    ResponseDTO response = new ResponseDTO();
	    response.success = true;
	    response.message = "모든 issue-set 을 초기화하였습니다.";
	      
	    return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}