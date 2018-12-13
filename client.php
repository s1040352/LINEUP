<?php require_once("include/db_connection.php");?>
<?php
$query = "SELECT tel,name FROM linego";
 $result=mysqli_query($connection,$query);//user資料表名稱
 while($row=mysqli_fetch_assoc($result)){
 $tmp[]=$row;
 }
 echo json_encode($tmp);
 mysqli_close($connection);
?>