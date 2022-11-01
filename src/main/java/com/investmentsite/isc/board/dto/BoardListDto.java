package com.investmentsite.isc.board.dto;

import lombok.Data;

@Data
public class BoardListDto {
    private Integer id;
    private String title;
    private String writer;
    private String date;
    private Integer views;
}
