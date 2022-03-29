drop schema if exists animearena;
create schema animearena;
use animearena;
show tables;

-- USERS --
create table USERS (
	userID int not null,
    username varchar(16) not null,
    UNIQUE(username),
    pass varchar(32) not null,
    registerDate datetime not null,
    xp int not null,
    CHECK(xp >= 0),
    avatar tinyblob,
    email varchar(128) not null,
    nWins int not null,
    CHECK (nWins >= 0),
    nLosses int not null,
    CHECK (nLosses >= 0),
    selectionBackground blob,
    battleBackground blob,
    streak int not null,
    CHECK (streak >= 0),
    highestStreak int not null,
    CHECK (highestStreak >= 0),
    highestLevel int not null,
    CHECK (highestLevel > 0),
    estado datetime not null,

    constraint pk_user primary key(userID)
    
);

-- USER IS-A --
create table MEMBERS (
	membersID int not null,
    constraint pk_members primary key (membersID),
    constraint fk_members foreign key (membersID) references USERS(userID)
);

-- MEMBERS IS-A --
create table ADMINISTRATOR (
	administratorID int not null,
    constraint pk_administrator primary key (administratorID),
    constraint fk_administrator foreign key (administratorID) references MEMBERS(membersID)
);


-- CHARACTERS --
create table CHARACTERS (
	characterID int not null,

    constraint pk_characters primary key(characterID)
);

-- CHARACTERS IS-A ANIME --
create table BLEACH (
	bleachID int not null,
    category varchar(16) not null, 
    CHECK (category in ('human', 'shinigami', 'souls', 'arrancar', 'hollow', 'quincy', 'other')),
    
    constraint pk_bleach primary key (bleachID),
    constraint fk_bleach foreign key (bleachID) references CHARACTERS(characterID)
);

-- MISSION --
-- REQUISITOS?--
create table MISSION (
	missionID int not null,
    nome varchar(32) not null,
    UNIQUE(nome),
    descricao varchar(5000),
    image tinyblob,
    minimumLevel int,
    CHECK(minimumLevel >= 1),
    
    characterID int not null,
    
    constraint pk_mission primary key (missionID)
);

-- ABILITY --
create table ABILITY(
	abilityID int not null,
    nome varchar(16) not null,
    cooldown int not null,
    CHECK (cooldown >= 0),
    avatar tinyblob not null,
    
    characterID int not null,
    
    constraint pk_ability primary key (abilityID)
);

-- ENERGY --
create table ENERGY(
	energyID int not null,
    
    constraint pk_energy primary key (energyID)
);
create table E1(
	e1ID int not null,
    nome varchar(16) not null,
    UNIQUE(nome),
    color varchar(16) not null,
    UNIQUE(color),
    
    constraint pk_e1 primary key (e1ID),
    constraint fk_e1 foreign key (e1ID) references ENERGY(energyID)
);
create table E2(
	e2ID int not null,
    nome varchar(16) not null,
    UNIQUE(nome),
    color varchar(16) not null,
    UNIQUE(color),
    
    constraint pk_e2 primary key (e2ID),
    constraint fk_e2 foreign key (e2ID) references ENERGY(energyID)
);
create table E3(
	e3ID int not null,
    nome varchar(16) not null,
    UNIQUE(nome),
    color varchar(16) not null,
    UNIQUE(color),
    
    constraint pk_e3 primary key (e3ID),
    constraint fk_e3 foreign key (e3ID) references ENERGY(energyID)
);
create table E4(
	e4ID int not null,
    nome varchar(16) not null,
    UNIQUE(nome),
    color varchar(16) not null,
    UNIQUE(color),
    
    constraint pk_e4 primary key (e4ID),
    constraint fk_e4 foreign key (e4ID) references ENERGY(energyID)
);
create table E5(
	e5ID int not null,
    nome varchar(16) not null,
    UNIQUE(nome),
    color varchar(16) not null,
    UNIQUE(color),
    
    constraint pk_e5 primary key (e5ID),
    constraint fk_e5 foreign key (e5ID) references ENERGY(energyID)
);

-- THEME --
create table THEME(
	themeID int not null,
    
    constraint pk_theme primary key (themeID)
);


-- RELATIONS --

-- USER - CHARACTERS --
-- many to many
create table USER_CHARACTER (
	userID int not null, 
    characterID int,
    constraint pk_owns primary key (userID, characterID),
    constraint fk_users foreign key (userID) references USERS(userID),
    constraint fk_characters foreign key (characterID) references CHARACTERS(characterID)
);

-- MISSION- CHARACTERS --
-- um para um obrigatorio
ALTER TABLE MISSION add
	constraint fk_returns_character foreign key (characterID) references CHARACTERS(characterID);


-- USER + MISSION + CHARACTER MANY TO MANY --
create table USER_CHARACTER_MISSION (
	userID int not null, 
    characterID int not null,
    missionID int not null,
    constraint pk_accomplish primary key (userID, characterID, missionID),
    constraint fk_users_accomplish foreign key (userID) references USERS(userID),
    constraint fk_characters_accomplish foreign key (characterID) references CHARACTERS(characterID),
    constraint fk_mission_accomplish foreign key (missionID) references MISSION(missionID)
);

-- CHARACTER - ABILITY --
ALTER TABLE ABILITY add
	constraint fk_character_ability foreign key (characterID) references CHARACTERS(characterID);
    
-- THEME - CHARACTER --
create table THEME_CHARACTER (
	characterID int not null, 
    themeID int not null,
    nome varchar(16) not null,
    avatar tinyblob,
    descricao varchar(500) not null,
    constraint pk_associates_character primary key (themeID, characterID),
    constraint fk_theme_characters foreign key (themeID) references THEME(themeID),
    constraint fk_characters_theme foreign key (characterID) references CHARACTERS(characterID)
);
-- THEME - CHARACTER --
create table THEME_ABILITY (
	abilityID int not null, 
    themeID int not null,
    nome varchar(16) not null,
    avatar tinyblob not null,
    descricao varchar(500) not null,
    constraint pk_associates_character primary key (themeID, abilityID),
    constraint fk_theme_ability foreign key (themeID) references THEME(themeID),
    constraint fk_ability_theme foreign key (abilityID) references ABILITY(abilityID)
);

-- ABILITY - ENERGIES --
create table ABILITY_E1 (
	abilityID int not null,
    energyID int not null,
    quantity int not null,
    CHECK(quantity >= 0),
    
    constraint pk_has_ability_e1 primary key (abilityID, energyID),
    constraint fk_e1_ability foreign key (energyID) references E1(e1ID),
    constraint fk_ability_e1 foreign key (abilityID) references ABILITY(abilityID)
);
create table ABILITY_E2 (
	abilityID int not null,
    energyID int not null,
    quantity int not null,
    CHECK(quantity >= 0),
    
    constraint pk_has_ability_e2 primary key (abilityID, energyID),
    constraint fk_e2_ability foreign key (energyID) references E2(e2ID),
    constraint fk_ability_e2 foreign key (abilityID) references ABILITY(abilityID)
);
create table ABILITY_E3 (
	abilityID int not null,
    energyID int not null,
    quantity int not null,
    CHECK(quantity >= 0),
    
    constraint pk_has_ability_e3 primary key (abilityID, energyID),
    constraint fk_e3_ability foreign key (energyID) references E3(e3ID),
    constraint fk_ability_e3 foreign key (abilityID) references ABILITY(abilityID)
);
create table ABILITY_E4 (
	abilityID int not null,
    energyID int not null,
    quantity int not null,
    CHECK(quantity >= 0),
    
    constraint pk_has_ability_e4 primary key (abilityID, energyID),
    constraint fk_e4_ability foreign key (energyID) references E4(e4ID),
    constraint fk_ability_e4 foreign key (abilityID) references ABILITY(abilityID)
);
create table ABILITY_E5 (
	abilityID int not null,
    energyID int not null,
    quantity int not null,
    CHECK(quantity >= 0),
    
    constraint pk_has_ability_e5 primary key (abilityID, energyID),
    constraint fk_e5_ability foreign key (energyID) references E5(e5ID),
    constraint fk_ability_e5 foreign key (abilityID) references ABILITY(abilityID)
);
