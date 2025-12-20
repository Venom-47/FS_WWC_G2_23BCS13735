package Controller;

import com.miniproject.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final Map<Integer, Student> studentStore = new ConcurrentHashMap<>();

    @PostMapping
    public ResponseEntity<?> registerStudent( @RequestBody Student student) {
        if (studentStore.containsKey(student.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: Student with ID " + student.getId() + " already exists.");
        }

        studentStore.put(student.getId(), student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentStore.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        Student student = studentStore.get(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Student with ID " + id + " not found.");
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        if (!studentStore.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Cannot delete. Student with ID " + id + " does not exist.");
        }

        studentStore.remove(id);
        return ResponseEntity.ok("Student with ID " + id + " has been deleted successfully.");
    }
}

