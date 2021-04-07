<?php
require "TestDataBaseConfig.php";

class TestDataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new TestDataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function FetchTime()
    {
        $this->sql = "SELECT * FROM `mpu6050` WHERE 1";//select the table
        $result = mysqli_query($this->connect, $this->sql);//implement the sql
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) 
        {
            $resultText = $row['time'];
            return $resultText;
        }  
        else
        return false;   
    }

}

?>
