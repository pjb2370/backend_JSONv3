package com.investmentsite.isc.board.controller;

import com.investmentsite.isc.board.service.BoardService;
import com.investmentsite.isc.board.vo.BoardInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequestMapping("/board/{lcategory}/{mcategory}")
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    //게시글 작성
    @PostMapping("/post")
    public boolean boardPost(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory, @RequestBody BoardInput boardInput){
        try {
            return this.boardService.boardPost(lcategory, mcategory, boardInput);
        } catch (SQLException e) {
            return false;
        }
    }
    //댓글 작성
    @PostMapping("/post/comment")
    public boolean commentPost(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory, @RequestBody BoardInput boardInput){
        return this.boardService.commentPost(boardInput);
    }
    //게시글 전체 불러오기
    //게시글 1개 불러오기
    //1개의 게시글에 있는 댓글 불러오기
    //게시글 수정
    //댓글 수정
    //게시글 삭제
    //댓글 삭제
}
