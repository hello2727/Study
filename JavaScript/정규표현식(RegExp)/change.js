var urlPattern = /\b(?:https?):\/\/[a-z0-9-+&@#\/%?=~_|!:,.;]*/gim;
var content = '생활코딩 : http://opentutorials.org/course/1 입니다. 네이버 : http://naver.com 입니다. ';
var result = content.replace(urlPattern, function(url){ //"urlPattern을 함수의 url인자로 받음"
    return '<a href="'+url+'">'+url+'</a>';
});
console.log(result);
