CREATE TABLE Task(
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATE,
    priority VARCHAR(255),
    state VARCHAR(255)
);

    CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;