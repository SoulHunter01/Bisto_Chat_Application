<?php

$con=mysqli_connect("localhost","root","") or die("Error Connection");
mysqli_select_db($con,"loginlastseen");

$response=array();

$name=$_GET['name'];
$lastseen=$_GET['lastseen'];

$query="INSERT INTO `user` (`name`, `lastseen`) VALUES ('$name', '$lastseen')";
$res=mysqli_query($con,$query);

	if($res)
	{
		$response["success"]=1;
	}
	else{
		$response["success"]=0;
	}


echo json_encode($response);





?>