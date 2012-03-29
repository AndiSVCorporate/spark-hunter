<?php
include 'sqlutils.php';

$username="vtlan_test";
$password="M8Bp*bOK7UIC";
$database="vtlan_sparkhunter_battle_data";
$command = $_REQUEST['command'];

$out = "";

mysql_connect(localhost,$username,$password);
@mysql_select_db($database) or die( "Unable to select database");

if($command == "addBattle"){
	$id = $_REQUEST['id'];
	$desc = $_REQUEST['desc'];
	$out = mysql_insert('list', array('ID' => $id,'DESC' => $desc));

}
else if($command == "getBattles"){
	$query="SELECT * FROM list";
	$result=mysql_query($query);
	while ($db_field = mysql_fetch_assoc($result)) {
		echo  $db_field['DESC'] . ",";
	}
}
else if($command == "acceptBattle"){
	$id = $_REQUEST['id'];
	$id2 = $_REQUEST['id2'];
	
	$query="SELECT * FROM list";
	mysql_update('list',$id,array('ID2'=>$id2));
}

echo $out;
mysql_close();



?>