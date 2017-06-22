package com.kpluswebup.web.customer.dao.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kpluswebup.web.admin.system.dao.AreaDAO;
import com.kpluswebup.web.admin.system.dao.ExpressDAO;
import com.kpluswebup.web.admin.system.dao.ExpressFormatDAO;
import com.kpluswebup.web.admin.system.dao.MainDAO;
import com.kpluswebup.web.content.dao.AdvertDAO;
import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.domain.BrandDTO;
import com.kpluswebup.web.domain.CustomerDTO;
import com.kpluswebup.web.domain.ExpressDTO;
import com.kpluswebup.web.domain.ExpressFormatDTO;
import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ItemPropDTO;
import com.kpluswebup.web.domain.ProductCategoryDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.ProductPictureDTO;
import com.kpluswebup.web.domain.ProductPropDTO;
import com.kpluswebup.web.domain.ProductTypeBrandDTO;
import com.kpluswebup.web.domain.ProductTypeDTO;
import com.kpluswebup.web.domain.ProductTypeItemPropDTO;
import com.kpluswebup.web.domain.ProductTypeProductPropDTO;
import com.kpluswebup.web.product.dao.BrandDAO;
import com.kpluswebup.web.product.dao.ItemDAO;
import com.kpluswebup.web.product.dao.ItemPropDAO;
import com.kpluswebup.web.product.dao.ProductCategoryDAO;
import com.kpluswebup.web.product.dao.ProductDAO;
import com.kpluswebup.web.product.dao.ProductPictureDAO;
import com.kpluswebup.web.product.dao.ProductPropDAO;
import com.kpluswebup.web.product.dao.ProductTypeBrandDAO;
import com.kpluswebup.web.product.dao.ProductTypeDAO;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.BrandVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.MemberCountVO;
import com.kpluswebup.web.vo.OrderVolumeVO;
import com.kpluswebup.web.vo.ProductCategoryVO;
import com.kpluswebup.web.vo.ProductStatisticsVO;
import com.kpluswebup.web.vo.ProductTypeBrandVO;
import com.kpluswebup.web.vo.ProductVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.UUIDUtil;

public class CustomerDAOTest {

    public static ApplicationContext context = null;

    static {
        context = new ClassPathXmlApplicationContext("spring-context-adminsystem.xml", "spring-mysql-db.xml");
    }

    private CustomerDAO              customerDAO;

    private AreaDAO                  areaDAO;

    private ProductTypeDAO           productTypeDAO;

    private ProductPropDAO           productPropDAO;

    private ItemPropDAO              itemPropDAO;

    private BrandDAO                 brandDAO;

    private ProductCategoryDAO       productCategoryDAO;

    private ProductDAO               productDAO;

    private AdvertDAO                advertDAO;

    private ExpressDAO               expressDAO;

    private ItemDAO                  itemDAO;

    private ExpressFormatDAO         expressFormatDAO;

    private ProductTypeBrandDAO      productTypeBrandDAO;

    private ProductPictureDAO        productPictureDAO;

    private MainDAO                  mainDAO;

    //@Before
    public void init() {
        customerDAO = (CustomerDAO) context.getBean("customerDAO");
        areaDAO = (AreaDAO) context.getBean("areaDAO");
        productTypeDAO = (ProductTypeDAO) context.getBean("productTypeDAO");
        productPropDAO = (ProductPropDAO) context.getBean("productPropDAO");
        itemPropDAO = (ItemPropDAO) context.getBean("itemPropDAO");
        brandDAO = (BrandDAO) context.getBean("brandDAO");
        productCategoryDAO = (ProductCategoryDAO) context.getBean("productCategoryDAO");
        productDAO = (ProductDAO) context.getBean("productDAO");
        advertDAO = (AdvertDAO) context.getBean("advertDAO");
        expressDAO = (ExpressDAO) context.getBean("expressDAO");
        itemDAO = (ItemDAO) context.getBean("itemDAO");
        expressFormatDAO = (ExpressFormatDAO) context.getBean("expressFormatDAO");
        productTypeBrandDAO = (ProductTypeBrandDAO) context.getBean("productTypeBrandDAO");
        productPictureDAO = (ProductPictureDAO) context.getBean("productPictureDAO");
        mainDAO = (MainDAO) context.getBean("mainDAO");

    }

    //@Test
    public void testAdd() {
        for (int i = 30; i < 50; i++) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setMainID("006" + i);
            customerDTO.setUsername("username3" + i);
            customerDTO.setName("朱慧培2" + i);
            customerDTO.setEmail(i + "499348444//@qq.com");
            customerDTO.setAvatar("http://www.vtongbao.com/web/website/WeiTongBao/img/logo.png");
            customerDTO.setBirthday(new Date());
            customerDTO.setMobile("150886057" + i);
            customerDTO.setAmount(20.00);
            customerDTO.setAge(10 + i);
            customerDTO.setSex(1);
            customerDTO.setScore(30d);
            customerDTO.setTelephone("0517-12345678");
            customerDTO.setZip("310000");
            // customerDAO.insertCustomer(customerDTO);
        }

    }

    //@Test
    public void updateCustomerByMainId() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setMainID("001");
        customerDTO.setEmail("ddddd");
        customerDTO.setAmount(20.00);
        customerDTO.setUsername("username update");
        // customerDAO.updateCustomerByMainId(customerDTO);
    }

    //@Test
    public void getAllProvince() {
         List<AreaVO> list = areaDAO.getAllProvince();
         System.out.println(list.size());
        // List<AreaVO> lists = areaDAO.getAreaByParentID("3780"); //3780,3781
        // System.out.println(lists.size());
    }

    //@Test
    public void productType() {
        for (int i = 30; i < 50; i++) {
            ProductTypeDTO productTypeDTO = new ProductTypeDTO();
            productTypeDTO.setmainID("P002" + i);
            productTypeDTO.setName("name" + i);
            productTypeDTO.setDescription("description" + i);
            ;
            // productTypeDAO.insertProductType(productTypeDTO);
        }
        // productTypeDTO.setName("updatename");
        // //productTypeDAO.updatProductTypeByMainID(productTypeDTO);
        // long count=productTypeDAO.findProductTypeCount(productTypeDTO);
        // System.out.println(count);
        // productTypeDAO.findProductTypeByMainID("P002");
    }

    //@Test
    public void productProp() {
        for (int i = 30; i < 50; i++) {
            ProductPropDTO productPropDTO = new ProductPropDTO();
            productPropDTO.setMainID("PP" + i);
            productPropDTO.setPropType((i % 2) + 1);
            productPropDTO.setName("name" + i);
            // productPropDAO.insertProductProp(productPropDTO);
        }
        ProductPropDTO productPropDTO = new ProductPropDTO();
        productPropDAO.deleteProductPropByMainID("PP30");
        productPropDAO.findProductPropCount(productPropDTO);
        ProductTypeProductPropDTO productTypeProductPropDTO = new ProductTypeProductPropDTO();
        productPropDAO.findProductTypeProductProp(productTypeProductPropDTO);
        ProductTypeItemPropDTO productTypeItemPropDTO = new ProductTypeItemPropDTO();
        productTypeItemPropDTO.setProductTypeID("11111");
        productPropDAO.findProductTypeItemProp(productTypeItemPropDTO);

    }

    //@Test
    public void itemProp() {
        ItemPropDTO itemPropDTO = new ItemPropDTO();
        itemPropDTO.setMainID(UUIDUtil.getUUID());
        itemPropDTO.setName("规格明");
        itemPropDTO.setDescription("描述");

        // itemPropDAO.insertItemProp(itemPropDTO);
        // itemPropDAO.deleteItemPropByMainID(itemPropDTO.getMainID());
        // Long count = itemPropDAO.findItemPropCount(new ItemPropDTO());
        // System.out.println(count);
        // itemPropDAO.findItemPropByPagination(new ItemPropDTO());
    }

    //@Test
    public void brandDAO() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setDescription("description");
        brandDTO.setDomain("www.kata.com.cn");
        brandDTO.setTitle("title");
        brandDTO.setMetaDescription("metaDescription");
        brandDTO.setName("name");
        brandDTO.setMainID("mainID");
        // brandDAO.insertBrand(brandDTO);
        BrandDTO newbrandDTO = new BrandDTO();
        newbrandDTO.setName("a");
        // Long count = brandDAO.findBrandCount(newbrandDTO);
        List<BrandVO> list = brandDAO.findBrandByPagination(newbrandDTO);
        System.out.println(list.size());
    }

    //@Test
    public void productCategoryDAO() {
        List<ProductCategoryVO> list = productCategoryDAO.findALLProductCategory();
        System.out.println(list.size());
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setMainID("uuid");
        // productCategoryDAO.insertProductCategory(productCategoryDTO);
    }

    //@Test
    public void productDAO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setMainID("mainID");
        productDTO.setName("name");
        productDTO.setVirtual(1);
        productDTO.setBrandID("brandID");
        productDTO.setDescription("description");
        productDTO.setTitle("title");
        productDTO.setMetaDescription("metaDescription");
        productDTO.setMetaKeywords("metaKeywords");
        productDTO.setCatalog(1);
        productDTO.setUnit("unit");
        productDTO.setPicURL("picURL");
        // productDAO.insertProduct(productDTO);
        // productDAO.deleteProductByMainID("mainID");
        productDTO.doPage(productDAO.findProducCount(new ProductDTO()), new ProductDTO().getPageNo(),
                          new ProductDTO().getPageSize());
        List<ProductVO> list = productDAO.findProducByPagination(productDTO);
        System.out.println(list.size());
    }

    //@Test
    public void searchName() {
        advertDAO.findBrandByName("1");
    }

    //@Test
    public void expressDAO() {
        ExpressDTO expressDTO = new ExpressDTO();
        expressDTO.setCode("code");
        expressDTO.setDef(1);
        expressDTO.setDescription("description");
        expressDTO.setMainID("mainID");
        expressDTO.setName("name");
        expressDTO.setMobile("15088605754");
        // expressDAO.addExpress(expressDTO);
        // expressDAO.deleteExpressByMainID("mainID");
        List<ExpressVO> list = expressDAO.findALlExpress();
        System.out.println(list.size());
    }

    //@Test
    public void itemDAO() {
        String mainID = UUIDUtil.getUUID();
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setCostPrice(30.00);
        itemDTO.setCubage(1);
        itemDTO.setDescription("description");
        itemDTO.setDistributionPrice(40.00);
        itemDTO.setMainID(mainID);
        itemDTO.setMinimumQty(90);
        itemDTO.setWeight(20d);
        itemDTO.setStock(80);
        itemDTO.setSalesPrice(8d);
        itemDTO.setName("name");
        itemDTO.setQuantity(50);
        // itemDAO.insertItem(itemDTO);
        // itemDAO.deleteItemByMainID(mainID);
        itemDTO.setName("nameupdate");
        // itemDAO.updateItemByMainID(itemDTO);
    }

    //@Test
    public void expressFormatDAO() {
        String mainID = UUIDUtil.getUUID();
        ExpressFormatDTO expressFormatDTO = new ExpressFormatDTO();
        expressFormatDTO.setExpressID("expressID");
        expressFormatDTO.setHeight(20d);
        expressFormatDTO.setIsDefault(0);
        expressFormatDTO.setMainID(mainID);
        expressFormatDTO.setName("name");
        expressFormatDTO.setWidth(30d);
        expressFormatDTO.setPicURL("picURL");
        // expressFormatDAO.addExpressFormat(expressFormatDTO);
        // expressFormatDAO.deleteExpressFormatByMainID(mainID);
    }

    //@Test
    public void productTypeBrandDAO() {
        ProductTypeBrandDTO productTypeBrandDTO = new ProductTypeBrandDTO();
        productTypeBrandDTO.setId(1l);
        productTypeBrandDTO.setbrandID("brandID");
        productTypeBrandDTO.setproductTypeID("99999");
        // productTypeBrandDAO.insertProductTypeBrand(productTypeBrandDTO);
        List<ProductTypeBrandVO> productTypeBrandList = productTypeBrandDAO.findProductTypeBrand(productTypeBrandDTO);
        // productTypeBrandDAO.deleteProductTypeBrandById(1l);
    }

    //@Test
    public void testproductPictureDAO() {
        ProductPictureDTO productPictureDTO = new ProductPictureDTO();
        productPictureDTO.setPicURL("");
        productPictureDTO.setProductID("b846fca63eea4f2e965b5146daceea7b");
        productPictureDTO.setName("test21");
        productPictureDTO.setItemID("910ece9271df4a618591490f8a6cf2cf");
        // productPictureDAO.insertProductPicture(productPictureDTO);
    }

    //@Test
    public void testmainDAO() {
        List<OrderVolumeVO> list = mainDAO.findOrderVolume(DateUtil.getDateAgo(7), DateUtil.getDateAgo(1));
        System.out.println(list.size());
        List<MemberCountVO> list1 = mainDAO.findMemberCount(DateUtil.getDateAgo(7), DateUtil.getDateAgo(1));
        System.out.println(list1.size());
        List<ProductStatisticsVO> list2 = mainDAO.findProductStatistics(DateUtil.getDateAgo(7), DateUtil.getDateAgo(1));
        System.out.println(list2.size());
        List<CustomerVO> list3 = mainDAO.findCustomerStatistics(DateUtil.getDateAgo(7), DateUtil.getDateAgo(1));
        System.out.println(list3.size());

    }
}
