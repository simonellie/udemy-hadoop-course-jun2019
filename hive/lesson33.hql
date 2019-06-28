CREATE VIEW IF NOT EXISTS topMovieIDs 
AS
SELECT movieID, COUNT(movieID) as ratingCount
  FROM RATINGS
GROUP BY movieID
ORDER BY ratingCount DESC;

SELECT names.name, ratingCount
  FROM topMovieIDs
  JOIN names
    ON topMovieIDs.movieID = names.movieID;

-- is possible to use CREATE/DROP...sql-like!;
