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
	try 
	{	
		a = parseFloat(req.query.a);
		b = parseFloat(req.query.b);
		c = parseInt(req.query.op);

	} catch (ex) {
		res.status(400).json({'error':'Invalid numbers'});
		return;
	}
	if (!a || !b || isNaN(a) || isNaN(b))
	{
		 res.status(400).json({'error':'Invalid numbers'});
		 return;
	}
	if (!c || c<0 || c>4)
	{
		res.status(400).json({'error':'Invalid operator'});
		 return;
	}
	//Check cache


	//If not found then compute
	if (c==1)
	{

	}
	else if(c==2)
	{

	}
	else if(c==3)
	{

	}
	else if(c==4)
	{

	}
	console.log(a);
	console.log(b);
	console.log(c);
	res.json({ 'result': 'OK' });
});


module.exports = router;
