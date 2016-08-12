/**
 * Created by RichardLu on 5/12/16.
 */

var rootUrl1 = "http://119.29.98.174:8080";
var rootUrl2 = "http://119.29.98.174:8081";
var rootUrl3 = "http://119.29.98.174:8082";

var commandDic = {
    1:'捐款',
    2:'提款',
    3:'自定义操作'
}

function requestGiveMoney(url, amount, remark) {
    var data = '{"type": "normal","msgBody": {"command": 1,"remark": "'+remark+'","amount":'+amount+',"js": null}}';
    return $.ajax({
        type: "post",
        url: url + '/addBlock',
        contentType: "application/json;charset=UTF-8",
        data: data
    }).done(function(data) {
        handleGiveMoney(data);
    }).fail(function(error) {

    });
}


function requestFetchMoney(url, amount, remark) {
    var data = '{"type": "normal","msgBody": {"command": 2,"amount": '+amount+',"remark": "'+remark+'","js": null}}';
    return $.ajax({
        type: "post",
        url: url + '/addBlock',
        contentType: "application/json;charset=UTF-8",
        data: data
    }).done(function(data) {
        handleFetchMoney(data);
    }).fail(function(error) {

    });
}

function requestCustomOp(url, amount, remark, js) {
    var data = '{"type": "normal","msgBody": {"command": 3,"amount":null,"remark": "","js": "'+js+'"}}';
    return $.ajax({
        type: "post",
        url: url + '/addBlock',
        contentType: "application/json;charset=UTF-8",
        data: data
    }).done(function(data) {
        handleCustomOp(data);
    }).fail(function(error) {

    });
}

function requestQueryRec(url, htmlHandle) {
    $.ajax({
        type: "get",
        url: url + '/queryBlock',
        dataType: 'json'
    }).done(function(data) {
        handleQueryRec(data, htmlHandle);
    }).fail(function(error) {

    });
}

function requestQueryRecWithIP(url, ip, htmlHandle) {
    return $.ajax({
        type: "post",
        url: url + '/queryRecordByIP',
        contentType: "application/json;charset=UTF-8",
        data: '{"ip":'+ip+'}',
        dataType: 'json'
    }).done(function(data) {
        handleQueryRecWithIp(data, htmlHandle);
    }).fail(function(error) {

    });
}

function requestAllQueryRec(url, htmlHandle) {
    return $.ajax({
        type: "get",
        dataType: 'json',
        url: url + '/queryAllBlock'
    }).done(function(data) {
        handleAllQueryRec(data, htmlHandle);
    }).fail(function(error) {

    });
}


function handleGiveMoney(data) {
    // console.log(data);
    notie.alert(1, '捐款成功', 1);
}

function handleFetchMoney(data) {
    notie.alert(1, '提款成功', 1);
}

function handleCustomOp(data) {
    notie.alert(1, '自定义操作成功', 1);
}

function handleAllQueryRec (data, htmlHandle) {
    var rowHtml = "";
    $.each(data, function(index, item) {
        var bodyArr = item.body;
        rowHtml += '<li><p class="block-header">Header  前序块号:' + item.prevIndex +' 前序Hash:'+ item.prevHash +' 当前块号:'+ item.index + ' Hash:'+item.hash+'时间戳:'+item.generateTime+'</p>'
        $.each(bodyArr, function(index, item){
            rowHtml += '<p>用户号:'+item.ip +' '+ commandDic[item.command] +':'+ item.amount +'元'+ ' 备注:'+item.remark+'\n 时间:'+ item.time +'</p>';
        });
        rowHtml += '</li>'
    });

    htmlHandle(rowHtml);
} 

function handleQueryRec(data, htmlHandle) {

    var block = data.block;
    var rowHtml = '<p class="block-header">Header  前序块号:' + block.prevIndex +' 前序Hash:'+ block.prevHash +' 当前块号:'+ block.index + ' Hash:'+block.hash+'时间戳:'+block.generateTime+'</p>';
    var bodyArr = block.body;
    $.each(bodyArr, function(index, item){
        rowHtml += '<p>用户号:'+item.ip +' '+ commandDic[item.command] +':'+ item.amount +'元'+ ' 备注:'+item.remark+'\n 时间:'+ item.time +'</p>';
    });
    htmlHandle([data.total.toFixed(2), rowHtml]);
}

function handleQueryRecWithIp(data, htmlHandle) {

    var bodyArr = data;
    var rowHtml = "";
    $.each(bodyArr, function(index, item){
        rowHtml += '<li><p>用户号:'+item.ip +' '+ commandDic[item.command] +':'+ item.amount +'元'+ ' 备注:'+item.remark+'\n 时间:'+ item.time +'</p></li>';
    });
    htmlHandle(rowHtml);
}


function str2JSON(str) {
    try {
        return JSON.parse(str);
    } catch (e) {
        console.log(e);
        return e;
    }
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

function checkEmpty(s) {
    if (s.length == 0) {
        notie.alert(3,"请输入备注信息!",1);
        return false;
    }
    return true;    
}



// function customOpt() {
//     return [{
//         "command":1,
//         "amount":10,
//         "remark":"asas"
//     },{
//         "command":1,
//         "amount":10,
//         "remark":"asas"
//     },{
//         "command":1,
//         "amount":10,
//         "remark":"asas"
//     },{
//         "command":2,
//         "amount":9,
//         "remark":"asas"
//     },{
//         "command":2,
//         "amount":5,
//         "remark":"asas"
//     }];
// }
// document.querySelector('')

// function customOpt(){return '[{\"command\":1,\"amount\":10,\"remark\":\"asas\"},{\"command\":1,\"amount\":10,\"remark\":\"asas\"}]';}
