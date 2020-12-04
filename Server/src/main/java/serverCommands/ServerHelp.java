package serverCommands;

import server.ClientHandler;
import server.CommandProvider;

import java.io.IOException;

/**
 * print helpful information about the commands
 */
public class ServerHelp extends ServerCommand {
    ClientHandler clientHandler;
    CommandProvider commandProvider;
    public ServerHelp(ClientHandler clientHandler, CommandProvider commandProvider) {
        super(clientHandler, commandProvider);
        this.clientHandler = clientHandler;
        this.commandProvider = commandProvider;
    }

    public void onCall(Object additionalInput) throws IOException {
        clientHandler.answer = ("	help : вывести справку по доступным командам\n" +
                "    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "    show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "    add {element} : добавить новый элемент в коллекцию\n" +
                "    update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "    remove_by_id id : удалить элемент из коллекции по его id\n" +
                "    clear : очистить коллекцию\n" +
                "    save : сохранить коллекцию в файл\n" +
                "    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "    exit : завершить программу (без сохранения в файл)\n" +
                "    add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "    remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "    history : вывести последние 6 команд (без их аргументов)\n" +
                "    max_by_id : вывести любой объект из коллекции, значение поля id которого является максимальным\n" +
                "    filter_less_than_description description : вывести элементы, значение поля description которых меньше заданного\n" +
                "    filter_greater_than_description description : вывести элементы, значение поля description которых больше заданного");
    }

    @Override
    public String getHelp() {
        return super.getHelp();
    }

    @Override
    public void getArgs(String args) {
    }
}
