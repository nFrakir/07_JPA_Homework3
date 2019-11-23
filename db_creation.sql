CREATE DATABASE bank;
SET GLOBAL time_zone="+2:0";

CREATE TABLE currencies (
	id INT NOT NULL AUTO_INCREMENT,
    abbreviation VARCHAR(3),
    PRIMARY KEY(id)
);

CREATE TABLE users (
	id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE billings (
	id INT NOT NULL AUTO_INCREMENT,
    total DOUBLE NOT NULL DEFAULT 0,
    currency_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY(id),
    UNIQUE KEY (currency_id, user_id),
    FOREIGN KEY (currency_id) REFERENCES currencies(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE rates (
	id INT NOT NULL AUTO_INCREMENT,
    currency_from INT NOT NULL,
    currency_to INT NOT NULL,
    rate DOUBLE NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    FOREIGN KEY (currency_from) REFERENCES currencies(id) ON DELETE CASCADE,
    FOREIGN KEY (currency_to) REFERENCES currencies(id) ON DELETE CASCADE
);

CREATE TABLE transactions (
	id INT NOT NULL AUTO_INCREMENT,
    action_date DATETIME NOT NULL DEFAULT now(),
    total DOUBLE NOT NULL,
    billing_id INT NOT NULL,
    user_id INT NOT NULL,
    incoming BOOLEAN NOT NULL DEFAULT true,
    PRIMARY KEY(id),
    FOREIGN KEY (billing_id) REFERENCES billings(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO currencies (abbreviation) VALUES ("UAH"), ("USD"), ("EUR");
INSERT INTO users (login) VALUES ("Maria"), ("Roman"), ("Elvira"), ("Vlad");

INSERT INTO rates VALUES
	(DEFAULT, 1, 2, 0.042),
    (DEFAULT, 1, 3, 0.037),
    (DEFAULT, 2, 1, 24.0),
    (DEFAULT, 3, 1, 27.0);
    
INSERT INTO billings (total, currency_id, user_id) VALUES
	(100, 1, 1), (10, 2, 1), (10, 3, 1),
    (200, 1, 2), (120, 2, 2), (120, 3, 2),
    (3000, 1, 3), (0, 2, 3), (0, 3, 3),
    (0, 1, 4), (0, 2, 4), (0, 3, 4);