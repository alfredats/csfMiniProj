SELECT "INSERT INTO contact_mobile";
INSERT INTO addressBook.contact_mobile(
    name,
    mobile
) values 
    ('Fred', 123456),
    ('Wilma', 234567),
    ('Betty', 45678),
    ('Barney', 9784456);

SELECT "INSERT UNTO contact_email";
INSERT INTO addressBook.contact_email (
    name,
    email
) values 
    ('Fred', 'fred@gmail.com'),
    ('Wilma', 'wilma@gmail.com'),
    ('Betty', 'betty@gmail.com'),
    ('Barney', 'barney@gmail.com');