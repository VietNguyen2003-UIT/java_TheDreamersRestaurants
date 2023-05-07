
package GUI.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;


public class Message extends javax.swing.JPanel {

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
    
    
    private MessageType messageType=MessageType.SUCCESS;
    private boolean show;
    public Message() {
        initComponents();
        setOpaque(false);
        setVisible(false);
    }

    public void showMessage(MessageType messageType,String message){
        this.messageType=messageType;
        lbMessage.setText(message);
        if(messageType==MessageType.SUCCESS){
            lbMessage.setIcon(new ImageIcon(getClass().getResource("/GUI/icon/success.png")));
        }else{
            lbMessage.setIcon(new ImageIcon(getClass().getResource("/GUI/icon/error.png")));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbMessage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        lbMessage.setBackground(new java.awt.Color(204, 204, 204));
        lbMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(248, 248, 248));
        lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage.setText("Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    public static enum MessageType{
        SUCCESS,ERROR
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2= (Graphics2D)g;
        if(messageType==MessageType.SUCCESS){
            g2.setColor(new Color(15,174,37));
        }else{
            g2.setColor(new Color(240,52,53));
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.9f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(new Color(245,245,245));
        g2.drawRect(0, 0, getWidth()-1, getHeight()-1);
        super.paintComponent(g); 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbMessage;
    // End of variables declaration//GEN-END:variables
}
