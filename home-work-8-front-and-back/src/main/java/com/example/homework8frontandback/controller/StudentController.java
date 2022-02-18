package com.example.homework8frontandback.controller;

import com.example.homework8frontandback.entity.Student;
import com.example.homework8frontandback.repository.StudentRepository;
import com.example.homework8frontandback.rest.ErrorDto;
import com.example.homework8frontandback.rest.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController (StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Student> getAll () {
        return studentRepository.findAll ();
    }

    @GetMapping("/{studentId}/id")
    @ResponseStatus(HttpStatus.OK)
    public Student getById (@PathVariable("studentId") Long studentId) {
        return studentRepository.findById (studentId)
                .orElseThrow (() -> new NotFoundException (String.format ("Student with id = %s not exist", studentId)));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Student create (@RequestBody Student student) {
        if (student.getId () != null) {
            throw new IllegalStateException ("Can`t create student with id not null");
        }
        return studentRepository.save (student);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Student update (@RequestBody Student student) {
        if (student.getId () == null) {
            throw new IllegalStateException ("Can`t update student with id null");
        }
        return studentRepository.save (student);
    }

    @DeleteMapping("/{studentId}/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("studentId") Long studentId) {
        studentRepository.deleteById (studentId);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundExceptionHandler (NotFoundException ex) {
        return new ErrorDto (ex.getMessage ());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto illegalArgumentExceptionHandler (IllegalArgumentException ex) {
        return new ErrorDto (ex.getMessage ());
    }


}
