package com.investmentsite.isc.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Date date = new Date();
    String str = sdf.format(date);
    String datePath = str.replace("-", File.separator);
    UUID uuid = UUID.randomUUID();
    String[] uuids = uuid.toString().split("-");
    String uniqueName = uuids[0];

    public String postFile(MultipartFile file){
        String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
        long size = file.getSize(); //파일 사이즈
        System.out.println("파일명 : "  + fileRealName);
        System.out.println("용량크기(byte) : " + size);
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        String uploadFolder = "C:\\SCsite\\image";
        File uploadPath = new File(uploadFolder, datePath);
        if (uploadPath.exists() == false){
            uploadPath.mkdirs();
        }
        File saveFile = new File(uploadFolder + "\\" + datePath + "\\"+ uniqueName + fileExtension);  // 적용 후
            try {
                file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
                return "file";
    }

    public String patchFile(MultipartFile file){
        String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
        long size = file.getSize(); //파일 사이즈
        System.out.println("파일명 : "  + fileRealName);
        System.out.println("용량크기(byte) : " + size);
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        String uploadFolder = "C:\\SCsite\\image";
        File uploadPath = new File(uploadFolder, datePath);
            if (uploadPath.exists() == false){
                uploadPath.mkdirs();
            }
        File patchFile = new File(uploadFolder + "\\" + datePath + "\\"+ uniqueName + fileExtension);
            try {
                file.transferTo(patchFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "file";
    }
    public String deleteFile(MultipartFile file) {
        String filePath = "C:\\SCsite\\image";
        File deleteFile = new File(filePath);
        if (deleteFile.exists()){
            deleteFile.delete();
            return "파일을 삭제하였습니다.";
        } else {
            return "파일이 존재하지 않습니다.";
        }
    }
}
