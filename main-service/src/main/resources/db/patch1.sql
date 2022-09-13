CREATE TABLE IF NOT EXISTS categories (
category_id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
name VARCHAR NOT NULL,
PRIMARY KEY (category_id));

CREATE TABLE IF NOT EXISTS users (
user_id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
email VARCHAR NOT NULL,
name VARCHAR NOT NULL);

CREATE TABLE IF NOT EXISTS events (
event_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
title VARCHAR NOT NULL ,
description VARCHAR NOT NULL ,
annotation VARCHAR NOT NULL ,
created_on timestamp NOT NULL ,
event_date timestamp NOT NULL ,
published_on timestamp ,
paid boolean NOT NULL ,
state VARCHAR NOT NULL ,
request_moderation boolean NOT NULL DEFAULT true ,
participant_limit int NOT NULL DEFAULT 0 ,
category_id BIGINT NOT NULL REFERENCES categories (category_id),
initiator bigint NOT NULL REFERENCES users (user_id),
PRIMARY KEY (event_id));

