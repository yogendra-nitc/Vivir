<?php
	
	require_once '../includes/Booking.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['aptId']))
        	{
				$obj = new Booking();
			    $data = $obj->getApartment($_POST['aptId']);
			    	
        				$response['error'] 	   =  false;
        				$response['email']     =  $data['email'];
        				$response['ownerName'] =  $data['ownerName'];
        				$response['contact']   =  $data['contact'];
        				$response['aptAdd']    =  $data['aptAdd'];
        				$response['ownerid']   =  $data['ownerid'];
        				$response['aptType']   =  $data['aptType'];
        				$response['rentAmt']   =  $data['rentAmt'];
        				$response['img1'] 	   =  $data['img1'];
        				$response['img2']      =  $data['img2'] ;
        				$response['img3']      =  $data['img3'];
			}
			else
			{
				$response['error'] = true;
				$response['message'] = "Apartment is not found";
			}
	}
	else
	{
		$response['error'] = true;
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);

?>
