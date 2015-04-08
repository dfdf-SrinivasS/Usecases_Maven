package routines;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;

public class UC2_avro_Tweets_5miles 
{
	static int countfiles = 1;

	public static void main(String[] args)
	{
		System.out.println("Welcome to Avro converter!");
		
			try {
				UC2_avro_Tweets_5miles.readAvroFile("C:/Users/Public/Documents/USECASE2/Talend_input/Tweets_5miles.avro");
				countfiles++;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	

	public static File[] finder(String dirName) 
	{
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".avro");
			}
		});

	}

	@SuppressWarnings("unchecked")
	static void readAvroFile(String strFile) throws IOException 
	{
		File file = new File(strFile);

		DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>();

		DataFileReader<GenericRecord> dataFileReader;
		dataFileReader = new DataFileReader<GenericRecord>(file, reader);

		Schema s = dataFileReader.getSchema();
		//System.out.println(s.toString(true));

		Record record;
		File resultFile = new File("C:/Users/Public/Documents/USECASE2/Talend_output/Tweets_5miles.csv");
if (resultFile.exists()) {
			
	resultFile.delete();
			//res_file.createNewFile();
		}
		// if file doesnt exists, then create it
		if (!resultFile.exists()) 
		{
			resultFile.createNewFile();
		}

		FileWriter fw = new FileWriter(resultFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		while (dataFileReader.hasNext()) 
		{
			record = (Record) dataFileReader.next();
			//System.out.println("Record:"+record);

			String rec = getStrForRec(record);
			//System.out.println("Record rec:"+rec);

			bw.write(rec + "\n");
		}
		bw.flush();
		bw.close();		
	}

	private static String getStrForRec(Record rec)
	{
		String avroValue = "";

		for (int i = 0; i < rec.getSchema().getFields().size(); i++)
			avroValue += rec.get(i) + ";" ;

		return avroValue.substring(0,avroValue.length()-1);
	}
}