package main;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class UserCreation extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JButton jButtonAdd;
	private JButton jButtonCancel;
	private JLabel jLabelUserID;
	private JLabel jLabelFirstName;
	private JLabel jLabelLastName;
	private JLabel jLabelUserName;
	private JLabel jLabelPassword;
	private JPanel jPanel1;
	private JTextField txtfirstname;
	private JTextField txtlastname;
	private JPasswordField txtpassword;
	private JTextField txtuserid;
	private JTextField txtusername;
	// End of variables declaration//GEN-END:variables

	public void setTxtuserid(String userid) {
		this.txtuserid.setText(userid);
	}

	public void setTxtfirstname(String firstName) {
		this.txtfirstname.setText(firstName);
	}

	public void setTxtlastname(String lastname) {
		this.txtlastname.setText(lastname);
	}

	public void setTxtpassword(String password) {
		this.txtpassword.setText(password);
	}

	public void setTxtusername(String username) {
		this.txtusername.setText(username);
	}

	public void setStatementString(String query) {
		statementString = query;
	}
	/**
	 * Creates new form UserCreation
	 */
	public UserCreation() {
		initComponents();
		autoID();
	}

	String statementString;
	ResultSet rs;
	Statement statement;
	MysqlDataSource d = null;
	Connection con;
	PreparedStatement pst;
	ResultSetMetaData rsm;
	public UserCreation(MysqlDataSource ds) {
		initComponents();
		this.d = ds;
	}


	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new JPanel();
		jLabelUserID = new JLabel();
		jLabelFirstName = new JLabel();
		jLabelLastName = new JLabel();
		jLabelUserName = new JLabel();
		jLabelPassword = new JLabel();
		txtuserid = new JTextField();
		txtfirstname = new JTextField();
		txtlastname = new JTextField();
		txtusername = new JTextField();
		jButtonAdd = new JButton();
		jButtonCancel = new JButton();
		txtpassword = new JPasswordField();

		jButtonCancel.setName("cancel");
		jButtonAdd.setName("add");

		txtuserid.setName("userid");
		txtfirstname.setName("first");
		txtlastname.setName("last");
		txtusername.setName("username");
		txtpassword.setName("password");

		jPanel1.setBorder(
				javax.swing.BorderFactory.createTitledBorder("User Creation"));

		jLabelUserID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabelUserID.setText("User ID");

		jLabelFirstName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabelFirstName.setText("FirstName");

		jLabelLastName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabelLastName.setText("LastName");

		jLabelUserName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabelUserName.setText("User Name");

		jLabelPassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		jLabelPassword.setText("Password");

		txtuserid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
		txtuserid.setForeground(new java.awt.Color(255, 0, 0));
		txtuserid.setText(txtuserid.getText());

		jButtonAdd.setText("Add");
		jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonAddActionPerformed(evt);
			}
		});

		jButtonCancel.setText("Cancel");
		jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonCancelActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(44, 44, 44)
						.addGroup(jPanel1Layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabelUserID)
								.addComponent(jLabelFirstName)
								.addComponent(jLabelLastName)
								.addComponent(jLabelUserName)
								.addComponent(jLabelPassword))
						.addGap(55, 55, 55)
						.addGroup(jPanel1Layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING,
								false).addComponent(txtuserid)
								.addComponent(txtfirstname)
								.addComponent(txtlastname)
								.addComponent(txtusername)
								.addComponent(txtpassword,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										140, Short.MAX_VALUE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup()
								.addContainerGap(208, Short.MAX_VALUE)
								.addComponent(jButtonAdd,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										109,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jButtonCancel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										107,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(27, 27, 27)
						.addGroup(jPanel1Layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout
												.createParallelGroup(
														javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(jPanel1Layout
														.createSequentialGroup()
														.addGroup(jPanel1Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(
																		jLabelUserID)
																.addComponent(
																		txtuserid))
														.addGap(37, 37, 37)
														.addGroup(jPanel1Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(
																		jLabelFirstName)
																.addComponent(
																		txtfirstname,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(44, 44, 44)
														.addComponent(
																jLabelLastName))
												.addComponent(txtlastname,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(49, 49, 49)
										.addComponent(jLabelUserName))
								.addComponent(txtusername,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(46, 46, 46)
						.addGroup(jPanel1Layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabelPassword)
								.addComponent(txtpassword,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(
								javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								26, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButtonAdd,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										48,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonCancel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										44,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(27, 27, 27)
						.addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(23, 23, 23)
						.addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE)));

		pack();
	}

	public boolean jButtonAddActionPerformed(ActionEvent evt) {
		String pattern = "[a-zA-z0-9]{8,}";
		Pattern compiledPattern = Pattern.compile(pattern);

		String id = txtuserid.getText();
		String firstname = txtfirstname.getText();
		String lastname = txtlastname.getText();
		String username = txtusername.getText();
		String password = new String(txtpassword.getPassword());
		Matcher m = compiledPattern.matcher(password);
		System.out.println(id);
		System.out.println(lastname);
		System.out.println(firstname);
		System.out.println(username);
		System.out.println(password);
		boolean passwordMatch = m.matches();

		if ((firstname.isEmpty()) ||(lastname.isEmpty()) ||(username.isEmpty())|| (password.length() < 8 || !passwordMatch)){
			JOptionPane.showMessageDialog(this,"Field cannot be left empty");

			return false;
		}
		try {
			if(d == null) {
				d = new MysqlDataSource();
				d.setUser("root");
				d.setPassword("1234");
				d.setDatabaseName("airline");
			}
			con =  d.getConnection();
			pst = con.prepareStatement(
					"insert into user(id,firstname,lastname,username,password)values(?,?,?,?,?)");

			pst.setString(1, id);
			pst.setString(2, firstname);
			pst.setString(3, lastname);
			pst.setString(4, username);
			pst.setString(5, password);
			pst.executeUpdate();

			JOptionPane.showMessageDialog(null, "User Created...");

		} catch (SQLException ex) {
			Logger.getLogger(Addflight.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return true;
	}

	private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
		this.hide();
	}

	public void autoID() {
		try {
			if(d == null) {
				d = new MysqlDataSource();
				d.setUser("root");
				d.setPassword("1234");
				d.setDatabaseName("airline");
			}
			con =  d.getConnection();
			if(statementString == null) {
				statement = con.createStatement();
				rs = statement.executeQuery("select MAX(id) from customer");
			} else {
				rs = statement.executeQuery(statementString);
			}
			rs.next();
			rs.getString("MAX(id)");
			if (rs.getString("MAX(id)") == null) {
				txtuserid.setText("UO001");
			} else {
				long id = Long.parseLong(rs.getString("MAX(id)").substring(2,
						rs.getString("MAX(id)").length()));
				id++;
				txtuserid.setText("UO" + String.format("%03d", id));

			}

		} catch (SQLException ex) {
			Logger.getLogger(AddCustomer.class.getName()).log(Level.SEVERE,
					null, ex);
		}

	}
}