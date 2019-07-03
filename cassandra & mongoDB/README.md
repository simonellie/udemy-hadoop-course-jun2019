# Section 6 - Using non-relational data stores with Hadoop

## Cassandra
Works without a relational structure, but use key-value associations.

I simmply list here commands we make during exercise:
* install docker for Cassandra: ``docker pull cassandra``
* run container: ``docker run --name cassandra-local -d cassandra:latest``
* access to Cassandra: ``cqlsh``
* "schema" creation: ``create keyspace movielens with replication = {'class': 'SimpleStrategy', 'replication_factor':'1'} and durable_writes = true;``
* "schema" access: ``use movielens``
* create table "users" with key (for hashing) "user_id": ``CREATE TABLE users (user_id int, age int, gender text, occupation text, zip text, PRIMARY KEY (user_id));``

Then, we make an exercise:
* run ``wget http://media.sundog-soft.com/hadoop/CassandraSpark.py`` for get python script
* here:
  * we're reading file from HDFS (u.user from ml-100k datasets)
  * we're writing this, using Spark (v2), inside of our Cassandra
* run with: ``spark-submit --packages datastax:spark-cassandra-connector:2.0.0-M2-s_2.11 CassandraSpark.py``

simple insert syntax for Cassandra: ``insert into movielens.users (user_id,age,gender,occupation,zip) values (1,10,'M','technical','23424') ;``


## MongoDB

Good for documents, not for relational data (no great support to join and structured data).

From docker-hub:
* install: ``docker pull mongo``
* run: ``docker run --name mongoDB -d mongo``
* explore container: ``docker exec -it mongoDB bash``

Otherwise, it's possible to install it inside of virtual box image:
* execute ``su root``
* ``cd /var/lib/ambari-server/resources/stacks/HDP``
* check for the version, and cd in VERSION/services
* there's a connector we can download from: https://github.com/nikunjness/mongo-ambari.git
* so, call ``git clone https://github.com/nikunjness/mongo-ambari.git``
* restart service; ``sudo service ambari restart``
* when we access using administrator to Ambari, we select Action -> Services and we'll select mongoDB

For the example we need to install pymongo, with ``pip install pymongo``

Example run:
* ``spark-submit --packages org.mongodb.spark:mongo-spark-connector_2.11:2.0.0``

If we want to navigate inside of mongo...we only need to call ``mongo`` from bash.
