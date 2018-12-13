<?php require_once("include/db_connection.php");?>
<?php

function storeSPIKE($spike_start_1_1, $spike_start_1_2, $spike_start_2_1, $spike_start_2_2, $spike_end_1_1, $spike_end_1_2, $spike_end_2_1, $spike_end_2_2,  $spike_waiting_time){
 global $connection;
  
 $query = "UPDATE spiketime SET spike_start_1_1 = '$spike_start_1_1' , spike_start_1_2 = '$spike_start_1_2' , spike_start_2_1 = '$spike_start_2_1' , spike_start_2_2 = '$spike_start_2_2' , spike_end_1_1 = '$spike_end_1_1' , spike_end_1_2 = '$spike_end_1_2' , spike_end_2_1 = '$spike_end_2_1' , spike_end_2_2 = '$spike_end_2_2' , spike_waiting_time = '$spike_waiting_time' WHERE s_id IN(SELECT id FROM store WHERE st_username = 's123456')";
 
 /*$query = "INSERT INTO spiketime(";
 $query .= "spike_start_1_1, spike_start_1_2, spike_start_2_1, spike_start_2_2, spike_end_1_1, spike_end_1_2, spike_end_2_1, spike_end_2_2,  spike_waiting_time)";
 $query .= "VALUES('{$spike_start_1_1}','{$spike_start_1_2}', '{$spike_start_2_1}','{$spike_start_2_2}','{$spike_end_1_1}','{$spike_end_1_2}','{$spike_end_2_1}','{$spike_end_2_2}','{$spike_waiting_time}')";*/
 $result = mysqli_query($connection, $query);
 if($result){
   $user = "SELECT * FROM spiketime WHERE spike_start_2_2 = '{$spike_start_2_2}'";
   $res = mysqli_query($connection, $user);
   while ($user = mysqli_fetch_assoc($res)){
    return $user;
   }
  }else{
    return false;
   }
}

$response = array("error" => FALSE);

if(isset($_POST['spike_start_1_1']) && isset($_POST['spike_start_1_2']) && isset($_POST['spike_start_2_1']) && isset($_POST['spike_start_2_2']) && isset($_POST['spike_end_1_1']) && isset($_POST['spike_end_1_2']) && isset($_POST['spike_end_2_1']) && isset($_POST['spike_end_2_2']) && isset($_POST['spike_waiting_time'])) {
 
    header('Content-type=application/json; charset=utf-8');
 header('Content-type: text/xml');
 
 // receiving the post params
    $spike_start_1_1 = $_POST['spike_start_1_1'];
 $spike_start_1_2 = $_POST['spike_start_1_2'];
    $spike_start_2_1 = $_POST['spike_start_2_1'];
 $spike_start_2_2 = $_POST['spike_start_2_2'];
    $spike_end_1_1 = $_POST['spike_end_1_1'];
 $spike_end_1_2 = $_POST['spike_end_1_2'];
    $spike_end_2_1 = $_POST['spike_end_2_1'];
    $spike_end_2_2 = $_POST['spike_end_2_2'];
    $spike_waiting_time = $_POST['spike_waiting_time'];
 
 $user = storeSPIKE($spike_start_1_1, $spike_start_1_2, $spike_start_2_1, $spike_start_2_2, $spike_end_1_1, $spike_end_1_2, $spike_end_2_1, $spike_end_2_2,$spike_waiting_time);
 if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["user"]["spike_start_1_1"] = $user["spike_start_1_1"];
   $response["user"]["spike_start_1_2"] = $user["spike_start_1_2"];
            $response["user"]["spike_start_2_1"] = $user["spike_start_2_1"];
   $response["user"]["spike_start_2_2"] = $user["spike_start_2_2"];
            $response["user"]["spike_end_1_1"] = $user["spike_end_1_1"];
   $response["user"]["spike_end_1_2"] = $user["spike_end_1_2"];
            $response["user"]["spike_end_2_1"] = $user["spike_end_2_1"];                
            $response["user"]["spike_end_2_2"] = $user["spike_end_2_2"];
            $response["user"]["spike_waiting_time"] = $user["spike_waiting_time"];
            echo json_encode($response);
        } 
        else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in spile time!";
            echo json_encode($response);
        }
    
}
else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!";
    echo json_encode($response);
}
?>