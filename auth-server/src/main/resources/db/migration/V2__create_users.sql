DROP TABLE IF EXISTS domain_user_details;
CREATE TABLE domain_user_details (
  id        VARCHAR(255) PRIMARY KEY,
  email     VARCHAR(255) UNIQUE NOT NULL,
  password  VARCHAR(255)        NOT NULL,
  authority VARCHAR(255)        NOT NULL,
  is_active  BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO domain_user_details
VALUES ('1', 'fes-admin@gmail.com', '$2a$10$pOek0.3/0l7Snc0VW90I4eVdqgj7qrLy5mVIpNLNCYRyE69ATYdpa', 'ROLE_ADMIN', TRUE);