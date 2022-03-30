use animearena;
show tables;


-- USER
insert into USERS values(
	0, 'axe', 'password', curdate(), 0, null, 
    'pncdias@hotmail.com', 0, 0, null, null, 0, 0, 1, 
    curdate()
);
insert into USERS values(
	2, 'betrayer', 'password', curdate(), 0, null, 
    'pncdiarrrrrs@hotmail.com', 0, 0, null, null, 0, 0, 1, 
    curdate()
);
-- CHARACTER--
insert into CHARACTERS values(
	0, "Esdeath", null, "description character Esdeath"
);
select * from CHARACTERS;

-- MISSIONS
insert into MISSION values(
	0, "Esdeath Mission Name", "description here", null, 16, 1
);
select * from mission;

