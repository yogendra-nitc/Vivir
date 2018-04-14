<?php

include('Database.php');
    class Booking extends Database{
        
// ********** METHOD FOR FLAT BOOKING **********

        public function Apartments()
        {
        $sql = "SELECT * FROM apartment as A,image as I where A.aptId = I.aptId";
        	$result = $this->connect()->query($sql);
        	$numRows = $result->num_rows;
          return $result;
        }

        public function getApartment($apartmentId)
        {
        	$sql = "SELECT ownerid FROM apartment where aptId ='$apartmentId'";
        	$result = $this->connect()->query($sql);
        	$row = $result->fetch_array(MYSQLI_BOTH);

        	$ownerid = $row['ownerid'];
        	$sql = "SELECT email from owner WHERE oid = '$ownerid'";
        	$result = $this->connect()->query($sql);
        	$row = $result->fetch_array(MYSQLI_BOTH);

        	$email = $row['email'];


        	//GET OWNER DATA
        	$sql = "SELECT email,name,contact FROM user where email = '$email'";
        	$result = $this->connect()->query($sql);
        	$row = $result->fetch_array(MYSQLI_BOTH);

        	$data['email'] = $row['email'];
        	$data['ownerName'] = $row['name'];
        	$data['contact'] = $row['contact'];


        	//GET APARTMENT DATA
        	$sql = "SELECT * FROM apartment WHERE aptId = '$apartmentId'";
        	$result = $this->connect()->query($sql);
        	$row = $result->fetch_array(MYSQLI_BOTH);

			$aptAdd = $row['aptName'].", ".$row['locality'].", ".$row['city'];

        	$data['aptAdd'] = $aptAdd;
        	$data['ownerid'] = $row['ownerid'];
        	$data['aptType'] = $row['aptType'];
        	$data['rentAmt'] = $row['rentAmt'];

        	//GET APARTMENT DATA
        	$sql = "SELECT * FROM image WHERE aptId = '$apartmentId'";
        	$result = $this->connect()->query($sql);
        	$row = $result->fetch_array(MYSQLI_BOTH);
        	$url1 = $row['img1'];
        	$url2 = $row['img2'];
        	$url3 = $row['img3'];
        	$data['img1'] = "https://vivir18.000webhostapp.com/vivir/media/".$url1;
        	$data['img2'] = "https://vivir18.000webhostapp.com/vivir/media/".$url2;
        	$data['img3'] = "https://vivir18.000webhostapp.com/vivir/media/".$url3;

        	return $data;

        }
    }
?>
