var pattern = /a/;
pattern.exec('abcde'); //a
pattern.exec('bcdefg'); //null
pattern.test('abcde'); //true
pattern.test('bcdefg'); //false

var str = 'bcdef';
str.match(pattern); //null
var str = 'abcdef';
str.replace(pattern, 'A'); //Abcdef


var pattern = /a./; //".은 문자 1개 의미"
pattern.exec('abcde'); //ab


var xi = /a/;
"Abcde".match(xi); //null
var oi = /a/i; //"i는 대소문자 구분 안함 의미"
"Abcde".match(oi); //A


var xg = /a/;
"abcdea".match(xg); //a, "중복되는 문자가 한개만 출력"
var og = /a/g;
"abcdea".match(xg); //a a, "중복되는 문자 모두 출력"
var ig = /a/ig;
"AabcdAa".match(ig); //A a A a, "중복되는문자 대소문자 구분없이 모두 출력"
