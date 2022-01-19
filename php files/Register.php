<?php
if(isset($_GET['email']) && isset($_GET['password'])){
    require_once "connection.php";
    require_once "validate.php";
    $response=array();
        $email=validate($_GET['email']);
        $password=validate($_GET['password']);
    $sql="INSERT INTO `logincredentials` (`email`, `password`) VALUES ('$email', '$password');";
        $result=$conn->query($sql);
        if($result){
            echo "Success";
        }
        else{
            echo "Failure";
        }
    
}
     
?>




