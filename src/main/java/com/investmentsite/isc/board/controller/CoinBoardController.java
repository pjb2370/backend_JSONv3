package com.investmentsite.isc.board.controller;

import com.investmentsite.isc.board.service.CoinBoardService;
import com.investmentsite.isc.board.dto.BoardDto;
import com.investmentsite.isc.board.dto.BoardListDto;
import com.investmentsite.isc.board.dto.CommentListDto;
import com.investmentsite.isc.board.vo.BoardInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/board/coin")
@RequiredArgsConstructor
@RestController
public class CoinBoardController {
    private final CoinBoardService coinBoardService;

    //게시글 작성
    @PostMapping("/post")
    public boolean boardPost(@RequestBody BoardInput boardInput) {
        try {
            return this.coinBoardService.boardPost(boardInput);
        } catch (SQLException e){
            return false;
        }
    }
    //댓글 작성
    @PostMapping("/post/comment")
    public boolean commentPost(@RequestBody BoardInput boardInput){
        return this.coinBoardService.commentPost(boardInput);
    }
    //게시글 전체 불러오기
    @GetMapping("/get")
    public List<BoardListDto> get() {
        return this.coinBoardService.findAll();
    }
    //게시글 1개 불러오기
    @GetMapping("/getid")
    public BoardDto idGet(@RequestBody BoardInput boardInput){
        return this.coinBoardService.findByIdToBoard(boardInput);
    }
    //id값의 댓글 불러오기
    @GetMapping("/getid/comment")
    public List<CommentListDto> commentGet(@RequestBody BoardInput boardInput){
        return this.coinBoardService.findByIdToComment(boardInput);
    }
    //게시글 수정
    @PatchMapping("/patch")
    public boolean boardPatch(@RequestBody BoardInput boardInput) {
        return this.coinBoardService.boardPatch(boardInput);
    }
    //댓글 수정
    @PatchMapping("/patch/comment")
    public boolean commentPatch(@RequestBody BoardInput boardInput){
        return this.coinBoardService.commentPatch(boardInput);
    }
    //게시글 삭제
    @DeleteMapping("/delete")
    public boolean boardDelete(@RequestBody BoardInput boardInput){
        return this.coinBoardService.boardDelete(boardInput);
    }
    //댓글 삭제
    @DeleteMapping("/delete/comment")
    public boolean commentDelete(@RequestBody BoardInput boardInput) {
        return this.coinBoardService.commentDelete(boardInput);
    }
}
