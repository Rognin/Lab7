/**
 * The main class, launches the program
 *
 * @author Artem Gusev
 * @version 1.0
 */
package client;

import java.net.*;
import java.io.*;
import java.util.*;

import basic.CommandPack;
import commands.*;

public class Client {
    public Client() {
        socket = null;
        input = new BufferedReader(new InputStreamReader(System.in));
        commands = new HashMap<>();
        socket = null;
        in = null;
        out = null;
        additionalOutputToServer = null;
        history = new ArrayDeque<>();
        date = new Date();
    }

    public BufferedReader input;
    public Socket socket;
    public ObjectInputStream in;
    public ObjectOutputStream out;
    public Object additionalOutputToServer;
//    private static BufferedWriter bw = null;

    /**
     * a queue for keeping track of command history
     */
    public Queue<String> history;

    /**
     * the date of last collection initialization
     */
    public Date date;

    /**
     * a map that contains a copy of each command
     */
    public HashMap<String, Command> commands;

    /**
     * a method that executes a command
     *
     * @param s the name of the command
     */
    public CommandPack executeCommandClient(String s) throws NullPointerException, NoSuchElementException {

        int index = s.indexOf(' ');

        String commandName;
        String keyWords;

        if (index > -1) {
            commandName = s.substring(0, index);
            keyWords = s.substring(index + 1);
        } else {
            commandName = s;
            keyWords = "";
        }
        try {

            if (history.size() > 5) {
                history.poll();
            }
            history.add(commandName);
            commands.get(commandName).onCall(keyWords);

        } catch (NullPointerException e) {
            System.out.println("There is no such command");
            return null;
        } catch (IOException e) {
            System.out.println("we don't have a permission to interact with a file (or an unknown error occurred)");
            return null;
        }
        return (new CommandPack(commandName, additionalOutputToServer));
    }

    public void doCommand(String line, ObjectInputStream in, ObjectOutputStream out) throws IOException, NullPointerException {
        CommandPack currentCommandPack = executeCommandClient(line);
//                System.out.println("command executed");

        if (currentCommandPack == null) {
            additionalOutputToServer = null;
            return;
        }

        out.writeObject(currentCommandPack);
        additionalOutputToServer = null;
        out.flush();
//                System.out.println("sent command to server");
        String answer = in.readUTF();
//                System.out.println("answer received");
        System.out.println(answer);
    }

    public boolean tryToConnect(InetSocketAddress isa) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(isa.getAddress(), isa.getPort()), 2000);
//            InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 5000);
            System.out.println("Connected");
            // sends output to the socket
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
//            sc = SocketChannel.open();
//            sc.connect(isa);
        } catch (IllegalArgumentException e) {
            System.out.println("IlegalArgEx");
            return false;
        } catch (UnknownHostException u) {
            System.out.println("unknown host exception");
            return false;
        } catch (ConnectException e) {
            System.out.println("Server is unavailable. Try again by entering 'connect'\n(there might be something =wrong with the address you entered, you may want to try relaunching the program with a different one)");
            return false;
        } catch (SocketException e) {
            System.out.println("something wrong with the socket");
        } catch (SocketTimeoutException e) {
            System.out.println("timeout. It doesn't seem like the address you entered is available\nExiting program\nTry again with another address");
            System.exit(0);
        } catch (IOException i) {
            System.out.println("io exception, this could be a few things");
//            i.printStackTrace();
            return false;
        }
        return true;
    }


    public void doTheThing() throws IOException {
        commands.put("help", new Help(this));
        commands.put("info", new Info(this));
        commands.put("show", new Show(this));
        commands.put("add", new Add(this));
        commands.put("update", new Update(this));
        commands.put("remove_by_id", new RemoveById(this));
        commands.put("clear", new Clear(this));
        commands.put("execute_script", new ExecuteScript(this));
        commands.put("add_if_min", new AddIfMin(this));
        commands.put("remove_lower", new RemoveLower(this));
        commands.put("history", new History(this));
        commands.put("max_by_id", new MaxById(this));
        commands.put("filter_less_than_description", new FilterLessThanDescription(this));
        commands.put("filter_greater_than_description", new FilterGreaterThanDescription(this));

        String address = "";
        int port = -1;
        boolean addressAndPortSet = false;
        InetSocketAddress isa = null;
        while (!addressAndPortSet) {
            System.out.println("enter the host and the port in the format \"adress:port\" or \"d\" for the default (127.0.0.1:5000). You can also enter \"exit\" to exit the program");
            try {
                String inp = input.readLine();
                if (inp.equals("exit")) {
                    System.exit(0);
                }
                if (inp.equals("d")) {
                    address = "127.0.0.1";
                    port = 5000;
                } else {
                    String[] trimString = inp.trim().split(":", 2);
                    address = trimString[0];
                    port = Integer.parseInt(trimString[1]);
                    if (port < 0 || port > 65535) {
                        System.out.println("the port should be a number between 0 and 65535");
                        continue;
                    }
                }
                isa = new InetSocketAddress(address, port);
                addressAndPortSet = true;
            } catch (NumberFormatException e) {
                System.out.println("the port should be a number between 0 and 65535");
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println("something's wrong with the address you entered, try again");
            } catch (IOException e) {
                System.out.println("aaand we got an IOException. Great");
            } catch (NullPointerException e){
                System.out.println("It was nice to meet you. Stay determined");
                System.exit(0);
            }
        }

        boolean isConnected = tryToConnect(isa);

        String line;
        while (true) {
            line = "";
            if (input.ready()) {
                try {
                    line = input.readLine();
                } catch (IOException e) {
                    System.out.println("io exception while trying to read something from input");
//                    e.printStackTrace();
                    break;
                }
            }
            try {
                try {
                    if (line.equals("exit"))
                        break;
                    if (line.equals("connect") && !isConnected) {
                        isConnected = tryToConnect(isa);
                        continue;
                    }
                    if (line.equals(""))
                        continue;
                    if (!isConnected) continue;
                } catch (NullPointerException e) {
//                    e.printStackTrace();
                }

                doCommand(line, in, out);
            } catch (NullPointerException e) {
                System.out.println("nullpointer at some point");
//                e.printStackTrace();
                continue;
            } catch (SocketException e) {
                System.out.println("server is down. You can try to connect again by entering 'connect'");
                isConnected = false;
            } catch (EOFException e) {
//                System.out.println(e);
//                e.printStackTrace();
                break;
            } catch (NoSuchElementException e) {
                System.out.println(":(");
                System.exit(0);
            }
        }
        // close the connection
        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println("failed to close something");
        } catch (NullPointerException e) {

        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.doTheThing();
        } catch (IOException e) {
//            System.out.println("IOex final");
//            e.printStackTrace();
        }
    }

}
