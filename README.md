## back-work

- MSA 서비스 구현을 위한 작업 관리 백엔드 서버입니다.
  
- 개발 환경
  - JDK
  - Eclipse IDE
  - Spring
  - PostgreSQL
  - Mybatis

  - 개발 기획
    - API 명세서
   
      
      |기능|Method|PATH|
      |------|---|---|
      |작업 생성|POST|/work/|
      |작업 조회|GET|/work/:workID|
      |작업 삭제|DELETE|/work/:workId|
      |작업 현황 수정|PUT|/work/:workId?action=status|
      |작업 내용 수정|PUT|/work/:workId?action=content|
      |하위 작업 추가|POST|/work/:workID/sub|
      |작업자 할당|POST|/work/:workId/worker/|
      |작업자 제외|DELETE|/work/:workId/worker|
      |이슈 생성|POST|/work/:workId/issue/|
      |이슈 수정|PUT|/work/:workId/issue/:issueId|
      |이슈 삭제|DELETE|/work/:workId/issue/:issueId|
      |이슈 완료|PUT|/work/:workId/issue/:issueId?action=complete|
      |이슈 조회|GET|/work/:workId/issue/:issueId|

    - 데이터베이스 ERD
