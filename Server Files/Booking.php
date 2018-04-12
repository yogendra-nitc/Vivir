<?php

include('Database.php');
    class Booking extends Database{
        
// ********** METHOD FOR FLAT BOOKING **********

        public function Apartments()
        {
        //	$sql = "SELECT A.aptId,A.aptName,A.locality,A.city,I.img1 FROM apartment as A,image as I where A.aptId = I.aptId";
        $sql = "SELECT * FROM apartment as A,image as I where A.aptId = I.aptId";
        	$result = $this->connect()->query($sql);
        	$numRows = $result->num_rows;
          return $result;
        }
    }
?>