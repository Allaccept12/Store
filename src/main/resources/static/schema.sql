--CREATE TABLE IF NOT EXISTS users(
--    users_id bigint NOT NULL auto_increment,
--    email VARCHAR(40) NOT NULL,
--    password VARCHAR(255) NOT NULL,
--    PRIMARY KEY(users_id)
--) character set utf8mb4;
--
--CREATE TABLE IF NOT EXISTS products(
--    products_id bigint not null auto_increment,
--    name varchar(255) not null,
--    price int not null,
--    primary key(products_id)
--)character set utf8mb4;
--
--CREATE TABLE IF NOT EXISTS orders(
--    orders_id bigint not null auto_increment,
--    users_id bigint not null,
--    products_id bigint not null,
--    primary key(orders_id),
--    FOREIGN KEY (users_id) REFERENCES users(users_id),
--    FOREIGN KEY (products_id) REFERENCES products(products_id)
--)character set utf8mb4;

insert into Product(product_id, price, name) values('product_1', 10000, 'spring 웹 개발')
insert into Product(product_id, price, name) values('product_2', 20000, 'spring 웹 개발 + Jquery')
insert into Product(product_id, price, name) values('product_3', 500000, 'spring 웹 개발 + JPA')
insert into Product(product_id, price, name) values('product_4', 1000000, 'spring 웹 개발 + DDD + MSA')