create table Account_Master 
(
Account_ID NUMBER(10),
Account_Type VARCHAR2(25), 
Account_Balance NUMBER(15),
Open_Date DATE
);

create table Customer(
Account_ID NUMBER(10) , 
customer_name VARCHAR2(50), 
Email VARCHAR2(30), 
Address VARCHAR2(100),
Pancard VARCHAR2(15)
);


create table Transactions (
Transaction_ID NUMBER,
Tran_description VARCHAR2(100),
DateofTransaction DATE, 
TransactionType VARCHAR2(1),
TranAmount NUMBER(15),
Account_No NUMBER(10)
);

create table Service_Tracker (
Service_ID NUMBER, 
Service_Description VARCHAR2(100),
Account_ID NUMBER, 
Service_Raised_Date DATE,
Service_status VARCHAR2(20)
);


create table User_Table
(Account_ID NUMBER ,
user_id NUMBER,
login_password VARCHAR2(15),
secret_question VARCHAR2(50),
Transaction_password VARCHAR2(15),
lock_status VARCHAR2(1)
);



create table Fund_Transfer(
FundTransfer_ID NUMBER ,
Account_ID NUMBER(10) ,
Payee_Account_ID NUMBER(10), 
Date_Of_Transfer DATE, 
Transfer_Amount NUMBER(15)
);


create table PayeeTable(
Account_Id NUMBER ,
Payee_Account_Id NUMBER, 
Nick_name VARCHAR2(40)
);


create sequence transId start with 1000 increment by 1 ;