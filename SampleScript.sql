DROP TABLE MEMBER;              -- ������� ���� �÷����°��� ����
DROP SEQUENCE SEQ_USERNO;   

CREATE TABLE MEMBER(
    USERNO NUMBER PRIMARY KEY,                  -- ȸ�� ��ȣ
    USERID VARCHAR2(15) NOT NULL UNIQUE,       -- ȸ��ID
    USERPWD VARCHAR2(15) NOT NULL,              -- ȸ�� ���
    USERNAME VARCHAR2(20) NOT NULL,             -- ȸ�� �̸�
    GENDER CHAR(1) CHECK (GENDER IN ('M', 'F')),    -- ����
    AGE NUMBER,                                      -- ����
    EAMIL VARCHAR2(40),                         -- �̸���
    PHONE CHAR(11),                             -- -���� ����
    ADDRESS VARCHAR2(100),                      -- �ּ�
    HOBBY VARCHAR2(50),                         -- ���
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL    -- ȸ�� ������
);

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

INSERT INTO MEMBER VALUES(
    SEQ_USERNO.NEXTVAL, 'admin', '1234', '������', 'M', 45, 'admin@kh.or.kr', '01013442354', '����', NULL, '2025-07-01');
    
INSERT INTO MEMBER VALUES(
    SEQ_USERNO.NEXTVAL, 'user01', 'pass01', '������', NULL, 23, 'user01@kh.or.kr', '01047334284', '�λ� Ư����', '���,��ȭ����', '2025-07-03');

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



INSERT INTO MEMBER VALUES (SEQ_USERNO.NEXTVAL,'usdk' ,'disk','���','M' ,29,'dkfd@djfui.com','01099833553','�����','���߱�', SYSDATE);

COMMIT;









