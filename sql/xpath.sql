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

select * from MISSION;