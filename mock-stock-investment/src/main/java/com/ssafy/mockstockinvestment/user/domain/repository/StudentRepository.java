package com.ssafy.mockstockinvestment.user.domain.repository;

import com.ssafy.mockstockinvestment.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

}
