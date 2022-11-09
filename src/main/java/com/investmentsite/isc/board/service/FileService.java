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
    public String upload(MultipartFile file){
        String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
        long size = file.getSize(); //파일 사이즈
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = sdf.format(date);
        String datePath = str.replace("-", File.separator);

        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        /* 저장 장소 설정 (폴더 생성) */
        String uploadFolder = "C:\\SCsite\\image";
        File uploadPath = new File(uploadFolder, datePath);
        if (uploadPath.exists() == false){
        uploadPath.mkdirs();
    }
		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */

    UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    String[] uuids = uuid.toString().split("-");

    String uniqueName = uuids[0];
        System.out.println("생성된 고유문자열" + uniqueName);
        System.out.println("확장자명" + fileExtension);


    // File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전

    File saveFile = new File(uploadFolder + "\\" + datePath + "\\"+ uniqueName + fileExtension);  // 적용 후
        try {
        file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
    } catch (IllegalStateException e) {
        e.printStackTrace();
    } catch (
    IOException e) {
        e.printStackTrace();
    }
        return "file";
}
}
