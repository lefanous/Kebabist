<?php
$connect = mysqli_connect('localhost', 'aygdk_Kebabruger', 'Password321', 'aygdk_Kebabs');
if ($connect->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$query = "Select * FROM Kebabsteder;";
$result = $connect->query($query);
$temp_array = array();

if ($result->num_rows > 0) {
  while($row = $result->fetch_assoc()) {
        $temp_array[] = $row;
    }
} else {
echo "got 0 rows from database";
}

header('Content-Type: application/json; charset=utf-8');
echo json_encode(array("Kebabsteder"=>$temp_array));
$connect->close();
?>
