
package GUI.swing.table;

import GUI.model.ModelStudent;


public interface EventAction {
    public void delete(ModelStudent student);
    
    public void update(ModelStudent student);
}
