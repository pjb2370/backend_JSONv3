package com.investmentsite.isc.board.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Data
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contents;
    @Column(length = 20)
    private String writer;
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private String date;
    private Integer boardIndex;
}
