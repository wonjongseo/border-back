# 사용툴

    # Front
        React, HTML, CSS, JavaScript
    # Back
        Spring boot, Java, Spring data jpa, Spring Sequrity
    # DB
        MySql

# 화면

    1. 네비게이션 바
    2. 홈페이지 (/)
    3. 회원가입페이지 (/join)
    4. 로그인페이지 (/login)
    5. 게시판페이지 (/border)
    6. 게시판디테일페이지  (/border-detail)

# 개요 :

    네비게이션 바

        1. Home, ( Login , Join | Logout ) , Border 로 이동할 수 있다
        2. 로그인 되어 있으면 Login, Join 을 숨키고 Logout 을 나타낸다
        3. 로그인 되어 있지 않으면 Login, Join 을 나타내고 Logout 을 숨킨다.

    유저
        회원가입
            1. Email, name, password1, password2 를 입력하여 회원가입한다.
            2. 회원가입 페이지로 라우팅 할 수 있다.

            예외 )
                1. password1과 password2가 같지 않으면 password2로 focus를 이동시켜 에러 메세지를 화면에 추가한다.

                2. 중복된 이메일을 입력하면 focus를 이동시켜 에러 메세지를 화면에 추가한다.

                3. 비밀번호 암호화한다.
             

        로그인
            1. 회원가입에 성공하면 로그인 페이지로 Redirect 시키며 입력한 email과 password를 자동적으로 입력시킨다.
            2. 로그인 성공하면 홈페이지로 이동하며 유저 이름을 화면에 출력한다


            예외)
                1. 유저 정보가 옳바르지 않으면 에러메세지를 화면에 출력한다.

    게시판

        네비게이션 바
            1. All Posts , My Posts (로그인된 유자만), Search 기능이 있다.
            2. All Posts , My Posts를 눌러 조건에 맞는 게시판을 볼 수 있다
            3. Search 기능을 사용해서 keyword에 맞는 게시판들을 보여준다.

        카테고리
            1. 日本語授業、IT授業 두 카테고리로 게시판들을 볼 수 있다.

        게시판 만들기
            1. 카테고리, 제목, 내용을 입력하고 게시판을 저장한다.

        게시판 리스트
           1. 게시판당 번호, 제목 , 작성된 날짜, 작성자의 정보를 보여준다.
           2. 로그인된 유저가 작성한 게시판이라면 수정, 삭제 할 수 있다.

        게시판 디테일
            1. 모든 게시판의 정보를 화면에 출력한다.




    
