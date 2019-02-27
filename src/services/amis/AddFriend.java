package services.amis;
import services.ErrorJSON;

public class AddFriend {
	
public static JSONObject addFriend(String key, int idB){
		
		try{
			if(!UserTools.keyVerified(key))
				return ErrorJSON.serviceRefused("Non connecté!!!", 4);
			int idA=UserTools.idKey(key);
			String logA=UserTools.loginId(idA);
			String logB=UserTools.loginId(idB);
		
			if(logA==null||logB==null)
				return ServiceTools.serviceRefused("Paramètres manquants", -3);
			
			if(logA.equals(logB))
				return ServiceTools.serviceRefused("Amis similaires", 5);
		
			if(UserTools.userExists(logA)){
				if(UserTools.userExists(logB)){
					if(UserTools.isFriend(idA, idB)){
						return ServiceTools.serviceAccepted();
					}else{
						bd.UserTools.addFriend(idA, idB);
						return ServiceTools.serviceAccepted();
					}
				}else{
					System.out.println("Ami imaginaire");
					return ServiceTools.serviceRefused("Ami imaginaire",6);
					
				}
			}else{
				System.out.println("Vous n'existez pas");
				return ServiceTools.serviceRefused("Vous n'existez pas", 7);
			}
		
		}catch (SQLException e){
			return ServiceTools.serviceRefused("Erreur SQL", -1);
		} catch (userNotFoundException e) {
			//ne doit pas arriver
			return ServiceTools.serviceRefused("", 3);
		} catch (KeyNotFoundException e) {
			//ne doit pas arriver
			return ServiceTools.serviceRefused("", -2);
		}
	}

}
