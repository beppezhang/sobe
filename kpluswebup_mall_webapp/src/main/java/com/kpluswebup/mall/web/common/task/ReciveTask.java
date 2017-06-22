package com.kpluswebup.mall.web.common.task;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.kpluswebup.mall.web.control.BaseController;
import com.kpluswebup.web.domain.ItemReviewDTO;
import com.kpluswebup.web.domain.SalesOrderDTO;
import com.kpluswebup.web.member.service.ItemReviewService;
import com.kpluswebup.web.service.SalesOrderLineService;
import com.kpluswebup.web.service.SalesOrderService;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ItemReviewVO;
import com.kpluswebup.web.vo.SalesOrderLineVO;
import com.kpluswebup.web.vo.SalesOrderVO;
import com.kpuswebup.comom.util.GenerationNum;
import com.kpuswebup.comom.util.StringUtil;
import com.kpluswebup.web.vo.CustomerVO;
@Component
public class ReciveTask extends BaseController {
	@Autowired
	private SalesOrderService salesOrderService;
	  @Autowired
	    private ItemReviewService				   itemReviewService;
	  @Autowired
	    private SalesOrderLineService                salesOrderLineService;
	/**
	 * 
	 * @author zhoulei
	 *发货后自动收货
	 */
	//@Scheduled(cron = "0 0 0/2 * * ?")//间隔2小时执行一次
	public void automaticOrderStatus(){
		SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
		salesOrderDTO.setOrderStatus(4);
		salesOrderDTO.setPageSize(1000l);
		List<SalesOrderVO> orderList = salesOrderService.getSalesOrderList(salesOrderDTO);//查找已发货的订单
		System.out.println("---------------自动收货程序开始启动-----start---------------"+orderList.size());
		StringBuffer sb = new StringBuffer();
		for(SalesOrderVO salesOrderVO:orderList){
			Date shipmentsTime = salesOrderVO.getShipmentsTime();
			Date newDate = new Date();
			System.out.println(salesOrderVO.getMainID() +":  "+(newDate.getTime() - shipmentsTime.getTime()));
			if((newDate.getTime() - shipmentsTime.getTime())>=1296000000){//15天自动确认收货
				sb.append(salesOrderVO.getMainID()+",");
			}if((newDate.getTime() - shipmentsTime.getTime())>=1555200000){//18天自动提交评价
				submit("非常满意", 5);

		}
		if(null!=sb){
			salesOrderService.updateSalesOrderStatus(sb.toString(),"5",null);
		}
		}
		System.out.println("---------------自动收货程序开始启动----- end ---------------");
		
		
	}
	public String submit(String content,  Integer score) {
        ItemReviewDTO itemReviewDTO = new ItemReviewDTO();
        CustomerVO customer = this.findUserInfo();
        itemReviewDTO.setScore(score);
        itemReviewDTO.setContent(content);
        
        try {
			if(StringUtil.isNotEmpty(content)&&content.equals(new String(content.getBytes("iso-8859-1"), "iso-8859-1")))
			{
			content=new String(content.getBytes("iso-8859-1"),"utf-8");
			itemReviewDTO.setContent(content);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        itemReviewDTO.setReplytime(new Date());
        
        itemReviewService.addtReview(itemReviewDTO);
        return "redirect:/mall/product/productDetail.htm?score="+score+"&content="+content;
    }
	
	//@Scheduled(cron = "0 0 0/2 * * ?")//间隔2小时执行一次
	public void autoCanelOrder(){
		SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
		salesOrderDTO.setOrderStatus(1);//未被确认
		salesOrderDTO.setPaymentStatus(1);//为付款
		salesOrderDTO.setPageSize(1000l);
		List<SalesOrderVO> orderList = salesOrderService.getSalesOrderList(salesOrderDTO);//查找未确认未付款的订单
		System.out.println("---------------自动取消程序开始启动-----start---------------"+orderList.size());
		StringBuffer sb = new StringBuffer();
		for(SalesOrderVO salesOrderVO:orderList){
			Date createTime = salesOrderVO.getCreateTime();
			Date newDate = new Date();
			System.out.println(salesOrderVO.getMainID() +":  "+(newDate.getTime() - createTime.getTime()));
			if((newDate.getTime() - createTime.getTime())>=7200000){//2小时自动取消订单
				sb.append(salesOrderVO.getMainID()+",");
			}
		}
		if(null!=sb){
			salesOrderService.updateSalesOrderStatus(sb.toString(),"0",null);
		}
		System.out.println("---------------自动取消程序开始启动----- end ---------------");
		
	}
	
	//@Scheduled(fixedRate = 1000)  
    public void heartbeat() {  
//        System.out.println("心跳更新... " + new Date());  
    } 	
	
	public static void main(String[] args){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = null;
		Date newDate = new Date();
		try {
			now = df.parse("2015-08-06 09:11:42");
			//date=df.parse("2015-07-01 18:51:42");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long l=newDate.getTime()-now.getTime();
		System.out.println(l);
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		
	}
}
