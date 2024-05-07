CREATE TABLE user_game
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    score       int,
    play_game_id BIGINT NOT NULL,
    FOREIGN KEY (play_game_id) REFERENCES play_game (id),
    user_id      BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
)
