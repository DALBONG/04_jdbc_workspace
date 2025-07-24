DROP TABLE MEMBER;              -- 드랍문은 위에 올려놓는것이 정석
DROP SEQUENCE SEQ_USERNO;   

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                  -- 회원 번호
    USERID VARCHAR2(15) NOT NULL UNIQUE,       -- 회원ID
    USERPWD VARCHAR2(15) NOT NULL,              -- 회원 비번
    USERNAME VARCHAR2(20) NOT NULL,             -- 회원 이름
    GENDER CHAR(1) CHECK (GENDER IN ('M', 'F')),    -- 성별
    AGE NUMBER,                                      -- 나이
    EAMIL VARCHAR2(40),                         -- 이메일
    PHONE CHAR(11),                             -- -없이 폰번
    ADDRESS VARCHAR2(100),                      -- 주소
    HOBBY VARCHAR2(50),                         -- 취미
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL    -- 회원 가입일
);

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

INSERT INTO MEMBER VALUES(
    SEQ_USERNO.NEXTVAL, 'admin', '1234', '관리자', 'M', 45, 'admin@kh.or.kr', '01013442354', '서울', NULL, '2025-07-01');
    
INSERT INTO MEMBER VALUES(
    SEQ_USERNO.NEXTVAL, 'user01', 'pass01', '차웅우', NULL, 23, 'user01@kh.or.kr', '01047334284', '부산 특별시', '등산,영화보기', '2025-07-03');

SELECT * FROM MEMBER;
COMMIT;

CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE
);

SELECT * FROM TEST; 
SELECT * 
  FROM MEMBER;



INSERT INTO MEMBER VALUES (SEQ_USERNO.NEXTVAL,'usdk' ,'disk','김기','M' ,29,'dkfd@djfui.com','01099833553','서울시','춤추기', SYSDATE);

COMMIT;









