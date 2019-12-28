package Model;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {

    public int id;
    public String gender;
    public String firstName;
    public String lastName;
    public String addreess;
    public String birthDate;
    public String email;
    public String password;
    public String isPrem="";
    public static DBConnection db = new DBConnection();
    Scanner input = new Scanner(System.in);

    public User() {
    }

    public User(
            int id,
            String gender,
            String firstName,
            String lastName,
            String addreess,
            String birthDate,
            String email,
            String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.addreess = addreess;
        this.birthDate = birthDate;
    }

    public User Search(String userMail) {
        User user = new User();
        try {
            db.Connectiontomysql();
            db.statement = (PreparedStatement) db.connection.prepareStatement("SELECT * FROM user where email=?");
            db.statement.setString(1, userMail);
            ResultSet result = db.statement.executeQuery();
            if (result.next()) {
                user.id = result.getInt("ID");
                user.gender = result.getString("gender");
                user.firstName = result.getString("firstName");
                user.lastName = result.getString("lastName");
                user.addreess = result.getString("address");
                user.email = result.getString("email");
                user.birthDate = result.getString("birthDate");
                user.password = result.getString("password");
                user.isPrem=result.getString("isPremium");
                db.statement.close();
                db.connection.close();
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User logIn(String emai, String passwor) {
        try {
            db.Connectiontomysql();
            db.statement = (PreparedStatement) db.connection.prepareStatement("SELECT * FROM user where email=?");
            db.statement.setString(1, emai);
            ResultSet result = db.statement.executeQuery();
            if (result.next()) {
                String pass = result.getString("password");
                if (passwor.equals(pass)) {
                    this.id = result.getInt("ID");
                    this.gender = result.getString("gender");
                    this.firstName = result.getString("firstName");
                    this.lastName = result.getString("lastName");
                    this.addreess = result.getString("address");
                    this.email = result.getString("email");
                    this.birthDate = result.getString("birthDate");
                    this.password = result.getString("password");
                    this.isPrem=result.getString("isPremium");
                    db.statement.close();
                    db.connection.close();
                    return this;
                } else {
                    db.statement.close();
                    db.connection.close();
                    return null;
                }
            } else {
                db.statement.close();
                db.connection.close();
                return null;
            }
        } catch (SQLException ex) {
            return null;
        }
    }

    public String signUp(String gender, String firstName, String lastName, String address, String birthDate, String email, String password) {

        try {
            db.Connectiontomysql();
            // String sql = ;
            db.statement = (PreparedStatement) db.connection.prepareStatement("SELECT * FROM user where email=?");
            db.statement.setString(1, email);
            ResultSet result = db.statement.executeQuery();
            if (result.next()) {
                return "Found";
            }
            System.out.println(firstName);

            db.statement = (PreparedStatement) db.connection.prepareStatement("insert into user (gender,firstName,lastName,address,birthDate,email,password,isPremium) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            db.statement.setString(1, gender);
            db.statement.setString(2, firstName);
            db.statement.setString(3, lastName);
            db.statement.setString(4, address);
            db.statement.setString(5, birthDate);
            db.statement.setString(6, email);
            db.statement.setString(7, password);
            db.statement.setInt(8, 0);
            db.statement.executeUpdate();
            db.statement.close();
            db.connection.close();
            return "Ok";
        } catch (SQLException ex) {
            return "Error";
        }
    }

    public String addFriend(String userMail) {
        User user = new User();
        user = Search(userMail);
        if (user != null) {
            try {
                db.Connectiontomysql();
                db.statement = (PreparedStatement) db.connection.prepareStatement("SELECT * FROM friendrequest where sentID=?");
                db.statement.setInt(1, this.id);
                ResultSet result = db.statement.executeQuery();
                if (result.next()) {
                    return "Sent Befor";
                }
                db.statement = (PreparedStatement) db.connection.prepareStatement("SELECT * FROM friendrequest where reseveID=?");
                db.statement.setInt(1, this.id);
                result = db.statement.executeQuery();
                if (result.next()) {
                    return "He/She Sent Me Befor";
                }
                db.statement = (PreparedStatement) db.connection.prepareStatement("SELECT * FROM friendlist WHERE firstID=? and secondID=?");
                db.statement.setInt(1, this.id);
                db.statement.setInt(2, user.id);
                result = db.statement.executeQuery();
                if (result.next()) {
                    return "alredy Freind";
                }
                db.statement = (PreparedStatement) db.connection.prepareStatement("INSERT INTO `friendrequest`(`sentID`, `reseveID`) VALUES (?,?)");
                db.statement.setInt(1, this.id);
                db.statement.setInt(2, user.id);
                db.statement.executeUpdate();
                db.statement.close();
                db.connection.close();
                return "Request Sent Successfuly";
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "not Found";
    }

    public String acceptRequest() {
        ArrayList<ArrayList<String>> request = new ArrayList();
        try {
            db.Connectiontomysql();
            db.statement = (PreparedStatement) db.connection.prepareStatement("SELECT sentID FROM friendrequest where reseveID=?");
            db.statement.setInt(1, this.id);
            ResultSet result = db.statement.executeQuery();
            while (result.next()) {
                try {
                    String id = result.getString("sentID");
                    db.statement = (PreparedStatement) db.connection.prepareStatement("SELECT firstName,lastName FROM user where ID=?");
                    db.statement.setString(1, id);
                    ResultSet result2 = db.statement.executeQuery();
                    while (result2.next()) {
                        ArrayList<String> name = new ArrayList<>();
                        name.add(result2.getString("firstName"));
                        name.add(result2.getString("lastName"));
                        name.add(id);
                        request.add(name);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            int choice;
            do {
                if (request.size() == 0) {
                    return "No Requests";
                }
                System.out.println("Requests List:");
                for (int i = 0; i < request.size(); i++) {
                    System.out.println((i + 1) + "-" + request.get(i).get(0) + " " + request.get(i).get(1));
                }
                System.out.print("Choose Friend to Accept or any Key to Exit");
                choice = input.nextInt();
                if (choice < 0 || choice > request.size()) {
                    return "";
                }
                db.statement = (PreparedStatement) db.connection.prepareStatement("INSERT INTO `friendlist`(firstID,secondID) VALUES (?,?)");
                db.statement.setInt(1, this.id);
                db.statement.setString(2, request.get(choice - 1).get(2));
                db.statement.executeUpdate();
                db.statement = (PreparedStatement) db.connection.prepareStatement("INSERT INTO `friendlist`(firstID,secondID) VALUES (?,?)");
                db.statement.setInt(2, this.id);
                db.statement.setString(1, request.get(choice - 1).get(2));
                db.statement.executeUpdate();
                db.statement = (PreparedStatement) db.connection.prepareStatement("DELETE  FROM `friendrequest` WHERE reseveID=? and sentID=?");
                db.statement.setInt(1, this.id);
                db.statement.setString(2, request.get(choice - 1).get(2));
                db.statement.executeUpdate();
                db.statement.close();
                db.connection.close();
                request.remove(choice - 1);
                System.out.println("Request Accepted.");
            } while (choice > 0 && choice <= request.size());

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return "";
    }

    public String CreditCard() {
        System.out.println("Welcome to Credit Card API.. to Confirm Paying 99$ press Y, to cancel press N.");
        String choice = input.next();
        if (choice.equals("Y") || choice.equals("y")) {
            System.out.println("Enter Credit Number:");
            String num = input.next();
            System.out.println("Enter Credit password:");
            num = input.next();
            System.out.println("Successful Operation");
            return "Ok";
        }
        return "No";
    }

    public String payPal() {
        System.out.println("Welcome to PayPal API.. to Confirm Paying 99$ press Y, to cancel press N.");
        String choice = input.next();
        if (choice.equals("Y") || choice.equals("y")) {
            System.out.println("Enter PayPal Number:");
            String num = input.next();
            System.out.println("Enter PayPal password:");
            num = input.next();
            System.out.println("Successful Operation");
            return "Ok";
        }
        return "No";
    }

    public void upgradePremuim() {
        System.out.println("to upgrading you Should pay 99$/Year.. to Confirm Upgrading and pay press Y, to cancel press N.");
        String choice = input.next();
        if (choice.equals("Y") || choice.equals("y")) {
            System.out.println("Choose Paying Way\n1-Credit Card.\n2-PayPal.");
            int ch = input.nextInt();
            String result = null;
            if (ch == 1) {
                result = CreditCard();
            } else if (ch == 2) {
                result = CreditCard();
            }
            if (result.equals("Ok")) {
                try {
                    db.Connectiontomysql();
                    db.statement = (PreparedStatement) db.connection.prepareStatement("update user set isPremium=? where ID=?");
                    db.statement.setInt(1, 1);
                    db.statement.setInt(2, this.id);
                    db.statement.executeUpdate();
                    db.statement.close();
                    db.connection.close();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        }
        this.isPrem="1";
    }
}
