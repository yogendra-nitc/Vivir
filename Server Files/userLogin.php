<?php
	
	includes('DbOperations.php');
    
    $response array();

    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['email']) and
        	isset($_POST['password'])
        	{
        		$checkLoginAuth = new userStart();
        		$result = $checkLoginAuth->userLogin($_POST['email'], $_POST['password']);
        		if($result == 1)
        		{
        			$checkLoginAuth->getAuthUserData($_POST['email']); 
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

?>