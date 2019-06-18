# Excercise lesson 15

Rank movies by their popularity.

## Multi stage job

Moving from

    def steps (self) :
         return [
             MRStep(mapper=self.mapper_get_rating,
                    reducer=self.reducer_count_ratings)
         ]


to 

    def steps (self) :
         return [
             MRStep(mapper=self.mapper_get_rating,
                    reducer=self.reducer_count_ratings),
             MRStep(reducer=self.reducer_sorted_output)
         ]


