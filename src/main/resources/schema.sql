-- Spring Security default JDBC authentication schema.
-- Used by JdbcUserDetailsManager for database-backed authentication (no JWT).
-- CREATE TABLE IF NOT EXISTS makes this safe to run on every application startup.

CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (username, authority),
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);
