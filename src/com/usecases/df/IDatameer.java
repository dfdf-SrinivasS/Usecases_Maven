package com.usecases.df;
public interface IDatameer 
{
	public static final String CURL_JOB_EXEC = "curl -u admin:admin -X POST http://54.86.180.167:7777/rest/job-execution?configuration=";
	public static final String CURL_JOB_STATUS = "curl -s -u admin:admin -X GET http://54.86.180.167:7777/rest/job-configuration/job-status/";
	
	public static final String CURL_IMP_JOB_DET = "curl -u admin:admin -X GET http://54.86.180.167:7777/rest/import-job";
	public static final String CURL_WB_JOB_DET = "curl -u admin:admin -X GET http://54.86.180.167:7777/rest/workbook";
	public static final String CURL_EXP_JOB_DET = "curl -u admin:admin -X GET http://54.86.180.167:7777/rest/export-job";
	
	public static final String PENTAHO_PLUGIN_PATH = "C:/Users/Public/Downloads/pdi-ce-5.2.0.0-209/data-integration/plugins/";
	public static final String PENTAHO_UC1_FILE = "C:/Users/Public/Demo/Usecase1/Pentaho/Usecase1_Demo.kjb";
	
	public static final String PYTHON_UC1_OFFERS  = "C:/Users/Administrator/PycharmProjects/untitled/UC1_offers.py";
	public static final String PYTHON_UC1_ALLTWEET_HASHTAG_SUBCATEGORY  = "C:/Users/Administrator/PycharmProjects/untitled/UC1_All_Hashtags_Subcategory.py";
	public static final String PYTHON_UC1_BIG5_HASHTAG_SUBCATEGORY  = "C:/Users/Administrator/PycharmProjects/untitled/UC1_Big5_Hashtag_Subcategory.py";
	public static final String PYTHON_UC1_KEYWORD_SUBCATEGORY  = "C:/Users/Administrator/PycharmProjects/untitled/UC1_Keyword_Subcategory.py";
	public static final String PYTHON_UC1_KEYWORDS  = "C:/Users/Administrator/PycharmProjects/untitled/ExtractKeywords.py";
	public static final String PYTHON_UC1_MYSQLTOHDFS  = "C:/Users/Administrator/PycharmProjects/untitled/MySQlToHDFS.py";
	public static final String PYTHON_UC1_MYSQLTOCASSANDRA_HASHTAGSOFFERS  = "C:/Users/Administrator/PycharmProjects/untitled/MySqlToCassandra_Hashtags.py";
	public static final String PYTHON_UC1_MYSQLTOCASSANDRA_KEYWORDSOFFERS  = "C:/Users/Administrator/PycharmProjects/untitled/MySqlToCassandra_Keywords.py";
	public static final String PYTHON_UC_EXECUTOR = "C:/Python27/python";
	public static final String PYTHON_UC_EXECUTOR_NEW = "C:/Anaconda/python";
	
	public static final String PYTHON_UC2_MYSQLTOHDFS  = "C:/Users/Administrator/PycharmProjects/untitled/MySQlToHDFS_UC2.py";
	public static final String PENTAHO_UC2_FILE = "C:/Users/Public/Demo/Usecase2/Pentaho/Usecase2.kjb";
	public static final String PYTHON_UC2_MYSQLTOMONGODB_NEGATIVE_OFFERS  = "C:/Users/Administrator/PycharmProjects/untitled/MySqlToMongoDB_Neg_Offers.py";
	public static final String PYTHON_UC2_MYSQLTOMONGODB_POSITIVE_OFFERS  = "C:/Users/Administrator/PycharmProjects/untitled/MySqlToMongoDB_Pos_Offers.py";
	
	public static final String PENTAHO_UC3_EFILE = "C:/Users/Public/Demo/usecase3/EAvroLocal2HDFSDemo.kjb";
	public static final String PENTAHO_UC3_IFILE1 = "C:/Users/Public/Demo/usecase3/2lemetry_CustomerLocation/Customer_Location.kjb";
	public static final String PENTAHO_UC3_IFILE2 = "C:/Users/Public/Demo/usecase3/IUsecase3Demo.kjb";
	public static final String PYTHON_UC3_MYSQLTOMONGODB_OFFERSDATA  = "C:/Users/Administrator/PycharmProjects/untitled/MySqlToMongoDB_Offersdata.py";
	
	public static final String PENTAHO_UC4_FILE1 = "C:/Users/Public/Demo/usecase4/UC4_Integration_job.kjb";
	
	public static final String PENTAHO_UC5_FILE1 = "C:/Users/Public/Demo/usecase5/MongoDB_Demo.kjb";
	public static final String PENTAHO_UC5_FILE11 = "C:/MongoDB_Demo.kjb";
	public static final String PENTAHO_UC5_FILE2 = "C:/Users/Public/Demo/usecase5/NOAA_Demo.kjb";
	public static final String PENTAHO_UC5_FILE3 = "C:/Users/Public/Demo/usecase5/Pentaho_Integration_Demo.kjb";
	
	public static final String PENTAHO_UC6_FILE1 = "C:/Users/Public/Demo/Usecase6/Demo/Pentaho/Pentaho_Extraction.kjb";
	public static final String PENTAHO_UC6_FILE2 = "C:/Users/Public/Demo/Usecase6/Demo/Pentaho/Sensor.kjb";
	public static final String PENTAHO_UC6_FILE3 = "C:/Users/Public/Demo/Usecase6/Demo/Pentaho/Integration_job.kjb";
}
