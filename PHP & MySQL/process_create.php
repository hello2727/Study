<?php
//var_dump():변수의 정보를 출력하는 함수
//$_POST:값을 전송하는 방식
$conn = mysqli_connect(
  'localhost',
  'root',
  'dnjseldkej347#W',
  'opentutorials');
$sql = "
  INSERT INTO topic
    (title, description, created)
    VALUES(
        '{$_POST['title']}',
        '{$_POST['description']}',
        NOW()
    )
";
$result = mysqli_query($conn, $sql);
if($result === false){
  echo '저장하는 과정에서 문제가 생겼습니다. 관리자에게 문의해주세요';
  error_log(mysqli_error($conn)); //관리자만 에러로그 볼 수 있음
} else {
  echo '성공했습니다. <a href="index.php">돌아가기</a>';
}
?>
