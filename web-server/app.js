var express = require('express');
var path = require('path');

var routes = require('./routes');
var app = express();

var http = require('http');

app.use('/', routes);

app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

app.use(function(err, req, res, next) {
  console.log('Error: ' + err);
  res.status(err.status || 500).json({ 'error': err.message});
});

port = '3000';
app.set('port', port);

var server = http.createServer(app);
server.listen(port);
server.on('listening', onListening);

function onListening() {
  var addr = server.address();
  console.log('Listening on ' + addr.port);
}
