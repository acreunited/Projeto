use animearena;

INSERT INTO THEME values(0);
INSERT INTO THEME values(1);

INSERT INTO CHARACTERS values (
	0
);
INSERT INTO BLEACH values(
	0, 'human'
);
select * from characters;
select * from theme;
INSERT INTO THEME_CHARACTER values(
	0, 0, 'char 1', null, 'description char 1'
);
INSERT INTO THEME_CHARACTER values(
	0, 1, 'Kurosaki Ichigo', null, 'Kurosaki Ichigo is a Human who is also a Substitute Shinigami. Ichigo is the son of Isshin and Masaki Kurosaki, and older brother of Karin and Yuzu'
);

select * from characters;

SELECT * FROM ADMINISTRATOR;
SELECT username 
FROM ADMINISTRATOR INNER JOIN USERS 
on ADMINISTRATOR.administratorID=USERS.userID;

select nome from theme_character
where characterID=0 and themeID=0;

select descricao from theme_character
where characterID=0 and themeID=0;

select nome from theme_character
where characterID=0 and themeID=1;

select descricao from theme_character
where characterID=0 and themeID=1;