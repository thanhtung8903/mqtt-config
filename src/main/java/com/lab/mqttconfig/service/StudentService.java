package com.lab.mqttconfig.service;

import com.lab.mqttconfig.dto.AddFaceRequest;
import com.lab.mqttconfig.entity.Student;
import com.lab.mqttconfig.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<AddFaceRequest> getAllAddFaceRequests() {
        List<Student> students = studentRepository.findAll();

        return students.stream().map(student -> AddFaceRequest.builder()
                .customId(UUID.randomUUID().toString())
                .name(student.getName())
                .pic(student.getPic())
                .gender(student.getGender())
                .tempCardType(0)
                .personType(0)
                .cardType(0)
                .build()).toList();
    }
}
