CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    body TEXT,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES users(id)
);