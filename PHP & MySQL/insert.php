<?php
// mysql 연결과 값 삽입, 에러발생시 오류 출력
// $mysqli = mysqli_connect("example.com", "user", "password", "database");
// $res = mysqli_query($mysqli, "SELECT 'Please, do not use ' AS _msg FROM DUAL");
// $row = mysqli_fetch_assoc($res);
// echo $row['_msg'];

$conn = mysqli_connect("localhost", "root", "dnjseldkej347#W", "opentutorials");
$sql = "
  INSERT INTO topic (
    title,
    description,
    created
  ) VALUES (
    'MySQL',
    'MySQL is ..',
    NOW()
  )";
$result = mysqli_query($conn, $sql);
if($result === false){
  echo mysqli_error($conn); //실무 개발환경에선 보안상 파일에 오류 출력하도록 설정하시오
}

?>
