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
        		if($result == 1)
        		{
        			$userDetails = $checkLoginAuth->getAuthUserData($_POST['email']); 
        			$response['error'] = false;
        			$response['email'] = $userDetails['email'];
        			$response['name'] = $userDetails['name'];
        			$response['userType'] = $userDetails['userType'];
        			$response['image'] = $userDetails['image'];
        			$response['city'] = $userDetails['city'];
        			$response['state'] = $response['state'];
        			return $response;
        		}
        		else if($result==2)
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
    }

?>