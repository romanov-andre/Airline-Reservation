package main;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Ticketreport extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JButton jButtonCancel;
	private JScrollPane jScrollPane1;
	private JTable jTable1;
	// End of variables declaration//GEN-END:variables

	/**
	 * Creates new form Ticketreport
	 */
	public Ticketreport() {
		initComponents();
		LoadData();
	}

	public Ticketreport(MysqlDataSource ds) {
		initComponents();
		this.d = ds;
	}


	MysqlDataSource d = null;
	Connection con;
	PreparedStatement pst;

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new JScrollPane();
		jTable1 = new JTable();
		jButtonCancel = new JButton();

		jButtonCancel.setName("cancel");

		jTable1.setModel(
				new javax.swing.table.DefaultTableModel(new Object[][] {

				}, new String[] { "TicketNo", "Flight No", "Customer ID",
						"Class", "Price", "Seats", "Date" }));
		jScrollPane1.setViewportView(jTable1);

		jButtonCancel.setText("Cancel");
		jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap().addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										509,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addGap(226, 226, 226).addComponent(jButtonCancel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										155,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(35, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(29, 29, 29)
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 259,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(35, 35, 35)
						.addComponent(jButtonCancel,
								javax.swing.GroupLayout.PREFERRED_SIZE, 49,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(37, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	public boolean jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		this.hide();
		return true;
	}//GEN-LAST:event_jButton1ActionPerformed

	public boolean LoadData() {
		try {

			if(d == null) {
				d = new MysqlDataSource();
				d.setUser("root");
				d.setPassword("1234");
				d.setDatabaseName("airline");
			}

			con =  d.getConnection();
			pst = con.prepareStatement("SELECT * from Ticket");
			ResultSet rs = pst.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			int c;
			c = rsm.getColumnCount();

			DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
			Df.setRowCount(0);

			while (rs.next()) {
				Vector<String> v2 = new Vector<String>();

				for (int i = 1; i <= c; i++) {
					v2.add(rs.getString("id"));
					v2.add(rs.getString("flightid"));
					v2.add(rs.getString("custid"));
					v2.add(rs.getString("class"));
					v2.add(rs.getString("price"));
					v2.add(rs.getString("seats"));
					v2.add(rs.getString("date"));
				}

				Df.addRow(v2);
			}

		} catch (SQLException ex) {
			Logger.getLogger(Ticket.class.getName()).log(Level.SEVERE, null,
					ex);
			return false;
		}

		return true;

	}

}
