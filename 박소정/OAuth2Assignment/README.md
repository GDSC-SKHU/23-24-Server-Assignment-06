## 구현할 기능 정리

- Google 로그인 API 구현:
    - User 클래스 구현
        - 사용자 정보를 저장
            - user_id
            - user_name
            - user_email
            - user_role
    - Authorization Code 발급
    - Access Token 발급
        - Authorization Code랑 맞교환
    - Resource Server에서 유저정보 받기


- 받은 정보를 토대로 유저 객체를 만들고 인가 토큰 발급
    - 이메일을 검색했을 때
        - 기존에 있는 사용자라면 저장된 걸 가져와서 토큰 발급
        - 처음 온 사용자라면 저장 후 액세스 토큰 발급


- 로그인된 사용자를 위한 미들웨어 구현(Servlet Filter을 이용):
    - 사용자의 로그인 상태를 확인
    - 인가된 사용자(액세스 토큰 O)이면 필터 통과
    - 인가되지 않은 사용자(액세스 토큰 X)면 403 에러 처리
> Q. dispatcher Servlet을 사용하는 게 맞는가?
> 
> 찾아본 바로는 웹 페이지 구현할 거 아니면 필요없을 거 같았는데, 무지렁이라 맞는지 아닌지 모릅니다...


- 간단한 글 작성 API 구현:
    - Post 클래스 구현:
        - 글 제목
        - 글 내용
        - 작성자
    - PostService 클래스:
        - Create
            - 글 제목, 내용, 작성자를 받음.
            - 하나라도 없다면 예외 출력
        - Read
            - 글 제목, 내용, 작성자를 반환

---
## 구현할 기능 목록(아직 잘 몰라서 만들면서 필요한 사항 추가할 예정)

> 로그인 구현 -> 글 작성 API 구현

-[x] config
    -[x] SecurityConfig // 나중에 글 작성 API 추가하기

-[x] controller
    -[x] AuthController
        -[x] googleCallback()
        -[x] loginOrSignup()
    -[x] TextController

-[x] domain
    -[x] Role: 권한
    -[x] User: 사용자
    -[x] Text: 글 작성

-[ ] dto
    -[x] Token: AccessToken 값 전달, OpenID Connect(OIDC)로 ~~도전..!~~ -> 어려워서 일단 포기, 나중에 다시 도전하겠습니다...
    -[x] UserInfo: AccessToken으로 사용자의 정보 받을 때 사용
    -[x] TextSaveReqDto
    -[x] TextResDto

-[x] jwt
    -[x] JwtFilter
    -[x] TokenProvider
      - [x] creatToken(): JWT 생성
      - [x] getAuthentication(): JWT에서 인증 정보 추출
      - [x] resolveToken(): HTTP 요청에서 JWT를 추출
      - [x] validateToken(): JWT 유효성 검증
      - [x] parseClaims():  JWT의 클레임(토큰에 담긴 데이터) 파싱

-[x] repository
    -[x] UserRepository
    -[x] TextRepository

-[x] service
    -[x] AuthService
        -[x] getGoogleAccessToken()
        -[x] loginOrSignUp()
        -[x] getUserInfo()
    -[x] TextService
        -[x] create
        -[x] read