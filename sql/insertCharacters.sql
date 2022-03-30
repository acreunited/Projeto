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

-- MISSION CHARACTER --
INSERT INTO CHARACTERS values (
	1
);
INSERT INTO BLEACH values(
	1, 'arrancar'
);
INSERT INTO THEME_CHARACTER values(
	1, 0, 'char 2', null, 'description char 2'
);
INSERT INTO THEME_CHARACTER values(
	1, 1, 'Child Nelliel', null, 'Also known as Nel Tu, she is a small, good-natured, childlike Arrancar. She lives in the desert of Hueco Mundo with her adoptive brothers and former Fracción, Dondochakka Birstanne and Pesche Guatiche, and their pet, Bawabawa. She is the archenemy of Nnoitra Gilga (who is responsible that she turned into a child after he severely damaged her head).'
);
INSERT INTO MISSION values(
	0, "Mission Child Nelliel", "Description Mission Child Nelliel", null, 5, 1
);
