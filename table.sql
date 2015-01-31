create table PERSON (login VARCHAR(20), lastname VARCHAR(20) not null, firstname VARCHAR(20) not null, mail VARCHAR(50) not null, website VARCHAR(50), dateOfBirth Date, password char(40) character set ascii not null, primary key(login));

create table MYGROUP (ID int NOT NULL AUTO_INCREMENT, groupname varchar (50) UNIQUE, primary key(ID));
