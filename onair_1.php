<?php require_once("include/db_connection.php");?>
<?php

function storeTIME($start1,$start2,$end1,$end2,$weekday,$holiday){
	global $connection;
		
	$query = "UPDATE onair SET start1 = '$start1' , start2 = '$start2' , end1 = '$end1' , end2 = '$end2' , weekday = '$weekday' , holiday = '$holiday' WHERE o_id IN(SELECT id FROM store WHERE st_username = 's123456')";
	$result = mysqli_query($connection, $query);
	if($result){
			$user = "SELECT * FROM onair WHERE end2 = '{$end2}'";
			$res = mysqli_query($connection, $user);
			while ($user = mysqli_fetch_assoc($res)){
				return $user;
			}
		}else{
				return false;
			}
}

$response = array("error" => FALSE);

if (isset($_POST['start1']) && isset($_POST['start2']) && isset($_POST['end1']) && isset($_POST['end2']) && isset($_POST['weekday']) && isset($_POST['holiday'])) {
	
	header('Content-type=application/json; charset=utf-8');
	header('Content-type: text/xml');
	
    // receiving the post params
    $start1 = $_POST['start1'];
    $start2 = $_POST['start2'];
    $end1 = $_POST['end1'];
    $end2 = $_POST['end2'];
    $weekday = $_POST['weekday'];
    $holiday = $_POST['holiday'];
	
	$user = storeTIME($start1,$start2,$end1,$end2,$weekday,$holiday);
	if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
			$response["user"]["start1"] = $user["start1"];
            $response["user"]["start2"] = $user["start2"];
            $response["user"]["end1"] = $user["end1"];
            $response["user"]["end2"] = $user["end2"];
			$response["user"]["weekday"] = $user["weekday"];
			$response["user"]["holiday"] = $user["holiday"];
            echo json_encode($response);
        } 
	else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred!";
            echo json_encode($response);
        }
    
}
else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!";
    echo json_encode($response);
}
?>