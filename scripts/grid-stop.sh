echo 'Stopping Selenium chrome node'
docker stop selenium-chrome1
echo 'Stopped'

echo 'Stopping Selenium firefox node'
docker stop selenium-ff1
echo 'Stopped'

echo 'Stopping Selenium grid hub'
docker stop selenium-hub
echo 'Stopped'
