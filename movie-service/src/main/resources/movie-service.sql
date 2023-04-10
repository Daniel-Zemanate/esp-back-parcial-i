INSERT INTO movies (name, genre, url_stream) SELECT * FROM (SELECT 'Dark Shadows'AS name, 'Terror'AS genre, 'https://www.netflix.com/title/70217909'AS url_stream) AS tmp
WHERE NOT EXISTS ( SELECT name FROM movies WHERE name = 'Dark Shadows' ) LIMIT 1;
INSERT INTO movies (name, genre, url_stream) SELECT * FROM (SELECT 'Gremlins' AS name, 'Terror' AS genre, 'https://www.netflix.com/title/562050' AS url_stream) AS tmp
WHERE NOT EXISTS ( SELECT name FROM movies WHERE name = 'Gremlins' ) LIMIT 1;
INSERT INTO movies (name, genre, url_stream) SELECT * FROM (SELECT 'Minions' AS name, 'Comedy' AS genre, 'https://www.netflix.com/title/80033394' AS url_stream) AS tmp
WHERE NOT EXISTS ( SELECT name FROM movies WHERE name = 'Minions' ) LIMIT 1;
INSERT INTO movies (name, genre, url_stream) SELECT * FROM (SELECT 'World War Z' AS name, 'Terror' AS genre, 'https://www.netflix.com/title/70262639' AS url_stream) AS tmp
WHERE NOT EXISTS ( SELECT name FROM movies WHERE name = 'World War Z' ) LIMIT 1;
INSERT INTO movies (name, genre, url_stream) SELECT * FROM (SELECT 'Kung-fu Panda' AS name, 'Comedy' AS genre, 'https://www.netflix.com/title/70075480' AS url_stream) AS tmp
WHERE NOT EXISTS ( SELECT name FROM movies WHERE name = 'Kung-fu Panda' ) LIMIT 1;
INSERT INTO movies (name, genre, url_stream) SELECT * FROM (SELECT 'Pacific Rim' AS name, 'Action' AS genre, 'https://www.netflix.com/title/70267241' AS url_stream) AS tmp
WHERE NOT EXISTS ( SELECT name FROM movies WHERE name = 'Pacific Rim' ) LIMIT 1;
INSERT INTO movies (name, genre, url_stream) SELECT * FROM (SELECT 'The Old Guard' AS name, 'Action' AS genre, 'https://www.netflix.com/title/81038963' AS url_stream) AS tmp
WHERE NOT EXISTS ( SELECT name FROM movies WHERE name = 'The Old Guard' ) LIMIT 1;
INSERT INTO movies (name, genre, url_stream) SELECT * FROM (SELECT 'Pride & Prejudice' AS name, 'Romance' AS genre, 'https://www.netflix.com/title/70032594' AS url_stream) AS tmp
WHERE NOT EXISTS ( SELECT name FROM movies WHERE name = 'Pride & Prejudice' ) LIMIT 1;
