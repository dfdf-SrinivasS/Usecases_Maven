package com.usecases.df;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;

import test.globalized_put_avro_and_avsc_to_hdfs_uc3_part2_0_1.GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC3_PART2;
import test.hotelbars_0_1.HotelBars;
import test.uc3_hotels_offers_0_1.UC3_HOTELS_OFFERS;

public class Usecase3Demo //extends JFrame implements ActionListener 
{
	JLabel uc3Label = new JLabel("Usecase3");
	JButton bRunUC3 = new JButton("Run UC3 Flow");
	Container c= null;
	DatafactoryService dfs = null;
	/*Usecase3Demo()
	{
		this.setBounds(20,20, 350, 300);
		this.setVisible(true);
		this.setTitle("Dragonfly DataFactory Usecase3");
		this.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.add(uc3Label);
		c.add(bRunUC3);
		bRunUC3.addActionListener(this);
		this.pack();
		validate();
	}*/
	
	public void performExecution() 
	{
		dfs = new DatafactoryService();
		
		// Pentaho Jobs Extraction
		dfs.executePentahoJob(IDatameer.PENTAHO_UC3_EFILE);
		
		// Talend Jobs
		String[] jobParam = new String[0];
		HotelBars talendJob1 = new HotelBars();
		talendJob1.runJob(jobParam);
		if ("end".equalsIgnoreCase(talendJob1.getStatus()))
			System.out.println("Successfully Completed Talend Job1");

		UC3_HOTELS_OFFERS talendJob2 = new UC3_HOTELS_OFFERS();
		talendJob2.runJob(jobParam);
		if ("end".equalsIgnoreCase(talendJob2.getStatus()))
			System.out.println("Successfully Completed Talend Job2");
		
		GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC3_PART2 talendJob3 = new GLOBALIZED_PUT_AVRO_AND_AVSC_TO_HDFS_UC3_PART2();
		talendJob3.runJob(jobParam);
		if ("end".equalsIgnoreCase(talendJob3.getStatus()))
			System.out.println("Successfully Completed Talend Job3");	

		// Pentaho Jobs Integration
		dfs.executePentahoJob(IDatameer.PENTAHO_UC3_IFILE1);
		dfs.executePentahoJob(IDatameer.PENTAHO_UC3_IFILE2);
		
		System.gc();
		
		//Python script MYSqlToMONGODB
				dfs.executePythonScript(IDatameer.PYTHON_UC_EXECUTOR, IDatameer.PYTHON_UC3_MYSQLTOMONGODB_OFFERSDATA);
		
		// Tabelu population
		dfs.populateVisualization("http://52.0.169.60/t/DFdf/workbooks?project=50");
	}

	
	/*public void actionPerformed(ActionEvent ae)
	{		
		if(ae.getSource() == bRunUC3)
		{
			performExecution();			
		}
	}*/

	public static void main(String[] args) 
	{
		Usecase3Demo uc3 = new Usecase3Demo();
		uc3.performExecution();
	}
}