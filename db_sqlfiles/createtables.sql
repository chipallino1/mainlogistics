create database logistic_db;

create table logistic_db.firms(
	id int not null primary key auto_increment,
    name varchar(45) not null,
    id_address int not null,
    rating double,
    type varchar(45),
    check('rating'>=0 | 'rating'<=5)
    
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
    id_route int not null,
    order_date date not null,
    payment_day date not null
);

