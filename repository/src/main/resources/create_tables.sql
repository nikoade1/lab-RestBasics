CREATE TABLE tags
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE gift_certificates
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(30) NOT NULL,
    description      VARCHAR(50),
    price            numeric,
    duration         INT,
    create_date      TIMESTAMP,
    last_update_date TIMESTAMP
);

CREATE TABLE gifts_to_tags
(
    gift_certificate_id int NOT NULL,
    tag_id              int NOT NULL,
    PRIMARY KEY (gift_certificate_id, tag_id),
    foreign key (gift_certificate_id) references gift_certificates (id),
    foreign key (tag_id) references tags (id)
)