echo 'Starting up Selenium grid hub'
docker start selenium-hub
echo 'Done'

echo 'Starting up Selenium Chrome node'
docker start selenium-chrome1
echo 'Done'

echo 'Starting up Selenium Firefox node'
docker start selenium-ff1
echo 'Done'
