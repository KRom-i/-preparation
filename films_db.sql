
-------- data-base ---------

DROP TABLE IF EXISTS `films`;
CREATE TABLE `films` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `sessions`;
CREATE TABLE `sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_date_time` datetime NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `film_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_session_movie_idx` (`film_id`),
  CONSTRAINT `FK_session_film` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `session_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ticket_sesion_idx` (`session_id`),
  CONSTRAINT `FK_ticket_sesion` FOREIGN KEY (`session_id`) REFERENCES `sessions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-------- 1 ---------

SELECT 
		F1.title AS film_1,
		S1.start_date_time AS start_time_1,
		F1.duration AS duration_1,
		F2.title AS film_2,
		F2.start_date_time AS start_time_2,
		F2.duration AS duration_2
	FROM 
		films AS F1
	INNER JOIN 
		sessions AS S1
	ON 
		F1.id = S1.film_id
		
			INNER JOIN (
				SELECT 
					S.id AS session_id, 
					F.title, 
					S.start_date_time, 
					F.duration
				FROM 
					films AS F
				INNER JOIN 
					sessions AS S
                   ON 
					F.id = S.film_id
					) AS F2
					
WHERE 
		S1.start_date_time < DATE_ADD(F2.start_date_time, interval F2.duration MINUTE)
	AND 
		DATE_ADD(S1.start_date_time, interval F1.duration MINUTE) > F2.start_date_time
	AND 
		S1.id <> F2.session_id
	AND 
		S1.id < F2.session_id
;
	
	
-------- 2 ---------

SELECT 
		F1.title AS film_1,
		S1.start_date_time AS start_time_1,
		F1.duration AS duration_1,
		F2.start_date_time AS start_time_2,
		F2.start_date_time  - DATE_ADD(S1.start_date_time, interval F1.duration MINUTE) AS break_time_2
	FROM 
		films AS F1
	INNER JOIN 
		sessions AS S1
	ON 
		F1.id = S1.film_id
		
			INNER JOIN (
				SELECT 
					S.start_date_time
				FROM 
					films AS F
				INNER JOIN 
					sessions AS S
                   ON 
					F.id = S.film_id
					) AS F2
					
WHERE 
		DATE_ADD(S1.start_date_time, interval F1.duration + 30 MINUTE) < F2.start_date_time
	AND
		(SELECT COUNT(*) FROM sessions WHERE (start_date_time BETWEEN  S1.start_date_time AND F2.start_date_time)) = 0
	ORDER BY
		break_time_2 
	DESC
;	


-------- 3 ---------
	
	(
	SELECT
			f.title AS film_title,
			COUNT(t.id) AS count_tickets,
			ROUND(COUNT(t.id) / COUNT(DISTINCT s.id)) AS average_session,
			SUM(s.price) AS amount
		FROM
			films AS f
		INNER JOIN
			(sessions AS s, tickets AS t)
		ON
			(s.film_id = f.id AND t.session_id = s.id)
		GROUP BY
			f.id
		ORDER BY
			amount
		DESC
	)
UNION ALL
	(
	SELECT
			null,
			COUNT(t.id) AS count_tickets,
			ROUND(COUNT(t.id) / COUNT(DISTINCT s.id)) AS average_session,
			SUM(s.price) AS amount
		FROM
			films AS f
		INNER JOIN
			(sessions AS s, tickets AS t)
		ON
			(s.film_id = f.id AND t.session_id = s.id)
	);

	
	
-------- 4 ---------		

SELECT
    COUNT(t.id) AS count_tickets,
    SUM(s.price) AS amount_cash
FROM
    tickets AS t
INNER JOIN
	(sessions AS s, films AS f)
ON
    (t.session_id = s.id AND s.film_id = f.id)
GROUP BY 
	floor(hour(start_date_time) / 3)
ORDER BY
	start_date_time;