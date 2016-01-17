#Dumb-Calculator

Course Assignment. An android app which does mundane calculations with the help of a web server backed by a cache.

You need to setup Redis before using this

Download the latest redis and install it
You next need to setup directories for redis persistence/temp files

sudo mkdir /etc/redis
sudo mkdir /var/redis
sudo mkdir /var/redis/6379

To initialise redis server run ./redis\_init\_script start
Then to start web server run npm start (in web-server/)

For android app, open the project in android studio.
Replace the string web-server in strings.xml with the server address of the server started above.
Deploy it on your phone
