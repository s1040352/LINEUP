<?php require_once("include/db_connection.php");?>
<?php
if(isset($_POST['spike_start_1_1']) && isset($_POST['spike_start_1_2']) && isset($_POST['spike_start_2_1']) && isset($_POST['spike_start_2_2']) && isset($_POST['spike_end_1_1']) && isset($_POST['spike_end_1_2']) && isset($_POST['spike_end_2_1']) && isset($_POST['spike_end_2_2']) && isset($_POST['spike_waiting_time'])) {
	
	$spike_start_1_1 = $_POST['spike_start_1_1'];
	$spike_start_1_2 = $_POST['spike_start_1_2'];
    $spike_start_2_1 = $_POST['spike_start_2_1'];
	$spike_start_2_2 = $_POST['spike_start_2_2'];
    $spike_end_1_1 = $_POST['spike_end_1_1'];
	$spike_end_1_2 = $_POST['spike_end_1_2'];
    $spike_end_2_1 = $_POST['spike_end_2_1'];
    $spike_end_2_2 = $_POST['spike_end_2_2'];
    $spike_waiting_time = $_POST['spike_waiting_time'];
	
	
 $query_1 = "SELECT * FROM spiketime";
 $result_1=mysqli_query($connection,$query_1);//user資料表名稱
 while($row_1=mysqli_fetch_assoc($result_1)){
 $tmp_1[]=$row_1;
 }
 echo json_encode($tmp_1);
}


if (isset($_POST['peak_waiting_time'])) {
$peak_waiting_time = $_POST['peak_waiting_time'];
$query_2 = "SELECT * FROM peaktime";
 $result_2=mysqli_query($connection,$query_2);//user資料表名稱
 while($row_2=mysqli_fetch_assoc($result_2)){
 $tmp_2[]=$row_2;
 }
 echo json_encode($tmp_2);
}

if (isset($_POST['start1']) && isset($_POST['start2']) && isset($_POST['end1']) && isset($_POST['end2']) && isset($_POST['weekday']) && isset($_POST['holiday'])) {
	
	$start1 = $_POST['start1'];
    $start2 = $_POST['start2'];
    $end1 = $_POST['end1'];
    $end2 = $_POST['end2'];
    $weekday = $_POST['weekday'];
    $holiday = $_POST['holiday'];
	
	$query_3 = "SELECT * FROM onair";
	while($row_3=mysqli_fetch_assoc($result_3)){
 $tmp_1[]=$row_3;
 }
 echo json_encode($tmp_3);
}
 mysqli_close($connection);

?>