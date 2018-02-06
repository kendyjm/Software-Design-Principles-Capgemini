drop table MEMBER if exists;

create table MEMBER (ID bigint identity primary key, EMAIL varchar(255) not null,
                        nom varchar(50) not null, prenom varchar(50), password varchar(255) not null, registered_date timestamp, age integer, unique(EMAIL));
                        
