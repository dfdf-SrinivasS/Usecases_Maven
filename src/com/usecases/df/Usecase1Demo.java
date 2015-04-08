package com.usecases.df;
import java.awt.Container;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;

import test.globalized_put_avro_and_avsc_to_hdfs_uc1_part1_0_1.GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC1_PART1;
import test.globalized_put_avro_and_avsc_to_hdfs_uc1_part2_0_1.GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC1_PART2;
import test.mongodb_cassandra_uc1_0_1.MONGODB_CASSANDRA_UC1;


public class Usecase1Demo //extends JFrame implements ActionListener 
{
	JLabel uc1Label = new JLabel("Usecase1");
	JButton bRunUC1 = new JButton("Run UC1 Flow");
	Container c= null;
	DatafactoryService dfs = null;
	
	/*Usecase1Demo()
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
	
	public void performExecution() throws IOException
	{
		dfs = new DatafactoryService();
		
		//Talend Jobs
		String[] jobParam =  new String[0];
		System.out.println("Talend Job Started");
		GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC1_PART1 talendJob1 = new GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC1_PART1();
		 talendJob1.runJob(jobParam);
		if("end".equalsIgnoreCase(talendJob1.getStatus()))
			System.out.println("Successfully Completed Job1");
		
		GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC1_PART2 talendJob2 =  new GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC1_PART2();
		talendJob2.runJob(jobParam);
		if("end".equalsIgnoreCase(talendJob2.getStatus()))
			System.out.println("Successfully Completed Job2");
		
		//Datameer Jobs
		String result ="";		
		String jobId[] = {"13","17","18","7","11","12"};
		
		for(String job:jobId)
		{
			result = dfs.executeDatameerJob(job);			
			if("COMPLETED".equals(result) || "COMPLETED_WITH_WARNINGS".equals(result))
			{				
				System.out.println("Job ID:"+job+" Final Job Status:"+result);
			}	
		}
		//Python script KEYWORDSEXTRACT
				dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR, IDatameer.PYTHON_UC1_KEYWORDS);
		
		/*		//Python script MYSqlToHDFS
		dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR, IDatameer.PYTHON_UC1_MYSQLTOHDFS);*/
		
		//Pentaho Jobs
		dfs.executePentahoJob(IDatameer.PENTAHO_UC1_FILE);
		
		MONGODB_CASSANDRA_UC1 talendJob3 = new MONGODB_CASSANDRA_UC1();
		talendJob3.runJob(jobParam);
		if("end".equalsIgnoreCase(talendJob3.getStatus()))
			System.out.println("Successfully Completed Job3");
		//Python script KEYWORD_SUBCATEGORY
		dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR_NEW, IDatameer.PYTHON_UC1_KEYWORD_SUBCATEGORY);
		
		//Python script BIG5_HASHTAG_SUBCATEGORY
				dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR_NEW, IDatameer.PYTHON_UC1_BIG5_HASHTAG_SUBCATEGORY);
				
				//Python script ALLTWEET_HASHTAG_SUBCATEGORY
				dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR_NEW, IDatameer.PYTHON_UC1_ALLTWEET_HASHTAG_SUBCATEGORY);
				
				//Python script PYTHON_UC1_OFFERS
				dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR_NEW, IDatameer.PYTHON_UC1_OFFERS);
		
		/*//Python script MYSqlToCassandra
				dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR, IDatameer.PYTHON_UC1_MYSQLTOCASSANDRA_HASHTAGSOFFERS);
				dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR, IDatameer.PYTHON_UC1_MYSQLTOCASSANDRA_KEYWORDSOFFERS);*/
		//Tabelu population
		dfs.populateVisualization("http://52.0.169.60/t/DFdf/workbooks?project=53");
	}
	
	/*public void actionPerformed(ActionEvent ae)
	{		
		if(ae.getSource() == bRunUC1)
		{			
			try {
				performExecution();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/

	public static void main(String[] args) 
	{
		Usecase1Demo uc1 = new Usecase1Demo();
		try {
			uc1.performExecution();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
