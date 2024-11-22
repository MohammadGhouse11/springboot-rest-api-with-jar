package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping(value="/students")
public class StudentREController {

    //REST API Return student object as a JSON to client
    @GetMapping("/student")
    public ResponseEntity <Student> getStudent(){
        Student student = new Student(1,"Mohammad","Ghouse");
        return ResponseEntity.ok(student);
    }

    //REST API Return List of Student as a JSON to client
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Mohammad","Ghouse"));
        students.add(new Student(2,"Mohammad","Ali"));
        students.add(new Student(3,"John","Cena"));
        return ResponseEntity.ok(students);
    }

    //REST API with Path Variable
    //{id}--> URI Template Variable
    //http://localhost:8081/students/1
    @GetMapping("{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable int id){
        Student student= new Student(id,"Mohammad","Ghouse");
        return ResponseEntity.ok(student);
    }

    //REST API with Multiple Path Variables
    //http://localhost:8081/students/09/Mohammad/Ghouse
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> multiplePathVariables(@PathVariable("id")int studentId,
                                         @PathVariable("first-name") String firstName,
                                         @PathVariable("last-name") String lastName){
       Student student=new Student(studentId,firstName,lastName);
       return ResponseEntity.ok(student);
    }

    //REST API to handle Query Paramter
    //http://localhost:8081/students/query?id=1
    //?id=1 -->Query Parameter
    @GetMapping("query")
    public ResponseEntity<Student> queryParamter(@RequestParam("id") int studentId){
        Student student= new Student(studentId,"Mohammad1","Ghouse1");
        return ResponseEntity.ok(student);
    }

    //REST API With Multiple query paramters
    //http://localhost:8081/students/query?id=1&firstName=Mohammad&lastName=Ghouse
    @GetMapping("multiple")
    public ResponseEntity<Student> multipleQuery(@RequestParam int id,
                                 @RequestParam String firstName,
                                 @RequestParam("last-name") String lastName) {
        System.out.println(id+firstName+lastName);
        Student student= new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //REST API Handles Http Post Request
    @PostMapping("/create")

    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    //REST API Handles Http Put Request
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student student){
        Student existingStudent = new Student(id,"Mohammad","Ghouse");
        System.out.println(existingStudent.getId());
        System.out.println(existingStudent.getFirstName());
        System.out.println(existingStudent.getLastName());
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        System.out.println("----------------");
        System.out.println(existingStudent.getId());
        System.out.println(existingStudent.getFirstName());
        System.out.println(existingStudent.getLastName());
        return ResponseEntity.ok(existingStudent);
    }

    //REST API Handles Incoming delete Request
   @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id){
        return ResponseEntity.ok("Student deleted successfully.."+id);
    }
}
