package prototype103;

import java.awt.EventQueue;


public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inputs w = new Inputs();
					//aeroplane window = new aeroplane("kshitij",10);
					//window.frame.setVisible(true);
					w.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
