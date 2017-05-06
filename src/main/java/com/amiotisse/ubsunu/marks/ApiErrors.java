package com.amiotisse.ubsunu.marks;

/**
 * @author himna
 * @since 4/8/2017.
 */
public class ApiErrors {

    public static final ApiError USER_TYPE_NOT_ALLOWED
            = new ApiError(
                    "USER_TYPE_NOT_ALLOWED" ,
            " userType is not allowed to request this resources"
    );

    public static final ApiError MARKS_LIST_NOT_FOUND
            = new ApiError(
            "MARKS_LIST_NOT_FOUND" ,
            " no list matches the request "
    );

    public static final ApiError MARKS_LIST_TITLE_TAKEN
            = new ApiError(
            "MARKS_LIST_TITLE_TAKEN" ,
            " title already exist"
    );
}
