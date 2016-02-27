var express = require('express');
var path = require('path');

var app = express();

app.set('port', process.env.PORT || 3000);

app.get('/', function (req, res) {
  res.sendFile( __dirname + "/public/" + "ServiceImpl.class" );
});

var server = app.listen(app.get('port'), function() {
  console.log('Express server listening on port ' + server.address().port);
});

module.exports = app;
