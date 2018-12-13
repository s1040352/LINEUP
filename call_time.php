<?php require_once("include/db_connection.php");?>
<?php
     if ($connection->connect_error) {
   die("Connection failed: " . $connection->connect_error);
  } 

 $sql1 = "DELETE FROM linego WHERE id LIMIT 1";
  $result = $connection->query($sql1);

  $sql = "SELECT * FROM linego";
  $res = $connection->query($sql);
  $list_arr=array();
  $list_arr1=array();
  $i=0;
  
  while($list=mysqli_fetch_array($res)){  //判斷是否還有資料沒有取完，如果取完，則停止while迴圈。
  $list_arr[$i]=$list;
  $l=$list_arr[$i][0];
  $list_arr1[$i] = ($i+1)*$list_arr[$i][4];
  $sql2="UPDATE linego SET linetime = '$list_arr1[$i]' WHERE id ='$l' "; 
  $res2 = $connection->query($sql2);
  $i++;
  }
  print_r($list_arr); //顯示查詢出來資料所組成的二維陣列內容。
  print_r($list_arr1);
  $connection->close();

  
?>