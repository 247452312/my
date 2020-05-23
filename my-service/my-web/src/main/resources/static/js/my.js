/**
 * 设置cookie
 * @param cname
 * @param cvalue
 * @param exdays
 */
function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    const expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

/**
 * 获取cookie
 * @param cname
 * @returns {string}
 */
function getCookie(cname) {
    const name = cname + "=";
    const ca = document.cookie.split(";");
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(cname) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

/**
 * 向后台发消息
 * @param token
 * @param interfaceName
 * @param methodName
 * @param args
 * @param success
 * @returns {null}
 */
function pushRequest(request, success) {
    var result = null;
    $.ajax({
        url: "/action",
        type: "POST",
        data: JSON.stringify(request),
        contentType: "application/json",
        async: false,
        success: function (data) {
            console.log(data);
            result = success(data);
        }
    });
    return result;
}

class request {
    constructor(token, interfaceName, methodName, args) {
        this.token = token;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.args = args;
    }
}

function getAttrBySession(attrName) {
    var result = {};
    $.ajax({
        url: "/getSession",
        type: "POST",
        data: JSON.stringify({
            "attrName": attrName
        }),
        async: false,
        contentType: "application/json;charset=UTF-8"
    }).done(function (data) {
        console.log("获取session: " + JSON.stringify(data))
        result = data;
    });
    return result;
}

function setAttrBySession(attrName, data) {
    $.ajax({
        url: "/setSession",
        type: "POST",
        data: JSON.stringify({
            "attrName": attrName,
            "data": data
        }),
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            console.log("注入session" + JSON.stringify(data))
        }
    });

}
