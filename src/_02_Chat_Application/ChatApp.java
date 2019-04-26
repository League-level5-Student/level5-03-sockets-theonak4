package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import _00_Click_Chat.gui.ButtonClicker;
import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */
public class ChatApp extends JFrame {
	JButton button = new JButton("CLICK");
	Server serve;
	Client client;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {
		int answer = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			serve = new Server(8221);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null,
					"Server started at: " + serve.getIPAddress() + "\nPort: " + serve.getPort());
			button.addActionListener((e) -> {
				serve.sendClick();
			});
			add(button);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			serve.start();
		} else {
			setTitle("CLIENT");
			String ipString = JOptionPane.showInputDialog("Enter the IP Address");
			String prtString = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtString);
			client = new Client(ipString, port);
			button.addActionListener((e) -> {
				client.sendClick();
			});
			add(button);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}
