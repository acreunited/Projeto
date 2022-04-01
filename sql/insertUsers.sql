use animearena;

insert into USERS values(
	0, 'axe', 'password', curdate(), 0, null, 
    'pncdias@hotmail.com', 0, 0, null, null, 0, 0, 1, 
    curdate()
);
insert into USERS values(
	1, 'betrayer', 'password', curdate(), 0, null, 
    'pncdiarrrrrs@hotmail.com', 0, 0, null, null, 0, 0, 1, 
    curdate()
);

insert into USERS values(
	2, 'ranktest', 'password', curdate(), 360, null, 
    'pncdiarrrrrs@hotmail.com', 1, 0, null, null, +1, +1, 1, 
    curdate()
);

select * from USERS;

insert into MEMBERS values(
	0
);
insert into MEMBERS values(
	1
);
insert into MEMBERS values(
	2
);

insert into ADMINISTRATOR values(
	0
);

select username 
from USERS INNER JOIN ADMINISTRATOR 
where USERS.userID=ADMINISTRATOR.administratorID;