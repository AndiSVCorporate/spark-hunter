<?php
	function mysql_insert($table, $inserts) {
		$values = array_map('mysql_real_escape_string', array_values($inserts));
		$keys = array_keys($inserts);
        
		return mysql_query('INSERT INTO `'.$table.'` (`'.implode('`,`', $keys).'`) VALUES (\''.implode('\',\'', $values).'\')');
	}
	function mysql_update($table, $id, $inserts) {
		$values = array_map('mysql_real_escape_string', array_values($inserts));
		$keys = array_keys($inserts);
        
		return mysql_query('UPDATE `'.$table.'` (`'.implode('`,`', $keys).'`) VALUES (\''.implode('\',\'', $values).'\') WHERE ID=`'.$id);
	}
?>