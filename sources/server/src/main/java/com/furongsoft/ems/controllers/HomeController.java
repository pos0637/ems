package com.furongsoft.ems.controllers;

import com.alibaba.fastjson.JSON;
import com.furongsoft.base.file.mappers.AttachmentDao;
import com.furongsoft.base.rbac.entities.Permission;
import com.furongsoft.base.rbac.entities.User;
import com.furongsoft.base.rbac.mappers.PermissionDao;
import com.furongsoft.base.rbac.mappers.ResourceDao;
import com.furongsoft.base.rbac.mappers.UserDao;
import com.furongsoft.ems.entities.cms.Company;
import com.furongsoft.ems.mappers.cms.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页控制器
 *
 * @author Alex
 */
@Controller
@ResponseBody
@RequestMapping("/home")
public class HomeController {
    private final UserDao userDao;
    private final PermissionDao permissionDao;
    private final CompanyDao companyDao;

    @Autowired
    public HomeController(UserDao userDao, PermissionDao permissionDao, CompanyDao companyDao) {
        this.userDao = userDao;
        this.permissionDao = permissionDao;
        this.companyDao = companyDao;
    }

    @RequestMapping("/index")
    public String index() {
        List<User> list = userDao.selectList(null);

        return "Hello, world! " + JSON.toJSONString(list);
    }

    @RequestMapping("/init")
    public String initialize() {
        /*
        User user = new User();
        user.setUserName("a");
        user.setPassword("b");
        user.setName("");
        user.setSalt("");
        userDao.insert(user);
        */

        Company company = new Company();
        company.setName("福州XXXX有限公司");
        company.setEmail("XXXX@XXX.com");
        company.setAddress("XXXX街XXXX号");
        company.setPhone("021-55988941");
        company.setCopyright("2018 www.xxxx.com");
        company.setCredential("京CP证xxxxxx号 京公安网备xxxxxxxxxx号");
        company.setSlogan1("科技铸造未来");
        company.setSlogan2("Technology to create the future, let us work together");
        company.setInformation("<div class=\"layui-col-md8\"> <div> <p> <span class=\"text\">&nbsp;&nbsp; 某某机械有限公司，成立于2008年，我公司为各行各业提供专业的it技术服务，经过3年多的努力与发展，已具一定的规模及实力，现拥有一支技术精湛的it服务团队，以卓越的服务品质、专业安全的技术服务实力，为不同群体的用户提供更高更优质的it服务。</span> </p> <p> <span class=\"text\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 我们的服务项目:企业电脑外包维护，电脑维修上门维修，电脑组装配件销售，网络工程综合布线，监控安装及维修，专业数据恢复，服务器局域网组建，打印机传真机维修，中小企业网站建设等多种it服务一体化公司。</span> </p> <p> <span class=\"text\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 24小时服务:为了支持客户的工作及日常运用，瑞宝电脑运用最先进的技术24小时为用户服务，并免费提供远程协助，电话支持。</span> </p> <p> <span class=\"text\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 我们的理念:“诚信为本、实力为先，全心全意为客户”，我们公司秉承客户至上、服务至上的经营理念，以卓越的it服务品质、专业的技术服务实力、技术精湛的客户服务团队，保障客户在信息时代的高速路上驰骋，又以稳固、发展、忠诚、高效、团结与创新的精神，尊重人才注重技术，使客户在享受信息科技发展最新成果的同时不断获得最大的收益。</span> </p> </div> </div> <div class=\"layui-col-md4\"> <div class=\"pic\"> <img src=\"http://y.hy755.cn/7344/diy/pics/20170722/1500709847.jpg\" border=\"0\" width=\"100%\"> </div> </div>");
        companyDao.insert(company);

        Permission pTest1 = new Permission();
        pTest1.setName("公司管理");
        pTest1.setPath("");
        pTest1.setType(0);
        pTest1.setState(0);
        permissionDao.insert(pTest1);

        Permission pTest3 = new Permission();
        pTest3.setParentId(pTest1.getId());
        pTest3.setName("公司信息");
        pTest3.setPath("dist/admin/cms/company/index.html");
        pTest3.setType(0);
        pTest3.setState(0);
        permissionDao.insert(pTest3);

        Permission pTest4 = new Permission();
        pTest4.setParentId(pTest1.getId());
        pTest4.setName("公司描述");
        pTest4.setPath("dist/admin/cms/profile/index.html");
        pTest4.setType(0);
        pTest4.setState(0);
        permissionDao.insert(pTest4);

        Permission pTest6 = new Permission();
        pTest6.setParentId(pTest1.getId());
        pTest6.setName("新闻分类");
        pTest6.setPath("dist/admin/cms/news/category/index.html");
        pTest6.setType(0);
        pTest6.setState(0);
        permissionDao.insert(pTest6);

        Permission pTest7 = new Permission();
        pTest7.setParentId(pTest1.getId());
        pTest7.setName("新闻");
        pTest7.setPath("dist/admin/cms/news/index.html");
        pTest7.setType(0);
        pTest7.setState(0);
        permissionDao.insert(pTest7);

        Permission pTest8 = new Permission();
        pTest8.setParentId(pTest1.getId());
        pTest8.setName("招聘信息");
        pTest8.setPath("dist/admin/cms/job/index.html");
        pTest8.setType(0);
        pTest8.setState(0);
        permissionDao.insert(pTest8);

        Permission pTest2 = new Permission();
        pTest2.setName("系统管理");
        pTest2.setPath("");
        pTest2.setType(0);
        pTest2.setState(0);
        permissionDao.insert(pTest2);

        Permission pTest5 = new Permission();
        pTest5.setParentId(pTest2.getId());
        pTest5.setName("权限管理");
        pTest5.setPath("dist/admin/system/permission/index.html");
        pTest5.setType(0);
        pTest5.setState(0);
        permissionDao.insert(pTest5);

        return "Successful";
    }
}
