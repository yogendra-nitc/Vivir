<?php
    
    require_once '../includes/DbOperations.php';
    $response = array();
    
    $obj = new userStart();
    $result = $obj->getcomplain($_POST['userId']);
        
    while($row = $result->fetch_array(MYSQLI_BOTH))
    {
        array_push($response,array("cid"=>$row['cid'],"sid"=>$row['sid'],"rid"=>$row['rid'],"cdate"=>$row['cdate']));
    }
    echo json_encode(array("AllComplain"=>$response));

?>