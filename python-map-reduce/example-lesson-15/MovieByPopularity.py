from mrjob.job import MRJob
from mrjob.step import MRStep

# Rank movies by their popularity.
class MovieByPopularity(MRJob):
    def steps(self):
        return [
            MRStep(mapper=self.mapper_get_rating,
                   reducer=self.reducer_count_ratings),
            MRStep(reducer=self.reducer_sorted_output)
        ]

    def mapper_get_rating (self, _, line):
        (userID, movieID, rating, timestamp) = line.split('\t')
        yield movieID, 1

    def reducer_count_ratings (self, key, values):
        yield str(sum(values)).zfill(5), key

    def reducer_sorted_output (self, count, movies):
        for movie in movies:
            yield movie, count

if __name__=='__main__':
    MovieByPopularity.run()
