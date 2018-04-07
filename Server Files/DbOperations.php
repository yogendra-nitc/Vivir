<?php
include('Database.php');
    class userStart extends Database{
        
        // user signup
        public function userRegistration($name, $email,$password){
            $sql = "SELECT * FROM user WHERE email = '$email'";
            $result = $this->connect()->query($sql);
            $numRows = $result->num_rows;
            if($numRows>0)
            {
                return 0;
            }
            else
            {
                $rdate = "2018-04-06";
                $sql = "INSERT INTO user(email, password,rdate)VALUES('$email','$password','$rdate')";
                $result = $this->connect()->query($sql);
                return 1;
            }
        }

        // user login
        public function userLogin($email, $password)
        {
            $sql = "SELECT email, password FROM user WHERE email = '$email'";
            $result = $this->connect()->query()($sql);
            $numRows = $result->num_rows;

            // CHECKING FOR REGISTRATION

            if(numRows>0)
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

        // getUser Data after login
        public function getAuthUserData($email)
        {
            $sql = "SELECT * FROM user where email = '$email'";
            $result = $this->connect()->query()($sql);
            while($row = $result->fetch_assoc())
            {
                $data[] = $row; 
            }
            return $data;
        }
    }

?>