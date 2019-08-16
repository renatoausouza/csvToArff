package csvToArff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Renato Souza
 * It converts a csv file to Arff file when all attributes are numbers.
 */

public class Converter {

    public static void main(String[] args) throws IOException {
    	
		String fileFolder = args[0];
		String fileName = args[1];
		ArrayList<Integer[]> csvFile = new ArrayList<Integer[]>();
		
		File file = new File(fileFolder+fileName);
		  
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String st;
		while ((st = br.readLine()) != null){
			Integer[] tempVector = new Integer[st.split(",").length];
			String[] temp = st.split(",");
			for(int i = 0; i < tempVector.length; i++) {
				tempVector[i] = Integer.parseInt(temp[i]);
			}
			
			csvFile.add(tempVector);
		}
		
		//Write the ARFF file
		BufferedWriter out = new BufferedWriter(new FileWriter(fileFolder + fileName + ".arff"));
		
		try {
			out.write("@RELATION " + fileName + "\n");
			
			for(int i = 0; i< csvFile.get(0).length;i++){
				out.write("@ATTRIBUTE v" + i + " NUMERIC\n");
			}
			//Write the body
			out.write("@DATA\n");
			for(int i = 0; i < csvFile.size(); i++){
				String tempData = "";
				for(int j = 0; j < csvFile.get(i).length; j++){
					if(j != csvFile.get(i).length-1){
						tempData += csvFile.get(i)[j] + ",";
					} else {
						tempData += csvFile.get(i)[j];
					}
				}
				out.write(tempData + "\n");
			}
		} catch (Exception e) {
			
		} finally {
			br.close();
			out.close();
		}
    }
}