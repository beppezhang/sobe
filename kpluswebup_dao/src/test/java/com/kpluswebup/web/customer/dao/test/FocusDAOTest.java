package com.kpluswebup.web.customer.dao.test;

import java.io.Reader;  
import java.sql.Connection;  
import java.util.List;

import org.apache.ibatis.io.Resources;  
import org.apache.ibatis.jdbc.ScriptRunner;  
import org.apache.ibatis.session.SqlSession;  
import org.apache.ibatis.session.SqlSessionFactory;  
import org.apache.ibatis.session.SqlSessionFactoryBuilder;  
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;  
import org.junit.Before;
import org.junit.BeforeClass;  
import org.junit.Test; 

import com.kpluswebup.web.customer.dao.FocusDAO;
import com.kpluswebup.web.vo.FocusEntity;
import com.kpuswebup.comom.util.UUIDUtil;

public class FocusDAOTest {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	private FocusEntity focusEntity;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		// create a SqlSessionFactory  
        Reader reader = Resources.getResourceAsReader("com/sds/config/mybatis/test/mybatis-config-test.xml");  
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
        reader.close(); 	
        
     // populate in-memory database  
        SqlSession session = sqlSessionFactory.openSession();  
        Connection conn = session.getConnection();  
        reader = Resources.getResourceAsReader("com/sds/config/mybatis/test/Focus-Hqldb.sql");  
        ScriptRunner runner = new ScriptRunner(conn);  
        runner.setLogWriter(null);  
        runner.runScript(reader);  
        reader.close();  
        session.close();        
    }
    @Before
    public void setUp() throws Exception
    {
    	focusEntity = new FocusEntity();
    	focusEntity.setCustomerID("0010590487899");
    	focusEntity.setFocusType(1);
    }
    
    @After
    public void tearDown() throws Exception
    {
    	
    } 
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    	
    }	
    
    @Test
    public void findFocusByCustomerID()
    {
        SqlSession sqlSession = sqlSessionFactory.openSession();  
        try  
        {  
        	FocusDAO focusDAO = sqlSession.getMapper(FocusDAO.class);
        	List<FocusEntity> focusEntitys = focusDAO.findFocusByCustomerID(focusEntity);
            Assert.assertEquals("A673E9A1-B9AB-4C80-9030-9C226FCC48FC", focusEntitys.get(0).getReferenceID());
            Assert.assertEquals(4, focusEntitys.size());
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally  
        {  
            sqlSession.close();  
        }     	
    }
    
    @Test
    public void insert()
    {
		FocusEntity focusEntity = new FocusEntity();
		
		String mainID = UUIDUtil.getOrigUUID();
		focusEntity.setMainID(mainID);
		focusEntity.setMyFocusInfo("测试关注");
		focusEntity.setReferenceID("123456789");
		focusEntity.setFocusType(1);
		focusEntity.setIsDelete(0);
		
        SqlSession sqlSession = sqlSessionFactory.openSession();  
        try  
        {  
        	FocusDAO focusDAO = sqlSession.getMapper(FocusDAO.class);
        	int i = focusDAO.insert(focusEntity);
        	Assert.assertEquals(1, i);
        	List<FocusEntity> focusEntitys = focusDAO.findFocusByCustomerID(focusEntity);
            Assert.assertEquals(5, focusEntitys.size());
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally  
        {  
            sqlSession.close();  
        }   	    	
    }
    
    
}
