
package GUI.main;

import GUI.component.Header;
import GUI.component.Menu;
import GUI.event.EventMenuSelected;
import GUI.event.EventShowPopupMenu;
import GUI.form.Form1;
import GUI.form.Form_Home;
import GUI.form.MainForm;
import GUI.model.ModelUser;
import GUI.swing.MenuItem;
import GUI.swing.PopupMenu;
import GUI.swing.icon.IconFontSwing;
import GUI.swing.icon.GoogleMaterialDesignIcons;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private final ModelUser user;
    public Main(ModelUser user) {
        this.user=user;
        initComponents();
        init();
    }
    public void init(){
        layout = new MigLayout("fill","0[]0[100%, fill]0","0[fill, top]0");
        bg.setLayout(layout);
        menu=new Menu();
        header= new Header();
        main= new MainForm();
        menu.addEvent(new EventMenuSelected(){
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index: "+menuIndex+" subMenuIndex: "+subMenuIndex);
                if(menuIndex==0){
                    if(subMenuIndex==0){
                        main.showForm(new Form_Home());
                    }else if(subMenuIndex==1){
                        main.showForm(new Form1());
                    }
                }
            }
        });
        menu.addEvenShowPopup(new EventShowPopupMenu(){
            @Override
            public void showPopup(Component com) {
                MenuItem item=(MenuItem)com;
                PopupMenu popup= new PopupMenu(Main.this,item.getIndex(),item.getEventSelected(),item.getMenu().getSubMenu());
                int x=Main.this.getX()+52;
                int y=Main.this.getY()+com.getY()+86;
                popup.setLocation(x,y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu,"w 260!, spany 2"); //Span Y 2cell
        bg.add(header,"h 50!, wrap");
        bg.add(main,"w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double width;
                if(menu.isShowMenu()){
                    width=60+(200*(1f-fraction));
                }else{
                    width=60+(200*fraction);
                }
                layout.setComponentConstraints(menu,"w "+width+"!, spany2");
                menu.revalidate();
            }
            
            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }
            
        
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    animator.start();
                }
                menu.setEnableMenu(false);
                if(menu.isShowMenu()){
                    //hide menu
                    menu.hideallMenu();
                }
            }
        
        });
        //init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //Start with this form
         main.showForm(new Form_Home());
         header.setUserName(user.getUserName());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1321, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(ModelUser user) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables

    
}
