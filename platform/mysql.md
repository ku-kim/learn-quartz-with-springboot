#!/usr/bin/env bash

# docker run
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=password -d -p 3306:3306 mysql:latest

# mysql setup
docker exec -it mysql-container bash

mysql -u root -p

show databases;

create database test_timer_database;

use test_timer_database;

# init .sql DDL