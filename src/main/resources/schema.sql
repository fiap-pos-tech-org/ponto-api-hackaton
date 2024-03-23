CREATE TABLE IF NOT EXISTS user (
    id bigint NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(100),
    creation_date timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS clock_registry (
    id bigint NOT NULL AUTO_INCREMENT,
    time_clock_id bigint NOT NULL,
    time timestamp NOT NULL,
--    check_in timestamp NOT NULL,
--    check_out timestamp,
    user_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);
