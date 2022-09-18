create schema qa2;

create table qa2.flights(
                            id serial unique primary key,
                            airplane_model varchar not null,
                            departure_time timestamp not null,
                            airport_departure_code varchar not null references qa2.airports(airport_code),
                            airport_arrival_code varchar not null references qa2.airports(airport_code),
                            flight_duration int not null,
                            count_place int not null,
                            flight_number varchar not null
);

create table qa2.countries(
                              id serial unique primary key,
                              name varchar unique,
                              country_code varchar unique
);

create table qa2.airports(
                             id serial unique,
                             city_name varchar not null,
                             airport_code varchar unique primary key,
                             code_country varchar not null references qa2.countries(country_code)
);

create table qa2.clients(
                            id serial unique primary key,
                            inn bigint unique,
                            passport_id int unique,
                            fio varchar not null,
                            gender varchar not null,
                            country_id int not null references qa2.countries(id)
);

create table qa2.tickets(
                            id serial unique,
                            client_id int not null references qa2.clients(id),
                            flight_id int not null references qa2.flights(id),
                            receiving_ticket timestamp not null,
                            number_ticket bigint unique
);