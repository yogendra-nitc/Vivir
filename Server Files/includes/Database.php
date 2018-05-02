<?php
    
    class Database {
        private $servername;
        private $username;
        private $password;
        private $dbname;
        
        protected function connect(){
            $this->servername = "localhost";
            $this->username = "id5251988_vivir";
            $this->password = "vivir@2018";
            $this->dbname = "id5251988_db_vivir";
            
            $conn = new mysqli($this->servername,$this->username,$this->password,$this->dbname);
            return $conn;
        }
    }
?>