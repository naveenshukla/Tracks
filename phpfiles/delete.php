<?php
$mob = '853895376';
// Create connection
$conn =  mysql_connect('mysql.gbfreehosting.com','u812019548_rnps','rajat123');

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
mysql_select_db('u812019548_rnps', $conn);
    
    //delete from mysql table
    $sql = "DELETE FROM tracks WHERE phone = '$mob'";
    if(!mysql_query($sql,$conn))
    {
        die('Error : ' . mysql_error());
    }
?>