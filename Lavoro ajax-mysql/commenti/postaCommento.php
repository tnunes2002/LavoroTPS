<?php
	$utente = $_GET["utente"];
	$commento = $_GET["commento"];
	$risposta = $_GET["risposta"];
	$id_commento = $_GET["id_commento"];
	
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "commentidb";
	
	$conn = new mysqli($servername, $username, $password, $dbname);
	
	if($conn -> connect_error){
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "INSERT INTO commenti (id_commento, nome, commento, commento_risposta, id_risposta) VALUES(DEFAULT,'". $utente ."','". $commento ."', '".$risposta."', '".$id_commento."')";
	
	$result = $conn->query($sql) or die(mysqli_error($conn));
	
	echo $result;
?>