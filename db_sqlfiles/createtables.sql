create database logistic_db;

create table logistic_db.firms(
	id int not null primary key auto_increment,
    name varchar(45) not null,
    id_address int not null,
    rating double,
    type varchar(45),
    check('rating'>=0 | 'rating'<=5)
    
);

