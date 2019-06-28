import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;


public class WorstMovies {
    public static void main (String[] args) {
        SparkConf conf = new SparkConf().setAppName("WorstMovies");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Laod up the raw u.data file
        JavaRDD<String> lines = sc.textFile("hdfs://user/maria_dev/data/u.data");

        // Convert to (movieID, (rating, 1.0))
        JavaPairRDD<String, Tuple2> movieRatings = lines.mapToPair(new PairFunction<String, String, Tuple2>() {
            public Tuple2<String, Tuple2> call(String s) throws Exception {
                String[] elems = s.split("\t");
                return new Tuple2(Integer.parseInt(elems[1]), new Tuple2(Double.parseDouble(elems[2]) , 1));
            }
        });

        // Reduce to (movieID, (sumOfRatings, totalRatings))
        JavaPairRDD ratingTotalsAndCount = movieRatings.reduceByKey(new Function2<Tuple2, Tuple2, Tuple2>() {
            public Tuple2 call(Tuple2 movie1, Tuple2 movie2) throws Exception {
                return new Tuple2((Double)movie1._1 + (Double)movie2._1, (Integer)movie1._1() + (Integer)movie2._2());
            }
        });

        // Map to (rating, averageRating)
        //ratingTotalsAndCount.mapValues(new Function<Tuple2,Tuple2>() {
        //});

    }

}

/*

    # Map to (rating, averageRating)
    averageRatings = ratingTotalsAndCount.mapValues(lambda totalAndCount : totalAndCount[0] / totalAndCount[1])

    # Sort by average rating
    sortedMovies = averageRatings.sortBy(lambda x: x[1])

    # Take the top 10 results
    results = sortedMovies.take(10)

* */