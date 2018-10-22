alter table logistic_db.contacts
add foreign key (id_firm) references logistic_db.firms(Id);

alter table logistic_db.contacts
add foreign key (id_passwords) references logistic_db.passwords(Id);

alter table logistic_db.firms
add foreign key (id_address) references logistic_db.addresses(Id);

alter table logistic_db.orders
add foreign key (id_route) references logistic_db.routes(Id);

alter table logistic_db.routes
add foreign key (id_route_info) references logistic_db.routes_info(Id);


