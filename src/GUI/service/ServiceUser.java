
package GUI.service;

import GUI.connection.DatabaseConnection;
import GUI.model.ModelLogin;
import GUI.model.ModelUser;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Random;


public class ServiceUser {
    
    private final Connection con;
            
    public ServiceUser() {
        con=DatabaseConnection.getInstance().getConnection();
    }
    public ModelUser login(ModelLogin login) throws SQLException{
        ModelUser user=null;
        String sql = "SELECT * FROM UserHT WHERE Email=? AND Password=? AND Status='Verified' FETCH FIRST 1 ROWS ONLY";
        PreparedStatement p=con.prepareStatement(sql);
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r= p.executeQuery();
        if(r.next()){
            int UserID=r.getInt("UserID");
            String UserName=r.getString("UserName");
            String email=r.getString("Email");
            user=new ModelUser(UserID,UserName,email,"");
        }
        r.close();
        p.close();
        return user;
    }
            
    public void insertUser(ModelUser user)throws SQLException{
        String sql = "INSERT INTO UserHT (UserName, Email, Password, VerifyCode) VALUES (?, ?, ?, ?)";
        PreparedStatement p=con.prepareStatement(sql);
        String code=generateVerifiyCode();
        
        p.setString(1, user.getUserName());
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(4, code);
        p.execute();
        PreparedStatement p1=con.prepareStatement("SELECT MAX(UserID) as UserID FROM UserHT");
        ResultSet r= p1.executeQuery();
        r.next();
        int userID=r.getInt("UserID");
        r.close();
        p.close();
        user.setUserID(userID);
        user.setVerifyCode(code);
    }
    public String generateVerifiyCode()throws SQLException{
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }
    private boolean checkDuplicateCode(String code) throws SQLException{
        boolean duplicate=false;
        String sql="SELECT * FROM UserHT WHERE VerifyCode=? FETCH FIRST 1 ROWS ONLY";
        //String sql="SELECT UserID FROM UserHT WHERE VerifyCode=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, code);
        ResultSet r=p.executeQuery();
        if(r.next()){
            duplicate=true;
        }
        r.close();
        p.close();
        return duplicate;
    }
    public boolean checkDuplicateEmail(String email) throws SQLException{
        boolean duplicate=false;
        String sql="SELECT * FROM UserHT WHERE Email=? AND Status='Verified' FETCH FIRST 1 ROWS ONLY";
        PreparedStatement p = con.prepareStatement(sql);
        p.setString(1, email);
        ResultSet r=p.executeQuery();
        if(r.next()){
            duplicate=true;
        }
        r.close();
        p.close();
        return duplicate;
    }
    public void doneVerify(int userID) throws SQLException{
        String sql="UPDATE UserHT SET VerifyCode='', Status='Verified' WHERE UserID=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, userID);
        p.execute();
        p.close();
    }
    public boolean verifyCodeWithUser(int userID,String code) throws SQLException{
        boolean verify=false;
        String sql="SELECT COUNT(UserID) as CountID FROM UserHT WHERE UserID=? AND VerifyCode=?";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, userID);
        p.setString(2,code);
        ResultSet r=p.executeQuery();
        if(r.next()){
            if(r.getInt("CountID")>0){
                verify=true;
            }
        }
        r.close();
        p.close();
        return verify;
    }
}
