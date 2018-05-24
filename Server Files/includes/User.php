<?php
include('Database.php');
   class User extends Database {
    
    //Method for setting profile
    public function setProfile($email,$name, $city, $state, $contact)
    {
        $sql = "UPDATE user set name = '$name', city = '$city', state= '$state', contact = '$contact' where email = '$email'";
        $result = $this->connect()->query($sql);
        if($result)
            return true;
        else
            return false;
    }

    // METHOD FOR BOOKING APARTMENT
    public function bookApartment($email,$aptId)
    {

        // CHECKING FOR ALREADY EXISTING REQUEST
        $tid = $email;

        $sql = "SELECT * FROM request WHERE tid = '$tid' AND aptId = '$aptId'";
        $result = $this->connect()->query($sql);
        $num = $result->num_rows;
        if($num > 0)
        {
            return 1;
        }
        else
        {
            // GETTING OWNER ID
            $sql = "SELECT ownerid from apartment where aptId ='$aptId'";
            $result = $this->connect()->query($sql);
            $row = $result->fetch_array(MYSQLI_BOTH);

            $oid = $row['ownerid'];
            $rtype = "booking";
            $rdate = date("Y-m-d");

            // INSERTION REQUEST TABLE
            $sql = "INSERT INTO request(rid,tid,ownerid,rtype,aptId,rdate)VALUES(null,'$tid','$oid','$rtype','$aptId','$rdate')";
            $result = $this->connect()->query($sql);
            if($result)
            {
                $userTo = $oid;
                $ndate = date("Y-m-d");
                $nstatus = 0;

                //GETTING NAME OF REQUESTING TENANT FOR DISPLAYING IN NOTIFICATION
                $sql1 = "SELECT name FROM user where email = '$email'";
                $res = $this->connect()->query($sql1);
                $row = $res->fetch_array(MYSQLI_BOTH);
                $name = $row['name'];
                $content = $name." "."has requested for booking your flat...";

                // INSERTION IN NOTIFICATION TABLE
                $sql2 = "INSERT INTO notifications(nid,userTo,content,ndate,nstatus)VALUES(NULL,'$userTo','$content','$ndate','$nstatus')";
                $finalRes = $this->connect()->query($sql2);

                if($finalRes)
                    return 0;
                else
                    return 3;
            }
            else
                return 2;
        }
    }
    
    
    
// METHOD FOR FETCHING ALL NOTIFICATION
  public function getNotification($userEmail, $limit)
  {
    $response = array();
  	if($limit=="few")
    {
        $sql = "SELECT * FROM notifications where userTo = '$userEmail' and nstatus = 0";
        $sql1 = "UPDATE notifications set nstatus=1 where userTo = '$userEmail'";
    }
    else
    {
        $sql = "SELECT * FROM notifications where userTo = '$userEmail'";
    }

    $result = $this->connect()->query($sql);

    if($limit=="few")
        $result1 = $this->connect()->query($sql1);

    return $result;

  }
  
  // METHOD FOR FILLING RENT DETAILS

  public function fillRentDetails($userEmail, $aptId, $amtPaid, $pdate)
  {
    $sql = "SELECT * FROM booking where aptId = '$aptId' and tid = '$userEmail'";
    $result = $this->connect()->query($sql);
    $num = $result->num_rows;

    $rstatus ="approved";
    $rtype = "booking";
    $sql = "SELECT * FROM request where aptId = '$aptId' and tid = '$userEmail' and rstatus='$rstatus' and rtype = '$rtype'";
    $result = $this->connect()->query($sql);
    $num1 = $result->num_rows;

        if($num>0)
        {
            $approved ="no";
            $sql = "UPDATE rent set amtPaid = '$amtPaid',approved='$approved',pdate = '$pdate' where tid = '$userEmail' and aptId='$aptId'";
        }
        else if($num1 > 0)
        {
            $total=0;
            $approved="no";
            $sql = "INSERT INTO rent(rentId, tid, aptId, amtPaid, total, pdate, approved)VALUES(null,'$userEmail','$aptId','$amtPaid','$total','$pdate','$approved')";
        }
        $result = $this->connect()->query($sql);
        if($result)
        {
        //Send request
        // owner email
        $owner_sql = "SELECT ownerid from apartment where aptId = '$aptId'";
        $owner_res = $this->connect()->query($owner_sql);
        $owner_row = $owner_res->fetch_array(MYSQLI_BOTH);

        //data for request query
        $ownerEmail =  $owner_row['ownerid'];
        $rtype = "rent";
        $reqstatus = "pending";
        $rdate = date("Y-m-d");

        $sql = "INSERT INTO request(rid, tid, ownerid, rtype, aptId, rdate,rstatus)VALUES(null,'$userEmail','$ownerEmail','$rtype','$aptId','$rdate','$reqstatus')";
        $result = $this->connect()->query($sql);

        // Notification to owner
        $nstatus=0;
        $content = "You have a pending request for approval of rent.Apartment - ".$aptId;
        $noti_sql = "INSERT INTO notifications(nid, userTo, content, ndate, nstatus)VALUES(null,'$ownerEmail','$content','$rdate','$nstatus')";
        $noti_res = $this->connect()->query($noti_sql);
        return 0;
        }
        else
        {
            return 1;
        }
  }
  
  
  //Adding profile image
  
  function uploadImage($Email,$name,$image)
        {
            $uploadpath="../media/$name";
            $sql = "UPDATE user set image = '$name' where email = '$Email'";
            $res = $this->connect()->query($sql);
            if($res)
            {
                file_put_contents($uploadpath,base64_decode($image));
                return true;
            }
            else
                return false;
        }
  }
?>