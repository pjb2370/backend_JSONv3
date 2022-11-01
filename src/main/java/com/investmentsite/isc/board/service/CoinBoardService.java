package com.investmentsite.isc.board.service;

import com.investmentsite.isc.board.dao.CoinBoardCommentRepository;
import com.investmentsite.isc.board.dao.CoinBoardRepository;
import com.investmentsite.isc.board.domain.CoinBoard;
import com.investmentsite.isc.board.domain.CoinBoardComment;
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
public class CoinBoardService {
    private final CoinBoardRepository coinBoardRepository;
    private final CoinBoardCommentRepository commentRepository;
    private final ModelMapper modelMapper;
    LocalDate now = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formatedNow = now.format(formatter);

    //게시글 작성
    public boolean boardPost(BoardInput boardInput) throws SQLException {
        CoinBoard C1 = new CoinBoard();
        C1.setTitle(boardInput.getTitle());
        C1.setContents(boardInput.getContents());
        C1.setWriter(boardInput.getWriter());
        C1.setViews(0);
        C1.setDate(formatedNow);
        this.coinBoardRepository.save(C1);
        return true;
    }
    //댓글 작성
    public boolean commentPost(BoardInput boardInput){
        Optional<CoinBoard> optionalCoinBoard = this.coinBoardRepository.findById(boardInput.getId());
        if (optionalCoinBoard.isPresent()){
            CoinBoardComment Cc1 = new CoinBoardComment();
            Cc1.setContents(boardInput.getContents());
            Cc1.setDate(formatedNow);
            Cc1.setWriter(boardInput.getWriter());
            Cc1.setBoardIndex(boardInput.getId());
            this.commentRepository.save(Cc1);
            return true;
        } else {
            return false;
        }
    }
    // 게시글 전체 불러오기
    public List<BoardListDto> findAll() {
        List<CoinBoard> coinBoard = coinBoardRepository.findAll();
        List<BoardListDto> boardListDto = coinBoard.stream()
                .map(coinBoard1 -> modelMapper.map(coinBoard1, BoardListDto.class))
                .collect(Collectors.toList());
        return boardListDto;
    }
    //게시글 1개 불러오기
    public BoardDto findByIdToBoard(BoardInput boardInput){
        CoinBoard coinBoard = coinBoardRepository.findById(boardInput.getId()).get();
        coinBoard.setViews(coinBoard.getViews().intValue() +1 );
        BoardDto boardDto = modelMapper.map(coinBoard, BoardDto.class);
        return boardDto;
    }
    //게시글 1개의 댓글 불러오기
    public List<CommentListDto> findByIdToComment(BoardInput boardInput){
        Optional<CoinBoardComment> opcomment = this.commentRepository.findById(boardInput.getId());
        opcomment.isPresent();
        List<CoinBoardComment> coinComment = commentRepository.findAllByBoardIndex(boardInput.getId());
        List<CommentListDto> commentListDto = coinComment.stream()
                .map(coinComments1 -> modelMapper.map(coinComments1, CommentListDto.class))
                .collect(Collectors.toList());
        return commentListDto;
    }
    //게시글 수정
    public boolean boardPatch(BoardInput boardInput) {
        Optional<CoinBoard> opCoinpa = coinBoardRepository.findById(boardInput.getId());
        //null값인지 아닌지 체크
        if (opCoinpa.isPresent()){
            //작성자가 맞는지 아닌지 체크
            if (boardInput.getWriter().equals(opCoinpa.get().getWriter())){
                CoinBoard P1 = opCoinpa.get();
                P1.setTitle(boardInput.getTitle());
                P1.setContents(boardInput.getContents());
                P1.setWriter(boardInput.getWriter());
                P1.setDate(formatedNow);
                coinBoardRepository.save(P1);
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
        Optional<CoinBoardComment> opCommentpa = commentRepository.findById(boardInput.getId());
        if (opCommentpa.isPresent()){
            if (boardInput.getWriter().equals(opCommentpa.get().getWriter())){
                CoinBoardComment Pp1 = opCommentpa.get();
                Pp1.setContents(boardInput.getContents());
                Pp1.setWriter(boardInput.getWriter());
                Pp1.setDate(formatedNow);
                Pp1.setBoardIndex(Pp1.getBoardIndex());
                commentRepository.save(Pp1);
                return true;
            }else {
                return false;
            }
        } else {
            return false;
        }
    }
    //게시글 삭제
    public boolean boardDelete(BoardInput boardInput){
        Optional<CoinBoard> opCoinde = this.coinBoardRepository.findById(boardInput.getId());
        if (opCoinde.isPresent()){
            if (boardInput.getWriter().equals(opCoinde.get().getWriter())){
                this.coinBoardRepository.deleteById(boardInput.getId());
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
        Optional<CoinBoardComment> opCommentde = this.commentRepository.findById(boardInput.getId());
        if (opCommentde.isPresent()){
            this.coinBoardRepository.deleteById(boardInput.getId());
            return true;
        } else {
            return false;
        }
    }
}
