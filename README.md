![header](https://capsule-render.vercel.app/api?type=rect&color=timeAuto&section=header&text=Urban-Waffle&height=60&fontSize=40)


## Index

1. [Abstract](#Abstract)
2. [Tech Stack](#Tech-Stack)
3. [ERD](#ERD)
4. [API](#API)
5. [Project Structure](#Project-Structure)


---

<br />

## 1. Abstract

<br />

**Quirky, Experimental, Inefficient, and SUPER Personal**

This Spring Boot Starter project includes basic CRUD operations for members, boards, and comments. I aim to improve my skills by exploring unconventional approaches through such endeavors.
The following are the functionalities or libraries I wanted to implement or use through this project.

+ [x] 기능의 모듈화
+ [x] 다중 Database 활용
+ [x] 휴면회원 처리
+ [x] Spring Security 사용
+ [x] RDB 계층형 데이터 설계


I aim to prioritize <code>scalability</code>, <code>efficiency</code>, and <code>readability</code> even without comments.  
If you want more information about this project, click here



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
|  GET   |        /join?enail={email}        |  이메일 중복 확인  |
|  POST  |              /login               |     로그인     |
|  POST  |            /help/email            |   이메일 찾기    |
|  POST  |          /help/password           |   비밀번호 찾기   |
|  GET   | /help/certification?email={email} | 본인 인증 메일 발송 |
|  POST  |    /help/cergification/cofirm     |  인증 번호 확인   |
|  GET   |              /{user}              |   프로필 정보    |
|  GET   |         /{user}?img={img}         | 프로필 이미지 변경  |
|  PUT   |    /{user}?nickname={nickname}    |   닉네임 변경    |
|  GET   |    /{user}?nickname={nickname}    |  닉네임 중복 확인  |
|  POST  |         /{user}/security          |   비밀번호 변경   |
| DELETE |              /{user}              |     탈퇴      |
</details>

<details>
<summary><b>포스트</b> (/api/posts)</summary>

| Method |          End Point          | Description |  
|:------:|:---------------------------:|:-----------:|
|        |              /              |             |


</details>

<details>
<summary><b>댓글</b> (/api/comments)</summary>

| Method |         End Point         | Description |  
|:------:|:-------------------------:|:-----------:|
|  POST  |          /{post}          |    댓글 생성    |
|  PUT   |        /{comment}         |    댓글 수정    |
| DELETE |        /{comment}         |    댓글 삭제    |
|  POST  |      {comment}/reply      |   대댓글 작성    |
|  PUT   | {comment}/reply/{comment} |   대댓글 수정    |
| DELETE | {comment}/reply/{comment} |   대댓글 삭제    |
|  GET   |          /{post}          |  전체 댓글 목록   |
|  GET   |     /{comment}/reply      |  전체 대댓글 목록  |
</details>


<br/>

<br/>

## 5. Project Structure

<br />

**Modular Monolith**  

In this project, I aimed to decompose independent operations or layers into modules.

- Separation of Features  

Firstly, I separated Spring Batch jobs that could impact the overall performance of the server system and degrade the quality of services.  
The module boundaries are clearly defined enough to be completely separate from the existing process.  
To address the issue associated with using a single database in modular monoliths, I created separate schemas, which makes it easier to separate the services in the future.

- Separation of Layers  

Next, I separated the layers into two parts: one handling database access and the other handling user interface.  


![footer](https://capsule-render.vercel.app/api?type=waving&&color=timeAuto&section=footer)

