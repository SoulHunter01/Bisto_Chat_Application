<?php
if(isset($_GET['email']) && isset($_GET['password'])){
    require_once "connection.php";
    require_once "validate.php";
    $response=array();
        $email=validate($_GET['email']);
        $password=validate($_GET['password']);
    $sql="select * from `logincredentials` where email='$email' and password='$password'";
        $result=$conn->query($sql);
        if($result->num_rows>0){
            echo "Success";
        }
        else{
            echo "Failure";
        }
    
}
     
     
?>