<html>
    <head></head>
    <body>
        <form method="post">
            <input type="submit" name="count" value="Start counting" />
            <input type="submit" name="beast" value="Reset" />
        </form>
    </body>
</html>
<?php require_once 'login_1.php';?>
<?php
session_start();
?>
<?php
		if(isset($_POST['count'])){
			$count = $_POST["count"];
			$st_username = $_POST["clusername"];
			$st_phone = $_POST["ciphone"];
			if($user != false){
            if(!(@$_SESSION['count'])){
                $_SESSION['count'] = 1;
				$count=$_SESSION['count'];
            }
			else{
				$count = $_SESSION['count'] + 1;
				$_SESSION['count'] = $count;
            }
			$query = "INSERT INTO linego(";
				$query .= "count,,clusername,clphone)";
				$query .= "VALUES('{$count}','{$st_username}','{$st_phone}')";
				$result = mysqli_query($connection, $query);
			 echo "排隊成功<br>";
			echo $_SESSION['count'];
			}
        }
		if(isset($_POST['beast'])){
			session_destroy();
		}
  ?>


  