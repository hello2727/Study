
<html>
  <body>
    <?php
    //출력 공격 차단(Cross Site Scripting)
      echo htmlspecialchars('<script>alert</script>')
    ?>
  </body>
</html>
