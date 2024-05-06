USE freshmenindustries;
CREATE TABLE IF NOT EXISTS games (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       PRIMARY KEY (id),
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       is_deleted BOOLEAN
)
