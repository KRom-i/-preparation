package app.controller;

import app.entity.Student;
import app.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController (StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getStudents () {
        return ResponseEntity.ok (studentService.getAllStudents ());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudent (@PathVariable("studentId") Long studentId) {
        return ResponseEntity.ok (studentService.getStudentById (studentId).get ());
    }

    @PostMapping()
    public ResponseEntity<?> saveStudent (@RequestBody Student student) {
        return ResponseEntity.ok (studentService.saveStudent (student));
    }

    @DeleteMapping("/{studentId}")
    public HttpStatus deleteStudentById (@PathVariable("studentId") Long studentId) {

        Student student = studentService.getStudentById (studentId)
                .orElseThrow (() -> new IllegalStateException (
                                String.format ("Student by ID [ %s ] not found.", studentId)
                        )
                );

        studentService.deleteStudent (student);
        return HttpStatus.OK;
    }


}
