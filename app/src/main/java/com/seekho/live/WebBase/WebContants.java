package com.seekho.live.WebBase;

public interface WebContants {

    //------------------------------ Web Headers -----------------------------------------
    String HEADER_KEY_TOKEN = "TOKEN";
    String HEADER_KEY_LANG = "LANG";
    String HEADER_KEY_DEVICE_ID = "DEVICE-ID";
    String HEADER_KEY_DEVICETYPE = "DEVICETYPE";
    String HEADER_KEY_DEVICEINFO = "DEVICEINFO";
    String HEADER_KEY_APPINFO = "APPINFO";
    String HEADER_KEY_AUTHORIZATION = "Authorization";
    String HEADER_KEY_CONTENT_TYPE = "Content-Type";

    String HEADER_LANG_VALUE = "1";
    String HEADER_DEVICETYPE_VALUE = "A";

    //------------------------------- Keys -----------------------------------------------
    String KEY_COURSE_ID = "courseid";
    String KEY_SUB_COURSES_ID = "subcourseid";
    String KEY_TOPIC_ID = "topicid";
    String KEY_EMAIL = "email";
    String KEY_PASSWORD = "password";
    String KEY_TITLE = "title";
    String KEY_NAME = "name";
    String KEY_NUMBER = "number";
    String KEY_OTP = "otp";
    String KEY_USER_ID = "userid";
    String KEY_OTP_TYPE = "type";
    String KEY_TOKEN = "token";
    String KEY_VERIFY = "verify";
    String KEY_ID = "id";
    String KEY_AUTH = "auth";
    String KEY_FIRST_NAME = "first_name";
    String KEY_LAST_NAME = "last_name";
    String KEY_PROFILE_IMAGE = "profile_image";
    String KEY_USER_STATUS = "user_status";
    String KEY_COURSE_NAME = "course_name";
    String KEY_HAVE_SUB_CATEGORY = "have_sub_category";
    String KEY_IMG = "img";
    String KEY_CQ_ID = "cq_id";
    String KEY_TEST_HISTORY_ID = "testhistoryid";
    String KEY_QUIZ_ID = "quizid";
    String KEY_SHOW_STATUS = "showstatus";
    String KEY_NOT_ANSWERED_QUESTIONS = "notansweredquestions";
    String KEY_ANSWERED_QUESTIONS = "answeredquestions";
    String KEY_MARKED_AS_REVIEW_QUESTIONS = "markedasreviewquestions";
    String KEY_ANSWER = "answer";
    String KEY_DURATION = "duration";
    String KEY_QUIZ_SUMMARY_MODEL = "quiz_summary_model";
    String KEY_MOCK_CATEGORY = "mockcategory";
    String KEY_TEST_SERIES_ID = "testseriesid";
    int KEY_COURSE_QUIZ_TYPE = 0;
    int KEY_MOCK_TEST_QUIZ_TYPE = 1;

    //------------------------------- Web Request IDs ------------------------------------
    int WEB_GET_COURSES_CODE = 1;
    int WEB_SUB_COURSES_CODE = 2;
    int WEB_SUB_COURSES_CHAPTERS_CODE = 3;
    int WEB_SUB_COURSES_DIVISION_CODE = 4;
    int WEB_MAIN_COURSES_CHAPTERS_CODE = 5;
    int WEB_TOPIC_VIDEOS_CODE = 6;
    int WEB_LOGIN_CODE = 7;
    int WEB_REGISTER_CODE = 8;
    int WEB_VERIFY_OTP_CODE = 9;
    int WEB_RESEND_OTP_CODE = 10;
    int WEB_FORGET_PASSWORD_CODE = 11;
    int WEB_VERIFY_FORGET_OTP_CODE = 12;
    int WEB_RESEND_FORGET_OTP_CODE = 13;
    int WEB_RESET_PASSWORD_CODE = 14;
    int WEB_USER_LOGIN_INFO_CODE = 15;
    int WEB_UPDATE_USER_PROFILE_CODE = 16;
    int WEB_UPDATE_USER_PASSWORD_CODE = 17;
    int WEB_USER_SUBSCRIBED_COURSES_CODE = 18;
    int WEB_ENROLL_COURSES_BY_USER_CODE = 19;
    int WEB_ENROLL_SUB_COURSES_BY_USER_CODE = 20;
    int WEB_UPDATE_PROFILE_IMAGE_CODE = 21;
    int WEB_GET_TOPIC_WISE_QUIZ_CODE = 22;
    int WEB_SAVE_AND_NEXT_QUIZ_RESULT_CODE = 23;
    int WEB_SUBMIT_FINAL_QUIZ_TEST_CODE = 24;
    int WEB_GET_MOCK_TEST_CATEGORIES_CODE = 25;
    int WEB_GET_MOCK_TEST_CATEGORY_DETAIL_CODE = 26;
    int WEB_GET_MOCK_TEST_CATEGORY_WISE_SERIES_CODE = 27;
    int WEB_GET_MOCK_TEST_SERIES_QUESTIONS_CODE = 28;
    int WEB_SAVE_AND_NEXT_MOCK_TEST_RESULT_CODE = 29;
    int WEB_SUBMIT_FINAL_MOCK_TEST_SERIES_CODE = 30;

}
