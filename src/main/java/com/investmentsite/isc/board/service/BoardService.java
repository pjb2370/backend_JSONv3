package com.investmentsite.isc.board.service;

import com.investmentsite.isc.board.dao.BoardCommentRepository;
import com.investmentsite.isc.board.dao.BoardRepository;
import com.investmentsite.isc.board.domain.Board;
import com.investmentsite.isc.board.domain.BoardComment;
import com.investmentsite.isc.board.dto.BoardDto;
import com.investmentsite.isc.board.dto.BoardListDto;
import com.investmentsite.isc.board.dto.CommentListDto;
import com.investmentsite.isc.board.vo.BoardInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardCommentRepository commentRepository;
    private final ModelMapper modelMapper;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formatedNow = now.format(formatter);

    //게시글 작성
    public boolean boardPost(String lcategory, String mcategory, BoardInput boardInput) throws SQLException{
        Board B1 = new Board();
        B1.setTitle(boardInput.getTitle());
        B1.setContents(boardInput.getContents());
        B1.setWriter(boardInput.getWriter());
        B1.setViews(0);
        B1.setDate(formatedNow);
        B1.setLcategory(lcategory);
        B1.setMcategory(mcategory);
        this.boardRepository.save(B1);
        return true;
    }
    //댓글 작성
    public boolean commentPost(BoardInput boardInput){
        Optional<Board> optionalBoard = this.boardRepository.findById(boardInput.getId());
        if (optionalBoard.isPresent()){
            BoardComment bc1 = new BoardComment();
            bc1.setContents(boardInput.getContents());
            bc1.setDate(formatedNow);
            bc1.setWriter(boardInput.getWriter());
            bc1.setBoardIndex(boardInput.getId());
            this.commentRepository.save(bc1);
            return true;
        }else {
            return false;
        }
    }
    //게시글 전체 불러오기
    public List<BoardListDto> findAll(String lcategory, String mcategory){
        List<Board> board = boardRepository.findAllByLcategoryAndMcategory(lcategory, mcategory);
        List<BoardListDto> boardListDto = board.stream()
                        .map(BoardListDto1 -> modelMapper.map(BoardListDto1, BoardListDto.class))
                        .collect(Collectors.toList());
        return boardListDto;
    }
    //게시글 1개 불러오기
    public BoardDto findByIdToBoard(String lcategory, String mcategory, Integer id){
        Board board = this.boardRepository.findByIdAndLcategoryAndMcategory(id, lcategory, mcategory);
        board.setViews(board.getViews() +1);
        BoardDto boardDto = modelMapper.map(board, BoardDto.class);
        return boardDto;
    }
    //게시글 1개의 댓글 불러오기
    public List<CommentListDto> findByIdToComment(String lcategory, String mcategory, BoardInput boardInput){
        Optional<Board> opComment = Optional.of(this.boardRepository.findByIdAndLcategoryAndMcategory(boardInput.getId(), lcategory, mcategory));
        opComment.isPresent();
            List<BoardComment> comment = commentRepository.findAllByBoardIndex(boardInput.getId());
            List<CommentListDto> boardListDto = comment.stream()
                    .map(comments1 -> modelMapper.map(comments1, CommentListDto.class))
                    .collect(Collectors.toList());
        return boardListDto;
    }
    //게시글 수정
    public boolean boardPatch(String lcategory, String mcategory, BoardInput boardInput){
        Optional<Board> opBoardpa = Optional.of(this.boardRepository.findByIdAndLcategoryAndMcategory(boardInput.getId(), lcategory, mcategory));
        if (opBoardpa.isPresent()){
            if (boardInput.getWriter().equals(opBoardpa.get().getWriter())){
                Board p1 = opBoardpa.get();
                p1.setTitle(boardInput.getTitle());
                p1.setContents(boardInput.getContents());
                p1.setWriter(boardInput.getWriter());
                p1.setDate(formatedNow);
                boardRepository.save(p1);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    //댓글 수정
    public boolean commentPatch(BoardInput boardInput){
        Optional<BoardComment> opCommentpa = commentRepository.findById(boardInput.getId());
        if (opCommentpa.isPresent()){
            if (boardInput.getWriter().equals(opCommentpa.get().getWriter())){
                BoardComment cp1 = opCommentpa.get();
                cp1.setContents(boardInput.getContents());
                cp1.setWriter(boardInput.getWriter());
                cp1.setDate(formatedNow);
                cp1.setBoardIndex(cp1.getBoardIndex());
                commentRepository.save(cp1);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    //게시글 삭제
    public boolean boardDelete(String lcategory, String mcategory, BoardInput boardInput){
        Optional<Board> opBoardde = Optional.of(this.boardRepository.findByIdAndLcategoryAndMcategory(boardInput.getId(), lcategory, mcategory));
        if (opBoardde.isPresent()){
            if (boardInput.getWriter().equals(opBoardde.get().getWriter())){
                this.boardRepository.deleteById(boardInput.getId());
                this.commentRepository.deleteAllByBoardIndex(boardInput.getId());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    //댓글 삭제
    public boolean commentDelete(BoardInput boardInput){
        Optional<BoardComment> opCommentde = this.commentRepository.findById(boardInput.getId());
        if (opCommentde.isPresent()){
            this.commentRepository.deleteById(boardInput.getId());
            return true;
        } else {
            return false;
        }
    }
}
