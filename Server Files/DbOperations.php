<?php
include('Database.php');
    class userStart extends Database{
        
        // user signup
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
                $sql = "INSERT INTO user(email, password,name,userType)VALUES('$email','$password','$name','$userType')";
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
                    $sql = "INSERT INTO tenant(tid,email,rdate)VALUES('$tid','$email','$rdate')";
                    $result = $this->connect()->query($sql);
                }
                else if($userType == "owner")
                {
                    $rdate = date("Y-m-d");
                    $oid = $temp."o";
                    $sql = "INSERT INTO owner(oid,email,rdate)VALUES('$oid','$email','$rdate')";
                    $result = $this->connect()->query($sql);
                }
                return 1;
            }
        }

        // user login
        public function userLogin($email, $password)
        {
            $sql = "SELECT * FROM user WHERE email = '$email' and password = '$password'";
            $result = $this->connect()->query()($sql);
            $numRows = $result->num_rows;
            if(numRows>0)
            {
                $sql = "SELECT * FROM tenant where email = '$email'";
            }
        }
    }

?>