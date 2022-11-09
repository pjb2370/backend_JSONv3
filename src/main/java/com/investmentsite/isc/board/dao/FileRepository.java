package com.investmentsite.isc.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileRepository extends JpaRepository {

    File deleteByFile(MultipartFile file);
}
