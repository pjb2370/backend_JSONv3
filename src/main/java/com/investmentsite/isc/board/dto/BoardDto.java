package com.investmentsite.isc.board.dto;

import lombok.Data;

@Data
public class BoardDto {
    private Integer id;
    private String title;
    private String contents;
    private String writer;
    private String date;
    private Integer views;
}
