package com.investmentsite.isc.user.dao;

import com.investmentsite.isc.user.domain.Sluser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SluserRepository extends JpaRepository<Sluser, Integer> {
    Sluser findByUserId(String userId);
}
