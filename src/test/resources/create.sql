DROP TABLE IF EXISTS first_phone_book;

CREATE TABLE first_phone_book (
  tel_num VARCHAR(11) PRIMARY KEY,
  add_date TIMESTAMP
);

DROP TABLE IF EXISTS second_phone_book;

CREATE TABLE second_phone_book (
  tel_num VARCHAR(11) PRIMARY KEY,
  add_date TIMESTAMP
);

DROP TABLE IF EXISTS event_log;

CREATE TABLE event_log (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tel_num VARCHAR(11) NOT NULL,
  event_date TIMESTAMP NOT NULL,
  decision VARCHAR(45) NOT NULL
);

INSERT INTO first_phone_book (tel_num, add_date) VALUES
  ('79999999999', CURRENT_TIMESTAMP()),
  ('78888888888', CURRENT_TIMESTAMP()),
  ('71111111111', CURRENT_TIMESTAMP());

INSERT INTO second_phone_book (tel_num, add_date) VALUES
  ('77777777777', CURRENT_TIMESTAMP()),
  ('78888888888', CURRENT_TIMESTAMP()),
  ('70000000000', CURRENT_TIMESTAMP());



