package ru.gb.jt.chat.server.gui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.jt.chat.server.core.ChatServer;
import ru.gb.jt.chat.server.core.SpringConfig;

import javax.swing.*;

public class ServerGUIRunner {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ChatServer chatServer = context.getBean("chatServer", ChatServer.class);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI(chatServer);
            }
        });
    }
}
