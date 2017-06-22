package com.sds.promotion.test;
import org.junit.Test;

import com.kpluswebup.web.vo.FocusEntity;


public class DaoEntityTest {
	private FocusEntity focusEntity;
	
	@Test
	public void testFocusEntity(){
    	
    	focusEntity = new FocusEntity();
    	focusEntity.setCustomerID("123456");
    	focusEntity.setFocusType(1);
    	
    	System.out.println(focusEntity);
	}
}
