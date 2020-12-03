package commands;

import client.Client;

/**print helpful information about the commands*/
public class Help extends Command {
	Client client;

	/**the name of a specific command that the information will be printed about, currently prints nothing*/
	String keyWord;

	public Help(Client client) {
		super(client);
		this.client = client;
	}

	public void onCall(String args) {

	}

	@Override
	public String getHelp() {
		return super.getHelp();
	}

	@Override
	public void getArgs(String args) {
		keyWord = args;
	}
}
