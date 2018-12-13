<?php
 
require_once 'include/db_functions_1.php';

// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['st_name']) && isset($_POST['st_username']) && isset($_POST['st_address']) && isset($_POST['st_phone']) && isset($_POST['password'])) {
 	
	header('Content-type=application/json; charset=utf-8');
	header('Content-type: text/xml');
    // receiving the post params
    $st_name = $_POST["st_name"];
    $st_address = $_POST["st_address"];
    $st_phone = $_POST["st_phone"];
    $st_username = $_POST["st_username"];
    $password = $_POST["password"];
 
    // check if user already exists with the same email
    if(emailExists($st_username)){
		// email already exists
        $response["error"] = TRUE;
        $response["error_msg"] = "Username already exists with " . $st_username;
        echo json_encode($response);
	}else {
        // create a new user
        $user = storeUser($st_name, $st_username, $st_address, $st_phone, $password);
        if ($user) {
            // user stored successfully
            $response["error"] = FALSE;
            $response["user"]["id"] = $user["id"];
			$response["user"]["st_name"] = $user["st_name"];
            $response["user"]["st_username"] = $user["st_username"];
            $response["user"]["st_address"] = $user["st_address"];
            $response["user"]["st_phone"] = $user["st_phone"];
            echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred!";
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters missing!";
    echo json_encode($response);
}
?>