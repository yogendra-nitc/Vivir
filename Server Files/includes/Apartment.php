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
            $ownerId = $ownerEmail;

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
    		$res1 = $this->connect()->query($sql);
    		$sql = "INSERT INTO image(aptId)VALUES('$aptId')";
    		$res2 = $this->connect()->query($sql);
    		if($res1 and $res2)
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
    	
// ********************  EDITING APARTMENT DETAILS ***************************

        // GET APARTMENT DETAILS 
    	public function getDetailsEdit($aptId)
    	{
    	    $response = array();
    		$sql = "SELECT * FROM apartment where aptId='$aptId'";
    		$result = $this->connect()->query($sql);
    		$row = $result->fetch_array(MYSQLI_BOTH);
    		
    		$response['error']        =  false;
            $response['aptName']      =  $row['aptName'];
            $response['aptLocality']  =  $row['locality'];
            $response['aptCity']      =  $row['city'];
            $response['aptType']      =  $row['aptType'];
            $response['rentAmt']      =  $row['rentAmt'];
		    return $response;
    	}
    	
    	// SET APARTMENT DETAILS
        public function setDetailsEdit($aptId, $aptName, $aptLocality, $aptCity, $aptType, $rentAmt)
        {
            $sql = "UPDATE apartment set aptName = '$aptName', locality = '$aptLocality', city = '$aptCity' , aptType = '$aptType', rentAmt = '$rentAmt' where aptId = '$aptId'";
            $result = $this->connect()->query($sql);
            if($result)
                return true;
            else
                return false;
        }
        
// DELETE APARTMENT FROM DATABASE
        public function deleteApartment($aptId)
        {
            $sql = "SELECT * FROM booking where aptId = '$aptId'";
            $result = $this->connect()->query($sql);
            $num = $result->num_rows;

            if($num > 0)
            {
                return 1;
            }
            else
            {
                $sql = "DELETE FROM apartment where aptId = '$aptId'";
                $result = $this->connect()->query($sql);
                if($result)
                    return 0;
                else
                    return 2;
            }
        }
    	
    }

?>