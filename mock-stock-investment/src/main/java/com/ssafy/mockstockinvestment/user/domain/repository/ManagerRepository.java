package com.ssafy.mockstockinvestment.user.domain.repository;

import com.ssafy.mockstockinvestment.user.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}
