/*
PROBLEM:
 - find all movies with an average rating less than 2.0
 - sort them by the total number of ratings
*/


ratings = LOAD '/user/maria_dev/data/u.data' AS (userID:int, movieID:int, rating:int, ratingTime:int);
metadata = LOAD '/user/maria_dev/data/u.item' USING PigStorage('|')
	AS (movieID:int, movieTitle:chararray, releaseDate:chararray, videoRealese:chararray, imdblink:chararray);

nameLookup = FOREACH metadata GENERATE movieID, movieTitle,
	ToUnixTime(ToDate(releaseDate, 'dd-MMM-yyyy')) AS releaseTime;

ratingsByMovie = GROUP ratings BY movieID;
avgRatings = FOREACH ratingsByMovie GENERATE group as movieID, AVG(ratings.rating) as avgRating, COUNT(ratings.rating) as nrOfRatings;
lessThenTwoStartRatings = FILTER avgRatings BY avgRating < 2.0;

twoStarsWithData = JOIN lessThenTwoStartRatings BY movieID, nameLookup BY movieID;

finalResult = FOREACH twoStarsWithData GENERATE nameLookup::movieTitle AS movieName,
	lessThenTwoStartRatings::avgRating AS avgRating, lessThenTwoStartRatings::nrOfRatings AS nrOfRatings;

finalResultSorted = ORDER finalResult BY nrOfRatings DESC;
DUMP finalResultSorted;
