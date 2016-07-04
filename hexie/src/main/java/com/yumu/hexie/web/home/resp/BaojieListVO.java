/**
 * Yumu.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yumu.hexie.web.home.resp;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.yumu.hexie.model.localservice.bill.BaojieBill;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author tongqian.ni
 * @version $Id: BaojieListVO.java, v 0.1 2016年5月27日 下午3:53:50  Exp $
 */
public class BaojieListVO implements Serializable {

    private static final long serialVersionUID = -7522498896467329710L;
    
    private long id;
    private String orderNo;

    private String billLogo;
    private String projectName;//项目名称，用于展示
    
    private int status;
    private String statusStr; //各业务自己处理
    
    private String payStatusStr;

    private int itemCount;//服务项个数，冗余，一对多的时候取其中一个

    private String requireDateStr;
    private String createDateStr;
    
    public BaojieListVO(){}
    public BaojieListVO(BaojieBill bill) {
        BeanUtils.copyProperties(bill, this);
        //FIXME
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public String getBillLogo() {
        return billLogo;
    }
    public void setBillLogo(String billLogo) {
        this.billLogo = billLogo;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getStatusStr() {
        return statusStr;
    }
    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
    public String getPayStatusStr() {
        return payStatusStr;
    }
    public void setPayStatusStr(String payStatusStr) {
        this.payStatusStr = payStatusStr;
    }
    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    public String getRequireDateStr() {
        return requireDateStr;
    }
    public void setRequireDateStr(String requireDateStr) {
        this.requireDateStr = requireDateStr;
    }
    public String getCreateDateStr() {
        return createDateStr;
    }
    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }
}
