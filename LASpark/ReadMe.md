# LASPARK  project:
Using SparkSQL to analysis LA Crimes happened in 2015

1. The source data comes from Los Angeles Open Data (https://data.lacity.org/A-Safe-City/LAPD-Crime-and-Collision-Raw-Data-for-2015/ttiz-7an8/data)

2. Download the data to local file with JSON format. Found that the format is not SparkSQL required JSON Format. (Sample downloaded format file: rowsTest.json)

3. DATA ETL: Wrote a Java program(FormatChange.java) to extract useful information and change to SparkSQL suitable JSON format(10 rows sample output: writeTest.json) with 220000+ row data.

4. Used SparkSQL(LAPDCrime.py) to query in JSON file. Got:Top five areas of crime most frequently happened. Top 5 most frequently crimal types in area 12. Top five areas that have most crimals between time 12:00 and 16:00

5. To run the program: $ pyspark LAPDCrime.py
