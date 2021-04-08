<?php
require "ConnectDB.php";
$db = new TestDataBase();
    if ($db->dbConnect()) {
        echo $db->FetchTime();       
    } else echo "Error: Database connection";
?>
