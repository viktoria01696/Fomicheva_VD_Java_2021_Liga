package ru.digitalleague.net.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ServerEndpoint(
        value = "/chat/{username}",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class WebSocketEndpoint {

    private static final List<WebSocketEndpoint> sockets = new ArrayList<>();
    private static final HashMap<String, String> users = new HashMap<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        /// запомнили сокет
        this.session = session;

        /// сохранили в общий список сокетов
        sockets.add(this);
        users.put(session.getId(), username);

        /// отправляем уведомление о подключении нового юзера
        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected!");

        /// для всех
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        /// пересылаем сообщение
        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) {
        /// убираем сокет из общего списка
        sockets.remove(this);

        /// и рассылаем всем уведомление об отключении юзера
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");

        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) {
        sockets.forEach(endpoint -> {
            try {
                endpoint
                        .session
                        .getBasicRemote()
                        .sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }
}