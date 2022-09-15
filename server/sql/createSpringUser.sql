CREATE USER 'springUser'@'%'
    IDENTIFIED BY 'pass';

GRANT ALL PRIVILEGES ON addressBook.* TO 'springUser'@'%';