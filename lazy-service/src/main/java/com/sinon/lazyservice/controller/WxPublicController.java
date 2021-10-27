package com.sinon.lazyservice.controller;

import com.alibaba.fastjson.JSON;
import com.sinon.lazyservice.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname WxPublicController
 * @Description TODO
 * @Date 2021/10/12 23:20
 * @Created by sinon
 * @Author <a href="huangyanzhi@wxchina.com">Sinon</a>
 */
@RestController
@CrossOrigin
@RequestMapping(path = "weChatPublic")
public class WxPublicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxPublicController.class);


    @GetMapping(value = "serial", produces = "text/plain;charset=utf-8")
    public String checkSign(HttpServletRequest request, HttpServletResponse response) {
        try {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            LOGGER.info("本身" + JSON.toJSONString(request.getParameterMap()));
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                return echostr;
            }
        } catch (Exception e) {
            LOGGER.error("验证公众号token失败", e);
        }
        return null;
    }

    @GetMapping(value = "test/{id}")
    public String test(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        return id;
    }
}
