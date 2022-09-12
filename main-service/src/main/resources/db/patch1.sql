CREATE TABLE IF NOT EXISTS events (
event_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    title varchar NOT NULL ,
    description varchar NOT NULL ,
    annotation varchar NOT NULL ,
    created_on timestamp NOT NULL ,
    event_date timestamp NOT NULL ,
    published_on timestamp ,
    paid boolean NOT NULL ,
    state varchar NOT NULL ,
    request_moderation boolean NOT NULL DEFAULT true ,
    participant_limit int NOT NULL DEFAULT 0 ,
    category_id bigint NOT NULL ,
    initiator bigint NOT NULL ,
    PRIMARY KEY (event_id));