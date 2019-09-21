-- DROP EXISTING TABLES
drop table if exists menudisrel;
drop table if exists ingdisrel;
drop table if exists inguserel;
drop table if exists ingredient;
drop table if exists dish;
drop table if exists menu;
drop table if exists user;

-- CREATE TABLES
create table if not exists user (
	id integer not null auto_increment, 
	email varchar(255), 
	first_name varchar(255), 
	last_name varchar(255), 
	primary key (id)
);

create table if not exists dish (
	id integer not null auto_increment, 
	name varchar(255), 
	primary key(id)
);

create table if not exists disuserel (
	dish_id integer not null, 
	user_id integer not null, 
	foreign key fk_dish_user(dish_id) references dish(id), 
	foreign key fk_user_dish(user_id) references user(id),
	primary key (dish_id, user_id)
);

create table if not exists ingredient (
	id integer not null auto_increment, 
	name varchar(255), 
	calories integer, 
	proteins integer, 
	fats integer, 
	carbohydrates integer, 
	primary key(id)
);

create table if not exists inguserel (
	ingredient_id integer not null, 
	user_id integer not null, 
	foreign key fk_ingredient_user(ingredient_id) references ingredient(id), 
	foreign key fk_user_ingredient(user_id) references user(id),
	primary key (ingredient_id, user_id)
);

create table if not exists ingdisrel (
	ingredient_id integer not null, 
	dish_id integer not null,
	foreign key fk_ingredient_dish(ingredient_id) references ingredient(id),
	foreign key fk_dish_ingredient(dish_id) references dish(id),
	primary key (ingredient_id, dish_id)
);

create table if not exists menu (
	id integer not null auto_increment, 
	user_id integer, 
	start_date DATE, 
	foreign key fk_menu_user(user_id) references user(id), 
	primary key(id)
);

create table if not exists menudisrel (
	menu_id integer not null, 
	dish_id integer not null, 
	dish_date DATETIME, 
	foreign key fk_menu_dish(menu_id) references menu(id), 
	foreign key fk_dish_menu(dish_id) references dish(id), 
	primary key(menu_id, dish_id, dish_date)
);

create table if not exists userconfiguration (	
	user_id integer not null,
	name varchar(255),
	value varchar(255), 
	foreign key fk_configuration_user(user_id) references user(id),
	primary key(user_id, name)
);

--create table if not exists meal (id integer not null auto_increment, name varchar(255), primary key(id));