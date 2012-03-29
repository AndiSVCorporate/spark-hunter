<?php
$username="vtlan_test";
$password="";
$database="vtlan_sparkhunter_test";

$command = $_REQUEST['command'];

$out = "";

mysql_connect(localhost,$username,$password);
@mysql_select_db($database) or die( "Unable to select database");

function mysql_insert($table, $inserts) {
    $values = array_map('mysql_real_escape_string', array_values($inserts));
    $keys = array_keys($inserts);
        
    return mysql_query('INSERT INTO `'.$table.'` (`'.implode('`,`', $keys).'`) VALUES (\''.implode('\',\'', $values).'\')');
}


if($command == "addBattle"){
	$name = $_REQUEST['name'];
	mysql_insert('Battle', array('Name' => $name));
	$out = "Insert Complete";
}
else if($command == "getBattles"){
	$query="SELECT * FROM Battle";
	$result=mysql_query($query);
	while ($db_field = mysql_fetch_assoc($result)) {
		echo  $db_field['Name'] . ",";
	}
}
echo $out;
mysql_close();



?>