/**
 * Yumu.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yumu.hexie.service;



/**
 * <pre>
 * 
 * </pre>
 *
 * @author tongqian.ni
 * @version $Id: RefreshTokenService.java, v 0.1 2016年5月9日 下午8:01:52  Exp $
 */
public interface RefreshTokenService {
	
	public static final String SYS_NAME_HEXIE = "hexie";
    public static final String SYS_NAME_BAOFANG = "baofang";
    public static final String SYS_NAME_CHUNHUI = "chunhui";
    public static final String SYS_NAME_LIANGYOU = "liangyou";
    public static final String SYS_NAME_WEIFA = "weifa";
    public static final String SYS_NAME_YOUYI = "youyi";
    public static final String SYS_NAME_GUANGMING = "guangming";
    public static final String SYS_NAME_DHZJ1 = "dhzj1";
    public static final String SYS_NAME_DHZJ2 = "dhzj2";
    public static final String SYS_NAME_DHZJ3 = "dhzj3";
    public static final String SYS_NAME_DHZJ4 = "dhzj4";
    public static final String SYS_NAME_ZHONGXIN = "zhongxin";
    public static final String SYS_NAME_XINGSHEQU = "xingshequ";
    
    public void refreshOtherAccessTokenJob();

    public void refreshAccessTokenJob();

    public void refreshJsTicketJob();
    
}
