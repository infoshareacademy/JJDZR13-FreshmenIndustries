USE freshmenindustries;
CREATE TABLE IF NOT EXISTS role (
                 id BIGINT NOT NULL AUTO_INCREMENT,
                 PRIMARY KEY (id),
                 role VARCHAR(255) NOT NULL,
                 user_id      BIGINT NOT NULL,
                 FOREIGN KEY (user_id) REFERENCES users(id)
)
