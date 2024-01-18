package com.example.slhs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/student")
    public List getOne(){
        //@RequestParam String sid,@RequestParam String name
        //String sql = "INSERT INTO student(學號,姓名) values('" + sid + "','" + name + "')";
        //jdbcTemplate.update(sql);
        List rows = jdbcTemplate.queryForList("SELECT * FROM student");
        return rows;
    }

    @PostMapping("/student")
    public List postOne(@RequestParam String sid,@RequestParam String name){
        String sql = "INSERT INTO student(學號,姓名) values('" + sid + "','" + name + "')";
        jdbcTemplate.update(sql);
        List rows = jdbcTemplate.queryForList("SELECT * FROM student");
        return rows;
    }
}
