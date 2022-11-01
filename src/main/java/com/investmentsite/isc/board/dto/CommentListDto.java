package com.investmentsite.isc.board.dto;

import lombok.Data;

@Data
public class CommentListDto {
    private Integer id;
    private String contents;
    private String writer;
    private String date;
}
