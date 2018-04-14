<?php
	
	require_once '../includes/DbOperations.php';
    
    $response  = array();

    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['email']) and
        	isset($_POST['password']))
        	{
        		$checkLoginAuth = new userStart();
        		$result = $checkLoginAuth->userLogin($_POST['email'], $_POST['password']);
        		if($result == 2)
        		{
        			$userDetails = $checkLoginAuth->getAuthUserData($_POST['email']); 
        			foreach($userDetails as $data)
        			{
        				$response['error'] = false;
        				$response['email'] = $data['email'];
        				$response['name'] = $data['name'];
        				$response['userType'] = $data['userType'];
        				$response['image'] = $data['image'];
        				$response['city'] = $data['city'];
        				$response['state'] = $data['state'];
        			}
        		}
        		else if($result==1)
        		{
        			$response['error'] = true;
        			$response['message'] = "Incorrect login credentials";
        		}
        		else
        		{
        			$response['error'] = true;
        			$response['message'] = "Sorry..!..you are not registered";
        		}
        	}
        	else
            {
                $response['error'] = true; 
                $response['message'] = "Required fields are missing";
            }
    }
    else
    {
        $response['error'] = true; 
        $response['message'] = "Invalid Request";
    }
 
    echo json_encode($response);

?>
