var button = document.getElementById("calcola");
var risultati = document.getElementById("risultati");

if(window.Worker){
	var worker = new Worker("worker.js");
	worker.onmessage = function(e) {
		aggiungiRete(e.data.rete, e.data.ip, e.data.subnet, e.data.broadcast);
	}
}else{
	console.log("worker not supported");
}

button.addEventListener("click", function(){
	var ipString = document.getElementById("ip").value;
	var subnetString = document.getElementById("subnet").value;
	
	var jsonData = {
		ip: ipString,
		subnet: subnetString
	};
	
	worker.postMessage(jsonData);
});

/*aggiungo l'indirizzo di rete al div risultati*/
function aggiungiRete(rete, ipString, subnetString, broadcast){
	var div = document.createElement("div");
	div.style.paddingTop = "10px";
	div.style.color = "black";
	div.innerHTML = "ip:" + ipString + "  subnet:" + subnetString + "  rete:" + rete + " broadcast:" + broadcast;
	risultati.appendChild(div);
}

