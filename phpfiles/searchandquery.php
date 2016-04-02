<?php
// Create connection

$conn =  mysql_connect('mysql.gbfreehosting.com','u812019548_rnps','rajat123');

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
mysql_select_db('u812019548_rnps', $conn);
$jsondata = file_get_contents('rajatnavee.json');
$data = json_decode($jsondata, true);
$name = $data['name'];
$phone_number = $data['phone_number'];
$source = $data['from'];
$destination = $data['to'];
$searchResult=mysql_query("select * from tracks where source='$source' and destination ='$destination'" );
$json = array();
if(mysql_num_rows($searchResult)){
while($row=mysql_fetch_row($searchResult)){
$json['Orders'][]=$row;
}
}

//while($row=mysql_fetch_assoc($sql))
//$output[]=$row;
print(json_encode($json)); 
 //insert into mysql table
 $sql = "INSERT INTO tracks(phone, name, source, destination)
    VALUES('$phone_number', '$name', '$source', '$destination')";
    if(!mysql_query($sql,$conn))
    {
        die('Error : ' . mysql_error());
    }
?>