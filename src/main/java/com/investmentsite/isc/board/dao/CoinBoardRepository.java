package com.investmentsite.isc.board.dao;

import com.investmentsite.isc.board.domain.CoinBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinBoardRepository extends JpaRepository<CoinBoard, Integer> {
    CoinBoard findByTitle(String title);



}
