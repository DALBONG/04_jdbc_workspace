CREATE TABLE PRODUCT(
    PRODUCT_ID VARCHAR2(10) PRIMARY KEY,
    P_NAME VARCHAR2(15) NOT NULL,
    PRICE NUMBER NOT NULL,
    DESCRIPTION VARCHAR2(15),
    STOCK NUMBER NOT NULL
    );
    
SELECT * FROM PRODUCT;

INSERT INTO PRODUCT 
    VALUES('nb_ss7'
         , '�Ｚ��Ʈ��'
         , 1570000
         , '�ø���7'
         , 10
    );
    
INSERT INTO PRODUCT
    VALUES('nb_ama4'
         , '�ƺϿ���'
         , 1200000
         , 'xcode4'
         , 20
    );
    
    
INSERT INTO PRODUCT
    VALUES('pc_ibm'
         , 'ibmPC'
         , 750000
         , 'window8'
         , 5
    );
    
    commit;