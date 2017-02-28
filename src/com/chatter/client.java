package com.chatter;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class client {

	private JFrame frame;
	static private JTextField update_area;
	static private JTextField msg2beSent;

	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	
	 	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client window = new client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try{
			s = new Socket("0.0.0.0",8009);
			System.out.println(s.getLocalSocketAddress());
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			String msgin = "";
			while(!msgin.equals("exit")){
				msgin = din.readUTF();
				update_area.setText(update_area.getText().trim() + "\n" + msgin);
			}
		}catch(Exception e){
			
		}
	}

	/**
	 * Create the application.
	 */
	public client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		update_area = new JTextField();
		update_area.setBounds(12, 12, 346, 182);
		frame.getContentPane().add(update_area);
		update_area.setColumns(10);
		
		msg2beSent = new JTextField();
		msg2beSent.setBounds(12, 230, 301, 19);
		frame.getContentPane().add(msg2beSent);
		msg2beSent.setColumns(10);
		
		JButton btnSendserver = new JButton("send2Server");
		btnSendserver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String msgout = "";
					msgout = msg2beSent.getText().trim();
					dout.writeUTF(msgout);
				}catch(Exception ex){
					
				}
			}
		});
		btnSendserver.setBounds(321, 227, 117, 25);
		frame.getContentPane().add(btnSendserver);
	}

}
