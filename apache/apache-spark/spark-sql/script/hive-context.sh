./bin/spark-submit \
--class org.ko.spark.HiveContextApp \
--name HiveContextApp
--master local[2] \
--jars /home/k.o/lib/mysql-connector-java-5.1.27.jar
/home/k.o/lib/spark-sql-1.0.jar