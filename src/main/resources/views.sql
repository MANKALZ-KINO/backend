DROP VIEW IF EXISTS freeseats3;

CREATE VIEW freeseats3 AS
SELECT a.*, b.seat_id IS NOT NULL AS seat_taken
FROM (
         SELECT
             s.seat_id, s.row_num, s.seat_numb,
             t.theater_id, m.movie_id, m.show_number,
             m.movie_plan_date, m.movie_plan_id
         FROM cinema.seat s
                  JOIN cinema.theater t ON s.theater_id = t.theater_id
                  JOIN cinema.movie_plan m ON t.theater_id = m.theater_id
     ) a
         LEFT JOIN (
    SELECT
        t.seat_id, s.row_num, s.seat_numb,
        s.theater_id, m.movie_id, m.show_number,
        m.movie_plan_date, m.movie_plan_id
    FROM cinema.ticket t, cinema.seat s, cinema.movie_plan m where
              t.movieplan_id=m.movie_plan_id and
              m.theater_id=s.theater_id and
              t.seat_id=s.seat_id
) b
                   ON a.seat_id = b.seat_id
                       AND a.movie_plan_id = b.movie_plan_id
                       AND a.theater_id = b.theater_id

ORDER BY movie_plan_date, show_number, row_num, seat_numb;

SELECT DISTINCT movie_plan_id FROM freeseats3;

