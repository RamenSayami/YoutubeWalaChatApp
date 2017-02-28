package com.chatter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class server {

	private JFrame frame;
	static private JTextField msg_area;
	static private JTextField msg2Bsent;
	
	static ServerSocket ss;
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
					server window = new server();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String msgin = "";
		try{
			ss = new ServerSocket(8009);
			System.out.println(ss.getLocalSocketAddress());

			s = ss.accept();
			System.out.println("Client connected!");
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			while (!msgin.equals("exit")){
				msgin = din.readUTF();
				msg_area.setText(msg_area.getText().trim()+"\n"+msgin);
			}
		}catch(IOException e){
			
		}
	}

	/**
	 * Create the application.
	 */
	public server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 300);
		frame.getContentPane().setLayout(null);
		
		msg_area = new JTextField();
		msg_area.setBounds(12, 12, 399, 149);
		frame.getContentPane().add(msg_area);
		msg_area.setColumns(10);
		
		msg2Bsent = new JTextField();
		msg2Bsent.setBounds(12, 219, 322, 19);
		frame.getContentPane().add(msg2Bsent);
		msg2Bsent.setColumns(10);
		
		JButton btnSend = new JButton("send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String msgout = "";
					msgout = msg2Bsent.getText().trim();
					dout.writeUTF(msgout);
				}catch(IOException ioe){
					
				}
			}
		});
		btnSend.setBounds(346, 216, 117, 25);
		frame.getContentPane().add(btnSend);
	}
}
