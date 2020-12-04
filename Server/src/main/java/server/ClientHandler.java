package server;

import basic.CommandPack;
import basic.UserInfo;
import serverCommands.*;

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
        commands = new HashMap<>();
        socket = theServer.socket;
        commandProvider = theServer.commandProvider;
        currentUI = new UserInfo("", "");
    }

    public Socket socket;
    public String answer;
    public HashMap<String, ServerCommand> commands;
    public CommandProvider commandProvider;
    public UserInfo currentUI;

    @Override
    public void run() {
        commands.put("help", new ServerHelp(this, commandProvider));
        commands.put("info", new ServerInfo(this, commandProvider));
        commands.put("show", new ServerShow(this, commandProvider));
        commands.put("add", new ServerAdd(this, commandProvider));
        commands.put("update", new ServerUpdate(this, commandProvider));
        commands.put("remove_by_id", new ServerRemoveById(this, commandProvider));
        commands.put("clear", new ServerClear(this, commandProvider));
        commands.put("execute_script", new ServerExecuteScript(this, commandProvider));
        commands.put("add_if_min", new ServerAddIfMin(this, commandProvider));
        commands.put("remove_lower", new ServerRemoveLower(this, commandProvider));
        commands.put("history", new ServerHistory(this, commandProvider));
        commands.put("max_by_id", new ServerMaxById(this, commandProvider));
        commands.put("filter_less_than_description", new ServerFilterLessThanDescription(this, commandProvider));
        commands.put("filter_greater_than_description", new ServerFilterGreaterThanDescription(this, commandProvider));
        commands.put("register", new ServerRegister(this, commandProvider));
        commands.put("login", new ServerLogin(this, commandProvider));
        try {
            System.out.println("Client accepted");
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                try {
                    CommandPack cp = (CommandPack) in.readObject();
                    currentUI = new UserInfo(cp.getUsername(), cp.getPasswordHash());
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
                    out.writeUTF(answer);
                    out.flush();
//                        System.out.println("executed, answer sent");
                    answer = "";
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
