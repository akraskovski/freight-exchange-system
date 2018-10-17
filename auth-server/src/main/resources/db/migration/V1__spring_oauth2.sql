-- For JDBC client storage

-- drop table if exists oauth_client_details;
-- CREATE TABLE oauth_client_details (
--   client_id               VARCHAR(256) PRIMARY KEY,
--   resource_ids            VARCHAR(256),
--   client_secret           VARCHAR(256),
--   scope                   VARCHAR(256),
--   authorized_grant_types  VARCHAR(256),
--   web_server_redirect_uri VARCHAR(256),
--   authorities             VARCHAR(256),
--   access_token_validity   INTEGER,
--   refresh_token_validity  INTEGER,
--   additional_information  VARCHAR(4096),
--   autoapprove             VARCHAR(256)
-- );
--
-- drop table if exists oauth_client_token;
-- CREATE TABLE oauth_client_token (
--   token_id          VARCHAR(256),
--   token             BYTEA,
--   authentication_id VARCHAR(256) PRIMARY KEY,
--   user_name         VARCHAR(256),
--   client_id         VARCHAR(256)
-- );

DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id          VARCHAR(256),
  token             BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name         VARCHAR(256),
  client_id         VARCHAR(256),
  authentication    BYTEA,
  refresh_token     VARCHAR(256)
);

DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id       VARCHAR(256),
  token          BYTEA,
  authentication BYTEA
);

-- DROP TABLE IF EXISTS oauth_code;
-- CREATE TABLE oauth_code (
--   code           VARCHAR(256),
--   authentication BYTEA
-- );
--
-- DROP TABLE IF EXISTS oauth_approvals;
-- CREATE TABLE oauth_approvals (
--   userId         VARCHAR(256),
--   clientId       VARCHAR(256),
--   scope          VARCHAR(256),
--   status         VARCHAR(256),
--   expiresAt      TIMESTAMP,
--   lastModifiedAt TIMESTAMP
-- );
