package com.amiotisse.ubsunu.marks.service;

import com.amiotisse.ubsunu.marks.exception.TitleAlreadyTakenException;
import com.amiotisse.ubsunu.marks.exception.UserTypeNotAllowedException;
import com.amiotisse.ubsunu.marks.model.UserToken;
import com.amiotisse.ubsunu.marks.model.UserType;
import org.springframework.data.repository.CrudRepository;

/**
 * @author himna
 * @since 5/23/2017.
 */
public class ArgsChecker {

    public void checkUserToken(UserToken userToken) throws UserTypeNotAllowedException {
        if (userToken.getUserType()  != UserType.teacher)
            throw new UserTypeNotAllowedException();
    }

    public void checkTitleDoNotExist(String title , CrudRepository<?, String> repository) throws TitleAlreadyTakenException {
        if (repository.exists( title ))
            throw new TitleAlreadyTakenException();
    }
}
