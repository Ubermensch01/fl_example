hub=$(docker ps -a -q --filter ancestor=selenium/hub)

echo "$hub"
