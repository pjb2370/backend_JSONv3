package com.investmentsite.isc.board.dao;

import com.investmentsite.isc.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findByTitle(String title);
    List<Board> findAllByLcategoryAndMcategory(String lcategory, String mcategory);
    Board findByIdAndLcategoryAndMcategory(Integer id, String lcategory, String mcategory);
}
