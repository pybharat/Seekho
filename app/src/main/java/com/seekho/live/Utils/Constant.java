package com.seekho.live.Utils;

public class Constant {

    //---------------------------------- Web Request ------------------------------------
    public static final String BASE_URL = "https://seekho.live/bharat-sir/seekhoapi/";

    public static final String DASHBOARD = "Dashboard/";

    public static final String KEY_GET_COURSES = DASHBOARD + "getcourses";
    public static final String KEY_GET_COURSES_SUB_CATEGORY_LIST = DASHBOARD + "getcoursesubcategorylist";
    public static final String KEY_GET_COURSES_SUB_DIVISION_LIST = DASHBOARD + "getcoursesubdivisonlist";
    public static final String KEY_GET_MAIN_COURSES_CHAPTERS_LIST = DASHBOARD + "getmaincousechapterslist";
    public static final String KEY_GET_MAIN_SUB_COURSES_CHAPTERS_LIST = DASHBOARD + "getmainsubcousechapterslist";
    public static final String KEY_GET_TOPIC_VIDEO_LIST = DASHBOARD + "gettopicvideolist";
    public static final String KEY_GET_LOGIN_USER_INFO = DASHBOARD + "getloginuserinfo";
    public static final String KEY_GET_UPDATE_USER_PROFILE = DASHBOARD + "updateuserprofile";
    public static final String KEY_GET_UPDATE_USER_PASSWORD = DASHBOARD + "updateuserpassword";
    public static final String KEY_GET_USER_SUBSCRIBED_COURSES_LIST = DASHBOARD + "getusersubscribedcourses";
    public static final String KEY_ENROLL_COURSE_BY_USER = DASHBOARD + "enrollcoursebyuser";
    public static final String KEY_ENROLL_SUB_COURSE_BY_USER = DASHBOARD + "enrollsubcoursebyuser";
    public static final String KEY_UPDATE_PROFILE_IMAGE = DASHBOARD + "updateprofileimage";
    public static final String KEY_GET_TOPIC_WISE_QUIZ = DASHBOARD + "gettopicwiseqiz";
    public static final String KEY_SAVE_AND_NEXT_QUIZ_RESULT = DASHBOARD + "saveandnextquizresult";
    public static final String KEY_SUBMIT_FINAL_QUIZ_TEST = DASHBOARD + "submitfinalquiztest";
    public static final String KEY_GET_MOCK_TEST_CATEGORIES = DASHBOARD + "getmocktestcategories";
    public static final String KEY_GET_MOCK_TEST_CATEGORY_DETAIL = DASHBOARD + "getmocktestcategorydetails";
    public static final String KEY_GET_MOCK_TEST_CATEGORY_WISE_SERIES = DASHBOARD + "getmocktestcategorywiseseries";
    public static final String KEY_GET_MOCK_TEST_SERIES_QUESTIONS = DASHBOARD + "getmocktestseriesquestions";
    public static final String KEY_SAVE_AND_NEXT_MOCK_TEST_RESULT = DASHBOARD + "saveandnextmocktestresult";
    public static final String KEY_SUBMIT_FINAL_MOCK_TEST_SERIES = DASHBOARD + "submitfinalmocktestseries";

    public static final String AUTH = "Auth/";

    public static final String KEY_LOGIN = AUTH + "login";
    public static final String KEY_REGISTER = AUTH + "register";
    public static final String KEY_VERIFY_OTP = AUTH + "verifyotp";
    public static final String KEY_RESEND_OTP = AUTH + "resendotp";
    public static final String KEY_FORGET_PASSWORD = AUTH + "forgotpassword";
    public static final String KEY_VERIFY_FORGET_OTP = AUTH + "verifyforgototp";
    public static final String KEY_RESEND_FORGET_OTP = AUTH + "resendforgototp";
    public static final String KEY_RESET_PASSWORD = AUTH + "resetpassword";

    public static final long THREE_HOURS_MILLI = 10800000;
    public static final long ONE_MIN_MILLI = 60000;

    //--------------------------------------- Enroll Dialog ---------------------------------------
    public static final boolean isEnrollDialogEnable = true;
    public static final int STORAGE_AND_CAMERA_PERMISSION_REQ_CODE = 101;
    public static final int STORAGE_AND_ALL_FILES_ACCESS_REQ_CODE = 100;
    public static final int PICK_IMAGE_FROM_GALLERY_CODE = 1001;
    public static final int PICK_CAPTURED_IMAGE_FROM_CAMERA_CODE = 1002;
}
