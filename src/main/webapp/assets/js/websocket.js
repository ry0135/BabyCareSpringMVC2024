var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');  // Đường dẫn tương ứng với endpoint được đăng ký
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(greeting.body);
        });
    });
}

function showGreeting(message) {
    // Hiển thị tin nhắn trong giao diện
    console.log(message);
}