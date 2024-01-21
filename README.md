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
 
      | 구분 | 컬럼 | Domain | NULL / NOT NULL | ETC |
      |------|------|--------|-----------------|-----|
      | 작업 주키 ID | workID | INT | NOT NULL | AutoIncrement |
      | 부모의 작업 ID | parentID | INT | NULL |  |
      | 작업 제목 | workTitle | VARCHAR(30) | NULL |  |
      | 작업 내용 | workContent | VARCHAR(100) | NULL |  |
      | 작업 상태 | workState | INT | NOT NULL | 0 |
      | 시작 날짜 | startDate | Date | NOT NULL |  |
      | 종료 날짜 | finishDate | Date | NOT NULL |  |
      | 이슈 주키 ID | issueID | INT | NOT NULL | Autolncrement |
      | 이슈 제목 | issueTitle | VARCHAR(30) | NULL | |
      | 이슈 내용 | issueContent | VARCHAR(100) | NULL | |
      | 이슈 상태 | issueState | INT | NOT NULL | 0 | 

