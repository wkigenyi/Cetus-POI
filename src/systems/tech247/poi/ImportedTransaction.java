/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.poi;

import systems.tech247.hr.Employees;

/**
 *
 * @author Wilfred
 */
public class ImportedTransaction {
    
    private Employees emp;
    private Double amount;

    public ImportedTransaction(Employees emp, Double amount) {
        this.emp = emp;
        //StatusDisplayer.getDefault().setStatusText(emp.getEmpCode());
        this.amount = amount;
    }

    /**
     * @return the emp
     */
    public Employees getEmp() {
        return emp;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return emp.getEmpCode();
    }

    /**
     * @param emp the emp to set
     */
    public void setEmp(Employees emp) {
        this.emp = emp;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    

    
    
}
