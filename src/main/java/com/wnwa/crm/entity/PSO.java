package com.wnwa.crm.entity;

public class PSO {
    private String departmentOri;
    private String contractDate;

    public String getDepartmentOri(){
        return departmentOri
;
    }

    public void setDepartmentName(String departmentOri){
        this.departmentOri = departmentOri;
    }

    public String getContractDate(){
        return contractDate;
    }

    public void setContractDate(String contractDate){
        this.contractDate = contractDate;
    }

    public PSO(String departmentOri, String contractDate){

        this.departmentOri = departmentOri;
        this.contractDate = contractDate;
    }
    @Override
    public String toString() {
        return "Department [departmentOri=" + departmentOri + ", contractDate=" + contractDate + "]";
    }
}
