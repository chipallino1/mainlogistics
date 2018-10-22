create database logistic_db;

create table logistic_db.firms(
	id int not null primary key auto_increment,
    name varchar(45) not null,
    id_address int not null,
    rating double,
    type varchar(45),
    check(rating>=0 | rating<=5)
    
);

create table logistic_db.addresses(
	id int not null primary key auto_increment,
    street_name varchar(45) not null,
    street_num int not null,
    city varchar(45) not null,
    country varchar(45) not null
    
);

create table logistic_db.orders(
	id int not null primary key auto_increment,
    id_route int not null,
    order_date date not null,
    payment_day date not null
    
);

create table logistic_db.routes(
	id int not null primary key auto_increment,
    `from` varchar(45) not null,
    `to` varchar(45) not null,
    cost int not null,
    id_route_info int not null
);

create table logistic_db.routes_info(
	id int not null primary key auto_increment,
    date_start date not null,
    date_finish date not null,
    optimality varchar(45),
    length int not null
);

create table logistic_db.contacts(
	id int not null primary key auto_increment,
    firstname varchar(45) not null,
    lastname varchar(45) not null,
    phone_num varchar(45) not null,
    email varchar(45),
    id_firm int not null,
    id_passwords int not null
);
