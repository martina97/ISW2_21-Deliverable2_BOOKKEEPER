package deliverable;

import java.io.FileWriter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import entities.DBEntriesM2;
import entities.JavaFile;
import entities.M2Entries;
import entities.Ticket;

import java.util.logging.Level;


public class CSVWriter {
	
	
	
	static Logger logger = Logger.getLogger(CSVWriter.class.getName());

	
	public static void writeCsvBugg(List<Release> releasesList) {
		try (
				   FileWriter fileWriter = new FileWriter("D:\\Programmi\\Eclipse\\eclipse-workspace\\ISW2_21-Deliverable2_BOOKKEEPER\\csv\\CSV FINALE13.csv")) {
				   
				   fileWriter.append("RELEASE ; FILENAME ; NR ; NAUTH ; BUGGYNESS \n");
				   for (Release release : releasesList) {
					   System.out.println("RELEASE CSV == " + release.getIndex());
					   for (JavaFile file : release.getFileList()) {
						   fileWriter.append(release.getIndex().toString());	//release
						   fileWriter.append(";");
						   fileWriter.append(file.getName());
						   fileWriter.append(";");
						   fileWriter.append(file.getNr().toString());
						   fileWriter.append(";");
						   fileWriter.append(file.getNAuth().toString());
						   fileWriter.append(";");
						   fileWriter.append(file.getBugg());
						   fileWriter.append("\n");
					   }
				   } 
				  } catch (Exception ex) {
					  logger.log(Level.SEVERE,"Error in csv writer");
					  ex.printStackTrace();
				  
				  }
				 }	
				
	
	
	public static void writeCsvBugg2(List<Release> releasesList, String nameProject) {
		try (
				   FileWriter fileWriter = new FileWriter("D:\\Programmi\\Eclipse\\eclipse-workspace\\ISW2_21-Deliverable2_BOOKKEEPER\\csv\\FINITO\\CSV FINALE" + nameProject + ".csv")) {

				   fileWriter.append("RELEASE;FILENAME;LOC;LOC_added;MAX_LOC_Added;AVG_LOC_Added;Churn;MAX_Churn;AVG_Churn;NR;NAUTH;ChgSetSize;MAX_ChgSet;AVG_ChgSet;BUGGYNESS\n");
				   for (Release release : releasesList) {
					   //System.out.println("RELEASE CSV == " + release.getIndex());
					   for (JavaFile file : release.getFileList()) {
						   fileWriter.append(release.getIndex().toString());
						   fileWriter.append(";");
						   fileWriter.append(file.getName());
						   fileWriter.append(";");
						   fileWriter.append(file.getSize().toString());
						   fileWriter.append(";");
						   fileWriter.append(file.getLOCadded().toString());
						   fileWriter.append(";");
						   
						   if(file.getLOCadded().equals(0)) {
							   fileWriter.append("0");
							   fileWriter.append(";");
							   fileWriter.append("0");
						   }
						   else {
							   int maxLocAdded = Collections.max((file.getLocAddedList()));
							   fileWriter.append(String.valueOf(maxLocAdded));
							   fileWriter.append(";");
							   double avgLocAdded = Utils.calculateAverage(file.getLocAddedList());
							   //fileWriter.append(String.valueOf(avgChgSet));
							   fileWriter.append(String.format("%.2f",avgLocAdded));
						   }
						   fileWriter.append(";");
						   fileWriter.append(file.getChurn().toString());
						   fileWriter.append(";");
						   if(file.getChurn().equals(0)) {
							   fileWriter.append("0");
							   fileWriter.append(";");
							   fileWriter.append("0");
						   }
						   else {
							   int maxChurn = Collections.max((file.getChurnList()));
							   fileWriter.append(String.valueOf(maxChurn));
							   fileWriter.append(";");
							   double avgChurn = Utils.calculateAverage(file.getChurnList());
							   //fileWriter.append(String.valueOf(avgChgSet));
							   fileWriter.append(String.format("%.2f",avgChurn));
						   }
						   fileWriter.append(";");

						   fileWriter.append(file.getNr().toString());
						   fileWriter.append(";");
						   int size = file.getNAuth().size();
						   fileWriter.append(String.valueOf(size));
						   fileWriter.append(";");
						   fileWriter.append(file.getChgSetSize().toString());
						   fileWriter.append(";");
						   if(file.getChgSetSize().equals(0)) {
							   fileWriter.append("0");
							   fileWriter.append(";");
							   fileWriter.append("0");
						   }
						   else {
							   int maxChgSet = Collections.max((file.getChgSetSizeList()));
							   fileWriter.append(String.valueOf(maxChgSet));
							   fileWriter.append(";");
							   double avgChgSet = Utils.calculateAverage(file.getChgSetSizeList());
							   //fileWriter.append(String.valueOf(avgChgSet));
							   fileWriter.append(String.format("%.2f",avgChgSet));
							   
						   }
						   fileWriter.append(";");
						   fileWriter.append(file.getBugg());
						   fileWriter.append("\n");
					   }
				   } 
				  } catch (Exception ex) {
					  logger.log(Level.SEVERE,"Error in csv writer");
					  ex.printStackTrace();
				  }
				 }	
	
	public static void writeCsvReleases(List<Ticket> ticketList) {
	 
	  try (
	   FileWriter fileWriter = new FileWriter("D:\\Programmi\\Eclipse\\eclipse-workspace\\ISW2_21-Deliverable2_BOOKKEEPER\\csv\\DOPO PROP VECCHIO.csv")) {
	   
	   fileWriter.append("TICKET ID ; IV ; OV ; FV ; AV \n");
	   for (Ticket ticket : ticketList) {
		   fileWriter.append(ticket.getID());
		   fileWriter.append(";");
		   fileWriter.append(ticket.getIV().toString());
		   fileWriter.append(";");
		   fileWriter.append(ticket.getOV().toString());
		   fileWriter.append(";");
		   fileWriter.append(ticket.getFV().toString());
		   fileWriter.append(";");
		   fileWriter.append(ticket.getAV().toString());
		   fileWriter.append("\n");
	   }
	   
	   
	   
	  } catch (Exception ex) {
		  logger.log(Level.SEVERE,"Error in csv writer");
		  ex.printStackTrace();
	  
	  	}
	 }	
	
	
	public static void writeCsvMilestone2(List<DBEntriesM2> dBentriesList) {
		try (
		   FileWriter fileWriter = new FileWriter("D:\\Programmi\\Eclipse\\eclipse-workspace\\ISW2_21-Deliverable2_BOOKKEEPER\\csv\\BookkeeperCeciliaWeka.csv")) {
		   
		   fileWriter.append("Dataset;#TrainingRelease;Classifier;Precision;Recall;AUC;Kappa\n");
		   

		   for(DBEntriesM2 entry : dBentriesList) {
			   
			   Map<String, List<Double>> classifierMap = entry.getClassifier();

			   for (Map.Entry<String,List<Double>> mapEntry : classifierMap.entrySet()) {
				    String classifierName = mapEntry.getKey();
				    List<Double> value = mapEntry.getValue();
				    fileWriter.append(entry.getDatasetName());
					fileWriter.append(";");
					fileWriter.append(entry.getNumTrainingRelease().toString());
					fileWriter.append(";");
					fileWriter.append(classifierName);
					fileWriter.append(";");
					for(int i = 0;i<value.size();i++) {
						fileWriter.append(value.get(i).toString());
						fileWriter.append(";");
					}
					fileWriter.append("\n");

				  }

			   
		   }
		   
		   
		  } catch (Exception ex) {
			  logger.log(Level.SEVERE,"Error in csv writer");
			  ex.printStackTrace();
		  
		  	}	 
	}
	
	public static void writeCsvMilestone3(List<M2Entries> dBentriesList) {
		try (
		   FileWriter fileWriter = new FileWriter("D:\\Programmi\\Eclipse\\eclipse-workspace\\ISW2_21-Deliverable2_BOOKKEEPER\\csv\\weka\\SyncopeWeka.csv")) {
		   
		   fileWriter.append("Dataset;#TrainingRelease;%training;%Defective in training;%Defective in testing;Classifier;"
		   		+ "Balancing;Feature Selection;Sensitivity;TP;FP;TN;FN;Precision;Recall;AUC;Kappa\n");
		   

		   for(M2Entries entry : dBentriesList) {
			  
			    fileWriter.append(entry.getDatasetName());
				fileWriter.append(";");
				fileWriter.append(entry.getNumTrainingRelease().toString());
				fileWriter.append(";");
				fileWriter.append(Utils.doubleTransform(entry.getTrainingPerc()));
				fileWriter.append(";");
				fileWriter.append(Utils.doubleTransform(entry.getDefectPercTrain()));
				fileWriter.append(";");
				fileWriter.append(Utils.doubleTransform(entry.getDefectPercTest()));
				fileWriter.append(";");
				fileWriter.append(entry.getClassifierName());
				fileWriter.append(";");
				fileWriter.append(entry.getFeatureSelection());
				fileWriter.append(";");
				fileWriter.append(entry.getBalancing());
				fileWriter.append(";");
				fileWriter.append(entry.getSensitivity());
				fileWriter.append(";");
				fileWriter.append(entry.getTP().toString());
				fileWriter.append(";");
				fileWriter.append(entry.getFP().toString());
				fileWriter.append(";");
				fileWriter.append(entry.getTN().toString());
				fileWriter.append(";");
				fileWriter.append(entry.getFN().toString());
				fileWriter.append(";");
				fileWriter.append(Utils.doubleTransform(entry.getPrecision()));
				fileWriter.append(";");
				fileWriter.append(Utils.doubleTransform(entry.getRecall()));
				fileWriter.append(";");
				fileWriter.append(Utils.doubleTransform(entry.getAuc()));
				fileWriter.append(";");
				fileWriter.append(Utils.doubleTransform(entry.getKappa()));
				fileWriter.append("\n");


			   
		   }
		   
		   
		  } catch (Exception ex) {
			  logger.log(Level.SEVERE,"Error in csv writer");
			  ex.printStackTrace();
		  
		  	}	 
	}
	
	
	public static void main(String[] args) {
		 // main method
	}
		 
		 
}
