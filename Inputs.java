package prototype103;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Inputs {

	JFrame frame;
	private JTextField textField;
	private JComboBox comboBox;
	private JLabel lblDegreeOfDisaster;
	private JComboBox comboBox_1;

		/**
	 * Create the application.
	 */
	public Inputs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame("Copyright © DA-IICT");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number of Passengers");
		lblNewLabel.setBounds(27, 42, 149, 19);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblTypeOfAircraft = new JLabel("Type of Aircraft");
		lblTypeOfAircraft.setBounds(27, 87, 113, 14);
		frame.getContentPane().add(lblTypeOfAircraft);
		
		comboBox = new JComboBox();
		comboBox.setBounds(193, 84, 98, 20);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("2x2");
		comboBox.addItem("3x3");
		
		textField = new JTextField();
		textField.setBounds(193, 42, 98, 19);
		textField.setText("1");
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		Inputs f = this;
		JButton btnSimulate = new JButton("Simulate");
		btnSimulate.setToolTipText("Start Evacuation");
		btnSimulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame3 newFrame = new frame3();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							/*Monitor frame_2 = new Monitor();
							frame_2.frame2.setVisible(true);*/
							Aeroplane frame_3 = new Aeroplane(f);
							frame_3.frame.setVisible(true);
							//frame2_temp.setVisible(false);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnSimulate.setBounds(27, 179, 89, 23);
		frame.getContentPane().add(btnSimulate);
		
		/*lblDegreeOfDisaster = new JLabel("Degree of Disaster");
		lblDegreeOfDisaster.setBounds(27, 136, 113, 14);
		frame.getContentPane().add(lblDegreeOfDisaster);
	
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(193, 131, 98, 19);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addItem("1");
		comboBox_1.addItem("2");
		comboBox_1.addItem("3");
		comboBox_1.addItem("4");
		comboBox_1.addItem("5");*/
	}
	
	public int getAirplaneType(){
		return comboBox.getSelectedIndex();
	}
	public String getNoofPassengers(){
		return textField.getText();
	}
	public int getDegreeofDisaster(){
		return comboBox_1.getSelectedIndex()+1;
	}
}
