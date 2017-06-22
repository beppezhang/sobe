package com.kpuswebup.comom.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

@Service
public class CachedClient {

    public static MemcachedClient memcachedClient;
    private static String         address  = null;
    private static String         user     = null;
    private static String         password = null;
    static {
        ClassPathResource resource = new ClassPathResource("server.properties");
        Properties prop = new Properties();
        try {
            prop.load(new FileReader(resource.getFile()));
            address = prop.getProperty("cache.address");
            user = prop.getProperty("cache.user");
            password = prop.getProperty("cache.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(address));
        builder.addAuthInfo(AddrUtil.getOneAddress(address), AuthInfo.plain(user, password));

        builder.setConnectionPoolSize(30);
        builder.setCommandFactory(new BinaryCommandFactory());
        try {
            memcachedClient = builder.build();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void set(final String key, final int exp, final Object object) {
        if (object != null) {
            try {
                memcachedClient.set(key, exp, object);
            } catch (TimeoutException e) {

                e.printStackTrace();
            } catch (InterruptedException e) {

                e.printStackTrace();
            } catch (MemcachedException e) {
                e.printStackTrace();
            }
        }
    }

    public final <T> T get(String str) {
        try {
            if (str == null) {
                return null;
            }
            return memcachedClient.get(str);
        } catch (TimeoutException e) {

            e.printStackTrace();
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (MemcachedException e) {

            e.printStackTrace();
        }
        return null;
    }

    public void add(String key, int exp, Object object) {
        if (object != null) {
            try {
                memcachedClient.add(key, exp, object);
            } catch (TimeoutException e) {

                e.printStackTrace();
            } catch (InterruptedException e) {

                e.printStackTrace();
            } catch (MemcachedException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String key) {
        if (key != null) {
            try {
                memcachedClient.delete(key);
            } catch (TimeoutException e) {

                e.printStackTrace();
            } catch (InterruptedException e) {

                e.printStackTrace();
            } catch (MemcachedException e) {

                e.printStackTrace();
            }
        }
    }
    
	public static void destroy() throws IOException
    {
    	if(!memcachedClient.isShutdown())
    	{
    		memcachedClient.shutdown();
    		System.out.println("memcachedClient shutdown complete");
    	}else
    	{
    		System.out.println("memcachedClient already shutdown");
    	}
    }
}
