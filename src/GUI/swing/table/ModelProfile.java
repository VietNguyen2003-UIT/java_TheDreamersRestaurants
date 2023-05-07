
package GUI.swing.table;

import javax.swing.Icon;


public class ModelProfile {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelProfile() {
    }

    public ModelProfile(Icon icon, String name) {
        this.icon = icon;
        this.name = name;
    }
    
    private Icon icon;
    private String name;
}
