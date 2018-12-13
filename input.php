<html><head>
<title>查詢結果</title>
</head><body>
</body></html>

<?php   
 require_once("include/db_connection.php");
global $connection;
  $username='V123';
  $sql = "select * from users where username = '$username'";
  $result = mysqli_query($connection, $sql);

      
  
  while($row = mysqli_fetch_array($result))
  {
     echo $row['name']." ";
  // echo $row['tel']."<br>";  
      echo "排隊成功" ;
   $flag = TRUE;
  }
if(@$flag==TRUE){
  $sql1="INSERT INTO linego(username,tel,name) SELECT username,tel,name FROM users where username = '$username'";
   $result2 = mysqli_query($connection, $sql1);}
   else{
    echo "無此帳號 請重新輸入";
   }
     

?>