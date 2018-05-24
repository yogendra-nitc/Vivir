<?php
	
	require_once '../includes/Booking.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['aptId']) and 
    		isset($_POST['userEmail']))
        	{
				$obj = new Booking();
			    $result = $obj->leaveApt($_POST['userEmail'],$_POST['aptId']);
			    if($result==1)
			    {
			    	$response['error'] = false;
					$response['message'] = "Request Sent successfully";
			    }
			    else
			    {
			    	$response['error'] = true;
			    	$response['message'] = "There is no booking with these details";
			    }
			}
			else
			{
				$response['error'] = true;
				$response['message'] = "Fields are missing";
			}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);

?>