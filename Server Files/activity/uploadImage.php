<?php
	require_once '../includes/User.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
		$obj = new User();
		$result = $obj->uploadImage($_POST['userEmail'],$_POST['name'],$_POST['image']);
		if($result)
		{
			$response['error'] = false;
			$response['message'] = "Profile changed successfully";
		}
		else
		{
			$response['error'] = true;
			$response['message'] = "Error in updation ..try again";
		}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);
?>