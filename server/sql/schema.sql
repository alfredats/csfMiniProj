DROP VIEW IF EXISTS CONTACTS;
DROP SCHEMA IF EXISTS addressBook;

CREATE SCHEMA addressBook;

USE addressBook;

CREATE TABLE contact_mobile (
    name char(64) not null,
    mobile char(32) not null,

    primary key(mobile)
);

CREATE TABLE contact_email (
    name char(64) not null,
    email char(128) not null,

    primary key(email)
);

CREATE VIEW contacts AS 
    SELECT contact_mobile.name AS name,
        contact_mobile.mobile AS mobile,
        contact_email.email AS email
    FROM 
        contact_mobile, contact_email
    WHERE
        contact_mobile.name = contact_email.name;
