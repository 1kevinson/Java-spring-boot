insert into user values(1001, sysdate(), 'AB');
insert into user values(1002, sysdate(), 'Jill');
insert into user values(1003, sysdate(), 'Jam');

insert into post values(10001, 'My first post', 1001);
insert into post values(10002, 'My second post', 1001);
insert into post values(10003, 'My third post', 1001);

insert into post values(10004, 'My reddit post', 1002);

insert into post values(10005, 'My twitter post', 1003);
insert into post values(10006, 'My tweet ads post', 1003);