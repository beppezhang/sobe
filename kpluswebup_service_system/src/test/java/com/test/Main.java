package com.test;


public class Main extends InitConf {

//    private AdminUserDAO     adminUserDAO;
//
//    private AdminUserService adminUserService;
//
//    @Before
//    public void init() {
//        adminUserDAO = (AdminUserDAO) context.getBean("adminUserDAO");
//        adminUserService = (AdminUserService) context.getBean("adminUserServiceImpl");
//
//    }

//    @Test
//    public void test1() {
//        List<AdminUserDTO> list = adminUserDAO.findAdminUser(null);
//        System.out.println(list.size());
//    }
//
//    @Test
//    public void test2() {
//        boolean is = adminUserService.doLogin("1", "2");
//        System.out.println(is);
//    }
    
    
    public static void main(String[] args) {
        String str1="/admin/permission.htm";
        String str2="permission.do";
        System.out.println(str1.split("\\.")[0]);
        System.out.println(str1.contains(str2));
    }
}
