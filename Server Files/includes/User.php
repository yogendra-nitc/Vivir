<?php
include('Database.php');
   class User extends Database {
    
    //Method for setting profile
    public function setProfile($name, $city, $state, $contact)
    {
        $sql = "UPDATE user set name = '$name' and city = '$city' and state= '$state' and contact = '$contact' where email = '$email'";
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

            // INSERTION REQUEST TABLE
            $sql = "INSERT INTO request(rid,tid,ownerid,rtype,aptId)VALUES(null,'$tid','$oid','$rtype','$aptId')";
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
                echo $name;
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

   }

?>