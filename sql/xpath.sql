use animearena;

insert into MEMBERS values(1);
insert into MEMBERS values(2);
SELECT * FROM MEMBERS;

insert into ADMINISTRATOR values(1);

SELECT * FROM ADMINISTRATOR;
SELECT username 
FROM ADMINISTRATOR INNER JOIN USERS 
on ADMINISTRATOR.administratorID=USERS.userID;

SELECT * FROM USERS;