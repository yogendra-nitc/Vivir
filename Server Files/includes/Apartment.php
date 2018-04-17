<?php

	include('Database.php');
    class Apartment extends Database{
    	public function myApartments($ownerId)
    	{
    		$sql = "SELECT * FROM apartment as A,image as I where A.ownerid = '$ownerId' and A.aptId = I.aptId";
        	$result = $this->connect()->query($sql);
          return $result;
    	}
    	
    // ADDING NEW APARTMENT

    	public function addApartment($ownerEmail,$aptName,$locality,$city,$aptType,$rentAmt)
    	{
    		// fetching owner id
    		$sql = "SELECT oid FROM owner where email = '$ownerEmail'";
			$res = $this->connect()->query($sql);
            $row = $res->fetch_array(MYSQLI_BOTH);
            $ownerId = $row['oid'];

    // ******** APARTMENT ID GENERATION ******

    		$sql = "SELECT * from apartment order by aptId desc";
			$res = $this->connect()->query($sql);
            $row = $res->fetch_array(MYSQLI_BOTH);
            $num = $res->num_rows;

            if($num>0)
            {
            	$temp = $row['aptId'];
	            $i=3;
	            $str ="";
	            while($temp[$i] != 'V')
	            {
	                $str = $str.$temp[$i];
	                $i =$i + 1; 
	            }
	             $str = $str +1; 
	             $aptId = "APT".$str."V";
	         }
	         else
	         {
	         	$aptId = "APT101V";
	         }

	// ******* DATA INSERTION *****

    		$sql = "INSERT INTO apartment(aptId, aptName,locality, city,ownerid,aptType,rentAmt) VALUES('$aptId','$aptName','$locality','$city','$ownerId','$aptType','$rentAmt')";
    		$res = $this->connect()->query($sql);
    		if($res)
    			return $aptId;
    		else 
    			return -1; 
    	}
// Method for adding apartment image

    	public function aptImage($img1, $img2, $img3)
    	{
    		$sql = "INSERT INTO image(aptId,img1,img2,img3)VALUES('$aptId','$img1','$img2','$img3')";
    		$res = $this->connect()->query($sql);

    		if($res)
    			return true;
    		else
    			return false;
    	}
    }

?>