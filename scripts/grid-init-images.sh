echo 'Stopping and removing existing images'
docker stop selenium-chrome1
docker rm selenium-chrome1
docker stop selenium-ff1
docker rm selenium-ff1
docker stop selenium-hub
docker rm selenium-hub
echo 'Done'

echo 'Creating Selenium grid hub'
docker run -d -P --name selenium-hub -p 4444:4444 selenium/hub
ehco 'Created'

echo 'Creating Selenium chrome node'
docker run -d --name selenium-chrome1 --link selenium-hub:hub selenium/node-chrome
echo 'Created'

echo 'Creating Selenium firefox node'
docker run -d --name selenium-ff1 --link selenium-hub:hub selenium/node-firefox
echo 'Created'
