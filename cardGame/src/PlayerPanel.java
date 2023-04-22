package CardGame;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PlayerPanel extends JPanel {
	
	private JLabel label;

	public PlayerPanel() {
		label = new JLabel("Player Panel"); // Example label text
		add(label);
	}
}
