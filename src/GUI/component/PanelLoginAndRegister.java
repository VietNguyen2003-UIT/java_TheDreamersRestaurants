
package GUI.component;

import GUI.model.ModelLogin;
import GUI.model.ModelUser;
import GUI.swing.Button;
import GUI.swing.MyPasswordField;
import GUI.swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;


public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public ModelUser getUser() {
        return user;
    }

    public ModelLogin getDataLogin() {
        return dataLogin;
    }
    
    private ModelUser user;
    private ModelLogin dataLogin;
    public PanelLoginAndRegister(ActionListener eventRegister,ActionListener eventLogin) {
        initComponents();
        initLogin(eventLogin);
        initRegister(eventRegister);
        register.setVisible(true);
        login.setVisible(false);
       
    }
    
    private void initRegister(ActionListener eventRegister){
        register.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label= new JLabel("Tạo Tài Khoản");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(7,164,121));
        register.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/GUI/icon/user (2).png")));
        txtUser.setHint("Tên tài khoản ...");
        register.add(txtUser,"w 60%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/GUI/icon/mail.png")));
        txtEmail.setHint("Email ...");
        register.add(txtEmail,"w 60%");
        MyPasswordField txtPassword = new MyPasswordField();
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/GUI/icon/pass.png")));
        txtPassword.setHint("Mật khẩu ...");
        register.add(txtPassword,"w 60%");
        Button cmd =new Button();
        cmd.setBackground(new Color(7,164,121));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("ĐĂNG KÝ");
        cmd.addActionListener(eventRegister);
        register.add(cmd,"w 40%, h 40");
        cmd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String userName = txtUser.getText().trim();
               String email=txtEmail.getText().trim();
               String password=String.valueOf(txtPassword.getPassword());
               user=new ModelUser(0,userName,email,password);
            }
        });
    }
    
    private void initLogin(ActionListener eventLogin){
        login.setLayout(new MigLayout("wrap","push[center]push","push[]25[]10[]10[]25[]push"));
        JLabel label= new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        label.setFont(new Font("sansserif",1,30));
        label.setForeground(new Color(7,164,121));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/GUI/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail,"w 60%");
        MyPasswordField txtPassword = new MyPasswordField();
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/GUI/icon/pass.png")));
        txtPassword.setHint("Mật khẩu");
        login.add(txtPassword,"w 60%");
        JButton cmdForget= new JButton("Quên mật khẩu của bạn ?");
        cmdForget.setForeground(new Color(100,100,100));
        cmdForget.setFont(new Font("sansserif",1,12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd =new Button();
        cmd.setBackground(new Color(7,164,121));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("ĐĂNG NHẬP");
        login.add(cmd,"w 40%, h 40");
        cmd.addActionListener(eventLogin);
        cmd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               String email=txtEmail.getText().trim();
               String password=String.valueOf(txtPassword.getPassword());
               dataLogin=new ModelLogin(email,password);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card2");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card3");
    }// </editor-fold>//GEN-END:initComponents
    
    public void showRegister(boolean show){
        if(show){
            register.setVisible(true);
            login.setVisible(false);
        }else{
           register.setVisible(false);
            login.setVisible(true); 
        }
    }   


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
