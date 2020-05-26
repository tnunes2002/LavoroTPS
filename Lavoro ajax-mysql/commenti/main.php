<?php 
	$servername = "localhost";
	$username = "root";
	$password = "";
	$dbname = "commentidb";
	
	
	$conn = new mysqli($servername, $username, $password, $dbname);
	
	if($conn -> connect_error){
		die("Connection failed: " . $conn->connect_error);
	}
	
	$sql = "SELECT * FROM commenti";

	$result = $conn->query($sql);
	
	if($result->num_rows > 0){
		$array =  [];
		
		while($row = $result ->fetch_assoc()){
			array_push($array, array('commento' => $row['commento'], 'nome' => $row['nome'], 'id_commento' => $row['id_commento'], 'risposta' => $row['commento_risposta'], 'id_risposta' => $row['id_risposta']));
		}
		echo json_encode($array);
	} else {
		echo $sql;
	}
?>