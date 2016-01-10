var redis = require("redis"),
    client = redis.createClient();

client.on('connect', function() {
	console.log('Redis connected');
});

module.exports = client;
