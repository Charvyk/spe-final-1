database='shubhanshu1902/spe_database'
docker build -t $database .
docker run -dp 127.0.0.1:3306:3306 $database