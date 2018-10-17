DROP TABLE IF EXISTS domain_user_details;
CREATE TABLE domain_user_details (
  id       VARCHAR(255) PRIMARY KEY,
  email    VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255)        NOT NULL
);

DROP TABLE IF EXISTS domain_user_details_authorities;
CREATE TABLE domain_user_details_authorities (
  domain_user_details_id VARCHAR(255),
  authorities            VARCHAR(255)
);

ALTER TABLE IF EXISTS domain_user_details_authorities
  ADD CONSTRAINT "fk_domain_user_details"
FOREIGN KEY (domain_user_details_id)
REFERENCES domain_user_details;

INSERT INTO domain_user_details
VALUES ('1', 'admin@admin.com', '$2a$10$pOek0.3/0l7Snc0VW90I4eVdqgj7qrLy5mVIpNLNCYRyE69ATYdpa');

INSERT INTO domain_user_details_authorities
VALUES ('1', 'ROLE_ADMIN');