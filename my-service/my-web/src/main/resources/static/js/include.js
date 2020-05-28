$('.top').load('/top.html');
$('.menu').load('/menu.html');
$('.page').load('/page.html');

$("head").append("<link>");
var toolbarCss = $("head").children(":last");
toolbarCss.attr({
    rel: "stylesheet",
    type: "text/css",
    href: "/css/otherPage.css"
});
