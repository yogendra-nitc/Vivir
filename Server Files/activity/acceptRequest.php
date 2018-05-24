<?php
	
	require_once '../includes/owner.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['rid'])and 
    		isset($_POST['rtype']))
        	{
        		$obj = new Owner();

        		//Booking request approval
        		if($_POST['rtype']=="booking")
			    {
			    	$result = $obj->acceptBooking($_POST['rid']); 
			    	if($result)
			    	{
			    		$response['error'] = false;
			    		$response['message'] = "Request accepted successfully";
			    	}
			    	else
			    	{
			    		$response['error'] = true;
			    		$response['message'] = "Error in acceptance!..try again";
			    	}

			    }
			    else if($_POST['rtype']=="leaving")
			    {
			    	//Leaving request approval
			    	$result = $obj->acceptLeaving($_POST['rid']); 
			    	if($result)
			    	{
			    		$response['error'] = false;
			    		$response['message'] = "Request accepted successfully";
			    	}
			    	else
			    	{
			    		$response['error'] = true;
			    		$response['message'] = "Error in request acceptance";
			    	}
			    }
			    else
			    {
			    	$result = $obj->acceptRent($_POST['rid']); 
			    	if($result)
			    	{
			    		$response['error'] = false;
			    		$response['message'] = "Request accepted successfully";
			    	}
			    	else
			    	{
			    		$response['error'] = true;
			    		$response['message'] = "Error in request acceptance";
			    	}
			    }
        	}
        	else
        	{
        		$response['error'] = true;
				$response['message'] = "Error in Request acceptance";
        	}
    }
    else
    {
    	$response['error'] = true;
		$response['message'] = "Invalid Request";
    }
        		
	echo json_encode($response);		   

?>