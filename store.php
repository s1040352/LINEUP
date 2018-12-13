<?php require_once("include/db_connection.php");?>
<?php
header("Content-type:text/html;charset=utf-8");  
if (mysqli_connect_errno()) {
		printf("Connect failed: %s\n", mysqli_connect_error());
		exit();
		}		


$sql = "SELECT st_name,st_phone FROM store WHERE id IN (SELECT id FROM store WHERE st_username = 's123456')";
		$result=mysqli_query($connection,$sql);
		while($e=mysqli_fetch_assoc($result)){
		$output[]=$e; 
		}

		print(json_encode($output)); 
		mysqli_close($connection);
?>