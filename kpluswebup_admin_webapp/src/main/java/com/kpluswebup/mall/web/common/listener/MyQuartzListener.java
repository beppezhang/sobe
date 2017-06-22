package com.kpluswebup.mall.web.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kpuswebup.common.lucene.LuceneWriter;
import com.kpuswebup.comom.util.CachedClient;

public class MyQuartzListener implements ServletContextListener {

	private WebApplicationContext springContext;
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
            // This can't use logging because it's (hopefully) been shut down by now.
            System.out.println("Sleep for a bit so that we don't get any errors about Quartz threads not being shut down yet. ");
         // Get a reference to the Scheduler and shut it down
            if(springContext == null)
            	springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
            System.out.println("CachedClient.memcachedClient.isShutdown()"+CachedClient.memcachedClient.isShutdown());
            CachedClient.destroy();
            System.out.println("CachedClient.memcachedClient.destroy()"+CachedClient.memcachedClient.isShutdown());
            Thread.sleep(5 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }	
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
	}

}
