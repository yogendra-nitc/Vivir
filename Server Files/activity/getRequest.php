<?php
    
    require_once '../includes/owner.php';
    $response = array();
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['ownerEmail']))
        {
		    $obj = new Owner();
		    $response = $obj->requestList($_POST['ownerEmail']);
		}
		else
		{
			$response['error'] = true;
			$response['message'] = "Try Again..!!..Activity loading..";
		}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid Request";
	}

	echo json_encode(array("AllRequest"=>$response));

?>