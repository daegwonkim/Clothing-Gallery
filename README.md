# Clothing Gallery

### 1. 프로젝트 개요
- 프로젝트 명: Clothing Gallery
- 개발 인원: 1명
- 개발 기간: 2023.07.01 ~
- 주요 기능
  - 사용자 관련: 회원가입 및 로그인(OAuth2.0 포함), 회원가입 및 로그인 유효성 검사(+중복 검사), 회원 정보 수정, 회원 등급
  - 상품 관련: CRUD 기능, 카테고리, 페이징 및 검색, 주문수, 문의 및 후기, 장바구니, 즐겨찾기
  - 주문 관련: 배송지 설정, 결제 방법 선택
  - 게시판 관련: CRUD 기능, 카테고리, 페이징 및 검색, 조회수
  - 관리자 관련: 상품 등록 및 공지사항 작성
- 개발 언어: Java 11
- 개발 환경: Spring Boot 3.1.1, Gradle, Spring Data JPA, Vue.js
- 데이터베이스: MySQL
- 형상관리 툴: GitHub

<hr>

### 2. 프로젝트 요구사항
- 회원가입
  - 유효성 검사
    - 아이디는 이메일 형식으로 지정(회원가입 시 이메일 인증 필요)
    - 비밀번호는 8~16자 사이로, 영문과 숫자, 특수문자를 포함
    - 비밀번호 확인을 위한 입력칸 필요
    - 닉네임은 2~10자 사이로, 특수문자를 제외한 한글, 알파벳 대소문자, 숫자로 구성
    - 필수 입력 칸이 공백이라면 그에 따른 예외 메시지 보여주기
  - 중복 확인
    - 이미 존재하는 아이디로 회원가입을 진행할 경우 그에 따른 예외 메시지 보여주기
  - 모든 검사를 통과했다면 회원으로 등록한 후 로그인 페이지로 이동
- 로그인
  - 로그인을 하지 않은 경우 아래의 페이지만 이용 가능하며, 이 외의 페이지로 접속할 경우 로그인 페이지로 강제 이동
    - 회원가입 페이지
    - 로그인 페이지
    - 메인 페이지
    - 상품 목록 페이지
    - 상품 상세보기 페이지
    - 상품 주문 페이지
    - 게시판 페이지
    - 장바구니 페이지
  - 유효성 검사
    - 아이디와 비밀번호가 일치하지 않을 경우 그에 따른 예외 메시지 보여주기
  - 모든 검사를 통과했다면 로그인 처리 후 메인 페이지로 이동
  - OAuth 2.0를 통한 소셜 로그인(구글, 카카오, 네이버, 페이스북 등)
- 회원정보 수정
  - 회원정보 수정은 비밀번호만 가능
  - 수정란이 공백인 경우 그에 따른 예외 메시지 보여주기
  - 수정할 때에도 동일하게 유효성 검사 적용
- 상품
  - 재고가 없을 경우 "장바구니에 담기" 버튼 비활성화
  - 상품 색상, 사이즈, 배송지 주소 등 주문에 필요한 정보를 입력하지 않은 경우 그에 따른 예외 메시지 보여주기
- 문의 및 후기
  - 후기는 상품을 구매한 로그인 회원만 작성 가능
  - 문의나 후기 작성 시 빈칸 또는 공백인 경우 그에 따른 예외 메시지 보여주기
  - 후기는 사진(선택) 및 별점을 포함할 수 있음
  - 문의 및 후기 수정, 삭제는 작성자만 가능
- 장바구니
  - 상품을 고를 시 장바구니에 담김
  - 장바구니에서는 지금까지 담은 상품 목록과 금액 등의 정보를 확인할 수 있음
  - 장바구니에서 결제 버튼을 눌러 주문 페이지로 이동
  - 장바구니에서 주문할 상품의 옵션 및 수량 변경 가능
- 주문
  - 배송지 주소 설정시 사용자가 기본 배송지를 설정해놓았다면, 자동으로 주소 설정
  - 배송 요청사항 작성 가능
  - 결제 방법 선택하여 최종 결제
  - 회원 등급 및 쿠폰, 이벤트 여부에 따라 할인 적용
- 게시판
  - 게시글은 작성 및 수정, 삭제는 관리자만 가능
  - 게시글 작성, 수정 시 제목 또는 내용이 빈칸 또는 공백인 경우 그에 따른 예외 메시지 보여주기

<hr>

### 3. DB 설계
![erd](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/ac538264-c799-485a-b483-a4cc98a5709c)

<hr>

### 4. 개발 내용
1. [[Clothing Gallery] 0. 프로젝트 개요](https://daegwonkim.tistory.com/455)
2. [[Clothing Gallery] 1. 메인 페이지 구현](https://daegwonkim.tistory.com/457)
3. [[Clothing Gallery] 2. SHOP 페이지 구현](https://daegwonkim.tistory.com/458)
4. [[Clothing Gallery] 3. BRANDS 페이지 구현](https://daegwonkim.tistory.com/459)
5. [[Clothing Gallery] 4. CLOTHING, FOOTWEAR, ACCESSORIES 페이지 구현](https://daegwonkim.tistory.com/461)
6. [[Clothing Gallery] 5. SALE, CONTACT 페이지 구현](https://daegwonkim.tistory.com/462)
7. [[Clothing Gallery] 6. DETAIL 페이지 구현](https://daegwonkim.tistory.com/463)
8. [[Clothing Gallery] 7. CART 페이지 구현](https://daegwonkim.tistory.com/465)
9. [[Clothing Gallery] 8. WISH 페이지 구현](https://daegwonkim.tistory.com/467)
10. [[Clothing Gallery] 9. PROFILE 페이지 구현](https://daegwonkim.tistory.com/468)

<hr>

### 5. 실행 화면
<details>
<summary>메인 페이지</summary>

![Home](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/e8945291-679d-4913-b2bb-3dfef5c67aa8)
![Shop](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/cbd2e8d3-14eb-4d5e-bb49-e7a11e7b81f4)
![New Arrivals](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/5b1d3025-e844-4dce-b938-87d6562ffd93)
![Brands](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/badedc5f-205b-4e1b-b0d3-cda6c3d4b461)
![Brands](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/38422a7b-1162-4665-ae2f-79c597d1ed16)
![Clothing, Footwear, Accessories](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/36326887-efbf-458d-aca1-caad63f8d213)
![Sale, Contact](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/b7c6b7df-ee0e-4006-affc-e019164a7678)
</details>

<details>
<summary>상품 페이지</summary>

![Detail](https://github.com/Baeg-won/Baeg-won-Mall/assets/45421117/7b17c5b6-9c57-4f6a-b2ce-c34dd7e8789a)
</details>

<details>
<summary>장바구니 페이지</summary>

![Cart](https://github.com/Baeg-won/Baeg-won-Clothing-Gallery/assets/45421117/78c1a42b-73be-40ae-97db-df273986f4e9)
</details>

<details>
<summary>즐겨찾기 페이지</summary>

![Wish](https://github.com/Baeg-won/Baeg-won-Clothing-Gallery/assets/45421117/a67e05ab-9bc8-40cf-a796-c09c1a70b8f9)
</details>

<details>
<summary>프로필 페이지</summary>

![Account](https://github.com/Baeg-won/Baeg-won-Clothing-Gallery/assets/45421117/282acad4-a24f-4fe7-affd-6d0df6d6b954)
![Address1](https://github.com/Baeg-won/Baeg-won-Clothing-Gallery/assets/45421117/cf721ae2-a0c9-45d7-aafd-d4d33af09c15)
![Address2](https://github.com/Baeg-won/Baeg-won-Clothing-Gallery/assets/45421117/d9283077-17f7-4498-894a-0494bec505e6)
</details>
