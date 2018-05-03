<?php
	
	require_once '../includes/DbOperations.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['email']) and 
    		isset($_POST['oldPwd']) and
    		isset($_POST['newPwd']))
        	{
				$obj = new userStart();
			    $result= $obj->changePassword($_POST['email'],$_POST['oldPwd'],$_POST['newPwd']);
			    if($result)
			    {
			    	$response['error'] = false;
			    	$response['message'] = "Password changed successfully..!";
			    }
			    else
			    {
			    	$response['error'] = true;
			    	$response['message'] = "Incorrect credential..";
			    }
			}
			else
			    {
			    	$response['error'] = true;
			    	$response['message'] = "Fields required";
			    }
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid request";
	}
	echo json_encode($response);
?>