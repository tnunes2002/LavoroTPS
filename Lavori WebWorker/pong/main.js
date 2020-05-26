//tasti attualmente premuti
			var keysDown = {};

			window.addEventListener("keydown", function(event) {
			  keysDown[event.keyCode] = true;
			});

			window.addEventListener("keyup", function(event) {
			  delete keysDown[event.keyCode];
			});
			
			if(window.Worker){
				var worker = new Worker("ball.js");
				worker.onmessage = function(e) {
					palla = e.data.palla;
					if(palla.vite == 0){
						running = false;
						draw("FFFFFF");
						context.fillStyle = "red";
						context.font = "bold 32px Arial";
						context.fillText("GAME OVER", (canvas.width / 2) - 75, (canvas.height / 2) + 8);
					}
				  }
			}else{
				console.log("worker not supported");
			}

			
			var canvas = document.createElement('canvas');
			var width = 1000;
			var height = 1000;
			canvas.width = width;
			canvas.height = height;
			var running = true;
			
			var paletta = {
				width: 80,
				height: 20,
				x: 460,
				y: 900
			};
			
			var palla = {
				raggio: 20,
				x: 500,
				y: 500,
				x_speed: 6,
				y_speed: -5,
				vite: 3
			};
			
			
			window.onload = function() {
				document.body.appendChild(canvas);
			};
			
			var animate = window.requestAnimationFrame ||
			  window.webkitRequestAnimationFrame ||
			  window.mozRequestAnimationFrame ||
			  function(callback) { window.setTimeout(callback, 1000/60) };
			
			var context = canvas.getContext('2d');
					
			var aggiorna = function(){
					if(running){
						update();
						draw("#FFFFFF");
						animate(aggiorna);
					}
			}
			
			animate(aggiorna);
			
			var draw = function(color){
				//disegno lo sfondo
				context.fillStyle = "#000000";
				context.fillRect(0, 0, width, height);
				
				//disegno la paletta
				context.fillStyle = color;
				context.fillRect(paletta.x, paletta.y, paletta.width, paletta.height);
				
				//disegno la palla
				context.beginPath();
				context.arc(palla.x, palla.y, palla.raggio, 2 * Math.PI, false);
				context.fillStyle = color;
				context.fill();
				
				//disegno le vite
				for(var i = 0; i < palla.vite; i++){
					context.beginPath();
					context.arc(i*30 + 20, 20, 12, 2 * Math.PI, false);
					context.fill();
				
				}
			
			};
						
			var update = function(){
				worker.postMessage({palla, paletta});
					
				for(var key in keysDown) {
					var value = Number(key);
					if(value == 37) { // freccia sinistra
						if(paletta.x > 0) paletta.x -= 10;
					} else if (value == 39) { // freccia destra
						if(paletta.x < 1000 - paletta.width) paletta.x += 10;						
					}
				  }
			
			};
			
			