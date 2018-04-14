<?php

    require_once '../includes/DbOperations.php';
    $response = array();
    
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(
            isset($_POST['name']) and
                isset($_POST['email']) and
                    isset($_POST['password']) and
                        isset($_POST['userType']))
                    {
                        $getRegister = new userStart();
                        $result = $getRegister->userRegistration($_POST['name'],$_POST['email'],$_POST['password'],$_POST['userType']);
                        if($result == 1)
                        {
                            $response['error'] = false; 
                            $response['message'] = "User registered successfully";
                        }
                        elseif($result == 0)
                        {
                            $response['error'] = true; 
                            $response['message'] = "It seems you are already registered, please choose a different email and username";                     
                        }
                        else
                        {
                            $response['error'] = true; 
                            $response['message'] = "Some error occurred please try again";          
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
