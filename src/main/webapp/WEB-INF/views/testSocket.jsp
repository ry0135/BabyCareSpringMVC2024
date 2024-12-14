<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Chat WebSocket</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <style>
    .chat-container {
      display: flex;
      flex-direction: column;
      height: 500px;
      width: 300px;
      border: 1px solid #ccc;
      border-radius: 8px;
      background-color: #f9f9f9;
    }

    .chat-messages {
      padding: 10px;
      height: 200px; /* Chiều cao cố định của khung tin nhắn */

      border: 1px solid #ddd;
      border-radius: 5px;
      background-color: #ffffff;
      margin-bottom: 10px; /* Khoảng cách với phần chat real-time */
    }
    .chat-messagess{
      padding: 10px;
      height: 200px; /* Chiều cao cố định của khung tin nhắn */
      overflow-y: scroll; /* Bật cuộn dọc */
      border: 1px solid #ddd;
      border-radius: 5px;
      background-color: #ffffff;
      margin-bottom: 10px; /* Khoảng cách với phần chat real-time */
    }

    .message {
      margin-bottom: 10px;
      padding: 8px;
      border-radius: 5px;
      word-wrap: break-word;
    }

    .message.from-user {
      background-color: #e1ffe1; /* Màu cho tin nhắn của người dùng */
      text-align: right;
    }

    .message.to-user {
      background-color: #f1f1f1; /* Màu cho tin nhắn của người khác */
      text-align: left;
    }

    .message small {
      display: block;
      font-size: 0.75em;
      color: #666;
    }

    .chat-input {
      padding: 10px;
      border-top: 1px solid #ccc;
      display: flex;
      flex-direction: column;
    }

    .chat-input input {
      margin: 5px 0;
      padding: 8px;
      border-radius: 5px;
    }

    .chat-input button {
      padding: 8px;
      border-radius: 5px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }

    .chat-input button:hover {
      background-color: #45a049;
    }

    .chat-header {
      font-weight: bold;
      font-size: 18px;
      text-align: center;
      padding: 10px;
      background-color: #4CAF50;
      color: white;
    }

    .chat-message.from-current-user {
      border-radius: 4px;
      margin-left: 220px;
      align-self: flex-end;
      background-color: #e1ffe1; /* Màu nền cho tin nhắn người gửi */
    }

    /* Tin nhắn từ người nhận (Căn trái) */
    .chat-message.from-other-user {
      align-self: flex-start;
      background-color: #f1f1f1; /* Màu nền cho tin nhắn người nhận */
      margin-right: 220px;
      border-radius: 4px;
    }


    .message.from-user {
      background-color: #f1f1f1; /* for the 'to' user */
      text-align: left;
      margin-right: 230px;
    }
    .message.to-user {
      margin-left: 230px;
      background-color: #e1ffe1; /* for the 'from' user */
      margin-right: 10px;

    }
    .chat-messages p{
      padding: 8px;
      height: 50px;
    }
  </style>
  <script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
      document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
      document.getElementById('response').innerHTML = '';
    }

    // function connect() {
    //   var socket = new SockJS('/Baby_war/chat');
    //   stompClient = Stomp.over(socket);
    //   stompClient.connect({}, function(frame) {
    //     setConnected(true);
    //     console.log('Connected: ' + frame);
    //     stompClient.subscribe('/topic/messages', function(message) {
    //       showMessage(JSON.parse(message.body));
    //     });
    //   }, function(error) {
    //     console.error("Error connecting to WebSocket:", error);
    //     alert("Could not connect to WebSocket. Please try again.");
    //   });
    // }

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

    function showChatForm() {
      document.getElementById('conversationDiv').style.visibility = 'visible';
      connect();
    }

    window.onload = function() {
      showChatForm();
    };


    // window.onload = function() {
    //   const chatMessages = document.querySelector('.chat-messagess');
    //   chatMessages.scrollTop = chatMessages.scrollHeight;
    // };

    // Cuộn xuống cuối khi có tin nhắn mới (sử dụng hàm này khi thêm nội dung động)
    function scrollToBottom() {
      const chatMessages = document.querySelector('.chat-messagess');
      chatMessages.scrollTop = chatMessages.scrollHeight;
    }


    function checkEnter(event) {
      if (event.key === "Enter") { // Kiểm tra xem phím nhấn có phải là Enter không
        event.preventDefault(); // Ngăn chặn hành động mặc định của phím Enter
        sendMessage(); // Gọi hàm gửi tin nhắn
      }
    }

  </script>
</head>
<body>

<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f1f1f1;
    margin: 0;
    padding: 0;
  }

  .chat-container {
    width: 400px;
    margin: 20px auto;
    background: white;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    overflow: hidden;
  }

  .chat-header {
    background-color: #007bff;
    color: white;
    padding: 10px 15px;
    font-size: 18px;
    font-weight: bold;
  }

  .chat-list {
    list-style: none;
    margin: 0;
    padding: 0;
  }

  .chat-item {
    display: flex;
    align-items: center;
    padding: 10px 15px;
    border-bottom: 1px solid #f1f1f1;
    transition: background 0.3s;
  }

  .chat-item:hover {
    background: #f9f9f9;
  }

  .chat-item:last-child {
    border-bottom: none;
  }

  .chat-avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    margin-right: 15px;
    background-color: #ddd;
    overflow: hidden;
  }

  .chat-avatar img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

  .chat-info {
    flex: 1; /* Chiếm toàn bộ chiều rộng còn lại */
    width: 200px; /* Đặt chiều rộng cố định cho chat info */
    max-width: 200px; /* Đặt chiều rộng tối đa */
    overflow: hidden; /* Ẩn nội dung không đủ chỗ */
    white-space: nowrap; /* Lớp không xuống dòng */
    text-overflow: ellipsis; /* Hiển thị "..." cho nội dung bị cắt */
  }

  .chat-name {
    font-size: 16px;
    font-weight: bold;
    margin: 0;
  }

  .chat-message {
    font-size: 14px;
    color: #555;
    margin: 2px 0 0;
  }

  .chat-time {
    font-size: 12px;
    color: #999;
    margin-left: 100px;
    margin-top: 12px;
  }
  .chat-link{
    display: flex;
    width: 100%; /* Đảm bảo liên kết chiếm toàn bộ chiều rộng */
    text-decoration: none; /* Bỏ gạch dưới ở liên kết */
  }
  .chattt{
    overflow-y: scroll; /* Bật cuộn dọc */
  }

  .fa-paper-plane:before {

    position: absolute;
    top: 628px;
    right: 80px;
    /* margin-bottom: 15px; */
    content: "\f1d8";
  }
</style>
<jsp:include page="include/header.jsp" />
<noscript>
  <h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable Javascript and reload this page!</h2>
</noscript>

<div class="con row" style="display: flex; width: 1500px">
  <div class="chat-container chattt  col-lg-4" style="height: 600px">
    <div class="chat-header">Đoạn chat</div>
    <c:forEach var="chat" items="${listLasstmessages}">
      <li class="chat-item">
        <a href="chatMessage?toId=${chat.account.userID}" class="chat-link"> <!-- Thay thế chat.account.userId bằng giá trị cần thiết -->
          <div class="chat-avatar">
            <img src="${pageContext.request.contextPath}/image/${chat.account.avatar}" alt="Avatar">
          </div>
          <div class="chat-info">
            <p class="chat-name">${chat.account.firstname} ${chat.account.lastname}</p>
            <p class="chat-message">${chat.message}</p>
          </div>
          <span class="chat-time">${chat.lastMessageTime}</span>
        </a>
      </li>
    </c:forEach>

  </div>

  <div class="chat-container col-lg-8" style="display: flex;justify-content: space-between; height: 600px">
    <div class="chat-header">
      Chat Room
    </div>

    <!-- Container chứa các tin nhắn từ foreach -->
    <div class="chat-messagess" style="height: 100%;">
<%--      <c:forEach var="message" items="${messages}">--%>
<%--        <div class="message ${message.fromId == idFrom ? 'to-user' :  'from-user' }">--%>
<%--          <strong>${message.fromId}:</strong> ${message.messageText}<br>--%>
<%--&lt;%&ndash;          <small>Sent: ${message.timestamp}</small>&ndash;%&gt;--%>
<%--        </div>--%>
<%--      </c:forEach>--%>

  <c:forEach var="message" items="${messages}">
    <div class="message ${message.fromId == idFrom ? 'to-user' :  'from-user'}">
      <c:if test="${message.fromId != idFrom}">
        <img style="width: 50px; /* Bạn có thể điều chỉnh kích thước theo ý muốn */
        height: auto; /* Duy trì tỷ lệ khung hình */
        max-width: 100%; /* Đảm bảo hình ảnh không lớn hơn vùng chứa */
        border-radius: 50%;" src="<c:choose>
                          <c:when test="${message.fromId == toId}">
                            ${pageContext.request.contextPath}/image/${message.avatar}
                          </c:when>
                          <c:otherwise>
                              path/to/default/image.png
                          </c:otherwise>
                      </c:choose>" alt="User Image" />
      </c:if>
      <span>${message.messageText}</span><br>
        <%-- <small>Sent: ${message.timestamp}</small> --%>
    </div>
  </c:forEach>
      <div id="response" class="chat-messages" style="height: 40px; border:none"></div>
    </div>

    <!-- Form nhập tin nhắn -->
    <div id="conversationDiv" class="chat-input">
      <input type="hidden" id="toId" value="${toId}" />
      <input type="hidden" id="fromId" value="${idFrom}" />
      <input type="text" id="messageText" onkeydown="checkEnter(event);" placeholder="Type your message..." />
      <i class="fas fa-paper-plane" id="sendMessage" onclick="sendMessage();" style="cursor: pointer; color: #ff4880; padding: 10px; font-size: 24px;"></i> <!-- Icon gửi tin nhắn -->

    <%--      <button id="sendMessage" onclick="sendMessage();" style="background-color:#ff4880;">Send</button>--%>
    </div>
  </div>
</div>
</body>
</html>
