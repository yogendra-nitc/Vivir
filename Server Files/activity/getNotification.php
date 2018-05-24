<?php
    
    require_once '../includes/User.php';
    $response = array();
    
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        if(isset($_POST['userEmail'])and
        	isset($_POST['limit']))
        	{
			    $obj = new User();
			    $result = $obj->getNotification($_POST['userEmail'],$_POST['limit']);
				    while($row = $result->fetch_array(MYSQLI_BOTH))
				    {
				        array_push($response,array("nid"=>$row['nid'],"content"=>$row['content'],"ndate"=>$row['ndate']));
				    }
				    
			}
	}
    echo json_encode(array("AllNotification"=>$response));

?>