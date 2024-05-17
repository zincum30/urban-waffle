![header](https://capsule-render.vercel.app/api?type=rect&color=timeAuto&section=header&text=Urban-Waffle&height=60&fontSize=40)


## 📑 Index

1. [Overview](#Overview)
2. [Tech Stack](#Tech-Stack)
3. [ERD](#ERD)
4. [API](#API)
5. [Project Structure](#Project-Structure)
6. [Features](#Features)


---

<br />

## 1. Overview

<br />

**Quirky, Experimental, Inefficient, and SUPER Personal**

This Spring Boot Starter project includes basic CRUD operations for members, boards, and comments. I aim to improve my skills by exploring unconventional approaches through such endeavors.
The following are the features I have completed in this project:

+ [x] 기능의 모듈화
+ [x] 다중 Database 활용
+ [x] 휴면회원 처리
+ [x] Spring Security 사용
+ [x] QueryDSL 사용

The following are the features I plan to implement in this project:

+ [ ] Axon Framework 적용
+ [ ] DDD 및 EDA 활용
+ [ ] gRPC, Web Client 구현

Of course, these may be changed or added to.

I aim to prioritize <code>scalability</code>, <code>efficiency</code>, and <code>readability</code> even without comments.  




<br />


<br />


## 2. Tech Stack


- **Backend**  
  <img src="https://img.shields.io/badge/JAVA-orange?style=flat&logo=JAVA&logoColor=white">  
  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=square&logo=Spring Boot&logoColor=white&color=6DB33F">
  <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=square&logo=Spring Security&logoColor=white">
  <img src="https://img.shields.io/badge/JSON Web Tokens-000000?style=square&logo=JSON Web Tokens&logoColor=white">
  <img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=square&logo=JPA&logoColor=white">
  <img src="https://img.shields.io/badge/Spring Batch-6DB33F?style=square&logo=JPA&logoColor=white">
  <img src="https://img.shields.io/badge/QueryDSL-0081CC?style=square&logo=QueryDSL&logoColor=white">



- **Database**  
  <img src="https://img.shields.io/badge/MySQL-4479A1.svg?style=square&logo=MySQL&logoColor=white">
  <img src="https://img.shields.io/badge/Redis-DC382D?style=square&logo=Redis&logoColor=white">
  <img src="https://img.shields.io/badge/AWS S3-569A31?style=square&logo=amazons3&logoColor=white">



- **Build & Deploy**  
  <img src="https://img.shields.io/badge/Gradle-02303A?style=square&logo=Gradle&logoColor=white">
  <img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=square&logo=Amazon AWS&logoColor=white">



<br />


<br />


## 3. ERD

![waffle_erd](https://github.com/zincum30/urban-waffle/assets/115124708/4c8e58a4-891b-489a-b196-5af1c4478d46)

- 참고 블로그 : [회원 가입 및 로그인을 위한 테이블 설계](https://rastalion.dev/%ed%9a%8c%ec%9b%90-%ea%b0%80%ec%9e%85-%eb%b0%8f-%eb%a1%9c%ea%b7%b8%ec%9d%b8%ec%9d%84-%ec%9c%84%ed%95%9c-%ed%85%8c%ec%9d%b4%eb%b8%94-%ec%84%a4%ea%b3%84/)



<br />


<br />

## 4. API


<details>
<summary><b>사용자</b> (/api/users)</summary>

| Method |             End Point             | Description |  
|:------:|:---------------------------------:|:-----------:|
|  POST  |               /join               |    회원가입     |
|  GET   |        /join?email={email}        |  이메일 중복 확인  |
|  POST  |              /login               |     로그인     |
|  POST  |          /help/password           |   비밀번호 찾기   |
|  GET   | /help/certification?email={email} | 본인 인증 메일 발송 |
|  POST  |        /help/cergification        |  인증 번호 확인   |
|  GET   |            /{nickname}            |   프로필 정보    |
|  PUT   |         /{nickname}/image         | 프로필 이미지 변경  |
|  PUT   |            /{nickname}            |   닉네임 변경    |
|  GET   |    /{user}?nickname={nickname}    |  닉네임 중복 확인  |
|  POST  |         /{user}/security          |   비밀번호 변경   |
| DELETE |              /{user}              |     탈퇴      |
</details>

<details>
<summary><b>포스트</b> (/api/posts)</summary>

| Method |   End Point   | Description |  
|:------:|:-------------:|:-----------:|
|  POST  |    /draft     |   포스트 작성    |
|  POST  | /{post}/image |   이미지 업로드   |
|  POST  |    /{post}    |   포스트 저장    |
|  PUT   |    /{post}    |   포스트 수정    |
| DELETE | /{post}/image |   이미지 삭제    |
| DELETE |    /{post}    |   포스트 삭제    |
|  GET   |               |  전체 포스트 목록  |
|  GET   |    /{post}    |  포스트 불러오기   |


</details>

<details>
<summary><b>댓글</b> (/api/comments)</summary>

| Method |        End Point        | Description |  
|:------:|:-----------------------:|:-----------:|
|  POST  |         /{post}         |    댓글 생성    |
|  PUT   |       /{comment}        |    댓글 수정    |
| DELETE |       /{comment}        |    댓글 삭제    |
|  POST  |     {comment}/reply     |   대댓글 작성    |
|  PUT   | {comment}?reply={reply} |   대댓글 수정    |
| DELETE | {comment}?reply={reply} |   대댓글 삭제    |
|  GET   |         /{post}         |  전체 댓글 목록   |
|  GET   |    /{comment}/reply     |  전체 대댓글 목록  |
</details>



<br/>

<br/>

## 5. Project Structure


#### Modular Monolith

I separated Spring Batch jobs that could impact the overall performance of the server system and degrade the quality of services.  

![project-structure](https://github.com/zincum30/urban-waffle/assets/115124708/c0fb090e-3b17-49c5-ba89-f5274b675d99)

The module boundaries are clearly defined enough to be completely separate from the existing process.
To address the issue associated with using a single database in modular monoliths, I created separate schemas, which makes it easier to separate the services in the future.


<br />

<br />




![footer](https://capsule-render.vercel.app/api?type=waving&&color=timeAuto&section=footer)

