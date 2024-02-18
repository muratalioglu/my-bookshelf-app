mvn install -DskipTests
docker build -t my-bookshelf-app .
docker compose down --volumes
docker compose up