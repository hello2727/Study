<?php
//var_dump():변수의 정보를 출력하는 함수
//$_POST:값을 전송하는 방식
//mysqli_multi_query():여러 명령의 결과 출력
//die($ ):출력하면서 실행 멈춤
$conn = mysqli_connect(
  'localhost',
  'root',
  'dnjseldkej347#W',
  'opentutorials');

$filtered = array(
  'title'=>mysqli_real_escape_string($conn, $_POST['title']), //데이터 필터링
  'description'=>mysqli_real_escape_string($conn, $_POST['description'])
);

$sql = "
  INSERT INTO topic
    (title, description, created)
    VALUES(
        '{$_POST['title']}',
        '{$_POST['description']}',
        NOW()
    )
";
$result = mysqli_multi_query($conn, $sql);
if($result === false){
  echo '저장하는 과정에서 문제가 생겼습니다. 관리자에게 문의해주세요';
  error_log(mysqli_error($conn)); //관리자만 에러로그 볼 수 있음
} else {
  echo '성공했습니다. <a href="index.php">돌아가기</a>';
}
?>
