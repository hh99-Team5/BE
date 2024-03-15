# 공부 일지 서비스

> 항해99 5조 주특기 프로젝트

## 🔧 구현 기능

- [x] 회원 가입 기능
- [x] 로그인 기능
    - Access Token 발행
- [x] 회원 정보 수정 기능
    - 비밀번호 변경
- [x] 작성한 일지 목록 조회 기능
- [x] 일지 CRUD 기능
    - 일지 작성, 조회, 수정, 삭제
- [x] 일지 검색 기능
    - 제목, 내용, 작성자
- [x] 일지 좋아요 기능
    - 좋아요, 좋아요 취소
- [x] 일지 댓글 CRUD 기능
    - 댓글 작성, 조회, 수정, 삭제

## 📚 스택

- JDK 17
- Spring Boot 3.1.9
- Spring Boot JPA
- Spring Boot Validation
- Spring Boot Security
- Swagger UI
- QueryDsl
- JWT
- Elastic Beanstalk, EC2, RDS
- MySQL
- Github Actions

## 🖼️ Use Case Diagram

![Use case diagram - Page 1 (4)](https://github.com/hh99-Team5/Study_diary_BE/assets/150704638/ebc577ac-16ad-4dfb-b459-7b39130216e9)

## 🔖 ERD

```mermaid
erDiagram
    Member {
        int id PK "ID"
        string email "이메일"
        string password "비밀번호"
        date created_at "생성일자"
        date updated_at "수정일자"
    }

    Article {
        int id PK "ID"
        int member_id FK "회원 ID"
        string title "제목"
        string contents "내용"
        date created_at "생성일자"
        date updated_at "수정일자"
    }

    Comment {
        int id PK "ID"
        int member_id FK "회원 ID"
        int article_id FK "일지 ID"
        string contents "내용"
        date created_at "생성일자"
        date updated_at "수정일자"
    }

    Article_Like {
        int id PK "ID"
        int member_id FK "회원 ID"
        int article_id FK "일지 ID"
        date created_at "생성일자"
    }

    Member ||--o{ Article: ""
    Member ||--o{ Comment: ""
    Article ||--o{ Comment: ""
    Member ||--o{ Article_Like: ""
    Article ||--o{ Article_Like: ""
```

## 📄 API 명세서

URL: http://hanghae-5.ap-northeast-2.elasticbeanstalk.com/swagger-ui/index.html

<img width="721" alt="스크린샷 2024-03-14 오전 10 37 38" src="https://github.com/hh99-Team5/Study_diary_BE/assets/150704638/3c4dea9f-8c84-4cff-ac6d-aaf0df409514">

## 📐 시스템 아키텍처

![Blank diagram - Page 1 (11)](https://github.com/hh99-Team5/Study_diary_BE/assets/150704638/2c471bdc-9b42-48b0-9fdb-eb09e9d26c60)
