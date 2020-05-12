/**
 * @Name: encryptRSA
 * @Type : Function
 * @Version : 1.0
 * @Date : 2019-04-15
 * @Author : Taehyun Kim
 * @Description : RSA PublicKey 전달
 */
var rsaPublicKeyModulus = sessionStorage.getItem("publicModulus");
var rsaPublicKeyExponent = sessionStorage.getItem("publicExponent");

var encryptByRSA = function (plainText) {

    let rsa = new RSAKey();
    rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);
    let encryptText = rsa.encrypt(plainText);

    rsa = null;

    return encryptText;
}

var encryptMapByRSA = function (inputParam) {
    let keys = Object.keys(inputParam);
    for (let i = 0; i < keys.length; i++) {
        inputParam[keys[i]] = encryptByRSA(inputParam[keys[i]], publicKeyModulus, publicKeyExponent);
    }
    return inputParam;
}

var encryptSha256 = function (inputParam) {
    let charSize = 8;
    let utfInputParam = Utf8Encode(inputParam);
    return binb2hex(core_sha256(str2binb(utfInputParam), utfInputParam.length * charSize))
}


var str2binb = function (str) {
    let chrsz = 8;
    let bin = Array();
    let mask = (1 << chrsz) - 1;
    for (let i = 0; i < str.length * chrsz; i += chrsz) {
        bin[i >> 5] |= (str.charCodeAt(i / chrsz) & mask) << (24 - i % 32);
    }
    return bin;
}

var Utf8Encode = function (string) {
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
}

var binb2hex = function (binarray) {
    var hexcase = 0;
    var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
    var str = "";
    for (var i = 0; i < binarray.length * 4; i++) {
        str += hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8 + 4)) & 0xF) +
            hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8)) & 0xF);
    }
    return str;
}


var Base64 = {

    // private property
    _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",

    // public method for encoding
    encode: function (input) {

        if (input == null || input == "") return "";

        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;

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

            output = output +
                this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
        }

        return output;
    },

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

        return output;
    }
};

var getReplaceTxt = function (codeData, idTxt) {
    // set replace text
    for (var j = 0; j < codeData.length; ++j) {
        if (idTxt == codeData[j].codeId) {
            return codeData[j].codeVal1;
        }
    }
}

/**
 * 오늘 날짜에서 계산된 날짜 반환
 * example : getAgoDate(0,0,0) -> 오늘 날짜를 yyyy-mm-dd 형태로 반환 함
 */
var getAgoDate = function (yyyy, mm, dd) {
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth();
    var day = today.getDate();

    var resultDate = new Date(yyyy + year, month + mm, day + dd);

    year = resultDate.getFullYear();
    month = resultDate.getMonth() + 1;
    day = resultDate.getDate();

    if (month < 10)
        month = "0" + month;
    if (day < 10)
        day = "0" + day;
    return year + "-" + month + "-" + day;
};

var getCalDiff = function (startDt, endDt, kind) {

    var arr1 = startDt.split('-');
    var arr2 = endDt.split('-');
    var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
    var dat2 = new Date(arr2[0], arr2[1], arr2[2]);

    // 날짜 차이 알아 내기
    var diff = dat2 - dat1;
    var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
    var currWeek = currDay * 7;// 주 만듬
    var currMonth = currDay * 30;// 월 만듬
    var currYear = currMonth * 12; // 년 만듬

    /*
    console.log("* 날짜 두개 : " + startDt + ", " + endDt);
    console.log("* 일수 차이 : " + parseInt(diff/currDay) + " 일");
    console.log("* 주수 차이 : " + parseInt(diff/currWeek) + "주");
    console.log("* 월수 차이 : " + parseInt(diff/currMonth) + "월");
    */

    if (kind == 'D') {
        return parseInt(diff / currDay);
    } else if (kind == 'W') {
        return parseInt(diff / currWeek) + 1;
    } else if (kind == 'M') {
        return parseInt(diff / currMonth) + 1;
    }
};

/**
 * 두 날짜 기간(일) 반환
 */
var getPeriod = function (startDt, endDt) {
    var arr1 = startDt.split('-');
    var arr2 = endDt.split('-');
    var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
    var dat2 = new Date(arr2[0], arr2[1], arr2[2]);

    // 날짜 차이 알아 내기
    var diff = dat2 - dat1;
    var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
    //var currWeek = currDay * 7;// 주 만듬
    //var currMonth = currDay * 30;// 월 만듬
    //var currYear = currMonth * 12; // 년 만듬

    return parseInt(diff / currDay);
};

/**
 * 두 날짜 기간(시간) 반환
 */
var getTimeDiff = function (sla) {
    var now = new Date();
    var gap = sla.getTime() - now.getTime();
    //if(gap < 0) return 0;
    // var sec_gap = gap / 1000; // 초 변환
    var min_gap = gap / 1000 / 60; // 분 변환
    min_gap = parseInt(min_gap) + 1; // int 형으로 변환해서 소수점 버림
    return min_gap;
};

/**
 * 지정 날짜에서 계산된 날짜 반환
 * example : getAgoDate('yyyy-mm-dd', 0,0,0) -> 오늘 날짜를 yyyy-mm-dd 형태로 반환 함
 */
var getCalDate = function (setDate, yyyy, mm, dd) {

    var dateArr = setDate.split("-");
    var resultDate = new Date(dateArr[0], dateArr[1] - 1, dateArr[2], 0, 0, 0);

    var resultYear = parseInt(dateArr[0]);
    var resultMonth = parseInt(dateArr[1]);
    if (yyyy != 0) {
        resultYear += yyyy;
    }
    if (mm != 0) {
        resultMonth += mm;

        if (resultMonth > 12) {
            resultYear++;
            resultMonth -= 12;
        } else if (resultMonth < 1) {
            resultYear--;
            resultMonth = 12 + resultMonth;
        }
    }
    resultDate.setDate(resultDate.getDate() + dd);

    if (dd == 0) {
        year = resultYear + "";
        month = resultMonth;
    } else {
        year = resultDate.getFullYear();
        month = resultDate.getMonth() + 1;
    }
    day = resultDate.getDate();

    if (month < 10)
        month = "0" + month;
    if (day < 10)
        day = "0" + day;

    dateStr = year + "-" + month + "-" + day;
    while (isValidDate(dateStr) == false) {
        day = parseInt(day) - 1;
        dateStr = year + "-" + month + "-" + day;
    }

    return dateStr = year + "-" + month + "-" + day;
};

/**
 * 지정 날짜에서 계산된 날짜 반환
 * example : getAgoDate('yyyy-mm-dd', 0,0,0) -> 오늘 날짜를 yyyy-mm-dd 형태로 반환 함
 */
var getCalDate_new = function (setDate, yyyy, mm, dd) {

    var dateArr = setDate.split("-");
    //var resultDate = new Date(Date.UTC(dateArr[0], dateArr[1]-1, dateArr[2], 0, 0, 0));
    var resultDate = new Date(dateArr[0], dateArr[1] - 1, dateArr[2], 0, 0, 0);

    var resultYear = parseInt(dateArr[0]);
    var resultMonth = parseInt(dateArr[1]);
    if (yyyy != 0) {
        resultYear += yyyy;
    }
    if (mm != 0) {
        resultMonth += mm;

        if (resultMonth > 12) {
            resultYear++;
            resultMonth -= 12;
        } else if (resultMonth < 1) {
            resultYear--;
            resultMonth = 12 + resultMonth;
        }
    }
    resultDate.setDate(resultDate.getDate() + dd);

    if (dd == 0) {
        year = resultYear + "";
        month = resultMonth;
    } else {
        year = resultDate.getFullYear();
        month = resultDate.getMonth() + 1;
    }
    day = resultDate.getDate();

    if (month < 10)
        month = "0" + month;
    if (day < 10)
        day = "0" + day;

    dateStr = year + "-" + month + "-" + day;
    while (isValidDate(dateStr) == false) {
        day = parseInt(day) - 1;
        dateStr = year + "-" + month + "-" + day;
    }

    return dateStr = year + "-" + month + "-" + day;
};

// 날짜 유효성 검사
function isValidDate(param) {
    try {
        param = param.replace(/-/g, '');
        param = param.replace(/\//g, '');

        if (isNaN(param) || param.length != 8) {
            return false;
        }

        var year = Number(param.substring(0, 4));
        var month = Number(param.substring(4, 6));
        var day = Number(param.substring(6, 8));

        var dd = day / 0;

        if (month < 1 || month > 12) {
            return false;
        }

        var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var maxDay = maxDaysInMonth[month - 1];

        if (month == 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)) {
            maxDay = 29;
        }

        if (day <= 0 || day > maxDay) {
            return false;
        }
        return true;

    } catch (err) {
        return false;
    }
}

// yyyymmdd 형식에 구분자를 붙여 반환
var getDateFormat = function (val, gbn) {
    if (val.length < 8) return val;
    return val.substring(0, 4) + gbn + val.substring(4, 6) + gbn + val.substring(6, val.length);
};

// yyyy-MM-dd String To Date
var getDateData = function (dateStr) {
    if (dateStr == null || dateStr == '') return;

    var rtnDt = new Date();
    var dateArr = dateStr.split('-');
    if (dateArr.length == 3) {
        rtnDt.setFullYear(dateArr[0], dateArr[1] - 1, dateArr[2]);
    }
    return rtnDt;
}

// 숫자 체크
function digit_check(evt) {
    var code = evt.which ? evt.which : event.keyCode;
    if (code < 48 || code > 57) {
        return false;
    }
}

// 숫자 천단위로 끊어서 Comma 삽입
var addComma = function (val) {
    return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
};

// 숫자와 소수점만 입력가능
function isNumberKey(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;


    // Textbox value
    var _value = event.srcElement.value;


    // 소수점(.)이 두번 이상 나오지 못하게
    var _pattern0 = /^\d*[.]\d*$/; // 현재 value값에 소수점(.) 이 있으면 . 입력불가
    if (_pattern0.test(_value)) {
        if (charCode == 46) {
            return false;
        }
    }

    /*
    // 1000 이하의 숫자만 입력가능
    var _pattern1 = /^\d{3}$/; // 현재 value값이 3자리 숫자이면 . 만 입력가능
    if (_pattern1.test(_value)) {
        if (charCode != 46) {
            alert("1000 이하의 숫자만 입력가능합니다");
            return false;
        }
    } */

    // 소수점 둘째자리까지만 입력가능
    var _pattern2 = /^\d*[.]\d{2}$/; // 현재 value값이 소수점 둘째짜리 숫자이면 더이상 입력 불가
    if (_pattern2.test(_value)) {
        return false;
    }

    return true;
}

function JSONToCSVConvertor(JSONData, fileName, ShowLabel) {
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
    var CSV = '';
    if (ShowLabel) {
        var row = "";
        for (var index in arrData[0]) {
            row += index + ',';
        }
        row = row.slice(0, -1);
        CSV += row + '\r\n';
    }
    for (var i = 0; i < arrData.length; i++) {
        var row = "";
        for (var index in arrData[i]) {
            var arrValue = arrData[i][index] == null ? "" : '="' + arrData[i][index] + '"';
            row += arrValue + ',';
        }
        row.slice(0, row.length - 1);
        CSV += row + '\r\n';
    }
    if (CSV == '') {
        growl.error("Invalid data");
        return;
    }

    if (fileName == null || fileName.length == 0) fileName = "Report"

    if (msieversion()) {
        var blob = new Blob([CSV], {type: "text/csv"});
        navigator.msSaveOrOpenBlob(blob, fileName + ".csv");

        /*
        var IEwindow = window.open();
        IEwindow.document.write('sep=,\r\n' + CSV);
        IEwindow.document.close();
        IEwindow.document.execCommand('SaveAs', true, fileName + ".csv");
        IEwindow.close();
        */
    } else {
        var uri = 'data:application/csv;charset=utf-8,' + escape(CSV);
        var link = document.createElement("a");
        link.href = uri;
        link.style = "visibility:hidden";
        link.download = fileName + ".csv";
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }
}

function msieversion() {
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");
    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) // If Internet Explorer, return true
    {
        return true;
    } else { // If another browser,
        return false;
    }
    return false;
}

function isNull(val) {
    if (val == null) {
        return '';
    } else {
        return val
    }
    ;
}


//실수 체크
function isNumberFloat(str) {
    return /^[0-9]+(.[0-9]+)?$/.test(str);
}

// 정수 체크
function isNumberInteger(str) {
    return /^\+?(0|[1-9]\d*)$/.test(str);
}

// 비밀번호 체크함수
function fnCheckPassword(upw) {
//	    if(!/^[a-zA-Z0-9!@#$%^&*+-./():;<=>?[\]_`{|}]{8,100}$/.test(upw)) {
//	        alert('Password should be over 8');
//	        return false;
//	    }

    if (upw.length < 8) {
        alert('Password should be over 8');
        return false;
    }

    var chk_num = upw.search(/[0-9]/g);
    var chk_eng = upw.search(/[a-z]/g);
    var chk_cap = upw.search(/[A-Z]/g);

    if (chk_num < 0 || chk_eng < 0 || chk_cap < 0) {
        alert('Password should be mixed letter and number and include over 1 digit capital letter');
        return false;
    }
    return true;
}

/*****************************************************************
 * @Name  : fn_excel_download
 * @Type  : Function
 * @Description : 엑셀다운로드 함수
 * @Author: SeungHyun Son
 * @param : filename
 *****************************************************************/
//function fn_excel_download(filename) {
//	downloadUrl = "/MoniPortal/jsp/filedownload.jsp?filename=" + filename;
//	var downloadFrame = document.createElement("iframe");
//	downloadFrame.setAttribute('src',downloadUrl);
//	downloadFrame.setAttribute('class',"excel_dowonload_iframe");
//	document.body.appendChild(downloadFrame);
//}
