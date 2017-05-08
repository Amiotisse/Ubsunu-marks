package com.amiotisse.ubsunu.marks.controller;

import com.amiotisse.ubsunu.marks.ApiErrors;
import com.amiotisse.ubsunu.marks.client.MailerFiegnClient;
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
    private MailerFiegnClient mailerFiegnClient;
    private boolean isMailerEnable;

    public PrivateMarkListController(MarkListRepository markListRepository, MailerFiegnClient mailerFiegnClient, boolean isMailerEnable) {
        this.markListRepository = markListRepository;
        this.mailerFiegnClient = mailerFiegnClient;
        this.isMailerEnable = isMailerEnable ;
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

        MarkList markList = markListRepository.save(
                new MarkListBuilder()
                .setTitle(title)
                .setOwnerUserId(userToken.getId())
                .addMark(marks)
                .build()
        );

        if( isMailerEnable ){
            mailerFiegnClient.publishMarkList(markList);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
