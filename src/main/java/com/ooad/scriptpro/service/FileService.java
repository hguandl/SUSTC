package com.ooad.scriptpro.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FileService {

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
    public void download(HttpServletRequest request, HttpServletResponse response){
        //todo
    }

}
