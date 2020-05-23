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


