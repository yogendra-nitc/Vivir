<?php
	
	require_once '../includes/Apartment.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['aptId']))
        	{
				$obj = new Apartment();
			    $response = $obj->getDetailsEdit($_POST['aptId']);
			}
			else
			{
				$response['error'] = true;
				$response['message'] = "Apartment is not found";
			}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);

?>