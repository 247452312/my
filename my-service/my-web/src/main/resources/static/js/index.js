var left_logo_path = "/pic/hzxq-little.png";
var right_logo_path = "/pic/my-uhyils.png";
var menu_id = 0;

function addMenu(elem, data, j) {
    if (data == null || data.length === 0) {
        return;
    }
    data.sort(function (a, b) {
        return a.sort - b.sort;
    });
    for (let i = 0; i < data.length; i++) {
        let new_li = $("<li class='left-span close mark" + j + " i" + j + "" + i + "'><a href='javascript:;' temp_fref='" + data[i].url + "'><i class='" + data[i].icon + "'>·</i><span>" + data[i].name + "</span></a></li>");
        let subNode = data[i].subNode;
        if (subNode == null || subNode.length === 0) {
            new_li.addClass("level");
            new_li.children('a').attr("id", "menu_id" + menu_id);
            menu_id++;
            elem.append(new_li);
            continue;
        } else {
            new_li.children('a').append("<i class='arrow arrow-left'></i>");
        }
        let new_ul = $("<ul></ul>");
        addMenu(new_ul, subNode, j * 10 + i);
        new_li.append(new_ul);
        new_li.children("a").click(function () {
            let css = $(this).parent().children("ul").css("display");
            if (css === "none") {
                $(this).children(".arrow").rotate({
                    angle: 225,
                    animateTo: 135,
                    easing: $.easing.easeInOutExpo
                });
                $(this).parent().children("ul").css("display", "block");
            } else {
                $(this).children(".arrow").rotate({
                    angle: 135,
                    animateTo: 225,
                    easing: $.easing.easeInOutExpo
                });
                $(this).parent().children("ul").css("display", "none");
            }
        });
        elem.append(new_li);
    }
}

$(function () {
    $('.my-left-logo').find('.img1').attr('src', left_logo_path);
    $('.my-left-logo').find('.img2').attr('src', right_logo_path);

    var json = [
        {
            id: "",
            name: "title1",
            icon: "title1-icon",
            sort: 1,
            type: false,
            url: "/a",
            subNode: [
                {
                    id: "",
                    name: "这是四字",
                    icon: "big1-icon",
                    sort: 1,
                    type: false,
                    url: "",
                    subNode: [
                        {
                            id: "",
                            name: "small1",
                            icon: "small1-icon",
                            sort: 1,
                            type: false,
                            url: "",
                            subNode: []

                        },
                        {
                            id: "",
                            name: "small2",
                            icon: "small2-icon",
                            sort: 3,
                            type: false,
                            url: "",
                            subNode: []
                        }
                    ]
                },
                {
                    id: "",
                    name: "这是四字",
                    icon: "big2-icon",
                    sort: 1,
                    type: false,
                    url: "/2",
                    subNode: [
                        {
                            id: "",
                            name: "small1",
                            icon: "small1-icon",
                            sort: 1,
                            type: false,
                            url: "",
                            subNode: []

                        },
                        {
                            id: "",
                            name: "small2",
                            icon: "small2-icon",
                            sort: 3,
                            type: false,
                            url: "",
                            subNode: []
                        }
                    ]
                }
            ]
        },
        {
            id: "",
            name: "title2",
            icon: "title1-icon",
            sort: 1,
            type: false,
            url: "",
            subNode: [
                {
                    id: "",
                    name: "big1",
                    icon: "big1-icon",
                    sort: 1,
                    type: false,
                    url: "",
                    subNode: [
                        {
                            id: "",
                            name: "small1",
                            icon: "small1-icon",
                            sort: 1,
                            type: false,
                            url: "",
                            subNode: [
                                {
                                    id: "",
                                    name: "little_small2",
                                    icon: "small2-icon",
                                    sort: 3,
                                    type: false,
                                    url: "",
                                    subNode: []
                                }
                            ]

                        },
                        {
                            id: "",
                            name: "small2",
                            icon: "small2-icon",
                            sort: 3,
                            type: false,
                            url: "",
                            subNode: []
                        }
                    ]
                },
                {
                    id: "",
                    name: "big2",
                    icon: "big2-icon",
                    sort: 1,
                    type: false,
                    url: "",
                    subNode: [
                        {
                            id: "",
                            name: "small1",
                            icon: "small1-icon",
                            sort: 1,
                            type: false,
                            url: "",
                            subNode: []

                        },
                        {
                            id: "",
                            name: "small2",
                            icon: "small2-icon",
                            sort: 3,
                            type: false,
                            url: "",
                            subNode: []
                        }
                    ]
                }
            ]
        }
    ]
    json.sort(function (a, b) {
        return a.sort - b.sort;
    });
    for (let i = 0; i < json.length; i++) {
        let new_div = $("<li class='left top-line title-span mark" + i + "'><a href='javascript:;' temp_fref='" + json[i].url + "'><i class='" + json[i].icon + "'></i><span>" + json[i].name + "</span></a></li>");
        $("#title-ul").append(new_div);
        let subNode = json[i].subNode;

        addMenu($("#left-tree"), subNode, i);
    }
    $("#title-ul").append($("<div class='clean'></div>"));
    $(".my-left-menu").find(".close").children("ul").css("display", "none");
    $($("#title-ul>li").get(1)).addClass("title-select");
    $("#left-tree").children("li").addClass("shadow");
    $("#left-tree>.mark0").removeClass("shadow");
    $(".top-line").click(function () {
        $(".top-line").removeClass("title-select");
        $(this).addClass("title-select");
        let classs = $(this).attr("class");
        let split = classs.split(' ');
        let temp = "";
        for (let i = 0; i < split.length; i++) {
            if (split[i].indexOf("mark") != -1) {
                temp = split[i];
                break;
            }
        }
        $("#left-tree").children("li").addClass("shadow");
        $("#left-tree").children("." + temp).removeClass("shadow");

    });


});


