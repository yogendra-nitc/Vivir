<?php
include('Database.php');
    class userStart extends Database{
        
// ********** METHOD FOR USER SIGNUP **********

        public function userRegistration($name, $email,$password,$userType){
            $sql = "SELECT * FROM user WHERE email = '$email'";
            $result = $this->connect()->query($sql);
            $numRows = $result->num_rows;
            if($numRows>0)
            {
                return 0;
            }
            else
            {
                $rdate = date("Y-m-d");
                $sql = "INSERT INTO user(email, password,name,userType,regDate)VALUES('$email','$password','$name','$userType','$rdate')";
                $result = $this->connect()->query($sql);
                
                // id generation
                $i=0; $temp="v";
                while($email[$i]!='@')
                {
                    $temp = $temp.$email[$i];
                    $i = $i + 1;
                }
                echo "$temp";
                if($userType == "tenant")
                {
                    $rdate = date("Y-m-d");
                    $tid = $temp."t";
                    $sql = "INSERT INTO tenant(uid,email,rdate)VALUES('$tid','$email','$rdate')";
                    $result = $this->connect()->query($sql);
                }
                else if($userType == "owner")
                {
                    $rdate = date("Y-m-d");
                    $oid = $temp."o";
                    $sql = "INSERT INTO owner(uid,email,rdate)VALUES('$oid','$email','$rdate')";
                    $result = $this->connect()->query($sql);
                }
                return 1;
            }
        }

// ********** METHOD FOR USER LOGIN **************

        public function userLogin($email, $password)
        {
            $sql = "SELECT email, password FROM user WHERE email = '$email'";
            $result = $this->connect()->query($sql);
            $numRows = $result->num_rows;

            // CHECKING FOR REGISTRATION

            if($numRows>0)
            {
                $row = $result->fetch_array(MYSQLI_BOTH);
                $DbEmail = $row['email'];
                $DbPassword = $row['password'];

            // CHECKING FOR WRONG PASSWORD

                if(($DbEmail == $email) and ($DbPassword == $password))
                    return 2;
                else
                    return 1;
            }
            else
            {
                return 0;
            }
        }

//******* METHOD FOR GETTING AUTHENTIC USER DATA *******

        public function getAuthUserData($email)
        {
            $sql = "SELECT * FROM user where email = '$email'";
            $result = $this->connect()->query($sql);
            $row = $result->fetch_assoc();
            $data[] = $row; 
            return $data;
        }
   //******** CHNAGE PASSWORD *********
	    public function changePassword($email, $oldPwd, $newPwd)
	    {
	    	$sql = "SELECT * FROM user where email = '$email' and password = '$oldPwd'";
	    	$result = $this->connect()->query($sql);
	    	$num = $result->num_rows;
	    	if($num>0)
	    	{
	    		$sql = "UPDATE user SET password ='$newPwd' WHERE email = '$email'";
	    		$result = $this->connect()->query($sql);
	    		if($result)
	    			return true;
	    		else
	    			return false;
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    	
	    }
	    

// Get all complain list
	public function getcomplain($userId)
	{
		$sql = "SELECT * from complain where sid = '$userId' or rid = '$userId' order by cdate desc";
		$result = $this->connect()->query($sql);
	//	$row = $result->fetch_array(MYSQLI_BOTH);
		return $result;
	}
	
	//writing complain
	public function writeComplain($sid,$rid,$content)
	{
		$cdate = date("Y-m-d");
		$sql = "INSERT INTO complain(cid, sid, rid, cdate, content) VALUES (null,'$sid','$rid','$cdate','$content')";
		$result = $this->connect()->query($sql);
		if($result)
			return true;
		else
			return false;
	}
	
	// Get content Details
	public function complainDetails($cid)
	{
		$sql = "SELECT * from complain where cid = '$cid'";
		$result = $this->connect()->query($sql);
		if($result)
		{
			$sql1 = "UPDATE complain set cstatus ='1' where cid = '$cid'";
			$result1 = $this->connect()->query($sql1);
		}
		$row = $result->fetch_array(MYSQLI_BOTH);
		$content = $row['content'];
		return $content;
	}
   
	}

?>