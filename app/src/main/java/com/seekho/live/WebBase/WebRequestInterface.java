package com.seekho.live.WebBase;

import com.google.gson.JsonObject;
import com.seekho.live.Models.Auth.ForgetPasswordModel;
import com.seekho.live.Models.Auth.LoginDataModel;
import com.seekho.live.Models.Auth.OTPVerificationModel;
import com.seekho.live.Models.Auth.RegisterDataModel;
import com.seekho.live.Models.Auth.ResetPasswordModel;
import com.seekho.live.Models.Courses.ChaptersModel.ChaptersModel;
import com.seekho.live.Models.Courses.ChaptersModel.TopicVideosModel;
import com.seekho.live.Models.Courses.CoursesModel;
import com.seekho.live.Models.Courses.SubCourses.SubCourseDivisionModel;
import com.seekho.live.Models.Courses.SubCourses.SubCoursesModel;
import com.seekho.live.Models.Courses.SubscribedCourses.SubscribedCoursesModel;
import com.seekho.live.Models.Enroll.EnrollDataModel;
import com.seekho.live.Models.MockTests.MockQuiz.MockQuizesListModel;
import com.seekho.live.Models.MockTests.MockTestCategoriesModel;
import com.seekho.live.Models.MockTests.MockTestCategoryDetailModel;
import com.seekho.live.Models.Quiz.QuizModel;
import com.seekho.live.Models.Quiz.SaveAndNextQuizResultModel;
import com.seekho.live.Models.Quiz.Results.SubmitFinalQuizTestModel;
import com.seekho.live.Models.UserProfile.UpdatePasswordModel;
import com.seekho.live.Models.UserProfile.UserInfoModel;
import com.seekho.live.Utils.Constant;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebRequestInterface {

    @POST(Constant.KEY_GET_COURSES)
    Call<CoursesModel> getCourses(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_COURSES_SUB_CATEGORY_LIST)
    Call<SubCoursesModel> getSubCourses(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_MAIN_COURSES_CHAPTERS_LIST)
    Call<ChaptersModel> getMainCoursesChaptersList(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_COURSES_SUB_DIVISION_LIST)
    Call<SubCourseDivisionModel> getSubCoursesDivisionList(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_MAIN_SUB_COURSES_CHAPTERS_LIST)
    Call<ChaptersModel> getMainSubCoursesChaptersList(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_TOPIC_VIDEO_LIST)
    Call<TopicVideosModel> getTopicVideoList(@Body JsonObject parameterObject);

    @POST(Constant.KEY_LOGIN)
    Call<LoginDataModel> getLogin(@Body JsonObject parameterObject);

    @POST(Constant.KEY_REGISTER)
    Call<RegisterDataModel> getRegister(@Body JsonObject parameterObject);

    @POST(Constant.KEY_VERIFY_OTP)
    Call<OTPVerificationModel> getVerifyOTP(@Body JsonObject parameterObject);

    @POST(Constant.KEY_RESEND_OTP)
    Call<OTPVerificationModel> getResendOTP(@Body JsonObject parameterObject);

    @POST(Constant.KEY_FORGET_PASSWORD)
    Call<ForgetPasswordModel> getForgetPassword(@Body JsonObject parameterObject);

    @POST(Constant.KEY_VERIFY_FORGET_OTP)
    Call<OTPVerificationModel> getVerifyForgetOTP(@Body JsonObject parameterObject);

    @POST(Constant.KEY_RESEND_FORGET_OTP)
    Call<OTPVerificationModel> getResendForgetOTP(@Body JsonObject parameterObject);

    @POST(Constant.KEY_RESET_PASSWORD)
    Call<ResetPasswordModel> getResetPassword(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_LOGIN_USER_INFO)
    Call<UserInfoModel> getLoginUserInfo(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_UPDATE_USER_PROFILE)
    Call<UserInfoModel> getUpdateUserProfile(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_UPDATE_USER_PASSWORD)
    Call<UpdatePasswordModel> getUpdateUserPassword(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_USER_SUBSCRIBED_COURSES_LIST)
    Call<SubscribedCoursesModel> getUserSubscribedCourses(@Body JsonObject parameterObject);

    @POST(Constant.KEY_ENROLL_COURSE_BY_USER)
    Call<EnrollDataModel> getEnrollCourseByUser(@Body JsonObject parameterObject);

    @POST(Constant.KEY_ENROLL_SUB_COURSE_BY_USER)
    Call<EnrollDataModel> getEnrollSubCourseByUser(@Body JsonObject parameterObject);

    @POST(Constant.KEY_UPDATE_PROFILE_IMAGE)
    Call<EnrollDataModel> getUpdateProfileImage(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_TOPIC_WISE_QUIZ)
    Call<QuizModel> getTopicWiseQuiz(@Body JsonObject parameterObject);

    @POST(Constant.KEY_SAVE_AND_NEXT_QUIZ_RESULT)
    Call<SaveAndNextQuizResultModel> getSaveAndNextQuizResult(@Body JsonObject parameterObject);

    @POST(Constant.KEY_SUBMIT_FINAL_QUIZ_TEST)
    Call<SubmitFinalQuizTestModel> getSubmitFinalQuizTest(@Body JsonObject parameterObject);

    //--------------------------------- Mock Tests ------------------------------------------------

    @POST(Constant.KEY_GET_MOCK_TEST_CATEGORIES)
    Call<MockTestCategoriesModel> getMockTestCategories(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_MOCK_TEST_CATEGORY_DETAIL)
    Call<MockTestCategoryDetailModel> getMockTestCategoryDetail(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_MOCK_TEST_CATEGORY_WISE_SERIES)
    Call<MockQuizesListModel> getMockTestCategoryWiseSeries(@Body JsonObject parameterObject);

    @POST(Constant.KEY_GET_MOCK_TEST_SERIES_QUESTIONS)
    Call<QuizModel> getMockTestSeriesQuestions(@Body JsonObject parameterObject);

    @POST(Constant.KEY_SAVE_AND_NEXT_MOCK_TEST_RESULT)
    Call<SaveAndNextQuizResultModel> getSaveAndNextMockTestResult(@Body JsonObject parameterObject);

    @POST(Constant.KEY_SUBMIT_FINAL_MOCK_TEST_SERIES)
    Call<SubmitFinalQuizTestModel> getSubmitFinalMockTestSeries(@Body JsonObject parameterObject);

}
