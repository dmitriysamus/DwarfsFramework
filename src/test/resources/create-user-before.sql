DELETE FROM roles_app;
DELETE FROM roles_bd;
DELETE FROM user_roles_app;
DELETE FROM user_roles_bd;
DELETE FROM users;


INSERT INTO roles_app (id, ROLE_NAME) VALUES
(1, 'COMMON'),(2, 'SILVER'),(3, 'GOLD');

INSERT INTO roles_bd (id, ROLE_NAME) VALUES
(1, 'ROLE_USER'),(2, 'ROLE_ADMIN'),(3, 'ROLE_MODERATOR');


INSERT INTO users (id, USER_NAME, USER_PASSWORD, USER_EMAIL, USER_CREATION_DATE, USER_LAST_VISITED_DATE) values
(1, 'Tester', '123456', 'tester@test.com', current_date, current_date),(2, 'Tester2', '234567', 'tester2@test.com', current_date, current_date);

INSERT INTO user_roles_bd VALUES
(1,1),(2,1),(2,2),(2,3);

--INSERT INTO user_roles_app VALUES
--(1,1),(2,1),(2,2),(2,3);

