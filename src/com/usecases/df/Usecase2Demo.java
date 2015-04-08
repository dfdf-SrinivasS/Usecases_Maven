package com.usecases.df;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;

import test.uc2_hdfs_get_0_1.UC2_HDFS_get;
import test.uc2_talend_integration_0_1.UC2_Talend_integration;

public class Usecase2Demo //extends JFrame implements ActionListener 
{
	JLabel uc1Label = new JLabel("Usecase2");
	JButton bRunUC1 = new JButton("Run UC2 Flow");
	Container c= null;
	DatafactoryService dfs = null;
	/*Usecase2Demo()
	{
		this.setBounds(20,20, 350, 300);
		this.setVisible(true);
		this.setTitle("Dragonfly DataFactory Usecase1");
		this.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.add(uc1Label);
		c.add(bRunUC1);
		bRunUC1.addActionListener(this);
		this.pack();
		validate();
	}*/
	
	public void performExecution()
	{
		dfs = new DatafactoryService();
		
		//Pentaho Jobs
		dfs.executePentahoJob(IDatameer.PENTAHO_UC2_FILE);

		//Datameer Jobs
		String result ="";		
		String jobId[] = {"6","9","14","15","16","20"};
				
		for(String job:jobId)
		{
			result = dfs.executeDatameerJob(job);			
			if("COMPLETED".equals(result) || "COMPLETED_WITH_WARNINGS".equals(result))
			{				
				System.out.println("Job ID:"+job+" Final Job Status:"+result);
			}	
		}
		//Python script MYSqlToHDFS
				dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR, IDatameer.PYTHON_UC2_MYSQLTOHDFS);
		
				//Talend Jobs
		String[] jobParam =  new String[0];
		UC2_HDFS_get talendJob1 = new UC2_HDFS_get();
		talendJob1.runJob(jobParam);
		if("end".equalsIgnoreCase(talendJob1.getStatus()))
			System.out.println("Successfully Completed Job1");
		
		UC2_Talend_integration talendJob2 =  new UC2_Talend_integration();
		
		talendJob2.runJob(jobParam);
		if("end".equalsIgnoreCase(talendJob2.getStatus()))
			System.out.println("Successfully Completed Job2");
		
		//Python script MYSqlToMongoDB
		dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR, IDatameer.PYTHON_UC2_MYSQLTOMONGODB_POSITIVE_OFFERS);
		dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR, IDatameer.PYTHON_UC2_MYSQLTOMONGODB_NEGATIVE_OFFERS);
		
		//Tabelu population
		dfs.populateVisualization("http://52.0.169.60/t/DFdf/workbooks?project=52");
	}
	
/*	public void actionPerformed(ActionEvent ae)
	{		
		if(ae.getSource() == bRunUC1)
		{			
			performExecution();
		}
	}*/

	public static void main(String[] args) 
	{
		Usecase2Demo uc2 = new Usecase2Demo();
		uc2.performExecution();
	}
}
