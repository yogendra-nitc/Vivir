<?php

include('Database.php');
    class Booking extends Database{
        
// ********** METHOD FOR FLAT BOOKING **********

        public function Apartments()
        {
        $sql = "SELECT * FROM apartment WHERE aptId NOT IN ( SELECT aptId FROM booking)";
        	$result = $this->connect()->query($sql);
        	$numRows = $result->num_rows;
          return $result;
        }

        public function getApartment($apartmentId)
        {
        	$sql = "SELECT ownerid FROM apartment where aptId ='$apartmentId'";
        	$result = $this->connect()->query($sql);
        	$row = $result->fetch_array(MYSQLI_BOTH);

        //	$ownerid = $row['ownerid'];
        //	$sql = "SELECT email from owner WHERE oid = '$ownerid'";
        //	$result = $this->connect()->query($sql);
        //	$row = $result->fetch_array(MYSQLI_BOTH);

        	$email =  $row['ownerid'];

        	$data = array();

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
            $data['img']     = $row['img'];

        	//GET APARTMENT DATA
        /*	$sql = "SELECT * FROM image WHERE aptId = '$apartmentId'";
        	$result = $this->connect()->query($sql);
        	$row = $result->fetch_array(MYSQLI_BOTH);
        	$url1 = $row['img1'];
        	$url2 = $row['img2'];
        	$url3 = $row['img3'];
        	$data['img1'] = "https://vivir18.000webhostapp.com/vivir/media/".$url1;
        	$data['img2'] = "https://vivir18.000webhostapp.com/vivir/media/".$url2;
        	$data['img3'] = "https://vivir18.000webhostapp.com/vivir/media/".$url3;*/

        	return $data;

        }

//Leave Flat Request
        public function leaveApt($userEmail, $aptId)
        {
            $sql = "SELECT * FROM booking where tid='$userEmail' and aptId = '$aptId'";
            $result = $this->connect()->query($sql);
            $num = $result->num_rows;

            if($num > 0)
            {
                $sql = "SELECT ownerid from apartment where aptId = '$aptId'";
                $result = $this->connect()->query($sql);
                $row = $result->fetch_array(MYSQLI_BOTH);
                $ownerid = $row['ownerid'];
                $rtype="leaving";
                $rdate=date("Y-m-d");
                $rstatus = "pending";

                // SEND REQUEST
                $sql = "INSERT INTO request(rid, tid, ownerid, rtype, aptId, rdate, rstatus) VALUES (null,'$userEmail','$ownerid','$rtype','$aptId','$rdate','$rstatus')";
                $result = $this->connect()->query($sql);
                
                //SEND NOTIFICATION
                $content = "You have a pending LEAVING request for the apartment -".$aptId;
                $nstatus = 0;
                $sql = "INSERT INTO notifications(nid, userTo, content, ndate, nstatus) VALUES (null,'$ownerid','$content','$rdate','$nstatus')";
                $result = $this->connect()->query($sql);
                return 1;
            }
            else
                return 0;
        }
    }
?>