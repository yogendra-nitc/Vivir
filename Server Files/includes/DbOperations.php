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
    }

?>
