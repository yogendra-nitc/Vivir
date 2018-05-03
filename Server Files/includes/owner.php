<?php
include('Database.php');
   class Owner extends Database {
    
    //Method for Handling Requests
	public function requestList($ownerId)
	{
  		// WHERE DateColumn < GETDATE()- 30
		$delsql = "DELETE FROM request WHERE (rdate < GETDATE() - '5') and rtype = 'booking'";
		$delRes = $this->connect()->query($delsql);

		$sql = "SELECT rid,tid,rtype,rdate from request where ownerid = '$ownerId'";
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

   }

 ?>