package com.investmentsite.isc.board.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
public class UploadVO {
    private String nmae;
    private MultipartFile file;
}
