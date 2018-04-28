<?php
	
	require_once '../includes/Apartment.php';
	$response = array();

	if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['aptId']) and
		    isset($_POST['aptName']) and
				isset($_POST['aptLocality']) and
					isset($_POST['aptCity']) and
						isset($_POST['aptType']) and
							isset($_POST['aptRent']))
        	{
				$obj = new Apartment();
			    $result = $obj->setDetailsEdit($_POST['aptId'],$_POST['aptName'],$_POST['aptLocality'],$_POST['aptCity'],$_POST['aptType'],$_POST['aptRent']);
			    	
        				if($result)
        				{
        					$response['error'] = false;
							$response['message'] = "Details Updated Successfully..";
        				}
        				else
        				{
        					$response['error'] = true;
							$response['message'] = "Error in details updation ..Try again..";
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