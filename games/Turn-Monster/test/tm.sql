DROP TABLE players;
DROP TABLE looted;

CREATE TABLE players (
  id serial4 primary key,
  name VARCHAR(255), 
  health INT4,
  mana INT4,
  level INT4,
  xp INT4
);

CREATE TABLE looted (
  id serial4 primary key,
  slot INT4,
  type VARCHAR(255),
  enchant VARCHAR(255)
);