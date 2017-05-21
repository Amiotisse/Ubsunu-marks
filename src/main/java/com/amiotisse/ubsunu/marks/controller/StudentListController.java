package com.amiotisse.ubsunu.marks.controller;

import com.amiotisse.ubsunu.marks.ApiErrors;
import com.amiotisse.ubsunu.marks.exception.TitleAlreadyTakenException;
import com.amiotisse.ubsunu.marks.exception.UserTypeNotAllowedException;

import com.amiotisse.ubsunu.marks.model.Student;
import com.amiotisse.ubsunu.marks.model.StudentList;
import com.amiotisse.ubsunu.marks.model.UserToken;
import com.amiotisse.ubsunu.marks.service.PrivateListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author himna
 * @since 5/21/2017.
 */
@RestController
public class StudentListController {


    private PrivateListService<Student,StudentList> service;


    public StudentListController(PrivateListService<Student, StudentList> studentListSaverService) {
        this.service = studentListSaverService;
    }

    @RequestMapping(path = "/teacher/student" , method = RequestMethod.POST)
    public ResponseEntity<?> saveStudentList  (
            @RequestAttribute("claims") UserToken userToken,
            @RequestBody List<Student> list,
            @RequestParam String title
    ){
        try {
            service.save( title, userToken, list );
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserTypeNotAllowedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ApiErrors.USER_TYPE_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        } catch (TitleAlreadyTakenException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ApiErrors.LIST_TITLE_TAKEN, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teacher/student/titles" ,method = RequestMethod.GET)
    public List<String> getListStudentListTitles (@RequestAttribute("claims") UserToken userToken){
        return service.getTitles(userToken);
    }

    @RequestMapping(path = "/teacher/student" , method = RequestMethod.GET)
    public StudentList getStudentList  (
            @RequestAttribute("claims") UserToken userToken,
            @RequestParam String title
    ){
        return service.getPrivateList(title , userToken);
    }

}
