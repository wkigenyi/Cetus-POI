/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.poi;

import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.nodes.Sheet.Set;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Wilfred
 */
public class NodeImportedTransaction extends AbstractNode{
    
    public NodeImportedTransaction(ImportedTransaction t){
        super(Children.LEAF,Lookups.singleton(t));
        setDisplayName(t.getEmp().getEmpCode());
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        final ImportedTransaction t = getLookup().lookup(ImportedTransaction.class);
        
        Property name = new PropertySupport("name", String.class, "NAME", "NAME", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return t.getEmp().getSurName()+" "+t.getEmp().getOtherNames();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property amount = new PropertySupport("amount", String.class, "Amount", "Amount", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return t.getAmount();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        set.put(name);
        
        set.put(amount);
        
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
