var websocket = null;
// 判断浏览器是否支持websocket
if ("websocket" in window) {
    websocket = new WebSocket("ws://localhost:8081/sn/ws");
} else {
    alert("not support websocket");
}
websocket.onopen = function (event) {
    appendMessage("open");
}

websocket.onerror = function (event) {
    appendMessage("error");
}

websocket.onclose = function (event) {
    appendMessage("close");
}

websocket.onmessage = function (event) {
    appendMessage(event.data);
}

function appendMessage(data) {
    let content = $("#content").html() + "<br/>" + data;
    $("#content").html(content);
}

function sendMessage() {
    let msg = $("#message").val();
    websocket.send(msg);
    appendMessage("客户端：" + msg)
}

function closeWebSocket() {
    websocket.close();
}