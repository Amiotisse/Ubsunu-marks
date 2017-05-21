package com.amiotisse.ubsunu.marks.controller;

import com.amiotisse.ubsunu.marks.ApiErrors;
import com.amiotisse.ubsunu.marks.model.MarkList;
import com.amiotisse.ubsunu.marks.model.builder.MarkListBuilder;
import com.amiotisse.ubsunu.marks.repository.MarkListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author himna
 * @since 5/6/2017.
 */
//@RestController
@RequestMapping(path = "/marks")
public class PublicMarkListController {

    private static Logger log = LoggerFactory.getLogger(PublicMarkListController.class);
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

    @RequestMapping(value = "/list/search", method = RequestMethod.GET)
    public ResponseEntity<List<String>> search (@RequestParam("title") String title){
        log.info("GET /marks/list/search?title={}" , title);
        return new ResponseEntity<>(
                markListRepository
                        .findAll(
                                Example.of(
                                        new MarkListBuilder()
                                                .setOwnerUserId(null)
                                                .setTitle(title)
                                                .setMarks(null)
                                                .build(),
                                        ExampleMatcher
                                                .matchingAll()
                                                .withIgnoreNullValues()
                                                .withIgnoreCase()
                                                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                                )
                        )
                        .stream()
                        .map(markList -> markList.getTitle())
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}
