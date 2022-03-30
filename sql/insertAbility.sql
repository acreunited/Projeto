use animearena;

-- ICHIGO --
insert into ABILITY values (
	0, 0, 0
);
insert into ABILITY_E1 values (
	0, 0, 0
);
insert into ABILITY_E2 values (
	0, 1, 0
);
insert into ABILITY_E3 values (
	0, 2, 0
);
insert into ABILITY_E4 values (
	0, 3, 0
);
insert into ABILITY_E5 values (
	0, 4, 2
);

insert into THEME_ABILITY values(
	0, 0, "ability 0", null, "description ability 0"
);
insert into THEME_ABILITY values(
	0, 1, "ability Ichigo", null, "Ichigo does some crazy thing"
);

-- ABILITY 2 -------------------------------
insert into ABILITY values (
	1, 1, 0
);
insert into ABILITY_E1 values (
	1, 0, 0
);
insert into ABILITY_E2 values (
	1, 1, 0
);
insert into ABILITY_E3 values (
	1, 2, 0
);
insert into ABILITY_E4 values (
	1, 3, 0
);
insert into ABILITY_E5 values (
	1, 4, 2
);

insert into THEME_ABILITY values(
	1, 0, "ability 1", null, "description ability 1"
);
insert into THEME_ABILITY values(
	1, 1, "ability 1 Ichigo", null, "Ichigo 1 does some crazy thing"
);

-- ABILITY 3 ---------------------
insert into ABILITY values (
	2, 0, 0
);
insert into ABILITY_E1 values (
	2, 0, 0
);
insert into ABILITY_E2 values (
	2, 1, 0
);
insert into ABILITY_E3 values (
	2, 2, 0
);
insert into ABILITY_E4 values (
	2, 3, 0
);
insert into ABILITY_E5 values (
	2, 4, 2
);

insert into THEME_ABILITY values(
	2, 0, "ability 2", null, "description ability20"
);
insert into THEME_ABILITY values(
	2, 1, "ability 2 Ichigo", null, "Ichigo 2 does some crazy thing"
);

-- ABILITY 4 ---------------
insert into ABILITY values (
	3, 0, 0
);
insert into ABILITY_E1 values (
	3, 0, 0
);
insert into ABILITY_E2 values (
	3, 1, 0
);
insert into ABILITY_E3 values (
	3, 2, 0
);
insert into ABILITY_E4 values (
	3, 3, 0
);
insert into ABILITY_E5 values (
	3, 4, 2
);

insert into THEME_ABILITY values(
	3, 0, "ability 3", null, "description ability 3"
);
insert into THEME_ABILITY values(
	3, 1, "ability 3 Ichigo", null, "Ichigo 3 does some crazy thing"
);

select nome, descricao 
from THEME_ABILITY
where themeID=1;

select abilityID
from ABILITY 
where characterID=0;

select nome, descricao
from THEME_ABILITY
where themeID=1 and abilityID IN (0, 1, 2, 3);

-- CHILD NELLIEL
insert into ABILITY values (
	4, 0, 1
);
insert into ABILITY_E1 values (
	4, 0, 0
);
insert into ABILITY_E2 values (
	4, 1, 0
);
insert into ABILITY_E3 values (
	4, 2, 0
);
insert into ABILITY_E4 values (
	4, 3, 0
);
insert into ABILITY_E5 values (
	4, 4, 2
);

insert into THEME_ABILITY values(
	4, 0, "ability 4", null, "description ability 4"
);
insert into THEME_ABILITY values(
	4, 1, "ability Child Nelliel", null, "Child Nelliel does some crazy thing"
);

-- ABILITY 2 -------------------------------
insert into ABILITY values (
	5, 1, 1
);
insert into ABILITY_E1 values (
	5, 0, 0
);
insert into ABILITY_E2 values (
	5, 1, 0
);
insert into ABILITY_E3 values (
	5, 2, 0
);
insert into ABILITY_E4 values (
	5, 3, 0
);
insert into ABILITY_E5 values (
	5, 4, 2
);

insert into THEME_ABILITY values(
	5, 0, "ability 5", null, "description ability 5"
);
insert into THEME_ABILITY values(
	5, 1, "ability 5 Child Nelliel", null, "Child Nelliel 5 does some crazy thing"
);

-- ABILITY 3 ---------------------
insert into ABILITY values (
	6, 0, 1
);
insert into ABILITY_E1 values (
	6, 0, 0
);
insert into ABILITY_E2 values (
	6, 1, 0
);
insert into ABILITY_E3 values (
	6, 2, 0
);
insert into ABILITY_E4 values (
	6, 3, 0
);
insert into ABILITY_E5 values (
	6, 4, 2
);

insert into THEME_ABILITY values(
	6, 0, "ability 6", null, "description ability 6"
);
insert into THEME_ABILITY values(
	6, 1, "ability 6 Child Nelliel", null, "Child Nelliel 6 does some crazy thing"
);

-- ABILITY 4 ---------------
insert into ABILITY values (
	7, 0, 1
);
insert into ABILITY_E1 values (
	7, 0, 0
);
insert into ABILITY_E2 values (
	7, 1, 0
);
insert into ABILITY_E3 values (
	7, 2, 0
);
insert into ABILITY_E4 values (
	7, 3, 0
);
insert into ABILITY_E5 values (
	7, 4, 2
);

insert into THEME_ABILITY values(
	7, 0, "ability 7", null, "description ability 7"
);
insert into THEME_ABILITY values(
	7, 1, "ability 7 Child Nelliel", null, "Child Nelliel 7 does some crazy thing"
);
