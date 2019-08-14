package com.learn.notes.controller;

import com.learn.notes.service.UploadDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    private UploadDaoImpl uploadDaoImpl;

    @RequestMapping("/upload")
    public String starMyNotes(Model model) {
        model.addAttribute("pic",uploadDaoImpl.getAllPicture());
        return "upload";
    }

    @RequestMapping(value="/uploads",produces= MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public Map<String, Object> uploads(@RequestParam(value = "blob", required = false) MultipartFile blob, HttpServletRequest request) throws FileNotFoundException {
        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
//        System.out.println("path:"+path.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static/images/upload/");
        if(!upload.exists()) upload.mkdirs();
        String uploadPath = upload.getAbsolutePath();

        Map<String, Object> result = new HashMap<>();
        if(!blob.isEmpty()){
            if(blob.getSize() >= 5242880){
                result.put("state", "3");
                result.put("msg", "请上传小于5M的文件！");
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsssss");
                String id = sdf.format(new Date());
                String filename = blob.getOriginalFilename();
                String[] endFileName = filename.split("\\.");
                String finalName = id + "." + endFileName[endFileName.length-1];
                File filepath = new File(uploadPath, finalName);
                if(!filepath.getParentFile().exists()){
                    filepath.getParentFile().mkdirs();
                }
                try {
                    blob.transferTo(new File(uploadPath + File.separator + finalName));
                    uploadDaoImpl.addPicName(finalName);
                    result.put("state", "1");
                    result.put("msg", "上传成功！");
                    result.put("fileAddress", "/static/images/upload/"+finalName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            result.put("state", "2");
        }
        return result;
    }
}
