package com.amiotisse.ubsunu.marks.controller;

import com.amiotisse.ubsunu.marks.ApiErrors;
import com.amiotisse.ubsunu.marks.model.Mark;
import com.amiotisse.ubsunu.marks.model.MarkList;
import com.amiotisse.ubsunu.marks.model.UserToken;
import com.amiotisse.ubsunu.marks.model.UserType;
import com.amiotisse.ubsunu.marks.model.builder.MarkListBuilder;
import com.amiotisse.ubsunu.marks.repository.MarkListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author himna
 * @since 5/6/2017.
 */
//@RestController
@RequestMapping(value = "/teacher/marks")
public class PrivateMarkListController {

    private MarkListRepository markListRepository;

    public PrivateMarkListController(MarkListRepository markListRepository) {
        this.markListRepository = markListRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save (
            @RequestAttribute("claims") UserToken userToken,
            @RequestBody List<Mark> marks,
            @RequestParam String title
    ){
        if (userToken.getUserType()  != UserType.teacher)
            return new ResponseEntity<>(ApiErrors.USER_TYPE_NOT_ALLOWED, HttpStatus.FORBIDDEN);
        if (markListRepository.exists(title))
            return new ResponseEntity<>(ApiErrors.MARKS_LIST_TITLE_TAKEN , HttpStatus.BAD_REQUEST);

        markListRepository.save(
                new MarkListBuilder()
                .setTitle(title)
                .setOwnerUserId(userToken.getId())
                .addMark(marks)
                .build()
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
