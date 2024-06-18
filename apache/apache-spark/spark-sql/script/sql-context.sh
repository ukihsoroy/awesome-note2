./bin/spark-submit \
--class org.ko.spark.SQLContextApp \
--name SQLContextApp
--master local[2] \
/home/k.o/lib/spark-sql-1.0.jar \
/home/k.o/tmp/people.json