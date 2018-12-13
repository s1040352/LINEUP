<?php require_once("include/db_connection.php");?>
<?php

function storePEAK($peak_waiting_time){
 global $connection;
  
 $query = "UPDATE peaktime SET peak_waiting_time = '$peak_waiting_time' WHERE p_id IN(SELECT id FROM store WHERE st_username = 's123456')";
 
 /*$query = "INSERT INTO peaktime(";
 $query .= "peak_waiting_time)";
 $query .= "VALUES('{$peak_waiting_time}')";*/
 $result = mysqli_query($connection, $query);
 if($result){
   $user = "SELECT * FROM peaktime WHERE peak_waiting_time = '{$peak_waiting_time}'";
   $res = mysqli_query($connection, $user);
   while ($user = mysqli_fetch_assoc($res)){
    return $user;
   }
  }else{
    return false;
   }
}

$response = array("error" => FALSE);

if (isset($_POST['peak_waiting_time'])) {
 
 header('Content-type=application/json; charset=utf-8');
 header('Content-type: text/xml');
    // receiving the post params
    $peak_waiting_time = $_POST['peak_waiting_time'];

 
 $user = storePEAK($peak_waiting_time);
 if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["user"]["peak_waiting_time"] = $user["peak_waiting_time"];
            echo json_encode($response);
        } 
        else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in peak time!";
            echo json_encode($response);
        }
    
}
else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!";
    echo json_encode($response);
}
?>