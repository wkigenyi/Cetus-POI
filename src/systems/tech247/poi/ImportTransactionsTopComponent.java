/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.poi;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.ActionID;
import org.openide.awt.StatusDisplayer;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.OutlineView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Currencies;
import systems.tech247.hr.Employees;
import systems.tech247.hr.TblPayrollCode;
import systems.tech247.hr.TblPeriods;
import systems.tech247.util.CapUploadable;
import systems.tech247.util.NotifyUtil;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.poi//ImportTransactions//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ImportTransactionsTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "systems.tech247.poi.ImportTransactionsTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_ImportTransactionsAction",
//        preferredID = "ImportTransactionsTopComponent"
//)
@Messages({
    "CTL_ImportTransactionsAction=Import Transactions",
    "CTL_ImportTransactionsTopComponent=Import Transactions ",
    "HINT_ImportTransactionsTopComponent="
})
public final class ImportTransactionsTopComponent extends TopComponent implements ExplorerManager.Provider, LookupListener{
    
    TopComponent tc = WindowManager.getDefault().findTopComponent("PayrollCodesTopComponent");
    Lookup.Result<TblPayrollCode> rslt = tc.getLookup().lookupResult(TblPayrollCode.class);
    
    TblPayrollCode code = null;
    
    ExplorerManager em = new ExplorerManager();
    final List<ImportedTransaction> listSuccess = new ArrayList();
    final List listError = new ArrayList();
    InstanceContent content = new InstanceContent();
    AbstractLookup lkp = new AbstractLookup(content);
    CapUploadable upload;
    File file;
    int startingAt = 6;
    public ImportTransactionsTopComponent() {
        initComponents();
        setName(Bundle.CTL_ImportTransactionsTopComponent());
        setToolTipText(Bundle.HINT_ImportTransactionsTopComponent());
        upload = () -> {
            uploadData();
        };
        //content.add(upload);
        associateLookup(lkp);
        
        jbProcess.setVisible(false);
        //jlStartingRowNumber.setVisible(false);
        // jSpinFieldHeaderRow.setVisible(false);
                
        jpView.setLayout(new BorderLayout());
        OutlineView ov = new OutlineView("Payroll #");
        ov.getOutline().setRootVisible(false);
        jpView.add(ov);
        //ov.addPropertyColumn("empCode", "Payroll Code");
        ov.addPropertyColumn("name", "Name");
        ov.addPropertyColumn("amount", "Amount");
        
        
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpView = new javax.swing.JPanel();
        jbSelectFile = new javax.swing.JButton();
        jlSelectedFile = new javax.swing.JLabel();
        jbProcess = new javax.swing.JButton();
        jSpinFieldHeaderRow = new com.toedter.components.JSpinField();
        jlStartingRowNumber = new javax.swing.JLabel();

        jpView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpViewLayout = new javax.swing.GroupLayout(jpView);
        jpView.setLayout(jpViewLayout);
        jpViewLayout.setHorizontalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpViewLayout.setVerticalGroup(
            jpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(jbSelectFile, org.openide.util.NbBundle.getMessage(ImportTransactionsTopComponent.class, "ImportTransactionsTopComponent.jbSelectFile.text")); // NOI18N
        jbSelectFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSelectFileActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jlSelectedFile, org.openide.util.NbBundle.getMessage(ImportTransactionsTopComponent.class, "ImportTransactionsTopComponent.jlSelectedFile.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jbProcess, org.openide.util.NbBundle.getMessage(ImportTransactionsTopComponent.class, "ImportTransactionsTopComponent.jbProcess.text")); // NOI18N
        jbProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbProcessActionPerformed(evt);
            }
        });

        jSpinFieldHeaderRow.setEnabled(false);
        jSpinFieldHeaderRow.setValue(4);

        org.openide.awt.Mnemonics.setLocalizedText(jlStartingRowNumber, org.openide.util.NbBundle.getMessage(ImportTransactionsTopComponent.class, "ImportTransactionsTopComponent.jlStartingRowNumber.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbSelectFile)
                        .addGap(18, 18, 18)
                        .addComponent(jlSelectedFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(jlStartingRowNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinFieldHeaderRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbSelectFile)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlSelectedFile)
                        .addComponent(jlStartingRowNumber))
                    .addComponent(jbProcess)
                    .addComponent(jSpinFieldHeaderRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSelectFileActionPerformed
        JFileChooser jfc = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("Transactions File", "xls","xlsx");
                jfc.setFileFilter(filter);
                
                
                int returnValue = jfc.showOpenDialog(WindowManager.getDefault().getMainWindow());
                String ext = "";
                
                if(returnValue == JFileChooser.APPROVE_OPTION){
                   file = jfc.getSelectedFile();
                    ext = file.getName().replaceAll("^.*\\.([^.]+)$", "$1");
                    jlSelectedFile.setText(file.getAbsolutePath());
                    //FileInputStream fips = new FileInputStream(file);
                    jlStartingRowNumber.setVisible(true);
                    jbProcess.setVisible(true);
                    jSpinFieldHeaderRow.setVisible(true);
                        
                }
    }//GEN-LAST:event_jbSelectFileActionPerformed

    private void jbProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbProcessActionPerformed
        jbProcess.setEnabled(false);
        listSuccess.clear();
        listError.clear();
        em.setRootContext(new AbstractNode(Children.LEAF));
        
        ProgressHandle ph = ProgressHandleFactory.createHandle("Parsing/Checking The File");
        RequestProcessor.getDefault().post(new Runnable() {
            @Override
            public void run() {
                ph.start();
                try{
                        XSSFWorkbook workbook = new XSSFWorkbook(file);
                        XSSFSheet spreadsheet = workbook.getSheetAt(0);
                        Iterator <Row> iterator = spreadsheet.iterator();
                        Employees emp = null;
                        XSSFRow row;
                        Double amount = 0.0;
                        while(iterator.hasNext()){
                            row = (XSSFRow)iterator.next();
//                            Iterator <Cell> cellIterator = row.cellIterator();
//                            while(cellIterator.hasNext()){
//                                Cell cell = cellIterator.next();
//                                switch(cell.getCellType()){
//                                    case NUMERIC:
//                                        System.out.println(cell.getNumericCellValue());
//                                        break;
//                                    case STRING:
//                                        System.out.println(cell.getStringCellValue());
//                                        break;
//                                }
//                            }
                            Cell cellEmpCode = row.getCell(2);
                            Cell cellAmount = row.getCell(13);
                            if(cellEmpCode.getRowIndex() > startingAt){
                            try{
                                String empcode = cellEmpCode.getStringCellValue();
                                if(empcode.length()<2){
                                    NotifyUtil.warn("Data Not Valid", "No Valid Employee Code Found At "+ cellEmpCode.getAddress().formatAsString()+" Row Skipped", false);
                                    listError.add(row);
                                    continue;
                                }else{
                                    emp = DataAccess.getEmployeeByClockinID(empcode);
                                    if( null == emp ){
                                        NotifyUtil.warn("Data Not Valid", "No Valid Employee Code Found At "+ cellEmpCode.getAddress().formatAsString()+" Row Skipped", false);
                                        continue;
                                    }else{
                                        try{
                                            amount = cellAmount.getNumericCellValue();
                                            listSuccess.add(new ImportedTransaction(emp, amount));
                                        }catch(Exception ex){
                                            NotifyUtil.error("Invalid Figure", "No Valid Figure Found At "+ cellAmount.getAddress().formatAsString()+" Row Skipped", ex, false);
                                            continue;
                                        }
                                        
                                    }
                                }
                                
                                
                                
                                
                            }catch(Exception ex){
                                NotifyUtil.error("Data Not Valid", "No Valid Employee Code Found At "+ cellEmpCode.getAddress().formatAsString()+" Row Skipped", ex, false);
                                continue;
                            }
                            }
//                            emp = DataAccess.getEmployeeByClockinID(cellEmpCode.getStringCellValue());
//                            try{
//                                
//                            }catch(Exception ex){
//                                NotifyUtil.error("Problem", "Problem", ex, false);
//                                continue;
//                            }
//                            
                         
                            
                        }
                        NotifyUtil.info("Status", listSuccess.size()+  " Valid Transaction " +listError.size()+" Invalid Transactions" , false);
                        em.setRootContext(new AbstractNode(Children.create(new FactoryImportedTransactions(listSuccess), true)));
                    }catch(IOException | InvalidFormatException ex){
                        NotifyUtil.error("Error", "There seems to be a problem", ex, false);
                    }catch(OLE2NotOfficeXmlFileException ex){
                        NotifyUtil.error("Old Version Not Supported", "Please save your template in Excel 2007+", ex, false);
                    }
                    ph.finish();
                    jbProcess.setEnabled(true);
                    if(listError.size()<2){
                        content.add(upload);
                    }
                    
                    
            }
            
        });
        
    }//GEN-LAST:event_jbProcessActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.components.JSpinField jSpinFieldHeaderRow;
    private javax.swing.JButton jbProcess;
    private javax.swing.JButton jbSelectFile;
    private javax.swing.JLabel jlSelectedFile;
    private javax.swing.JLabel jlStartingRowNumber;
    private javax.swing.JPanel jpView;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        rslt.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }
    
    void uploadData(){
        content.remove(upload);
        if(listSuccess.isEmpty()){
            StatusDisplayer.getDefault().setStatusText("Nothing to upload");
                    
        }else if(listError.size() > 1){
            StatusDisplayer.getDefault().setStatusText("Invalid Transactions Detected, Resolve");
        }else{
            
            DialogDisplayer.getDefault().notify(new DialogDescriptor(tc,"Select A Payroll Code"));
            final TblPeriods currentPeriod = DataAccess.getCurrentMonth();
            final Currencies currency = DataAccess.getBaseCurrency();
            if(null != code){
                ProgressHandle ph = ProgressHandleFactory.createHandle("Posting Transactions");
                RequestProcessor.getDefault().post(new Runnable() {
                    @Override
                    public void run() {
                        ph.start();
                        for(int i = 0; i<listSuccess.size(); i++){
                            ImportedTransaction t = listSuccess.get(i);
                            ph.progress(i+1+" / "+listSuccess.size());
                            //StatusDisplayer.getDefault().setStatusText(t.toString());
                            BigDecimal amount = new BigDecimal(t.getAmount()).round(MathContext.DECIMAL32);
                            try{
                            DataAccess.saveEmployeeTransaction(t.getEmp(), code, amount, currency, "Message", currentPeriod);
                            }catch(Exception ex){
                                NotifyUtil.error("Number Format Error", "Round Off The figures and try again", ex, false);
                            }        
                        }
                        ph.finish();
                    }
                });
            }
            
            
        }
                
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Lookup.Result<TblPayrollCode> rslt = (Lookup.Result<TblPayrollCode>)ev.getSource();
        for(TblPayrollCode code: rslt.allInstances()){
            this.code = code;
            StatusDisplayer.getDefault().setStatusText(code.getPayrollCodeName());
        }
    }
    
    
    
    
}
