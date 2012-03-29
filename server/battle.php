<?php
include 'sqlutils.php';

$username="vtlan_test";
$password="M8Bp*bOK7UIC";
$database="vtlan_sparkhunter_battle_data";
$command = $_REQUEST['command'];

$out = "";

mysql_connect(localhost,$username,$password);
@mysql_select_db($database) or die( "Unable to select database");


if($cmd == "setturn"){
	$id = $_REQUEST['id'];
	$hp = $_REQUEST['hp'];
	$mp = $_REQUEST['mp'];
	$str = $_REQUEST['str'];
	$int = $_REQUEST['int'];
	$agi = $_REQUEST['agi'];
	$def = $_REQUEST['def'];
	$cmd = $_REQUEST['cmd'];
	$p1 = $_REQUEST['p1'];
	$p2 = $_REQUEST['p2'];
	$p3 = $_REQUEST['p3'];
	mysql_insert('turn', array(
		'ID' => $id,
		'HP'=> $hp,'MP'=> $mp,
		'STR'=> $str,'INT'=> $int,
		'AGI'=> $agi,'DEF'=> $def,
		'CMD'=> $cmd,'P1'=> $p1,'P2'=> $p2,'P3'=> $p3));
	$out = "Insert Successful";
}

echo $out;
mysql_close();



?>