package com.ooad.scriptpro.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.thymeleaf.util.DateUtils;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileService {
    public String upload(MultipartFile uploadFile) throws  IOException {
        if(uploadFile!=null){
            try{
                String fileName = uploadFile.getOriginalFilename();
                String filePath = "/tmp/scripts";
                File file = new File(filePath);
                if(!file.exists()){
                    file.mkdirs();
                }
                file = new File(filePath, fileName);
                boolean isCreateSuccess = file.createNewFile();
                if(isCreateSuccess){
                    uploadFile.transferTo(file);
                }
                FileReader reader = new FileReader(file);
                BufferedReader bReader = new BufferedReader(reader);
                StringBuilder sb = new StringBuilder();
                String s = "";
                while ((s =bReader.readLine()) != null) {
                    sb.append(s + "\n");

                }
                bReader.close();
                String str = sb.toString();
                return str;
            }catch (Exception e){

                e.printStackTrace();
                return "";
            }
        }else{
            return "";
        }
    }
    /*
    public String upload(MultipartFile uploadFile) throws IOException {
        String folder = "/tmp/scripts/";
        InputStream fis = null;
        OutputStream outputStream = null;
        try{
            fis = uploadFile.getInputStream();
            outputStream = new FileOutputStream(folder+uploadFile.getOriginalFilename());
            IOUtils.copy(fis, outputStream);
            return folder+uploadFile.getOriginalFilename();
        }catch (IOException e){
            // return "" means file upload error
            return "";
        }finally{
            if(fis!=null){
                try{
                    fis.close();
                }catch(IOException e){
                    return "";
                }
            }
            if(outputStream!=null){
                try{
                    outputStream.close();
                }catch (IOException e){
                    return "";
                }
            }
        }
    }
    */

    public void download(HttpServletRequest request, HttpServletResponse response){
        //todo
    }

}
