package com.investmentsite.isc.board.dao;

import com.investmentsite.isc.board.domain.CoinBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinBoardCommentRepository extends JpaRepository<CoinBoardComment, Integer> {
    List<CoinBoardComment> findAllByBoardIndex(Integer boardIndex);
    List<CoinBoardComment> deleteAllByBoardIndex(Integer boardIndex);
}
