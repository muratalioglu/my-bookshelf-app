mvn install -DskipTests
docker network create my-bookshelf-api-network
docker volume rm my-bookshelf-api_mysql-data
docker compose up