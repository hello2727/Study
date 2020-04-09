<?php
//var_dump():변수의 정보를 출력하는 함수
//$result->num_rows: 실행결과, 몇개의 행이 있었는지 출력
//mysqli_fetch_array(): mysql언어를 php에서 사용할 수 있게 번역하도록 가져오는 배열.
//print_r(): 변수의 정보를 출력하는 함수
$conn = mysqli_connect(
  'localhost',
  'root',
  'dnjseldkej347#W',
  'opentutorials');
//row가 1개일때
echo '<h1>single row</h1>';
$sql = "SELECT * FROM topic WHERE id = 6";
$result = mysqli_query($conn, $sql);
$row = mysqli_fetch_array($result);
echo '<h2>'.$row['title'].'</h2>';
echo $row['description'];

//row가 여러개일때
echo '<h1>multi row</h1>';
$sql = "SELECT * FROM topic";
$result = mysqli_query($conn, $sql);
while($row = mysqli_fetch_array($result)) {
  echo '<h2>'.$row['title'].'</h2>';
  echo $row['description'];
}

//var_dump($row); //더이상 줄께 없을 경우 null리턴
