var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
	res.send('DAC server');
});

router.get('/api', function(req, res, next) {
	if ( !req.query.a || !req.query.b || !req.query.op )
	{
		res.status(400).json({'error':'invalid format'});
		return;
	}
	res.json({ 'result': 'OK' });
});


module.exports = router;
