# _Hair Salon_

#### By _Ben Vissotzky_

## Description

_A website admin page for a hair salon. Will let employees add stylists and clients for individual stylists. _

## Setup/Installation Requirements

* _Copy the repository from GitHub_
* _Compile the .java and run in console_

## Installation Instructions
- Download repository
- Start Postgress Database
- In PSQL:
  - CREATE DATABASE hair_salon;
  - \c hair_salon;
  - CREATE TABLE clients (id serial primary key, name varchar, stylist_id int);
  - CREATE TABLE stylists (id serial primary key, name varchar);
  - CREATE DATABASE hair_salon WITH TEMPLATE hair_salon;


## BDD
- Create new Stylist: "Jeremy"
- Create new Client: "Bill"
- Assign client to stylist: "Jeremy" stylist for "Bill"
- Update information for Stylist: "Jeremy Rogers"
- Update information for Client: "Billy Bob"
- Delete clients: delete "Billy Bob"; "Client list empty"
- Delete Stylist: delete "Jeremy Rogers", "Stylist list empty"

## GitHub link
https://github.com/benviss/epic-java-postgres-hair_salon

## Licensing

* MIT

Copyright (c) 2016 **_Ben Vissotzky_**
