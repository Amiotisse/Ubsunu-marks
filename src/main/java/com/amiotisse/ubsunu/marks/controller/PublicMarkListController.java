package com.amiotisse.ubsunu.marks.controller;

import com.amiotisse.ubsunu.marks.ApiErrors;
import com.amiotisse.ubsunu.marks.model.MarkList;
import com.amiotisse.ubsunu.marks.repository.MarkListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author himna
 * @since 5/6/2017.
 */
//@RestController
@RequestMapping(value = "/marks")
public class PublicMarkListController {

    private MarkListRepository markListRepository;

    public PublicMarkListController(MarkListRepository markListRepository) {
        this.markListRepository = markListRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list (@RequestParam("title") String title){
        MarkList list = markListRepository.findOne(title);

        if (list == null) return new ResponseEntity<>(ApiErrors.MARKS_LIST_NOT_FOUND , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

}
