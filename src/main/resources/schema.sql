DROP TABLE IF EXISTS game_type;

CREATE TABLE game_type
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS video_games;

CREATE TABLE video_games
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    title  VARCHAR(250) NOT NULL,
    type   INT(1)       NOT NULL,
    status TINYINT(1),
    FOREIGN KEY (type) REFERENCES game_type (id)
);

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    username       VARCHAR(250) NOT NULL,
    loyalty_points INT          NOT NULL
);

DROP TABLE IF EXISTS rents;

CREATE TABLE rents
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT  NOT NULL,
    vg_id           INT  NOT NULL,
    rented          DATE NOT NULL,
    promised_return DATE,
    returned        DATE,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (vg_id) REFERENCES video_games (id)
);


