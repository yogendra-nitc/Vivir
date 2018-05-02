<?php

    require_once '../includes/User.php';
    $response = array();
    
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(
            isset($_POST['email']) and
                isset($_POST['name']) and
                    isset($_POST['city']) and
                        isset($_POST['state']) and
        					isset($_POST['contact']))
           {
           		$changeProfile = new User();
                $result = $changeProfile->setProfile($_POST['email'],$_POST['name'],$_POST['city'],$_POST['state'],$_POST['contact']);
                if($result)
                {
                	$response['error'] = false; 
                    $response['message'] = "Details updated Successfully";
                }
                else
                {
                	$response['error'] = true; 
                    $response['message'] = "Some Error occurred..Try again..!.."; 
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