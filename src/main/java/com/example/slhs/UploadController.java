package com.example.slhs;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("")
public class UploadController {
    @PostMapping("/multiupload")
    public List<String> handleFileUpload(@RequestParam("files") List<MultipartFile> files, HttpServletRequest request) {
        List<String> uploadedFiles = new ArrayList<>();
        if (files.isEmpty()) {
            uploadedFiles.add("上傳失敗：請選擇檔案");
            return uploadedFiles;
        }

        try {
            // 指定上傳路徑
            String uploadDir = request.getSession().getServletContext().getRealPath("/img");
//            File indexFile = new File(indexPath);
//            String parentPath = indexFile.getParent(); // 取得 index.html 的父目錄
//            String uploadDir = parentPath + File.separator + "img" + File.separator;
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            // 儲存檔案
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                File dest = new File(uploadDir + File.separator + fileName);
                file.transferTo(dest);
                System.out.println(dest);
                uploadedFiles.add("img/" + fileName);
            }
            return uploadedFiles;
        } catch (IOException e) {
            e.printStackTrace();
            uploadedFiles.add("上傳失敗：" + e.getMessage());
            return uploadedFiles;
        }

    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "上傳失敗：請選擇一個檔案";
        }

        try {
            // 指定上傳路徑,這裡設定的就是網域的部分，例如: 網址/img
            // String uploadDir = "src/main/resources/static/";
            String uploadDir = request.getSession().getServletContext().getRealPath("/img");
//            File indexFile = new File(indexPath);
//            String parentPath = indexFile.getParent(); // 取得 index.html 的父目錄
//            String uploadDir = parentPath + File.separator + "img" + File.separator;
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            // 儲存檔案的位置
            String fileName = file.getOriginalFilename();
            File dest = new File(uploadDir + File.separator + fileName);
            file.transferTo(dest);

            // String uploadedFilePath = dest.getAbsolutePath();
            System.out.println(uploadDir);
            return "img/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "上傳失敗：" + e.getMessage();
        }
    }

}
