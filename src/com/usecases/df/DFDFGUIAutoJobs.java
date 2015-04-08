package com.usecases.df;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DFDFGUIAutoJobs extends JFrame implements ActionListener
{	
	DatameerDFAPI dataAPI = null;
	Runtime run = Runtime.getRuntime();
	Process pro = null;
	JSONParser parser=new JSONParser();
	
	Icon dfdfIcon = new ImageIcon("DFDFLogo.png");
    JLabel logoLabel = new JLabel(dfdfIcon);
        
    JTabbedPane jobTabs = null;
	JPanel datameerPanel = null;
	JPanel dPanel1 = null, dPanel2 = null, dPanel3 = null, dPanel4 = null;
	JPanel pentahoPanel = null;
	
	JTable jobsTbl = null,jobsPentTbl = null;
	DefaultTableModel dtm = null, dtmPent = null; 
    	
	JButton bJobExec = new JButton("Job Execute");	
	JButton bDataIJ = new JButton("Import Jobs");
	JButton bDataWJ = new JButton("Workbook Jobs");
	JButton bDataEJ = new JButton("Export Jobs");
	
	JButton bPentJobs = new JButton("Load Jobs");
	
	GridBagLayout gridLayout = null;	
	
	JLabel lbID = new JLabel("Datameer Job ID:");
	JLabel lbJID = new JLabel();
	JLabel lbStatus = new JLabel("Job Status:");
	JLabel lbJobStatus = new JLabel();
	
	Container c;
	
	DFDFGUIAutoJobs()
	{		
		this.setBounds(20,20, 350, 300);
		this.setVisible(true);
		this.setTitle("Dragonfly DataFactory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		
		gridLayout = new GridBagLayout();		
		c.setLayout(gridLayout);
		
		jobTabs = new JTabbedPane();
		
		datameerPanel = new JPanel(new GridBagLayout());
		datameerPanel.setBackground(Color.WHITE);
		
		dtm = new DefaultTableModel();
		dtm.addColumn("ID");
		dtm.addColumn("Name");
		dtm.addColumn("Description");
		jobsTbl = new JTable(dtm) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
			}			
        };
        jobsTbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              lbJID.setText(""+jobsTbl.getValueAt(jobsTbl.getSelectedRow(), 0));
            }
          });
		
		JScrollPane jsp = new JScrollPane(jobsTbl,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jobsTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		GridBagConstraints gbc = new GridBagConstraints();
		dPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,30,30));
		dPanel1.setBackground(Color.WHITE);
		dPanel1.add(bDataIJ);
		dPanel1.add(bDataWJ);
		dPanel1.add(bDataEJ);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 0;
		datameerPanel.add(dPanel1, gbc);
		
		dPanel2= new JPanel(new FlowLayout(FlowLayout.CENTER));
		dPanel2.setBackground(Color.GREEN);
		dPanel2.add(jsp);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 1;		
		datameerPanel.add(dPanel2, gbc);
		
		dPanel3 = new JPanel(new GridBagLayout());	
		dPanel3.setBackground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		dPanel3.add(lbID,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		dPanel3.add(lbJID,gbc);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 1;		
		dPanel3.add(bJobExec,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		dPanel3.add(lbStatus,gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		dPanel3.add(lbJobStatus,gbc);		
		bJobExec.setBackground(new Color(34,177,76));
		
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 1;
		gbc.gridy = 1;		
		datameerPanel.add(dPanel3, gbc);		
		
		pentahoPanel = new JPanel();
		pentahoPanel.setBackground(Color.WHITE);
		
		dtmPent = new DefaultTableModel();
		dtmPent.addColumn("Job Details");
		jobsPentTbl = new JTable(dtmPent) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
			}			
        };
       /* jobsPentTbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              lbJID.setText(""+jobsPentTbl.getValueAt(jobsPentTbl.getSelectedRow(), 0));
            }
          });*/
		
		JScrollPane jsp1 = new JScrollPane(jobsPentTbl,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jobsPentTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		dPanel4= new JPanel(new FlowLayout(FlowLayout.CENTER));
		dPanel4.setBackground(Color.GREEN);
		dPanel4.add(jsp1);
		dPanel4.add(bPentJobs);
		pentahoPanel.add(dPanel4);
		
		jobTabs.add("Datameer", datameerPanel);
		jobTabs.add("Pentaho", pentahoPanel);		
		
		gbc.fill = GridBagConstraints.HORIZONTAL;		
		gbc.gridx = 0;
		gbc.gridy = 0;	    
		c.add(logoLabel,gbc);
				
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
	    gbc.gridy = 1;		
	    c.add(jobTabs,gbc);
	    
		bDataIJ.addActionListener(this);		
		bDataWJ.addActionListener(this);
		bDataEJ.addActionListener(this);
		bJobExec.addActionListener(this);
		bPentJobs.addActionListener(this);
		this.pack();
		validate();
	}
	
	public void actionPerformed(ActionEvent ae)
	{		
		if(ae.getSource() == bDataIJ)
		{
			dataAPI = new DatameerDFAPI();
			JSONArray array = null;
			JSONObject jsonObj = null;
			Vector<Object> vec = null;
			
			int rc = dtm.getRowCount();
			for(int i=0;i<rc;i++)
				dtm.removeRow(0);
			jobsTbl.update(jobsTbl.getGraphics());
			
			try{				
				pro = run.exec(IDatameer.CURL_IMP_JOB_DET);
				String result = dataAPI.jobDetails(pro);
			
				if(!"".equals(result))
				{
					Object obj = parser.parse(result);
					System.out.println("Testing here"); 
					if(result.lastIndexOf("{") > 1)
					{
						array = (JSONArray) obj;
					
						Iterator<JSONObject> iterator = array.iterator();
						while(iterator.hasNext())
						{
							vec = new Vector<Object>();
							jsonObj = iterator.next();
							vec.addElement(jsonObj.get("id"));
							vec.addElement(jsonObj.get("name"));
							vec.addElement(jsonObj.get("description"));
						
							dtm.addRow(vec);
						}  			         
					}
					else{
						vec = new Vector<Object>();
						jsonObj = (JSONObject) obj;
						vec.addElement(jsonObj.get("id"));
						vec.addElement(jsonObj.get("name"));
						vec.addElement(jsonObj.get("description"));
					 
						dtm.addRow(vec);
					}
				}
				}catch(Exception e){
					  System.out.println("Exception:"+e);
				  }
			}
		
			if(ae.getSource() == bDataWJ)
			{
				dataAPI = new DatameerDFAPI();
				JSONArray array = null;
				JSONObject jsonObj = null;
				Vector<Object> vec = null;
				
				int rc = dtm.getRowCount();
				for(int i=0;i<rc;i++)
					dtm.removeRow(0);
				jobsTbl.update(jobsTbl.getGraphics());
				
				try{				
					pro = run.exec(IDatameer.CURL_WB_JOB_DET);
					String result = dataAPI.jobDetails(pro);
			
					if(!"".equals(result))
					{
						Object obj = parser.parse(result);
						System.out.println("Testing here"); 
						if(result.lastIndexOf("{") > 1)
						{
							array = (JSONArray) obj;
					
							Iterator<JSONObject> iterator = array.iterator();
							while(iterator.hasNext())
							{
								vec = new Vector<Object>();
								jsonObj = iterator.next();
								vec.addElement(jsonObj.get("id"));
								vec.addElement(jsonObj.get("name"));
								vec.addElement(jsonObj.get("description"));
						
								dtm.addRow(vec);
							}  			         
						}
						else{
							vec = new Vector<Object>();
							jsonObj = (JSONObject) obj;
							vec.addElement(jsonObj.get("id"));
							vec.addElement(jsonObj.get("name"));
							vec.addElement(jsonObj.get("description"));
					 
							dtm.addRow(vec);
						}
				}
				}catch(Exception e){
					  System.out.println("Exception:"+e);
				  }
			}	
			
			if(ae.getSource() == bDataEJ)
			{
				dataAPI = new DatameerDFAPI();
				JSONArray array = null;
				JSONObject jsonObj = null;
				Vector<Object> vec = null;
				
				int rc = dtm.getRowCount();
				for(int i=0;i<rc;i++)
					dtm.removeRow(i);
				jobsTbl.update(jobsTbl.getGraphics());
				
				try{				
					pro = run.exec(IDatameer.CURL_EXP_JOB_DET);
					String result = dataAPI.jobDetails(pro);
			
					if(!"".equals(result))
					{
						Object obj = parser.parse(result);
						System.out.println("Testing here"); 
						if(result.lastIndexOf("{") > 1)
						{
							array = (JSONArray) obj;
					
							Iterator<JSONObject> iterator = array.iterator();
							while(iterator.hasNext())
							{
								vec = new Vector<Object>();
								jsonObj = iterator.next();
								vec.addElement(jsonObj.get("id"));
								vec.addElement(jsonObj.get("name"));
								vec.addElement(jsonObj.get("description"));
						
								dtm.addRow(vec);
							}  			         
						}
						else{
							vec = new Vector<Object>();
							jsonObj = (JSONObject) obj;
							vec.addElement(jsonObj.get("id"));
							vec.addElement(jsonObj.get("name"));
							vec.addElement(jsonObj.get("description"));
					 
							dtm.addRow(vec);
						}
				}
				}catch(Exception e){
					  System.out.println("Exception:"+e);
				  }
			}	
		
			if(ae.getSource() == bJobExec && !"".equals(lbJID.getText()))
			{	
				System.out.println("Sudheer in JobExecute");
				dataAPI = new DatameerDFAPI();
				String jStatus = "";
				try{		
					System.out.println("Inside try after run:"+IDatameer.CURL_JOB_EXEC+lbJID.getText()+"\"");
						
					pro = run.exec(IDatameer.CURL_JOB_EXEC+lbJID.getText()+"\"");
					System.out.println("Data pro:"+lbJID.getText());
					Thread.sleep(1000);
					String result = "";
					pro = run.exec(IDatameer.CURL_JOB_STATUS+lbJID.getText()+"\"");
					do{							
						System.out.println("sudheer in do");
						pro = run.exec(IDatameer.CURL_JOB_STATUS+lbJID.getText()+"\"");
						result = dataAPI.jobDetails(pro);
						Object obj = parser.parse(result);
						JSONObject jsonObj = (JSONObject) obj;
						jStatus = jsonObj.get("jobStatus").toString();
						System.out.println("sudheer status:"+jStatus);
					//} while(jStatus.equals("COMPLETED") || jStatus.equals("COMPLETED_WITH_WARNINGS") || jStatus.equals("CANCELED") || jStatus.equals("ERROR"));
					} while(jStatus.equals("QUEUED") || jStatus.equals("RUNNING") || jStatus.equals("WAITING_FOR_OTHER_JOB"));
					lbJobStatus.setText(jStatus);
				}catch(Exception e){
						  System.out.println("Exception:"+e);
				  }	
			}
			
			if(ae.getSource() == bPentJobs)
			{
				File f = new File("C:/Penatho_files/Pent/");
				
				FilenameFilter kjbFilter = new FilenameFilter() {
					public boolean accept(File dir, String name) {
						String lowercaseName = name.toLowerCase();
						if (lowercaseName.endsWith(".kjb")) {
							return true;
						} else {
							return false;
						}
					}
				};

				
				String[]  files = f.list(kjbFilter);
				System.out.println(files.length);
				Vector<Object> vec = null;
				for(String file: files)
				{				
					vec = new Vector<Object>();
					vec.addElement(file);
					dtmPent.addRow(vec);
					System.out.println(file);
				}
			}
	}		
	
	
	
	public static void main(String arg[])
	{
		new DFDFGUIAutoJobs();
		System.getProperties().setProperty("KETTLE_PLUGIN_BASE_FOLDERS", IDatameer.PENTAHO_PLUGIN_PATH);
	}	
}
