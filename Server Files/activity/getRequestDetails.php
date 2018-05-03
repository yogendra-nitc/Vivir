<?php
	
	require_once '../includes/owner.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['rid']))
        	{
				$obj = new Owner();
			    $response = $obj->getRequestDetails($_POST['rid']);
			    $response['error'] = false;
			}
			else
			{
				$response['error'] = true;
				$response['message'] = "No Requests";
			}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);

?>