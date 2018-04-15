//1:页面初始化
$(function(){
	initPage();
});
//2:初始化数据
function initPage() {

    $('#fileClick').on('click', function() {
        var fd = new FormData();
        // fd.append("upload", 1);
        // var fd = "";
        // fd.add("file", $("#file_post").get(0).files[0])
        fd.append("file", $("#file_post").get(0).files[0]);
        $.ajax({
            url: "/demo/postFile",
            type: "POST",
            processData: false,
            contentType: false,
            data: fd,
            success: function (d) {
                alert(d.postFile);
                // console.log(d);
            }
        });
    });


    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!'
        }
    })

    var app5 = new Vue({
        el: '#app-5',
        data: {
            message: 'Hello Vue.js!'
        },
        methods: {
            reverseMessage: function () {
                this.message = this.message.split('').reverse().join('')
            }
        }
    })

}

function requestParamGet() {
    // alert("RequestParam_get");
    $.ajax({
        type: "GET",
        url: "/demo/get1",
        data : {
            code : "123",
            name : "456"
        },
        contentType: "application/json; charset=UTF-8",
        success: function(data){
            alert("code:" + data.code+"=====name:" + data.name);
        },
        error :function(jqXHR){
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
            // $.messager.alert('操作提示',"操作异常","error");
            hideWaitMsg();
        }
    });
}

// function requestParamGet1() {
//     // alert("RequestParam_get");
//     $.ajax({
//         type: "GET",
//         url: "/demo/get1",
//         data : {
//             code : "123#kalman#1",
//             name : "456"
//         },
//         contentType: "application/json; charset=UTF-8",
//         success: function(data){
//             alert("code:" + data.code+"=====name:" + data.name);
//         },
//         error :function(jqXHR){
//             /*弹出jqXHR对象的信息*/
//             alert(jqXHR.responseText);
//             alert(jqXHR.status);
//             alert(jqXHR.readyState);
//             alert(jqXHR.statusText);
//             // $.messager.alert('操作提示',"操作异常","error");
//             hideWaitMsg();
//         }
//     });
// }

function requestParamPost() {
    // alert("RequestParam_get");
    $.ajax({
        type: "POST",
        url: "/demo/get3",
        data : {
            code : "123",
            name : "456"
        },
        // contentType: "application/json; charset=UTF-8",
        // contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function(data){
            alert("code:" + data.code+"=====name:" + data.name);
        },
        error :function(jqXHR){
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
        }
    });
}

function User_post() {
    $.ajax({
        type: "POST",
        url: "/demo/post",
        data : {
            id : "001",
            code : "123",
            name : "456"
        },
        contentType: "application/json; charset=UTF-8",
        // contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function(data){
            var data = data.HelloWorld;
            alert("id:"+data.id+"====code:" + data.code+"=====name:" + data.name);
        },
        error :function(jqXHR){
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
        }
    });
}

function post2Click() {
    $.ajax({
        type: "POST",
        url: "/demo/post2",
        data : JSON.stringify({
            id : "001",
            code : "123",
            name : "456"
        }),
        contentType: "application/x-www-form-urlencoded",
        // contentType: "application/json; charset=UTF-8",
        success: function(data){
            var data = data.HelloWorld;
            alert("id:"+data.id+"====code:" + data.code+"=====name:" + data.name);
        },
        error :function(jqXHR){
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
        }
    });
}

function postList() {
    var list = new Array();
    list.push("hello");
    list.push("world");
    list.push("hi");
    $.ajax({
        type: "POST",
        url: "/demo/post3",
        data : {
            idList : list
        },
        contentType: "application/x-www-form-urlencoded",
        success: function(data){
            var data = data.HelloWorld;
            alert(data);
        },
        error :function(jqXHR){
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
        }
    });
}

function postMap() {
    alert("postmap");
    var map = {
        "id":"000123",
        "code":"123",
        "name":"456"
    };
    $.ajax({
        type: "POST",
        url: "/demo/post4",
        data : {
            params : map
        },
        // contentType: "application/json; charset=UTF-8",
        // contentType: "application/x-www-form-urlencoded",
        success: function(data){
            // var data = data.HelloWorld;
            alert("id:"+data.id+"====code:" + data.code+"=====name:" + data.name);
        },
        error :function(jqXHR){
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
        }
    });
}

function postJsonObj() {
    var obj = {};
    obj['name'] = "obj";
    obj['code'] = "123";
    obj['id'] = '001';

    $.ajax({
        url: '/demo/post5',
        method: 'post',
        contentType: 'application/json',
        data: JSON.stringify(obj), // 以json字符串方式传递
        success: function(data) {
            alert(data);
            console.log("success...");
        },
        error: function(data) {
            console.log("error...");
        }
    });
}

function postParam() {
    $.ajax({
        type: "post",
        url: "/demo/postParam?urlParam=456",
        data : {
            normalParam : 123
        },
        // contentType: "application/json; charset=UTF-8",
        // contentType: "application/x-www-form-urlencoded",
        success: function(data){
            // var data = data.HelloWorld;
            alert("urlParam:"+data.urlParam+"====normalParam:" + data.normalParam);
        },
        error :function(jqXHR){
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
        }
    });
}

function addCookie() {
    $.ajax({
        type: "post",
        url: "/demo/addCookie",
        data : {
            normalParam : 123
        },
        // contentType: "application/json; charset=UTF-8",
        // contentType: "application/x-www-form-urlencoded",
        success: function(data){
            console.log("addCookie success");
            // var data = data.HelloWorld;
            // alert("urlParam:"+data.urlParam+"====normalParam:" + data.normalParam);
        },
        error :function(jqXHR){
            /*弹出jqXHR对象的信息*/
            alert(jqXHR.responseText);
            alert(jqXHR.status);
            alert(jqXHR.readyState);
            alert(jqXHR.statusText);
        }
    });
}