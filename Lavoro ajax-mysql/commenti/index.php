<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	
	<title></title>
  </head>
  <body>
	<div style="margin: auto;width: 50%;">
		<div class="form-group">
			<form class="" action="index.html" method="post">
			  nome: <br><input type="text" class="form-control" name="name" id="nomeUtente" placeholder="Inserisci il tuo nome"><br><br>
			  commento: <br><textarea name="comment" class="form-control" rows="10" id="textCommento" cols="15" placecholder="Inserisci il commento"></textarea>
			</form>
		</div>
		<button type="button" style="margin: auto;" class="btn btn-primary" id="bottone" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">Aggiungi commento</button>
	</div>
    
	<div class="container" id="commentContainer">

	</div>
    <script src="ajax.js"></script>
	
  </body>
</html>
