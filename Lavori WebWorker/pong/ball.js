var wait = 0;
		  
onmessage = function(e) {

	e.data.palla.x += e.data.palla.x_speed;
	e.data.palla.y += e.data.palla.y_speed;
	
	if(e.data.palla.x + e.data.palla.raggio> 1000) e.data.palla.x_speed *= -1;
	if(e.data.palla.x - e.data.palla.raggio < 0) e.data.palla.x_speed *= -1;
	
	if(e.data.palla.y - e.data.palla.raggio < 0) e.data.palla.y_speed *= -1;
	if(e.data.palla.y + e.data.palla.raggio> 1000){
		e.data.palla = initializeBall(e.data.palla);
		e.data.palla.vite -= 1;
	}
	
	if(wait == 0){
		if((e.data.palla.y >= e.data.paletta.y && e.data.palla.y + e.data.palla.raggio <= e.data.paletta.y + e.data.paletta.height) && (e.data.palla.x - e.data.palla.raggio >= e.data.paletta.x && e.data.palla.x - e.data.palla.raggio <= e.data.paletta.x + e.data.paletta.width)){
						e.data.palla.y_speed *= -1;
						wait = 5;
					}
	}else{
		wait -= 1;
	}
	
	
	postMessage(JSON.parse(JSON.stringify(e.data)));
}

var initializeBall = function(palla){
		palla.x = 500;
		palla.y = 500;
		
		palla.x_speed = Math.floor(Math.random() * (10));
		palla.y_speed = 10 - palla.x_speed;
		
		return palla;
	}