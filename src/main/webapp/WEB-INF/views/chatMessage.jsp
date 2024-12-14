<!DOCTYPE html>
<html>
<head>
  <title>Chat WebSocket</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
  <script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
      document.getElementById('connect').disabled = connected;
      document.getElementById('disconnect').disabled = !connected;
      document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
      document.getElementById('response').innerHTML = '';
    }

    function connect() {
      var socket = new SockJS('/Baby_war/hello'); // Đường dẫn cần kiểm tra
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(message) {
          showMessage(JSON.parse(message.body));
        });
      }, function(error) {
        console.error("Error connecting to WebSocket:", error);
        alert("Could not connect to WebSocket. Please try again.");
      });
    }

    function disconnect() {
      if (stompClient != null) {
        stompClient.disconnect();
      }
      setConnected(false);
      console.log("Disconnected");
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
          timestamp: new Date().toISOString() // Tạo timestamp cho tin nhắn
        };

        stompClient.send("/app/chat", {}, JSON.stringify(message));
      } else {
        alert("Please fill in all fields!");
      }
    }

    function showMessage(message) {
      var response = document.getElementById('response');
      var p = document.createElement('p');
      p.style.wordWrap = 'break-word';
      p.appendChild(document.createTextNode(
              `[${message.timestamp}] ${message.fromId} to ${message.toId}: ${message.messageText}`
      ));
      response.appendChild(p);
    }
  </script>
</head>
<body onload="disconnect()">
<noscript>
  <h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable Javascript and reload this page!</h2>
</noscript>
<div>
  <div>
    <button id="connect" onclick="connect();">Connect</button>
    <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
  </div>
  <div id="conversationDiv" style="visibility: hidden;">
    <label>To ID:</label><input type="text" id="toId" />
    <label>From ID:</label><input type="text" id="fromId" />
    <label>Message:</label><input type="text" id="messageText" />
    <button id="sendMessage" onclick="sendMessage();">Send</button>
    <div id="response"></div>
  </div>
</div>
</body>
</html>
