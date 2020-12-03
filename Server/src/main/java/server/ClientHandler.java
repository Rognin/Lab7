package server;

import basic.CommandPack;
import serverCommands.ServerCommand;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ClientHandler implements Runnable {
    Server theServer;

    public ClientHandler(Server theServer) {
        this.theServer = theServer;
        answer = theServer.answer;
        commands = theServer.commands;
        socket = theServer.socket;
    }

    Socket socket;
    String answer;
    HashMap<String, ServerCommand> commands;

    @Override
    public void run() {
        try {
            System.out.println("Client accepted");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                try {
                    CommandPack cp = (CommandPack) in.readObject();
                    if (cp == null) {
                        System.out.println("an empty command pack was received");
//                        out.writeUTF("something went wrong, try again");
                        out.flush();
//                            System.out.println("answer sent");
                        continue;
                    }
//                        System.out.println("command " + currentCommand.getClass() + " received trying to execute");
                    try {
                        ServerCommand command = commands.get(cp.getCommandName());
                        command.onCall(cp.getAdditionalInput());
                    } catch (NullPointerException e) {
                        System.out.println("empty command received");
//                        e.printStackTrace();
                        continue;
                    }
//                    answer = "yey";
//                        System.out.println("executed now sending the answer");
                    out.writeUTF(theServer.answer);
                    out.flush();
//                        System.out.println("executed, answer sent");
                    theServer.answer = "";
                } catch (EOFException e) {
                    System.out.println("client disconnected");
//                        e.printStackTrace();
                    break;
                } catch (BindException e) {
                    System.out.println("the port you're trying to use is already taken. Please restart the program and try another one");
                } catch (SocketException e) {
                    System.out.println("socketEx, something wrong with the socket");
                } catch (IOException i) {
//                    System.out.println(i);
//                    i.printStackTrace();
                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

}
