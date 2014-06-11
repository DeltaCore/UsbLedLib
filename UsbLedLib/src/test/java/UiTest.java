import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import net.ccmob.usbled.lib.communication.ControllerInterface;
import net.ccmob.usbled.lib.communication.UsbLedException;
import net.ccmob.usbled.lib.programs.BaseProgram;
import net.ccmob.usbled.lib.programs.ProgramHandler;
import net.ccmob.usbled.lib.programs.ProgramState;
import net.ccmob.usbled.lib.programs.ProgramStateChangeListener;

public class UiTest implements ProgramStateChangeListener {

	private JFrame	         frmUsbledlib;

	public static UiTest	   instance;

	public JComboBox<String>	comboBox	    = new JComboBox<String>();
	public JComboBox<String>	ports	        = new JComboBox<String>();
	private JLabel	         lblDeviceState	= new JLabel("Disconnected");
	private JButton	         btnRun	        = new JButton("Run");
	private JButton	         btnConnect	    = new JButton("Connect");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					instance = new UiTest();
					instance.frmUsbledlib.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UiTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		BaseProgram colorChange = new ColorChange();
		BaseProgram otherColorChange = new OtherColor();
		BaseProgram set = new Set();

		ProgramHandler.getInstance().addProgram(colorChange);
		ProgramHandler.getInstance().addProgram(otherColorChange);
		ProgramHandler.getInstance().addProgram(set);

		frmUsbledlib = new JFrame();
		frmUsbledlib.setTitle("UsbLedLib");
		frmUsbledlib.setBounds(100, 100, 209, 229);
		frmUsbledlib.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.WEST, ports, 0, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, ports, -14, SpringLayout.EAST, frmUsbledlib.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 10, SpringLayout.NORTH, frmUsbledlib.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 10, SpringLayout.WEST, frmUsbledlib.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboBox, -14, SpringLayout.EAST, frmUsbledlib.getContentPane());
		frmUsbledlib.getContentPane().setLayout(springLayout);
		for (BaseProgram pgm : ProgramHandler.getInstance().getProgramList()) {
			comboBox.addItem(pgm.getId());
		}
		frmUsbledlib.getContentPane().add(comboBox);

		btnRun.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, btnRun, 6, SpringLayout.SOUTH, comboBox);
		springLayout.putConstraint(SpringLayout.WEST, btnRun, 0, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, btnRun, -14, SpringLayout.EAST, frmUsbledlib.getContentPane());
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgramHandler.getInstance().getProgramById((String) UiTest.instance.comboBox.getSelectedItem()).addProgramStateChangeListener(UiTest.instance);
				ProgramHandler.getInstance().runProgram((String) UiTest.instance.comboBox.getSelectedItem());
				UiTest.instance.btnRun.setEnabled(false);
				UiTest.instance.comboBox.setEnabled(false);
			}
		});
		frmUsbledlib.getContentPane().add(btnRun);

		springLayout.putConstraint(SpringLayout.NORTH, btnConnect, 6, SpringLayout.SOUTH, btnRun);
		springLayout.putConstraint(SpringLayout.NORTH, ports, 6, SpringLayout.SOUTH, btnConnect);
		springLayout.putConstraint(SpringLayout.WEST, btnConnect, 0, SpringLayout.WEST, comboBox);
		springLayout.putConstraint(SpringLayout.EAST, btnConnect, -14, SpringLayout.EAST, frmUsbledlib.getContentPane());
		btnConnect.setActionCommand("connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("connect")) {
					try {
						ControllerInterface.connect((String) UiTest.instance.ports.getSelectedItem());
						UiTest.instance.lblDeviceState.setText("Connected");
						UiTest.instance.btnRun.setEnabled(true);
						UiTest.instance.btnConnect.setText("Disconnect");
						UiTest.instance.btnConnect.setActionCommand("disconnect");
						UiTest.instance.ports.setEnabled(false);
					} catch (UsbLedException e1) {
						e1.printStackTrace();
						UiTest.instance.lblDeviceState.setText("Error");
					}
				} else if (e.getActionCommand().equals("disconnect")) {
					ControllerInterface.disconnect();
					UiTest.instance.lblDeviceState.setText("Disconnected");
					UiTest.instance.btnConnect.setActionCommand("connect");
					UiTest.instance.btnRun.setEnabled(false);
					UiTest.instance.btnConnect.setText("Connect");
					UiTest.instance.ports.setEnabled(true);
				}
			}
		});
		frmUsbledlib.getContentPane().add(btnConnect);
		int i = 0;
		for (String p : jssc.SerialPortList.getPortNames()) {
			ports.addItem(p);
			i++;
		}
		frmUsbledlib.getContentPane().add(ports);

		JLabel lblDeviceStatus_1 = new JLabel("Device status :");
		springLayout.putConstraint(SpringLayout.NORTH, lblDeviceStatus_1, 16, SpringLayout.SOUTH, ports);
		springLayout.putConstraint(SpringLayout.WEST, lblDeviceStatus_1, 0, SpringLayout.WEST, comboBox);
		frmUsbledlib.getContentPane().add(lblDeviceStatus_1);

		springLayout.putConstraint(SpringLayout.NORTH, lblDeviceState, 6, SpringLayout.SOUTH, lblDeviceStatus_1);
		springLayout.putConstraint(SpringLayout.WEST, lblDeviceState, 0, SpringLayout.WEST, comboBox);
		frmUsbledlib.getContentPane().add(lblDeviceState);

		if (i == 0) {
			JOptionPane.showMessageDialog(null, "No Serial ports found.");
			btnConnect.setEnabled(false);
			ports.setEnabled(false);
			comboBox.setEnabled(false);
			lblDeviceState.setText("No devices found.");
		}

	}

	@Override
	public void onStateChange(BaseProgram pgm, ProgramState state) {
		if (state == ProgramState.DONE) {
			this.btnRun.setEnabled(true);
			UiTest.instance.comboBox.setEnabled(true);
		}
	}
}
