#Dumb-Calculator

Course Assignment. An android app which does mundane calculations with the help of a web server backed by a cache.

##Pre-reqs:
1. Redis
2. Nodejs
3. Android Studio (with sdk 22, 23)

##Setting up redis:
Download the latest redis and install it. You next need to setup directories for redis persistence/temp files

	sudo mkdir /etc/redis
	sudo mkdir /var/redis
	sudo mkdir /var/redis/6379

##Starting the Web server:
To initialise redis server run `./redis\_init\_script start`
Then to start web server run (in web-server/) `npm start`

##Deploying the app:
For android app, open the project in android studio.
Replace the string web-server in strings.xml with the server address of the server started above.
Deploy it on your phone

##Architecture:
The architectural views can be found in the folder 4+1 views.
