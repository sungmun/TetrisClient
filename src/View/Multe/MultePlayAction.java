package View.Multe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Control.TetrisClient;
import Serversynchronization.MessageType;
import Serversynchronization.SocketMessage;
import Serversynchronization.User;
import Serversynchronization.UsersList;

public class MultePlayAction implements ActionListener, MessageType {
	ListView list;
	TetrisClient client = TetrisClient.getTetrisClient();

	public MultePlayAction(ListView list) {
		this.list = list;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		User us = list.getUser();
		int index = UsersList.getList().indexOf(us);
		us = UsersList.getList().get(index);
		client.send(new SocketMessage(USER_SELECTING, us));
	}

}
