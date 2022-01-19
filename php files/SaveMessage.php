<?php
if(isset($_GET['name']) && isset($_GET['message']) && isset($_GET['time']) && isset($_GET['to_username']) && isset($_GET['from_username'])){
    require_once "connection.php";
    require_once "validate.php";
        $name=validate($_GET['name']);
        $message=validate($_GET['message']);
        $time=validate($_GET['time']);
	$to_username=validate($_GET['to_username']);
	$from_username=validate($_GET['from_username']);
    
    $sql="INSERT INTO `messagetable` (`name`, `message`,`time`,`from_username`,`to_username`) VALUES ('$name', '$message','$time','$from_username','$to_username');";
        $result=$conn->query($sql);
        if($result){
            echo "Success";
        }
        else{
            echo "Failure";
        }
    
}
     
?>




