package service;

import org.json.*;

public class ErrorJSON {
	
	public static JSONObject serviceRefused(String message, int codeErreur) {
		 JSONObject json = new JSONObject();
		 String errorType = "";
		 if(codeErreur==-1) {
			  errorType = "Erreur Arguments";
		 }else if(codeErreur==100) {
			  errorType = "Erreur JSON";
		 }else if(codeErreur==1000){
			  errorType = "Erreur SQL";
		 }else if(codeErreur==10000) {
			  errorType = "Erreur JAVA";
		 }else if(codeErreur==100000) {
			  errorType = "Erreur JSON";
		 }
		 try {
			json.put("error type", errorType);
			json.put(message,codeErreur);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		 return json;
	}
	
	public static JSONObject serviceAccepted(String message, int codeErreur) throws JSONException {
		 JSONObject json = new JSONObject();
		 return json.put(message,codeErreur);
	}

}
