package Controller;

import Model.User;
import java.io.Console;
import java.util.logging.ConsoleHandler;



public class UserController{
	User user=new User();
        
        public UserController(){}
       
        public String signUp(String gender,String firstName,String lastName,String address,String birthDate,String email,String password){
            
            String result;
            result=user.signUp(gender,firstName,lastName,address,birthDate,email,password);
            return result;
    }
        
        public String logIn(String email,String pass){
           user=user.logIn(email, pass);
           if(user==null)
               return "invaid Email or Password";
           else
               return user.firstName+" "+user.lastName;
       }
        public String addFreind(String mail){
            String result=user.addFriend(mail);
            return result;
        }
        
        public String checkPrem(){
            if(user.isPrem.equals("0"))
                return"no";
            else
                return "yes";
        }
        public String logOut(){
            user=new User();
            return "Loged out.";
        }
        
         public String acceptRequest(){
           String result=user.acceptRequest();
           return result;
       }
       public void upgradePremuim(){
           user.upgradePremuim();
       }
        
	     
}
