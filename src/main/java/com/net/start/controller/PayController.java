package com.net.start.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.net.start.service.AliPayService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
public class PayController {
	Logger logger = LoggerFactory.getLogger("PayController.class");

	@Autowired
	AliPayService aliPayService;

	@ApiOperation("支付接口")
	@RequestMapping(value = "alipay/toPay/{amount}", method = RequestMethod.GET)
	public String alipay(@PathVariable(value = "amount") Integer amount) throws AlipayApiException {
	    return aliPayService.aliPay();
	}

	@ApiOperation("支付异步通知接口")
	@GetMapping("alipay/notify_url")
	public String notifyAlipay() {

	    logger.info("----notify-----");
	    return " a li pay notify ";
	}

	@ApiOperation("支付完成以后的回调接口")
	@GetMapping("alipay/return_url")
	public String returnAlipay() {
	    logger.info("----return-----");
	    return " a li pay return ";
	}

}
