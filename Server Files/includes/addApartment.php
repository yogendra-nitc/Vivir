<?php
    
    require_once '../includes/Apartment.php';
    $response = array();
    
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['ownerEmail']) and isset($_POST['aptName']) and isset($_POST['aptLocality']) and
        	isset($_POST['aptCity']) and isset($_POST['aptType']) and isset($_POST['rentAmt']))
        {
			    $obj = new Apartment();
			    $result = $obj->addApartment($_POST['ownerEmail'],$_POST['aptName'],$_POST['aptLocality'],$_POST['aptCity'],$_POST['aptType'],$_POST['rentAmt']);
			    if($result == -1)
			    {
			    	$response['error'] = true;
			    	$response['message'] = "Error in apartment adding";
			    }
			    else
			    {
			    	$response['error'] = false; 
			    	$response['aptId'] = $result;
			    	$response['message'] = "Apartment added successfully..";
			    }
        }
        else
        {
        	$response['error'] = true;
			$response['message'] = "Fields are missing";
        }
    }
    else
    {
    	$response['error'] = true;
		$response['message'] = "Invalid request";
    }
    echo json_encode($response);
?>