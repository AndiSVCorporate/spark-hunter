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
	echo mysql_insert('list', array('ID' => $id,'DESC' => $desc));

}
else if($command == "getBattles"){
	$query="SELECT * FROM list";
	$result=mysql_query($query);
	while ($db_field = mysql_fetch_assoc($result)) {
		echo  $db_field['ID'] . ",";
	}
}
else if($command == "acceptBattle"){
	$id = $_REQUEST['id1'];
	$id2 = $_REQUEST['id2'];
	echo mysql_query('UPDATE list SET ID2='.$id2.' WHERE ID='.$id);
	//mysql_update('list',$id,array('ID2'=>$id2));
}
else if($command == "checkforplayer"){
	$id = $_REQUEST['id1'];
	$result=mysql_query("SELECT * FROM list WHERE ID=$id");
	while ($db_field = mysql_fetch_assoc($result)) {
		echo $db_field['ID'] . ",";
		echo $db_field['ID2'] . ",";
	}
}
else if($command == "deletebattle"){
	$id = $_REQUEST['id1'];
	mysql_query("DELETE FROM list WHERE ID=$id");
}
else if($command == "startbattle"){
	$id = $_REQUEST['id1'];
	mysql_query("UPDATE list SET START=1 WHERE ID=$id");
}
else if($command == "checkstart"){
	$id = $_REQUEST['id1'];
	$result=mysql_query("SELECT * FROM list WHERE ID=$id");
	$db_field = mysql_fetch_assoc($result);
	echo $db_field['START'];
}
mysql_close();



?>