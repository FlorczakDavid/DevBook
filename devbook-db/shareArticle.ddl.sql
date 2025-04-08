
DROP TABLE IF EXISTS t_articles;
CREATE TABLE t_articles(
	id int GENERATED ALWAYS AS IDENTITY,
	url varchar(255),
	title varchar(100),
	image_path varchar(255),
	description varchar(255),
	author varchar(100)
);

SELECT * FROM t_articles;