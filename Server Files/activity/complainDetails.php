<?php
	
	require_once '../includes/DbOperations.php';
    
    $response  = array();

    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['cid']))
        {
        	$obj = new userStart();
    		$result = $obj->complainDetails($_POST['cid']);
    		$response['error'] = false; 
            $response['content'] = $result;
        }
        else
        {
            $response['error'] = true; 
            $response['message'] = "Error in loading";
         }
    }
    else
    {
        $response['error'] = true; 
        $response['message'] = "Invalid Request";
    }
 
    echo json_encode($response);

?>