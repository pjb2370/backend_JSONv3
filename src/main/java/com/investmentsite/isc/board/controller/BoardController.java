package com.investmentsite.isc.board.controller;

import com.investmentsite.isc.board.domain.Board;
import com.investmentsite.isc.board.dto.BoardDto;
import com.investmentsite.isc.board.dto.BoardListDto;
import com.investmentsite.isc.board.dto.CommentListDto;
import com.investmentsite.isc.board.service.BoardService;
import com.investmentsite.isc.board.service.FileService;
import com.investmentsite.isc.board.vo.BoardInput;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("/board/{lcategory}/{mcategory}")
@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;
    private final FileService fileService;

    //게시글 작성
    @PostMapping("/post")
    public boolean boardPost(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory, @RequestBody BoardInput boardInput){
        try {
            return this.boardService.boardPost(lcategory, mcategory, boardInput);
        } catch (SQLException e) {
            return false;
        }
    }
    //이미지 업로드
    @PostMapping("/post/file")
    public String postFile(@RequestParam("file") MultipartFile file) {
        return this.fileService.postFile(file);
    }

    //댓글 작성
    @PostMapping("/post/comment")
    public boolean commentPost(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory, @RequestBody BoardInput boardInput){
        return this.boardService.commentPost(boardInput);
    }
    //게시글 전체 불러오기
    @GetMapping("/get")
    public List<BoardListDto> get(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory){
        return this.boardService.findAll(lcategory, mcategory);
    }
    //게시글 1개 불러오기
    @GetMapping("/getid")
    public BoardDto boardget(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory, Integer id){
        return this.boardService.findByIdToBoard(lcategory, mcategory, id);
    }
    //1개의 게시글에 있는 댓글 불러오기
    @GetMapping("/getid/comment")
    public List<CommentListDto> commentGet(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory, @RequestBody BoardInput boardInput){
        return this.boardService.findByIdToComment(lcategory, mcategory, boardInput);
    }
    //게시글 수정
    @PatchMapping("/patch")
    public boolean boardPatch(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory, BoardInput boardInput){
        return this.boardService.boardPatch(lcategory, mcategory, boardInput);
    }
    //이미지 수정
    @PatchMapping("/patch/file")
    public String patchFile(@RequestParam("file") MultipartFile file){
        return this.fileService.patchFile(file);
    }
    //댓글 수정
    @PatchMapping("/patch/comment")
    public boolean commentPatch(@RequestBody BoardInput boardInput){
        return this.boardService.commentPatch(boardInput);
    }
    //게시글 삭제
    @DeleteMapping("/delete")
    public boolean boardDelete(@PathVariable("lcategory") String lcategory, @PathVariable("mcategory") String mcategory, @RequestBody BoardInput boardInput){
        return this.boardService.boardDelete(lcategory, mcategory, boardInput);
    }
    //이미지 삭제
    @DeleteMapping("/delete/file")
    public String deleteFile(@RequestParam("file")MultipartFile file) {
        return this.fileService.deleteFile(file);
    }
    //댓글 삭제
    @DeleteMapping("/delete/comment")
    public boolean commentDelete(@RequestBody BoardInput boardInput){
        return this.boardService.commentDelete(boardInput);
    }
}
