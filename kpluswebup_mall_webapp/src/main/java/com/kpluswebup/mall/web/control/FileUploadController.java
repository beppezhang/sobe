package com.kpluswebup.mall.web.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/file")
public class FileUploadController extends BaseController{

    /**
     * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用<input type="file" name="myfiles"/>
     * 上传文件完毕后返回给前台[0`filepath],0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
     */

    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam
    MultipartFile[] myfiles, HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String dir = "/file/" + DateUtil.getDateAgo(0);
        String realPath =  request.getSession().getServletContext().getRealPath(dir);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String originalFilename = null;
        String uuid = UUIDUtil.getUUID();
        String prefix = null;
        for (MultipartFile myfile : myfiles) {
            if (myfile.isEmpty()) {
                out.print("1`请选择文件后上传");
                out.flush();
                return null;
            } else {
                originalFilename = myfile.getOriginalFilename();
                prefix = "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                System.out.println("文件原名: " + originalFilename);
                System.out.println("文件名称: " + myfile.getName());
                System.out.println("文件长度: " + myfile.getSize());
                System.out.println("文件类型: " + myfile.getContentType());
                System.out.println("文件后缀: " + prefix);
                System.out.println("========================================");
                try {
                    // 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    // 此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
                    File newFile = new File(realPath, uuid + prefix);
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), newFile);
//                    try {
//                        ImageTools.cutImage(50, 50,newFile.getPath(), newFile.getPath()+"_50x50");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                } catch (IOException e) {
                    System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
                    e.printStackTrace();
                    out.print("1`文件上传失败，请重试！！");
                    out.flush();
                    return null;
                }
            }
        }
        out.print("0`" + dir + "/" + uuid + prefix);
        out.flush();
        return null;
    }
}
