<?php
if(isset($_GET['onlinestatus']) && ($_GET['email'])){
    require_once "connection.php";
    require_once "validate.php";
        $onlinestatus=validate($_GET['onlinestatus']);
	$email=validate($_GET['email']);
    $sql="UPDATE `logincredentials` SET onlinestatus='$onlinestatus' WHERE email='$email'";
        $result=$conn->query($sql);
        if($result){
            echo "Success";
        }
        else{
            echo "Failure";
        }
    
}
     
?>




