<?php

	include('Database.php');
    class Apartment extends Database{
    	public function myApartments($ownerId)
    	{
    		$sql = "SELECT * FROM apartment as A,image as I where A.ownerid = '$ownerId' and A.aptId = I.aptId";
        	$result = $this->connect()->query($sql);
          return $result;
    	}
    }

?>