<?php
    
    require_once '../includes/User.php';
    $response = array();
    
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['aptId'])and
        	isset($_POST['amtPaid']) and
        		isset($_POST['userEmail']) and
					isset($_POST['pdate']))
        	{
			    $obj = new User();
			    $result = $obj->fillRentDetails($_POST['userEmail'],$_POST['aptId'],$_POST['amtPaid'],$_POST['pdate']);
			    if($result==0)
			    {
			    	$response['error'] = false;
					$response['message'] = "Details uploaded successfully...";
			    }
			    else
			    {
			    	$response['error'] = true;
					$response['message'] = "Error in detail upload";
			    }
			    
			}
			else
			{
				$response['error'] = true;
				$response['message'] = "Please fill all fields";
			}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid Request";
	}
    echo json_encode($response);

?>