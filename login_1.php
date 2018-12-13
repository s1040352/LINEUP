<?php
require_once 'include/db_functions_1.php';
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['st_username']) && isset($_POST['password'])) {
 	header('Content-type=application/json; charset=utf-8');
	header('Content-type: text/xml');
    // receiving the post params
    $st_username = $_POST['st_username'];
    $password = $_POST['password'];
 
    // get the user by email and password
    $user = getUserByEmailAndPassword($st_username, $password);
    if ($user != false) {
        // user is found
        $response["error"] = FALSE;
		$response["user"]["id"] = $user["id"];
        $response["user"]["st_name"] = $user["st_name"];
        $response["user"]["st_username"] = $user["st_username"];
        $response["user"]["st_address"] = $user["st_address"];
        $response["user"]["st_phone"] = $user["st_phone"];
		
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Wrong email or password entered! Please try again!";
        echo json_encode($response);
    }
} else {
    //required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing :(!";
    echo json_encode($response);
}
?>