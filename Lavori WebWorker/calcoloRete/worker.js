var ipString;
var subnetString;
onmessage = function(e) {
	ipString = e.data.ip;
	subnetString = e.data.subnet;
	
	if(controlloVal(ipString) && controlloVal(subnetString)){
		var rete = calcoloRete(ipString.split("."), subnetString.split("."));
		var broadcast = calcoloBroadcast(rete.split("."), subnetString.split("."));
		var jsonData = {
			ip: ipString,
			subnet: subnetString,
			rete: rete,
			broadcast: broadcast
		}
		
		postMessage(jsonData);
	}else{
		console.log("errore nell'inserimento dei dati");
	}
}

/*funzione per il controllo di un ip/subnet*/
function controlloVal(stringa){
	var values = stringa.split(".");
	contaUno(values[1]);
	if(values.length == 4){
		for(const value of values){
			if(!controlloNum(value)) return false;
		}
			
		return true;
	}
	else{
		return false;
	}
}

/*funzione per il controllare se un numero 
è composto solo da valori numerici*/
function controlloNum(num){
	var value = parseInt(num, 10);
	
	if(isNaN(value) || value < 0 || value > 255) return false;
	
	return true;
}


/*calcolo dell'indirizzo di rete*/
function calcoloRete(ip, subnet){
	var text = "";
	
	for(let i = 0; i < 4; i++){
		var ipValue = parseInt(ip[i]);
		var subnetValue = parseInt(subnet[i]);
		
		text += "" + (ipValue & subnetValue) + ".";
	}
	
	return text;
}

/*funzione per il conteggio degli 1 in un numero decimale*/
function contaUno(num){
	var count = 0;
	
	for(var i = 0; i < num.length; i++){
		if(num[i] == '1') count ++;
	}
	
	return count;
}

function calcoloBroadcast(rete, subnet){
	var broadcast = "";
	var index = 0;  //variabile per il conteggio di quanti byte sono 255
	var numUno;
	
	for(let i = 0; i < 4; i++){
		if(subnet[i] == "255"){
			index ++;
			continue;
		}
			
		numUno = contaUno(parseInt(subnet[i]).toString(2));
		break;
	}
	
	var numeroHost = 8 - numUno;
	numeroHost = Math.pow(2, numeroHost);
	
	for(let i = 0; i < index; i ++)
		broadcast += rete[i] + "."
	
	broadcast += "" + (parseInt(rete[index]) + numeroHost - 1);
	
	for(let i = index + 1; i < 4; i++){
		broadcast += ".255";
	}

	return broadcast;
}