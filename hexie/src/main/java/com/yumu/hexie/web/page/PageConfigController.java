/**
 * Yumu.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.yumu.hexie.web.page;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yumu.hexie.web.BaseResult;
import com.yumu.hexie.common.Constants;
import com.yumu.hexie.model.user.User;
import com.yumu.hexie.model.view.Banner;
import com.yumu.hexie.service.page.PageConfigService;
import com.yumu.hexie.web.BaseController;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author tongqian.ni
 * @version $Id: PageConfigController.java, v 0.1 2016年1月18日 上午9:50:32  Exp $
 */
@Controller(value = "pageConfigController")
public class PageConfigController extends BaseController{
    
    @Inject
    private PageConfigService pageConfigService;
    @ResponseBody
    @RequestMapping(value = "/pageconfig/{tempKey}", method = RequestMethod.GET )
    public String process(HttpServletRequest request,
            HttpServletResponse response,@PathVariable String tempKey) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        return pageConfigService.findByTempKey(tempKey);
    }
    
    @ResponseBody
    @RequestMapping(value = "/pageconfig2/{tempKey}", method = RequestMethod.GET )
    public BaseResult<List<Banner>> process2(HttpServletRequest request
            ,HttpServletResponse response,@PathVariable String tempKey
            ,@ModelAttribute(Constants.USER)User user
            ) throws Exception {
        List<Long> regions = new ArrayList<>();
        regions.add(Long.valueOf(1));
        if(null!=user){
            regions.add(user.getXiaoquId());
        }
    	return BaseResult.successResult(pageConfigService.findByTempKey2(tempKey,regions));
    }

}
