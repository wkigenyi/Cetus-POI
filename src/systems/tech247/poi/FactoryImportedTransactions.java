/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.poi;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author Wilfred
 */
public class FactoryImportedTransactions extends ChildFactory<ImportedTransaction>{
    
    List list;
    
    public FactoryImportedTransactions(List list){
        this.list = list;
    }

    @Override
    protected boolean createKeys(List<ImportedTransaction> toPopulate) {
        toPopulate.addAll(list);
        return true;
    }

    @Override
    protected Node createNodeForKey(ImportedTransaction key) {
        Node node = null;
        try{
            
            node = new NodeImportedTransaction(key);
            
        }catch(Exception ex){
            
        }
        return node; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
