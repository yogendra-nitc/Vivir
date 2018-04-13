<?php
    
    require_once '../includes/Booking.php';
    $response = array();
    
    $obj = new Booking();
    $result = $obj->Apartments();
        
    while($row = $result->fetch_array(MYSQLI_BOTH))
    {
        array_push($response,array("aptId"=>$row['aptId'],"aptName"=>$row['aptName'],"locality"=>$row['locality'],"city"=>$row['city'],"ownerid"=>$row['ownerid'],"aptType"=>$row['aptType'],"rentAmt"=>$row['rentAmt'],"img1"=>$row['img1']));
    }
    echo json_encode(array("AllFlat"=>$response));

?>