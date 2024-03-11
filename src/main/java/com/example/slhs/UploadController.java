package com.example.slhs;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin("")
public class UploadController {
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return "上傳失敗：請選擇一個檔案";
        }

        try {
            // 指定上傳路徑
            // String uploadDir = "src/main/resources/static/";
            String indexPath = request.getSession().getServletContext().getRealPath("/img");
            File indexFile = new File(indexPath);
            String parentPath = indexFile.getParent(); // 取得 index.html 的父目錄
            String uploadDir =parentPath + File.separator + "img" + File.separator;
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            // 儲存檔案
            String fileName = file.getOriginalFilename();
            File dest = new File(uploadDir + File.separator + fileName);
            file.transferTo(dest);

            String uploadedFilePath = dest.getAbsolutePath();
            return uploadedFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "上傳失敗：" + e.getMessage();
        }
    }

}
