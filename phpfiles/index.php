<?php

/*$input1["operation"] = "login";
$user["name"] = "Taho";
$user["email"] = "tahaaaa@gmail.com";
$user["password"] = "12321";
$input1 ["user"] = $user;

$data = json_encode($input1);
echo $data;

echo "<p>Encoding<p>";


$DBop = new DBoperation();
*/

//$DBop->insert_into("H88Ofi","hofika@@ja.hu","egyketto");

require_once'DBoperation.php';



if ($_SERVER['REQUEST_METHOD'] == 'POST') {



    $data = json_decode(file_get_contents("php://input"));
    

    if (isset($data->operation)) {
        $operation = $data->operation;
        switch ($operation) {
            case "register":
                registration($data->user->name, $data->user->email, $data->user->password);
                break;
            case "login":
                login($data->user->email, $data->user->password);
                break;
            default :
                $response["result"] = "Failure!";
                $response["message"] = "Invalid operation";
                echo json_encode($response);
                break;
        }
    } else {
        $response["result"] = "Failure!";
        $response["message"] = "Missing operation";
        echo json_encode($response);
    }
} else {
    $response["result"] = "Faliure!";
    $response["message"] = "Requested method is not POST!";
    echo json_encode($response);
}

function registration($name, $email, $pw) {
    $DBop = new DBoperation();
    if (($DBop->checkUserExist($email))) {
        $response["result"] = "Faliure!";
        $response["message"] = "User already exsist!";
        echo json_encode($response);
    } else {

        $DBop->insert_into($name, $email, $pw);
        $response["result"] = "Succes!";
        $response["message"] = "User registration succesfull!";
        echo json_encode($response);
    }
}

function login($email, $password) {

    $DBop = new DBoperation();

    if ($user=$DBop->Login($email, $password)) {
        
        $response["result"] = "Succes!";
        $response["message"] = "User login succesfull!";
        $response["user"]= json_decode($user);
        
        echo json_encode($response);
    } else {
        $response["result"] = "Faliure!";
        $response["message"] = "User login faliure!";
        echo json_encode($response);
    }
}

?>  