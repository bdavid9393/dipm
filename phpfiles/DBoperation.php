<?php

class DBoperation {

    private $dbIp = '62.210.203.171';
    private $dbName = 'audiauto_andro';
    private $dbUser = 'audiauto_pubi';
    private $dbPass = 'Andro1c448';

    public function __construct() {
        $this->conn = new PDO("mysql:host=" . $this->dbIp . ";dbname=" . $this->dbName, $this->dbUser, $this->dbPass);
    }

    public function insert_into($name, $email, $pw) {
        $user_id = null;

        $sql = 'INSERT INTO user SET user_id =:user_id,name =:name,email =:email,pw =:pw';


        $query = $this->conn->prepare($sql);
        $query->execute(array('user_id' => $user_id, 'name' => $name, 'email' => $email, 'pw' => $pw));


        if ($query) {

            return true;
        } else {

            return false;
        }
    }

    public function checkUserExist($email) {

        $sql = 'SELECT COUNT(*) from user WHERE email =:email';
        $query = $this->conn->prepare($sql);
        $query->execute(array('email' => $email));

        if ($query) {

            $row_count = $query->fetchColumn();

            if ($row_count == 0) {

                return false;
            } else {

                return true;
            }
        } else {

            return false;
        }
    }

    public function Login($email, $password) {

        $sql = 'SELECT * from user WHERE email =:email';
        $query = $this->conn->prepare($sql);
        $query->execute(array('email' => $email));
        $data = $query->fetchObject();

        if (strcmp($data->pw, $password) == 0) {
            $user["name"] = $data->name;
            $user["password"] = $data->pw;
            $user["email"]=$data->email;
            $user= json_encode($user);
            return $user;
        } else {
            return false;
        }
    }

}
