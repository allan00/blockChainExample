/**
 * Created by RichardLu on 5/12/16.
 */

var rootUrl1 = "http://119.29.98.174:8080";
var rootUrl2 = "http://119.29.98.174:8081";
var rootUrl3 = "http://119.29.98.174:8082";


function requestGiveMoney(url, amount, remark) {
    var data = {
        "type": "normal",
        "msgBody": {
            "command": 1,
            "amount": amount,
            "remark": remark,
            "js": null
        }
    };
    return $.ajax({
        type: "post",
        url: url + '/addBlock',
        dataType: 'json',
        data: data
    }).done(function(data) {
        handleGiveMoney(data);
    }).fail(function(error) {

    });
}


function requestFetchMoney(url, amount, remark) {
    var data = {
        "type": "normal",
        "msgBody": {
            "command": 2,
            "amount": amount,
            "remark": remark,
            "js": null
        }
    };
    return $.ajax({
        type: "post",
        url: url + '/addBlock',
        data: data
    }).done(function(data) {
        handleFetchMoney(data);
    }).fail(function(error) {

    });
}

function requestCustomOp(url, amount, remark, js) {
    var data = {
        "type": "normal",
        "msgBody": {
            "command": 3,
            "amount": amount,
            "remark": remark,
            "js": js
        }
    };
    return $.ajax({
        type: "post",
        url: url + '/addBlock',
        data: data
    }).done(function(data) {
        handleCustomOp(data);
    }).fail(function(error) {

    });
}

function requestQueryRec(url) {
    return $.ajax({
        type: "get",
        url: url + '/queryBlock',
    }).done(function(data) {
        handleQueryRec(data);
    }).fail(function(error) {

    });
}


function handleGiveMoney(data) {
    // console.log(data);
    notie.alert(1, data, 1);
}

function handleFetchMoney(data) {
    notie.alert(1, data, 1);
}

function handleCustomOp(data) {
    console.log(data);
}

function handleQueryRec(data) {
    console.log(data);
}


function str2JSON(str) {
    try {
        return JSON.parse(str);
    } catch (e) {
        console.log(e);
        return e;
    }
}



// function query(isAll) {
//     var tabType = $('.prod-info-tabs.am-active').attr('id');
//     var keyword = '',
//         keywordType = '';

//     if (tabType == 'prod-tab') {
//         if (isAll) {
//             queryProdInfo(null, null, requestType.PROD);
//         } else {
//             keyword = $("input[name='keyWord_prod']").val();
//             keywordType = $("input[name='key-word-type']:checked").val();
//             queryProdInfo(keyword, keywordType, requestType.PROD);
//         }
//     } else {
//         if (isAll) {
//             queryProdInfo(null, null, requestType.K);
//         } else {
//             keyword = $("input[name='keyWord_k']").val();
//             keywordType = $("input[name='key-word-type']:checked").val();
//             queryProdInfo(keyword, keywordType, requestType.K);
//         }
//     }
// }

// function queryProdInfo(keyword, keywordType, type) {

//         switch (type) {
//             case requestType.K:
//                 if (!kCache.length) {
//                     requestKProdInfo().done(function() {
//                         queryData(kCache, keyword, keywordType);
//                     });
//                 } else {
//                     queryData(kCache, keyword, keywordType);
//                 }
//                 break;
//             case requestType.PROD:
//                 if (!proCache.length) {
//                     requestProProdInfo().done(function() {
//                         queryData(proCache, keyword, keywordType);
//                     });
//                 } else {
//                     queryData(proCache, keyword, keywordType);
//                 }
//                 break;
//         }

// }

function queryData(cache, keyword, keywordType) {

    var resultArr = [];
    if (keywordType == 'prodName') {
        $.each(cache, function(index, item) {
            if (item.productName && item.productName.indexOf(keyword) != -1)
                resultArr.push(item);
        });
    } else if (keywordType == 'prodCode') {
        $.each(cache, function(index, item) {
            if (item.productName && item.productCode.indexOf(keyword) != -1)
                resultArr.push(item);
        });
    } else {
        resultArr = cache;
    }

    var rowHtml = '';

    $.each(resultArr, function(index, item) {
        rowHtml += "<tr class='prod-list-row'> <td class='col-sm-3'>" + item.productName + "</td> <td class='col-sm-3'>" + item.productCode + "</td> <td class='col-sm-3'>" + item.productDesc + "</td> <td class='col-sm-3'>" + JSON.stringify(item) + "</td> </tr>";
    });

    $('.prod-list-body').empty();
    $('.prod-list-body').append(rowHtml);
}



//合法验证

function isDigit(s) {
    var patrn = /^-?\d+\.{0,}\d{0,}$/;
    if (!patrn.exec(s)) {
        notie(3,"请输入正确的金额", 1);
        return false
    } else {
        return true
    }
}

function checkEmpty() {
    
}

function CheckForm1(s) {
    if (s.length == 0) {
        notie.alert(3,"请输入您姓名!",1);
        return false;
    }
    return true;
}

