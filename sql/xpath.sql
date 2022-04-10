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

select nome from theme_character
where characterID=1 and themeID=0;

select descricao from theme_character
where characterID=1 and themeID=0;

select nome from theme_character
where characterID=1 and themeID=1;

select descricao from theme_character
where characterID=1 and themeID=1;

select nome
from THEME_CHARACTER
where THEME_CHARACTER.themeID=1;

select * from BLEACH;

SELECT * 
FROM MISSION, THEME_CHARACTER
WHERE THEME_CHARACTER.themeID = 1 and MISSION.characterID=THEME_CHARACTER.characterID;

SELECT MISSION.nome 
FROM MISSION, THEME_CHARACTER
WHERE THEME_CHARACTER.themeID = 1 and MISSION.characterID=THEME_CHARACTER.characterID;

select quantity
from ABILITY_E5
where abilityID=0;

select nome, descricao
from THEME_ABILITY
where themeID=1 and abilityID=0;

select characterID, nome
from THEME_CHARACTER
where THEME_CHARACTER.themeID=1;

select abilityID
from ABILITY
where characterID=1;
select nome, descricao
from THEME_ABILITY
where themeID=1;

select nome, descricao 
from THEME_ABILITY
where themeID=1;

select abilityID
from ABILITY 
where characterID=1;

select nome, descricao
from THEME_ABILITY
where themeID=1 and abilityID IN (4, 5, 6, 7);

select username 
from USERS INNER JOIN ADMINISTRATOR 
where USERS.userID=ADMINISTRATOR.administratorID;

select * from MISSION where missionID=0;

select * from ABILITY where characterID=1;
select * from THEME_ABILITY where themeID=1 and abilityID=0;

select * from THEME_CHARACTER where themeID=1 and characterID=0;

select * from THEME_CHARACTER where themeID=1;

select * from BLEACH INNER JOIN THEME_CHARACTER where BLEACH.bleachID=THEME_CHARACTER.characterID and THEME_CHARACTER.themeID=1 and BLEACH.bleachID=0;


select * from THEME_ABILITY where themeID=1;
select * from THEME_ABILITY where themeID=1 and abilityID=1;

select * from MISSION where missionID=0;

select * from THEME_CHARACTER;

select pass, userID FROM USERS where username="axe";

select * from ADMINISTRATOR where administratorID=1;

select * from USERS where userID=1;

SELECT * FROM USERS ORDER BY xp DESC LIMIT 10;
SELECT * FROM USERS ORDER BY xp DESC;

select * from ABILITY where characterID=0;

select * from USERS where userID=1;

select membersID from MEMBERS order by membersID DESC LIMIT 1;

select * from USERS;
select * FROM MISSION;
select * from MEMBERS;

select * from CHARACTERS;
select * from BLEACH;
select * from BLEACH;

select * from ABILITY;

select * from CHARACTERS order by characterID DESC LIMIT 1;
select * from ABILITY order by abilityID DESC LIMIT 1;

select * from MISSION order by missionID DESC LIMIT 1;

select * from USERS;
select * from USERS order by streak DESC LIMIT 10;

select username from USERS;

SELECT * FROM USERS ORDER BY xp DESC;

select * from USERS where username="axe";
select * from USERS where userID=1;

select * from USERS order by xp DESC;

select characterID from THEME_CHARACTER where themeID=1 LIMIT 12;
select * from USERS where userID=1;

select * from THEME_CHARACTER where themeID=1;
select * from THEME_CHARACTER where themeID=1 and characterID=1;

SELECT COUNT(characterID) from THEME_CHARACTER where themeID=1;

select * from ABILITY where characterID=6;
select * from THEME_ABILITY where themeID=1;

select * from THEME_ABILITY where themeID=1 and abilityID=1;
