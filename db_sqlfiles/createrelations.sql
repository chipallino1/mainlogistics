alter table logistic_db.contacts
add foreign key (firm_id) references logistic_db.firms(Id);

alter table logistic_db.contacts
add foreign key (passwords_id) references logistic_db.passwords(Id);

alter table logistic_db.addresses
add foreign key (firm_id) references logistic_db.firms(Id);

alter table logistic_db.orders
add foreign key (route_id) references logistic_db.routes(Id);

alter table logistic_db.orders
add foreign key (producer_id) references logistic_db.firms(Id);

alter table logistic_db.orders
add foreign key (consumer_id) references logistic_db.firms(Id);

alter table logistic_db.routes_info
add foreign key (route_id) references logistic_db.routes(Id);

alter table logistic_db.firms
add foreign key (password_id) references logistic_db.passwords(Id);

alter table logistic_db.users
add foreign key (firm_id) references logistic_db.firms(Id);

alter table logistic_db.users
add foreign key (contact_id) references logistic_db.contacts(Id);


