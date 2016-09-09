require('pg')

DROP TABLE players;

-- DROP TABLE looted;




CREATE TABLE players (
  id serial4 primary key, 
  name VARCHAR(255), 
  type VARCHAR(255),  
  level INT4, 
  xp INT4
  );

-- CREATE TABLE looted (
--   id serial4 primary key,
--   -- player_id INT4 references players(id),
--   slot INT4, 
--   type VARCHAR(255), 
--   enchant VARCHAR(255)
--   );