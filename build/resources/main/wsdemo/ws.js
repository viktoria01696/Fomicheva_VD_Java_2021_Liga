let context = {};

document.addEventListener("DOMContentLoaded",function () {

    let chatDiv = document.getElementById("chat");
    let messagesDiv = document.getElementById("chat-box");
    let loginDiv = document.getElementById("login");

    let loginInput = document.getElementById("name");
    let loginSendButton = document.getElementById("name-enter");
    loginSendButton.onclick = () => {
        let socket = new WebSocket("ws://localhost:8080/chat/" + loginInput.value);
        context.login = loginInput.value;
        context.ws = socket;

        socket.onmessage = function(event) {
            console.log(`[message] Данные получены с сервера: ${event.data}`);

            let data = JSON.parse(event.data);
            let messageDiv = document.createElement("div");
            messageDiv.classList.add("msg");
            messageDiv.innerHTML = `<span class="msg-login">${data.from}:\t</span><span>${data.content}</span>`;

            messagesDiv.appendChild(messageDiv);
            input.value = null;
        };

        loginDiv.style.display = "none";
        chatDiv.style.display = "block";

    }

    let sendButton = document.getElementById("send");
    let input = document.getElementById("input");

    sendButton.onclick = () => {
        let text = input.value;
        context.ws.send(JSON.stringify({from: context.login, content: text}));
    }


});