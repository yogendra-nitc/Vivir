<?php
	
	require_once '../includes/Apartment.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['aptId']))
        	{
				$obj = new Apartment();
			    $result = $obj->deleteApartment($_POST['aptId']);
        			if($result==0)
        			{
        				$response['error'] = false;
						$response['message'] = "Apartment has been deleted successfully..";
        			}
        			else if($result==1)
        			{
        				$response['error'] = true;
						$response['message'] = "Error in deletion..There is Booking for this Apartment..";
        			}
        			else if($result==2)
        			{
        				$response['error'] = true;
						$response['message'] = "Error in deletion..Try again..";
        			}
			}
			else
			{
				$response['error'] = true;
				$response['message'] = "Error..Some fields are missings.";
			}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);

?>