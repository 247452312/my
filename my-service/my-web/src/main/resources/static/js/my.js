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
function pushRequest(interfaceName, methodName, data, success, target = false) {
    let result = null;
    let json = JSON.stringify(new my_request(getAttrBySession("token").token, interfaceName, methodName, data));
    console.log("发送")
    console.log(json);
    $.ajax({
        url: "/action",
        type: "POST",
        data: json,
        contentType: "application/json;charset=utf8",
        async: false,
        success: function (data) {
            if (target) {
                layer.msg(data.msg);
            }
            if (data.code == 200) {
                result = success(data.data);
            } else {
            }
        }
    });
    return result;
}

class my_request {
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
        xhrFields: {withCredentials: true},
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
    console.log('attrName:  ' + attrName);
    console.log('data:  ' + data);
    $.ajax({
        url: "/setSession",
        type: "POST",
        xhrFields: {withCredentials: true},
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

var Base64 = {

    // private property
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

    // public method for encoding
    encode: function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;

        input = Base64._utf8_encode(input);

        while (i < input.length) {

            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output + this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) + this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);

        }

        return output;
    },

    // public method for decoding
    decode: function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;

        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        while (i < input.length) {

            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

        }

        output = Base64._utf8_decode(output);

        return output;

    },

    // private method for UTF-8 encoding
    _utf8_encode: function (string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";

        for (var n = 0; n < string.length; n++) {

            var c = string.charCodeAt(n);

            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if ((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }

        return utftext;
    },

    // private method for UTF-8 decoding
    _utf8_decode: function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;

        while (i < utftext.length) {

            c = utftext.charCodeAt(i);

            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if ((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i + 1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i + 1);
                c3 = utftext.charCodeAt(i + 2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }

        }

        return string;
    }

}
