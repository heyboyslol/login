package com.ssafy.mockstockinvestment.user.service;

import java.util.Optional;

import com.ssafy.mockstockinvestment.user.domain.Manager;
import com.ssafy.mockstockinvestment.user.domain.Student;
import com.ssafy.mockstockinvestment.user.domain.UserEnum;
import com.ssafy.mockstockinvestment.user.domain.repository.ManagerRepository;
import com.ssafy.mockstockinvestment.user.domain.repository.StudentRepository;
import com.ssafy.mockstockinvestment.user.dto.request.CreateUserRequest;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final ManagerRepository managerRepository;
    private final StudentRepository studentRepository;

    public String register(CreateUserRequest createUserRequest) {
        Optional<Manager> managerResult = managerRepository.findByEmail(createUserRequest.getEmail());
        Optional<Student> studentResult = studentRepository.findByEmail(createUserRequest.getEmail());
        if (managerResult.isPresent() || studentResult.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 메일이라능!");
        }

        if (createUserRequest.getTeacher()) {
            Manager manager = new Manager();
            manager.setEmail(createUserRequest.getEmail());
            // 비밀번호 암호화 하면 좋아용 ^.^
            manager.setUserPassword(createUserRequest.getPassword());
            manager.setUserRole(UserEnum.관리자);
            Manager savedManager = managerRepository.save(manager);
            return savedManager.getUserId();
        } else {
            Student student = new Student();
            student.setEmail(createUserRequest.getEmail());
            student.setUserPassword(createUserRequest.getPassword());
            student.setUserRole(UserEnum.학생);
            Student savedStudent = studentRepository.save(student);
            return savedStudent.getUserId();
        }
    }

}
