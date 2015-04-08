package com.usecases.df;

public class Usecase5Demo //extends JFrame implements ActionListener 
{
	/*JLabel uc1Label = new JLabel("Usecase5");
	JButton bRunUC1 = new JButton("Run UC5 Flow");
	Container c= null;
	DatafactoryService dfs = null;
	Usecase5Demo()
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
			dfs.executePentahoJob(IDatameer.PENTAHO_UC5_FILE1);
			dfs.executePentahoJob(IDatameer.PENTAHO_UC5_FILE2);
			dfs.executePentahoJob(IDatameer.PENTAHO_UC5_FILE3);
			dfs.populateVisualization("http://127.0.0.1:2020/domo/dashboards/Hotel_1/main.dashxml?CTNAVID=1000");
		}
	}*/

	public static void main(String[] args) 
	{
		new Usecase5Demo();
		DatafactoryService dfs = new DatafactoryService();
		dfs.executePentahoJob(IDatameer.PENTAHO_UC5_FILE1);
		dfs.executePentahoJob(IDatameer.PENTAHO_UC5_FILE2);
		dfs.executePentahoJob(IDatameer.PENTAHO_UC5_FILE3);
		dfs.populateVisualization("http://54.84.31.191:2020/domo/dashboards/Hotel_Temp/main.dashxml");
	}
}
