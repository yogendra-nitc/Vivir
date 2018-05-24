<?php
	
	require_once '../includes/owner.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['rid']))
        	{
        		$obj = new Owner();
			    $result = $obj->rejectRequest($_POST['rid']);
			    if($result == true)
			    {
			    	$response['error'] = false;
					$response['message'] = "Request has been Cancelled Successfully.";
			    }
			    else
			    {
			    	$response['error'] = true;
					$response['message'] = "Error! Request not cancelled";
			    }
        	}
        	else
        	{
        		$response['error'] = true;
				$response['message'] = "Error in Request cancellation";
        	}
    }
    else
    {
    	$response['error'] = true;
		$response['message'] = "Invalid Request";
    }
    
   echo json_encode($response);

?>