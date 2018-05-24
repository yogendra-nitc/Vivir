<?php
include('Database.php');
   class Owner extends Database {
    
    //Method for Handling Requests
	public function requestList($ownerId)
	{
  		// WHERE DateColumn < GETDATE()- 30
		$delsql = "DELETE FROM request WHERE (rdate < GETDATE() - '5') and rtype = 'booking'";
		$delRes = $this->connect()->query($delsql);

		$sql = "SELECT rid,tid,rtype,rdate from request where ownerid = '$ownerId' and rstatus = 'pending'";
		$result = $this->connect()->query($sql);

		$response = array();
		while($row = $result->fetch_array(MYSQLI_BOTH))
		{
			$userEmail = $row['tid'];
			$sql1 = "SELECT name from user where email ='$userEmail'";
			$res = $this->connect()->query($sql1);
			$row1 = $res->fetch_array(MYSQLI_BOTH);

			array_push($response,array("rid"=>$row['rid'],"name"=>$row1['name'],"rtype"=>$row['rtype'],"rdate"=>$row['rdate']));
		}
		return $response;
	}

	// ******** METHOD FOR REQUEST DETAILS ********

		public function getRequestDetails($rid)
		{
			$response = array();

			// Details from request table - Booking
			$sql = "SELECT * FROM request where rid = '$rid'";
			$result = $this->connect()->query($sql);
			$request = $result->fetch_array(MYSQLI_BOTH);

			$aptId = $request['aptId'];
			$userEmail = $request['tid'];
			$rtype = $request['rtype'];

			// Details from apartment table
			$sql = "SELECT aptName,locality,city from apartment where aptId = '$aptId'";
			$result = $this->connect()->query($sql);
			$apartment = $result->fetch_array(MYSQLI_BOTH);

			// Details from user table
			$sql = "SELECT name from user where email = '$userEmail'";
			$result = $this->connect()->query($sql);
			$user = $result->fetch_array(MYSQLI_BOTH);

			$response['tenantName'] = $user['name'];
			$response['tenantId'] = $userEmail;
			$response['aptName'] = $apartment['aptName'].', '.$apartment['locality'].', '.$apartment['city'];
			$response['rdate'] = $request['rdate'];

			if($rtype=="rent")
			{
				//Details from rent table - Rent upload
				$sql = "SELECT amtPaid,pdate from rent where tid = '$userEmail'" ;
				$result = $this->connect()->query($sql);
				$Payment = $result->fetch_array(MYSQLI_BOTH);
				$response['paymentAmount'] = $Payment['amtPaid'];
				$response['paymentDate'] = $Payment['pdate'];
			}
			else if($rtype == "leaving")
			{
				//Details from rent table - Leaving
				$sql = "SELECT total from rent where tid = '$userEmail'" ;
				$result = $this->connect()->query($sql);
				$Leaving = $result->fetch_array(MYSQLI_BOTH);
				$response['Dues'] = $Leaving['total'];
			}
			return $response;
		} 
		
		// DELETE TENANT REQUEST

	public function rejectRequest($rid)
	{
		// SEND NOTIFICATION

		$sql = "SELECT * from request where rid = '$rid'";
		$result = $this->connect()->query($sql);
		$row = $result->fetch_array(MYSQLI_BOTH);

		$userTo = $row['tid'];

		$content = "Your"." ".$row['rtype']." "."request for apartment"." ".$row['aptId']." "."has been cancelled.";
		$ndate = date("Y-m-d");
		$nstatus = 0;

// DELETION OF REQUEST
		
		$delRequest = "DELETE FROM request where rid = '$rid'";
		$delRes = $this->connect()->query($delRequest);
		if($delRes)
		{
			$sql = "INSERT into notifications(nid,userTo,content,ndate,nstatus)Values(null,'$userTo','$content','$ndate','$nstatus')";
			$result = $this->connect()->query($sql);
			if($result)
				return true;
			else
				return false;
		}
		else
		return false;
	}
	
	
	//ACCEPTING TENANT REQUEST

	//Accept Booking Method
	public function acceptBooking($rid)
	{

			// Get data from request table
			$sql = "SELECT * from request where rid = '$rid'";
			$result = $this->connect()->query($sql);
			$row = $result->fetch_array(MYSQLI_BOTH);

			$aptId = $row['aptId'];
			$tid = $row['tid'];

		// INSERT DATA INTO BOOKING TABLE
			$bdate = date('Y-m-d');
			$sql = "INSERT INTO booking(bid, aptId, tid, bdate) VALUES (null,'$aptId','$tid','$bdate')";
			$res_b = $this->connect()->query($sql);

			if($res_b)
			{
		// INSERT DATA IN BOOKING
			$amtPaid = 0;
			$total = 0;
			$approved = "yes"; 
			$sql = "INSERT INTO rent(rentId, tid, aptId, amtPaid, total, pdate, approved) VALUES (null,'$tid','$aptId','$amtPaid','$total','$bdate','$approved')";
			$res_r = $this->connect()->query($sql);
			}

			if($res_r)
			{
		// Notification for booking confirmation
			$content = "Your booking has been completed.Your apartment id = ".$aptId;
			$nstatus = 0;
			$sql = "INSERT INTO notifications(nid, userTo, content, ndate, nstatus) VALUES (null,'$tid','$content','$bdate','$nstatus')";
			$res_n = $this->connect()->query($sql);
			}

		// delete rest of the request
			if($res_n)
			{
				$sql = "DELETE from request where aptId = '$aptId' and rtype = 'booking' ";
				$res_req = $this->connect()->query($sql);
			}

			if($res_req)
				return true;
			else
				return false;

	}
// Method for accepting leaving request

	public function acceptLeaving($rid)
	{
		// Get data from request table
			$sql = "SELECT * from request where rid = '$rid'";
			$result = $this->connect()->query($sql);
			$row = $result->fetch_array(MYSQLI_BOTH);

			$aptId = $row['aptId'];
			$tid = $row['tid'];

			// delete from rent 
			$sql = "DELETE from rent where aptId ='$aptId' and tid = '$tid' ";
			$res_r = $this->connect()->query($sql);

			// delete from booking
			if($res_r)
			{
				$sql = "DELETE from booking where aptId ='$aptId' and tid = '$tid' ";
				$res_b = $this->connect()->query($sql);
			}

			// delete from request
			if($res_b)
			{
				$sql = "DELETE from request where rid = '$rid'";
				$res_req = $this->connect()->query($sql);
			}

			//send notification
			if($res_req)
			{
				$ndate = date("Y-m-d");
				$content = "Your leaving request has been accepted.Now you are no more tenant of this apartment.Apartment id = ".$aptId;
				$nstatus = 0;
				$sql = "INSERT INTO notifications(nid, userTo, content, ndate, nstatus) VALUES (null,'$tid','$content','$ndate','$nstatus')";
				$res_n = $this->connect()->query($sql);
			}
			if($res_n)
				return true;
			else
				return false;
	}  

// Method foe approving payment

	public function acceptRent($rid)
	{

		// Get data from request table
			$sql = "SELECT * from request where rid = '$rid'";
			$result = $this->connect()->query($sql);
			$row = $result->fetch_array(MYSQLI_BOTH);

			$aptId = $row['aptId'];
			$tid = $row['tid'];

			//GET the price of apartment
			$sql = "SELECT rentAmt from apartment where aptId = '$aptId'";
			$resu_price = $this->connect()->query($sql);
			$row_price = $resu_price->fetch_array(MYSQLI_BOTH);



		//UPDATE DATA OF RENT TABLE 
			$price = $row_price['rentAmt']; 
			$amtPaid = 0;
			$approved = "yes";
			$sql = "UPDATE rent SET total = (amtPaid + total - '$price'),approved='$approved' WHERE tid ='$tid' and aptId = '$aptId'";
			$res_r = $this->connect()->query($sql);

			// delete from request
			if($res_r)
			{
				$sql = "DELETE from request where rid = '$rid'";
				$res_req = $this->connect()->query($sql);
			}

			//send notification
			if($res_req)
			{
				$ndate = date("Y-m-d");
				$content = "Your rent has been approved.Apartment id = ".$aptId;
				$nstatus = 0;
				$sql = "INSERT INTO notifications(nid, userTo, content, ndate, nstatus) VALUES (null,'$tid','$content','$ndate','$nstatus')";
				$res_n = $this->connect()->query($sql);
			}
			if($res_n)
			return true;
			else
			return false;
	}

   }

 ?>