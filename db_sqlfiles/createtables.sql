drop database logistic_db; 
create database logistic_db;

create table logistic_db.firms(
	id int not null primary key auto_increment,
    firm_name varchar(45) not null,
    rating double,
    firm_type varchar(45),
    check(rating>=0 | rating<=5),
    description varchar(100),
    email varchar(45) not null
    
);

create table logistic_db.addresses(
	id int not null primary key auto_increment,
    street_name varchar(45) not null,
    street_num int not null,
    city varchar(45) not null,
    country varchar(45) not null,
    firm_id int not null
    
);

create table logistic_db.orders(
	id int not null primary key auto_increment,
    route_id int not null,
    order_date date not null,
    payment_day date not null,
    producer_id int not null,
    consumer_id int not null
    
);

create table logistic_db.routes(
	id int not null primary key auto_increment,
    point_from varchar(45) not null,
    point_to varchar(45) not null,
    cost int not null
);

create table logistic_db.routes_info(
	id int not null primary key auto_increment,
    date_start date not null,
    date_finish date not null,
    optimality varchar(45),
    length int not null,
    route_id int not null
);

create table logistic_db.contacts(
	id int not null primary key auto_increment,
    first_name varchar(45) not null,
    last_name varchar(45) not null,
    phone_num varchar(45) not null,
    email varchar(45),
    firm_id int,
    passwords_id int not null
);

create table logistic_db.passwords(
	id int not null primary key auto_increment,
    pass_hash varchar(300),
    salt varchar(300)
);