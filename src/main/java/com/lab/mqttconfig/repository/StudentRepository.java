package com.lab.mqttconfig.repository;

import com.lab.mqttconfig.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}