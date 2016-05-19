/**
 * Yumu.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yumu.hexie.service;

import org.springframework.scheduling.annotation.Scheduled;

import com.yumu.hexie.common.util.StringUtil;
import com.yumu.hexie.integration.wechat.constant.ConstantWeChat;
import com.yumu.hexie.integration.wechat.entity.AccessToken;
import com.yumu.hexie.integration.wechat.util.WeixinUtil;
import com.yumu.hexie.integration.wechat.util.WeixinUtilV2;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author tongqian.ni
 * @version $Id: RefreshTokenService.java, v 0.1 2016年5月9日 下午8:01:52  Exp $
 */
public interface RefreshTokenService {
    public void refreshOtherAccessTokensJob();

    public void refreshAccessTokenJob();

    public void refreshJsTicketJob();

}
