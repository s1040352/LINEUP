<?php require_once("include/db_connection.php");?>
<?php

function storeSP($waitingtime){
 global $connection;
  
 $query = "UPDATE linego SET waitingtime = '$waitingtime' ";
 
 /*$query = "INSERT INTO peaktime(";
 $query .= "peak_waiting_time)";
 $query .= "VALUES('{$peak_waiting_time}')";*/
 $result = mysqli_query($connection, $query);
 if($result){
   $user = "SELECT * FROM linego WHERE waitingtime = '{$waitingtime}'";
   $res = mysqli_query($connection, $user);
   while ($user = mysqli_fetch_assoc($res)){
    return $user;
   }
  }else{
    return false;
   }
}

$response = array("error" => FALSE);

if (isset($_POST['waitingtime'])) {
 
 header('Content-type=application/json; charset=utf-8');
 header('Content-type: text/xml');
    // receiving the post params
    $waitingtime = $_POST['waitingtime'];

 
 $user = storeSP($waitingtime);
 if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["user"]["waitingtime"] = $user["waitingtime"];
            echo json_encode($response);
        } 
        else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in time!";
            echo json_encode($response);
        }
    
}
else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!";
    echo json_encode($response);
}
?>