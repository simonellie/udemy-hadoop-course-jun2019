# Hadoop and Pig

From Ambari web application we can find a combo in which we can select "Pig View".
Here we can upload and execute pig scripts.

We can use sample pig run, ore pig run with Tez (so much faster).

## Pig commands

Watch lecture of this course from 19 to 23 for more informations about the following commands.
What we can do with relations:
 - **LOAD STORE DUMP**
 - **FILTER DISTINCT FOREACH/GENERATE MAPREDUCE STREAM SAMPLE**
 - **JOIN COGROUP GROUP CROSS CUBE**
 - **ORDER RANK LIMIT**
 - **UNION SPLIT**
 
Diagnostics:
 - **DESCRIBE**
 - **EXPLAIN**
 - **ILLUSTRATE**
 
UDF's:
 - **REGISTER** (from jar for example)
 - **DEFINE**
 - **IMPORT** (macros in pig script)

Other functions and loaders:
 - **Aggregation functions (AVG, CONCAT, COUNT and so on)**
 - **PigStorage**
 - **TextLoader**
 - **JsonLoader**
 - **AvroStorage**
 - **ParquetLoader**
 - **OrcStorage**
 - **HBaseStorage**
 