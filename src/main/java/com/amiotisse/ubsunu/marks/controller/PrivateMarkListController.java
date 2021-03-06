package com.amiotisse.ubsunu.marks.controller;

import com.amiotisse.ubsunu.marks.ApiErrors;
import com.amiotisse.ubsunu.marks.exception.TitleAlreadyTakenException;
import com.amiotisse.ubsunu.marks.exception.UserTypeNotAllowedException;
import com.amiotisse.ubsunu.marks.model.Mark;
import com.amiotisse.ubsunu.marks.model.MarkList;
import com.amiotisse.ubsunu.marks.model.TitleCoef;
import com.amiotisse.ubsunu.marks.model.UserToken;
import com.amiotisse.ubsunu.marks.service.MarkListService;
import com.amiotisse.ubsunu.marks.service.PrivateListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author himna
 * @since 5/6/2017.
 */
@RestController
public class PrivateMarkListController {

    private PrivateListService<Mark , MarkList> service;
    private MarkListService markListService ;

    public PrivateMarkListController(
            PrivateListService<Mark, MarkList> service,
            MarkListService markListService
    ) {
        this.service = service;
        this.markListService = markListService;
    }

    @RequestMapping(path = "/teacher/marks" , method = RequestMethod.POST)
    public ResponseEntity<?> saveMarkList(
            @RequestAttribute("claims") UserToken userToken,
            @RequestBody List<Mark> marks,
            @RequestParam String title
    ){
        try {
            MarkList markList = service.save( title, userToken, marks );
            markListService.sendMails(markList);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserTypeNotAllowedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ApiErrors.USER_TYPE_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        } catch (TitleAlreadyTakenException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ApiErrors.LIST_TITLE_TAKEN, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teacher/marks/average" , method = RequestMethod.POST)
    public ResponseEntity<?> saveMarkListsAverage(
            @RequestAttribute("claims") UserToken userToken,
            @RequestBody List<TitleCoef> titleCoefList,
            @RequestParam String title
    ){
        try {
            MarkList markList = markListService.computeAvrageAndSave(title, userToken, titleCoefList);
            markListService.sendMails(markList);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserTypeNotAllowedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ApiErrors.USER_TYPE_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        } catch (TitleAlreadyTakenException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ApiErrors.LIST_TITLE_TAKEN, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = "/teacher/marks/titles" ,method = RequestMethod.GET)
    public List<String> getMarkListTitles (@RequestAttribute("claims") UserToken userToken){
        return service.getTitles(userToken);
    }

    @RequestMapping(path = "/teacher/marks" ,method = RequestMethod.GET)
    public MarkList getMarkList (
            @RequestAttribute("claims") UserToken userToken,
            @RequestParam String title
    ){
        return service.getPrivateList(title , userToken);
    }
}
