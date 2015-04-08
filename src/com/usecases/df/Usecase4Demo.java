package com.usecases.df;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Usecase4Demo //extends JFrame implements ActionListener 
{
	JLabel uc4Label = new JLabel("Usecase4");
	JButton bRunUC4 = new JButton("Run UC4 Flow");
	Container c= null;
	DatafactoryService dfs = null;
	/*Usecase4Demo()
	{
		this.setBounds(20,20, 350, 300);
		this.setVisible(true);
		this.setTitle("Dragonfly DataFactory Usecase4");
		this.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.add(uc4Label);
		c.add(bRunUC4);
		bRunUC4.addActionListener(this);
		this.pack();
		validate();
	}*/
	
	public void performExecution()
	{
		dfs = new DatafactoryService();
		
		//Datameer Jobs
		String result ="";		
		String jobId[] = {"21","27","28","30","31","32"};
		
		for(String job:jobId)
		{
			//result = dfs.executeDatameerJob(job);			
			if("COMPLETED".equals(result) || "COMPLETED_WITH_WARNINGS".equals(result))
			{				
				System.out.println("Job ID:"+job+" Final Job Status:"+result);
			}	
		}
		
		//Pentaho Jobs
		dfs.executePentahoJob(IDatameer.PENTAHO_UC4_FILE1);
		
		//Tabelu population
		//dfs.populateVisualization("http://52.0.169.60/t/DFdf/workbooks?project=51");
	}
	
	/*public void actionPerformed(ActionEvent ae)
	{		
		if(ae.getSource() == bRunUC4)
		{			
			performExecution();
		}
	}*/

	public static void main(String[] args) 
	{
		Usecase4Demo uc4 = new Usecase4Demo();
		uc4.performExecution();
	}
}
