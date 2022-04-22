package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basm060 {
@Id
@Column(name = "cust_no")
private Integer custNo;
@Column(name = "cust_name")
private String custName;
@Column(name = "cust_tel")
private String custTel;
@Column(name = "cust_fax")
private String custFax;
@Column(name = "inv_addr")
private String invAddr;
@Column(name = "zip_code")
private String zipCode;
@Column(name = "resp_man")
private String respMan;
@Column(name = "call_man")
private String callMan;
@Column(name = "call_tel")
private String callTel;
@Column(name = "unify_no")
private String unifyNo;
@Column(name = "pcs_no")
private String pcsNo;
@Column(name = "public_code")
private String publicCode;
@Column(name = "file_code")
private String fileCode;
@Column(name = "tx_code")
private String txCode;
@Column(name = "main_custno")
private Integer mainCustno;
@Column(name = "remk")
private String remk;
@Column(name = "old_custno")
private String oldCustno;
@Column(name = "create_id")
private String createId;
@Column(name = "create_date")
private Date createDate;
@Column(name = "update_id")
private String updateId;
@Column(name = "update_date")
private Date updateDate;
public Integer getCustNo() {
	return custNo;
}
public void setCustNo(Integer custNo) {
	this.custNo = custNo;
}
public String getCustName() {
	return custName;
}
public void setCustName(String custName) {
	this.custName = custName;
}
public String getCustTel() {
	return custTel;
}
public void setCustTel(String custTel) {
	this.custTel = custTel;
}
public String getCustFax() {
	return custFax;
}
public void setCustFax(String custFax) {
	this.custFax = custFax;
}
public String getInvAddr() {
	return invAddr;
}
public void setInvAddr(String invAddr) {
	this.invAddr = invAddr;
}
public String getZipCode() {
	return zipCode;
}
public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}
public String getRespMan() {
	return respMan;
}
public void setRespMan(String respMan) {
	this.respMan = respMan;
}
public String getCallMan() {
	return callMan;
}
public void setCallMan(String callMan) {
	this.callMan = callMan;
}
public String getCallTel() {
	return callTel;
}
public void setCallTel(String callTel) {
	this.callTel = callTel;
}
public String getUnifyNo() {
	return unifyNo;
}
public void setUnifyNo(String unifyNo) {
	this.unifyNo = unifyNo;
}
public String getPcsNo() {
	return pcsNo;
}
public void setPcsNo(String pcsNo) {
	this.pcsNo = pcsNo;
}
public String getPublicCode() {
	return publicCode;
}
public void setPublicCode(String publicCode) {
	this.publicCode = publicCode;
}
public String getFileCode() {
	return fileCode;
}
public void setFileCode(String fileCode) {
	this.fileCode = fileCode;
}
public String getTxCode() {
	return txCode;
}
public void setTxCode(String txCode) {
	this.txCode = txCode;
}
public Integer getMainCustno() {
	return mainCustno;
}
public void setMainCustno(Integer mainCustno) {
	this.mainCustno = mainCustno;
}
public String getRemk() {
	return remk;
}
public void setRemk(String remk) {
	this.remk = remk;
}
public String getOldCustno() {
	return oldCustno;
}
public void setOldCustno(String oldCustno) {
	this.oldCustno = oldCustno;
}
public String getCreateId() {
	return createId;
}
public void setCreateId(String createId) {
	this.createId = createId;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public String getUpdateId() {
	return updateId;
}
public void setUpdateId(String updateId) {
	this.updateId = updateId;
}
public Date getUpdateDate() {
	return updateDate;
}
public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}


}
