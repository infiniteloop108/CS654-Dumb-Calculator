var express = require('express');
var router = express.Router();
var redis = require('./redis');

/* GET home page. */
router.get('/', function(req, res, next) {
	res.send('DAC server');
});

router.get('/api', function(req, res, next) {
	if ( !req.query.a || !req.query.b || !req.query.op )
	{
		res.status(400).json({'error':'Invalid format'});
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
	if (a==null || b==null || isNaN(a) || isNaN(b))
	{
		 res.status(400).json({'error':'Invalid numbers'});
		 return;
	}
	if (c==null || c<0 || c>4)
	{
		res.status(400).json({'error':'Invalid operator'});
		 return;
	}
	console.log("Got request for " +a + " " + b + " " + c);
	//Check cache
	query = a + "," + b + "," + c;
	redis.get(query, function(err, reply) {
		if(err) {
			res.status(500).json({'error':'internal server error'});
		} else {
			if(reply != null) {
				res.json({ 'result': reply,'cached': 'true'});
			} else {
				//If not found then compute and store
				answer = operate(a,b,c);
				redis.set(query, answer);
				res.json({ 'result': answer,'cached': 'false'});
			}
		}
	});
});
  
function operate(a,b,c) {
	ans = "NaN";
	if (c==1)
		ans = a+b;
	else if(c==2)
		ans = a-b;
	else if(c==3)
		ans = a*b;
	else if(c==4)
	{
		if( b==0 )
			return "NaN";
		ans = a/b;
	}
	if( ans != "NaN" ){
		ans = +ans.toFixed(8);
	}
	return ans;
}

module.exports = router;
