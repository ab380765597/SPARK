import re
import json
import sys
from pyspark import SparkConf, SparkContext
from pyspark.sql import SQLContext, Row
#import collections
conf = SparkConf().setMaster("local").setAppName("SparkSQL")
sc = SparkContext(conf = conf)
sqlContext = SQLContext(sc)
#/user/cloudera/pyspark/myfileObj.json
#/home/cloudera/Desktop/User/sparkpy/myfileObj.json
biz = sqlContext.read.json("/user/cloudera/pyspark/OrigChange.json")
biz.printSchema()
biz.registerTempTable("crime")

# SQL can be run over DataFrames that have been registered as a table.
#1. Find top five crime happened area
# goodRes = sqlContext.sql("SELECT areaName, area , COUNT(area) AS Number_of_Crime_per_Year FROM crime GROUP BY areaName, area ORDER BY COUNT(area) DESC LIMIT 5")
#2. Find Top five areas that have most crimals between time 12:00 and 16:00
#goodRes = sqlContext.sql("SELECT areaName, area , COUNT(area) AS Number_of_Crime_in_Four_Hour FROM crime WHERE timeOCC BETWEEN 1200 AND 1600 GROUP BY areaName, area ORDER BY COUNT(area) DESC LIMIT 5")
#3. Find the top 5 crimal types in area 12.
goodRes = sqlContext.sql("SELECT crimeCode, crimeDesc , COUNT(crimeCode) AS Number_of_Crime_in_This_Type FROM crime WHERE area=12 GROUP BY crimeCode, crimeDesc ORDER BY COUNT(crimeCode) DESC LIMIT 5")



# The results of SQL queries are RDDs and support all the normal RDD operations.
f = open('out.txt', 'w')

for res in goodRes.collect():
  print(res)
  f.write(str(res))

f.close()
