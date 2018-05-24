<?php
    
    require_once '../includes/DbOperations.php';
    $response = array();
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['sid']) and
        	isset($_POST['rid']) and 
        		isset($_POST['content']) )
        {
		    $obj = new userStart();
		    $result= $obj->writeComplain($_POST['sid'],$_POST['rid'],$_POST['content']);
		    if ($result) {
		    	$response['error'] = false;
				$response['message'] = "Send Successfully";
		    }
		    else
		    {
		    	$response['error'] = true;
				$response['message'] = "Error in sending try again";
		    }
		    
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

	echo json_encode(array($response));

?>