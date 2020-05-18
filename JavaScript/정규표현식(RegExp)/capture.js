var pattern = /(\w+)\s(\w+)/;
var str = "coding everybody";
var result = str.replace(pattern, "$2, $1"); //"$:그룹 의미"
console.log(result);

//everybody, coding
