<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BabyCare</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>

<body>

<jsp:include page="include/header.jsp" />


</body>

<script>
    var stompClient = null;

    function setConnected(connected) {
        var conversationDiv = document.getElementById('conversationDiv');
        var response = document.getElementById('response');

        // Kiểm tra nếu các phần tử tồn tại
        if (conversationDiv && response) {
            conversationDiv.style.visibility = connected ? 'visible' : 'hidden';
            response.innerHTML = '';
        }
    }

    window.onload = function() {
        var account = '${sessionScope.account != null ? sessionScope.account.username : ""}'; // Lấy thông tin người dùng từ session

        if (account !== "") {
            // Người dùng đã đăng nhập, tự động kết nối WebSocket
            connect();
        } else {
            alert("Bạn chưa đăng nhập!");
        }
    };


    function connect() {
        var socket = new SockJS('/Baby_war/chat');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);

            // Subscribe nhận tin nhắn
            var userId = document.getElementById('fromId').value; // ID người dùng hiện tại
            stompClient.subscribe('/topic/messages', function(message) {
                showMessage(JSON.parse(message.body));
            });

            // Subscribe nhận thông báo
            stompClient.subscribe('/user/' + userId + '/queue/notifications', function(notification) {
                showNotification(JSON.parse(notification.body));
            });
        });
    }

    function showNotification(notification) {
        alert(notification.message); // Hoặc hiển thị thông báo trong UI
    }


    function sendMessage() {
        var toId = document.getElementById('toId').value;
        var fromId = document.getElementById('fromId').value;
        var messageText = document.getElementById('messageText').value;

        if (toId && fromId && messageText) {
            var message = {
                toId: toId,
                fromId: fromId,
                messageText: messageText,
                timestamp: new Date().toISOString()
            };

            stompClient.send("/app/chat", {}, JSON.stringify(message));
        } else {
            alert("Please fill in all fields!");
        }
    }

    function showMessage(message) {
        var toId = document.getElementById('toId').value;
        var fromId = document.getElementById('fromId').value;

        if ((message.toId === toId && message.fromId === fromId) || (message.toId === fromId && message.fromId === toId)) {
            var response = document.getElementById('response');
            var messageElement = document.createElement('div');
            messageElement.className = 'chat-message';

            if (message.fromId === fromId) {
                messageElement.classList.add('from-current-user');
            } else {
                messageElement.classList.add('from-other-user');
            }

            var p = document.createElement('p');
            p.appendChild(document.createTextNode(message.messageText));
            messageElement.appendChild(p);

            response.appendChild(messageElement);

            response.scrollTop = response.scrollHeight;
        }

    }
</script>

</html>
