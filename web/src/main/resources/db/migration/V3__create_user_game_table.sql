CREATE TABLE play_game (
       id BIGINT PRIMARY KEY AUTO_INCREMENT,
       start_date DATE NOT NULL,
       end_date DATE,
       is_finished BOOLEAN,
       game_id BIGINT NOT NULL,
       FOREIGN KEY (game_id) REFERENCES games(id)
);
