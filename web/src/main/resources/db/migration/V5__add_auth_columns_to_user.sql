
ALTER TABLE users ADD COLUMN  role VARCHAR(255);
ALTER TABLE users ADD COLUMN email VARCHAR(255);
ALTER TABLE users ADD COLUMN password VARCHAR(500);
ALTER TABLE users ADD COLUMN is_enable BOOLEAN;
