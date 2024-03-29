
ECREATE TABLE ers_users (
Id SERIAL PRIMARY KEY,

userName VARCHAR (250) UNIQUE NOT NULL,
password VARCHAR (250) NOT NULL,
role VARCHAR (250) NOT NULL
);


CREATE TABLE ers_reimbursements (

id SERIAL PRIMARY KEY,
author INT NOT NULL,
resolver INT,
description TEXT NOT NULL,
type VARCHAR (250) NOT NULL,
status VARCHAR (250) NOT NULL,
amount DOUBLE NOT NULL,

CONSTRAINT fk_author
	FOREIGN KEY (author)
	REFERENCES ers_users(id),
CONSTRAINT fk_resolver
	FOREIGN KEY (resolver)
	REFERENCES ers_users(id)
);


DROP TABLE IF EXISTS ers_users CASCADE;
DROP TABLE IF EXISTS ers_reimbursements CASCADE;


create type role as ENUM ('Employee', 'Manager'); 
create type type as ENUM ('Lodging', 'Travel', 'Food', 'Other'); 
create type status as ENUM ('Pending', 'Approved", "Denied');


INSERT INTO ers_users (userName, password, role)
VALUES ('default', 'guest', 'Employee'), ('admin', 'admin', 'Manager');

