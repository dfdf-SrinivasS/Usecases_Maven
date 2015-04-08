package com.usecases.df;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;

public class DatafactoryService 
{
	JSONParser parser=new JSONParser();
	DatameerDFAPI dataAPI = null;
	Runtime run = Runtime.getRuntime();
	Process proc = null;
	
	DatafactoryService()
	{
		System.getProperties().setProperty("KETTLE_PLUGIN_BASE_FOLDERS", IDatameer.PENTAHO_PLUGIN_PATH);
	}
	
	//DATAMEER JOB EXECUTION
	public String executeDatameerJob(String jobId)
	{
		System.out.println("Inside executeDatameerJob:"+jobId);
		dataAPI = new DatameerDFAPI();
		String jStatus = "";
		
		try{				
			proc = run.exec(IDatameer.CURL_JOB_EXEC+jobId);			
			Thread.sleep(1000);
			String result = "";		
				
			do{					
				proc = run.exec(IDatameer.CURL_JOB_STATUS+jobId);				
				result = dataAPI.jobDetails(proc);
				
				Object obj = parser.parse(result.toString());
				JSONObject jsonObj = (JSONObject) obj;
				jStatus = jsonObj.get("jobStatus").toString();
				System.out.println("Datameer Job status:"+jStatus);
			} while(jStatus.equals("QUEUED") || jStatus.equals("RUNNING") || jStatus.equals("WAITING_FOR_OTHER_JOB"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return jStatus;
	}
	public void executePythonScript(String arg1,String arg2){
		System.out.println("Pyhtonjob started");
		
		String[] cmd = new String[2];
		cmd[0] = arg1;
		cmd[1] = arg2;
		Runtime rt = Runtime.getRuntime();
		Process pr = null;
		String result = null;
		try {
			   pr = rt.exec(cmd);
			   
			   String line = null;
			   BufferedReader read = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			  
			   while((line = read.readLine()) != null) 
			   {
			      System.out.println(line);
			   }
			   result ="Python Job Successful";
			   System.out.println("Result:"+result);
			   
			   //return result;
			  
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result ="Python Job Failed";
			System.out.println(result);
			e.printStackTrace();
			
		}
		
		
		
	}
	public void executePentahoJob(String file)
	{	
		System.out.println("Inside executePentahoJob:"+file);
		try {
			KettleEnvironment.init();
		
			JobMeta jobMeta = new JobMeta(file, null);
			Job job = new Job(null, jobMeta);
			job.run();
			job.waitUntilFinished();
			if (job.getErrors()!=0)
				System.out.println("Error encountered!");
			else
				System.out.println("PentahoJob "+file+" executed successfully");			
		} catch (KettleException e) {
			e.printStackTrace();
		}
	}	
	
	public void populateVisualization(String url)
	{
		try{
			Desktop desktop = java.awt.Desktop.getDesktop();
			URL oURL = new URL(url);
		  
			URLConnection uc = oURL.openConnection();
			String userpass = "admin:admin";
		  
			@SuppressWarnings("restriction")
			String basicAuth = "Basic " + new String(new sun.misc.BASE64Encoder().encode(userpass.getBytes()));
			uc.setRequestProperty ("Authorization", basicAuth);
			
			desktop.browse(oURL.toURI());
		}catch(Exception e){
			System.out.println(e);
		}
	}		
}
