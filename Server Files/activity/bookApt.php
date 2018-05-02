<?php

	require_once '../includes/User.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['email']) and 
    		isset($_POST['aptId']))
        	{
        		$obj = new User();
			    $result= $obj->bookApartment($_POST['email'],$_POST['aptId']);

			    if($result == 0)
			    {
			    	$response['error'] = false;
					$response['message'] = "Your request has been sent successfully";
			    }
			    else if($result == 1)
			    {
			    	$response['error'] = true;
					$response['message'] = "You have already requested for this Apartment.";
			    }
			    else if($result == 2)
			    {
			    	$response['error'] = true;
					$response['message'] = "Error in booking request ..try again..!";
			    }
			    else if($result==3)
			    {
			    	$response['error'] = true;
					$response['message'] = "Your request has been sent successfully..but error in owner notification..";
			    }
        	}
        	else
        	{
        		$response['error'] = true;
				$response['message'] = "Please click the request button again..";
        	}
    }
    else
    {
    	$response['error'] = true;
		$response['message'] = "Invalid request";
    }
    echo json_encode($response);

?>