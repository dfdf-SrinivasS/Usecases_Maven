package com.usecases.df;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Usecase6Demo // extends JFrame implements ActionListener 
{
	JLabel uc1Label = new JLabel("Usecase6");
	JButton bRunUC1 = new JButton("Run UC6 Flow");
	Container c= null;
	DatafactoryService dfs = null;
	/*Usecase6Demo()
	{
		this.setBounds(20,20, 350, 300);
		this.setVisible(true);
		this.setTitle("Dragonfly DataFactory Usecase5");
		this.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.add(uc1Label);
		c.add(bRunUC1);
		bRunUC1.addActionListener(this);
		this.pack();
		validate();
	}*/
	
	/*public void actionPerformed(ActionEvent ae)
	{		
		if(ae.getSource() == bRunUC1)
		{
			dfs = new DatafactoryService();
			dfs.executePentahoJob(IDatameer.PENTAHO_UC6_FILE1);
			dfs.executePentahoJob(IDatameer.PENTAHO_UC6_FILE2);
			dfs.executePentahoJob(IDatameer.PENTAHO_UC6_FILE3);
		}
	}*/

	public static void main(String[] args) 
	{
		new Usecase6Demo();
		DatafactoryService dfs = new DatafactoryService();
		dfs.executePentahoJob(IDatameer.PENTAHO_UC6_FILE1);
		dfs.executePentahoJob(IDatameer.PENTAHO_UC6_FILE2);
		System.out.println("31");
		dfs.executePentahoJob(IDatameer.PENTAHO_UC6_FILE3);
		System.out.println("32");
		dfs.populateVisualization("http://54.84.31.191:2020/domo/dashboards/UseCase6/main.dashxml");
	}
}
