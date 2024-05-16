package kr.co.dbinc.back_work.controller;

import javax.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

   
   @PostMapping("/create")
   public ResponseEntity<ResponseDTO> createWork(@Valid @RequestBody WorkVO workVO) {
      ResponseDTO response = new ResponseDTO();

      // 작업상태초기화 진행중
      workVO.setWorkState(1);
      
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
   
   
   @PostMapping("/{workID}/sub")
   public ResponseEntity<ResponseDTO> createSubWork(@PathVariable int workID, @Valid @RequestBody WorkVO workVO){
	   ResponseDTO response = new ResponseDTO();
	   
	   WorkVO existwork = workService.selectWorkById(workID);
	   
	   if (existwork != null) {
		   workVO.setWorkState(1);
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
    
   
   @GetMapping("/{workID}")
   public ResponseEntity<ResponseDTO> searchWork(@PathVariable int workID) {
      ResponseDTO response = new ResponseDTO();
      
      WorkVO work = workService.selectWorkById(workID);
      
      if(work != null) {
         response.success = true;
         response.message = "조회결과입니다.";
         response.workinfo = work;
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
      else {
         response.success = false;
         response.message = "해당 작업이 존재하지 않습니다.";
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
             response.message = "작업 수정이 완료되었습니다.";
             return new ResponseEntity<>(response, HttpStatus.OK);
          }
          else {
             response.success = false;
             response.message = "서버 오류로 수정이 되지 않았습니다.";
             return new ResponseEntity<>(response, HttpStatus.OK);
          }
      }
      else {
    	  response.success = false;
    	  response.message = "해당 작업이 존재하지 않습니다.";
    	  return new ResponseEntity<>(response, HttpStatus.OK);
      }
      
   }
   
   @PatchMapping("/{workID}")
   public ResponseEntity<ResponseDTO> updateWorkState(@PathVariable int workID, @Valid @RequestBody WorkVO workVO){
      ResponseDTO response = new ResponseDTO();
      
      WorkVO existwork = workService.selectWorkById(workID);
      
      if(existwork != null) {
    	  workVO.setWorkID(workID);
          int result = workService.updateWorkState(workVO);
          
          if(result != 0) {
        	  response.success = true;
        	  response.message = "상태변경이 완료되었습니다.";
        	  return new ResponseEntity<>(response, HttpStatus.OK);
          }
          else {
        	  response.success = false;
        	  response.message = "서버 오류로 수정이 되지 않았습니다.";
        	  return new ResponseEntity<>(response, HttpStatus.OK);
          }
      }
      else {
    	  response.success = false;
    	  response.message = "해당 작업이 존재하지 않습니다.";
    	  return new ResponseEntity<>(response, HttpStatus.OK);
      }   
   }
   
   @DeleteMapping("/{workID}")
   public ResponseEntity<ResponseDTO> deleteWork(@PathVariable int workID){
      ResponseDTO response = new ResponseDTO();
      
      WorkVO work = workService.selectWorkById(workID);
      int result = workService.deleteWorkById(workID);
      
      if(result != 0) {
         response.success = true;
         response.message = "작업삭제가 완료되었습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
      else if(work == null) {
         response.success = true;
         response.message = "해당 작업이 존재하지 않습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
      else {
         response.success = false;
         response.message = "서버 오류로 수정이 되지 않았습니다.";
         return new ResponseEntity<>(response, HttpStatus.OK);
      }
   }
   

}