var xhttp;
var commentContainer;
var button;
var utente, commento;

$(document).ready(function(){
	commentContainer = document.getElementById("commentContainer");
	if(window.XMLHttpRequest){
		xhttp = new XMLHttpRequest(); 
	}
	
	xhttp.onload=function(){
		if(this.readyState==4 && this.status==200){
			//rimuovo tutti i  commenti per fare richiesta al server
			if(this.responseText != "commenti non trovati"){
				//posto tutti i commenti
				console.log(JSON.parse(this.responseText));
				postComments(JSON.parse(this.responseText));
			}
			else
				console.log(this.resposeText);
		}else{
			console.log(this.readyState);
		}
	}
	
	button = document.getElementById("bottone");
	button.addEventListener("click", function(){
		utente = document.getElementById("nomeUtente").value;
		commento = document.getElementById("textCommento").value;
		
		let stringa = "utente="+utente+"&commento="+commento+"&risposta=0&id_commento=0";
		console.log(stringa);
		postaCommento(stringa);
	});
	
	getComments();
});

function postaCommento(stringa){
console.log(stringa);
$.ajax({
		type: 'GET',
		url: "postaCommento.php",
		data: stringa,
		success: function(data){
			console.log(data);
			getComments();
			utente.value = '';
			commento.value = '';
		}
	});
}

function richiesta (stringa){
		//chiedo al server se il commento ha delle risposte
	xhttp.open("GET", stringa, true);
	xhttp.send();
}			

function getComments(){			
	//faccio richiesta al server di tutti  i commenti
	richiesta("main.php");
}
function postComments(comments){
	commentContainer.innerHTML = '';
	comments.forEach(postComment);	
	
	comments.forEach(postAnswer);
}

function postComment(comment){
	if(comment.risposta == 1)
		return; 
	
	let commentBox = document.createElement("div");
	commentBox.className = "media comment-box";
	
	let mediaBox = document.createElement("div");
	mediaBox.className = "media-left";
	commentBox.appendChild(mediaBox);
	
	let imgProfile = document.createElement("img");
	imgProfile.src = "https://ssl.gstatic.com/accounts/ui/avatar_2x.png";	
	mediaBox.appendChild(imgProfile);
	
	let mediaBody = document.createElement("div");
	mediaBody.className = "media-body";
	mediaBody.id = comment.id_commento;
	commentBox.appendChild(mediaBody);
	
	let mediaHead = document.createElement("h4");
	mediaHead.innerHTML = comment.nome;
	mediaHead.className = "media-heading";
	mediaBody.appendChild(mediaHead);
	
	let mediaText = document.createElement("p");
	mediaText.innerHTML = comment.commento;
	mediaBody.appendChild(mediaText);
	
	let responseText = document.createElement("a");
	responseText.href = "javascript:void(0);";
	responseText.id = "risposta"+comment.id_commento;
	responseText.innerHTML = "rispondi";
	responseText.setAttribute("onclick","rispondi('"+ comment.id_commento +"');");
	
	mediaBody.appendChild(responseText);
	commentContainer.appendChild(commentBox);
}

function rispondi(id_commento){
	let commento = document.getElementById(id_commento);
	let rispondi = document.getElementById("risposta"+id_commento);
	
	rispondi.innerHTML = "";
	let textNome = document.createTextNode("nome:");
	commento.appendChild(textNome);
	
	rispondi.appendChild(document.createElement("br"));
	
	let input = document.createElement("input");
	input.type = "text";
	input.id = "risp"+id_commento;
	input.className = "form-control";
	commento.appendChild(input);
	
	commento.appendChild(document.createElement("br"));
	
	let textCommento = document.createTextNode("commento:");
	commento.appendChild(textCommento);
	
	let textArea = document.createElement("textarea");
	textArea.className = "form-control";
	textArea.id = "comm"+id_commento;
	commento.appendChild(textArea);
	
	let bottoneRisposta = document.createElement("button");
	bottoneRisposta.type = "button";
	bottoneRisposta.className = "btn btn-primary";
	
	bottoneRisposta.setAttribute("onclick","creaStringa('"+ id_commento +"');");
	bottoneRisposta.innerHTML = "Aggiungi commento";
	commento.appendChild(bottoneRisposta);
}

function creaStringa(id_commento){
	let stringa = "utente="+document.getElementById("risp"+id_commento).value+"&commento="+document.getElementById("comm"+id_commento).value+"&risposta=1&id_commento="+id_commento;
	postaCommento(stringa);
}

function postAnswer(comment){
	if(comment.risposta == 0)
		return;
	
	let mediaBodyComment = document.createElement("div");
	mediaBodyComment.className = "media";
	
	let mediaBox = document.createElement("div");
	mediaBox.className = "media-left";
	mediaBodyComment.appendChild(mediaBox);
	
	let imgProfile = document.createElement("img");
	imgProfile.src = "https://ssl.gstatic.com/accounts/ui/avatar_2x.png";	
	mediaBox.appendChild(imgProfile);
	
	let mediaBody = document.createElement("div");
	mediaBody.className = "media-body";
	mediaBodyComment.appendChild(mediaBody);
	
	let mediaHead = document.createElement("h4");
	mediaHead.innerHTML = comment.nome;
	mediaHead.className = "media-heading";
	mediaBody.appendChild(mediaHead);
	
	let mediaText = document.createElement("p");
	mediaText.innerHTML = comment.commento;
	mediaBody.appendChild(mediaText);
		
	let mainComment = document.getElementById(comment.id_risposta);
	mainComment.appendChild(mediaBodyComment);
}