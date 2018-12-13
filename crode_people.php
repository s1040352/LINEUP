<?php
require_once("include/db_connection.php");


$sql = "select * from linego limit 1;";
$result = $connection->query($sql);

if ($result->num_rows > 0) {
 
 // output data of each row
 while($row1[] = $result->fetch_assoc()) {
 
 $json = json_encode($row1);
 
 
 }
} else {
 echo "0 results";
}
echo $json;
$connection->close();
?>
