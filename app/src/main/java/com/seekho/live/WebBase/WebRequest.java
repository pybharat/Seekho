package com.seekho.live.WebBase;

import android.content.Context;

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

import retrofit2.Call;

public class WebRequest extends WebBase {

    Context context;
    int webRequestID;

    public WebRequest(Context context) {
        this.context = context;
    }

    public int getWebRequestID() {
        return webRequestID;
    }

    public void setWebRequestID(int webRequestID) {
        this.webRequestID = webRequestID;
    }


    public void callCoursesApi(JsonObject parameterObject, WebListener webListener) {
        Call<CoursesModel> responseCall = getWebRequestInterface().getCourses(parameterObject);
        WebRequest webRequest = new WebRequest(context);
        webRequest.setWebRequestID(WEB_GET_COURSES_CODE);
        callApis(context, responseCall, webRequest, webListener);
    }

    public void callSubCoursesApi(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<SubCoursesModel> responseCall = getWebRequestInterface().getSubCourses(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_SUB_COURSES_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getSubCoursesDivisionList(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<SubCourseDivisionModel> responseCall = getWebRequestInterface().getSubCoursesDivisionList(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_SUB_COURSES_DIVISION_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getMainCoursesChaptersList(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<ChaptersModel> responseCall = getWebRequestInterface().getMainCoursesChaptersList(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_MAIN_COURSES_CHAPTERS_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getMainSubCoursesChaptersList(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<ChaptersModel> responseCall = getWebRequestInterface().getMainSubCoursesChaptersList(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_SUB_COURSES_CHAPTERS_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getTopicVideoList(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<TopicVideosModel> responseCall = getWebRequestInterface().getTopicVideoList(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_TOPIC_VIDEOS_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getLogin(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<LoginDataModel> responseCall = getWebRequestInterface().getLogin(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_LOGIN_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getRegister(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<RegisterDataModel> responseCall = getWebRequestInterface().getRegister(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_REGISTER_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getVerifyOTP(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<OTPVerificationModel> responseCall = getWebRequestInterface().getVerifyOTP(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_VERIFY_OTP_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getResendOTP(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<OTPVerificationModel> responseCall = getWebRequestInterface().getResendOTP(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_RESEND_OTP_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getForgetPassword(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<ForgetPasswordModel> responseCall = getWebRequestInterface().getForgetPassword(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_FORGET_PASSWORD_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getVerifyForgetOTP(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<OTPVerificationModel> responseCall = getWebRequestInterface().getVerifyForgetOTP(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_VERIFY_FORGET_OTP_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getResendForgetOTP(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<OTPVerificationModel> responseCall = getWebRequestInterface().getResendForgetOTP(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_RESEND_FORGET_OTP_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getResetPassword(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<ResetPasswordModel> responseCall = getWebRequestInterface().getResetPassword(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_RESET_PASSWORD_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getUserLoginInfo(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<UserInfoModel> responseCall = getWebRequestInterface().getLoginUserInfo(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_USER_LOGIN_INFO_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getUpdateUserProfile(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<UserInfoModel> responseCall = getWebRequestInterface().getUpdateUserProfile(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_UPDATE_USER_PROFILE_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getUpdateUserPassword(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<UpdatePasswordModel> responseCall = getWebRequestInterface().getUpdateUserPassword(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_UPDATE_USER_PASSWORD_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getUserSubscribedCourses(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<SubscribedCoursesModel> responseCall = getWebRequestInterface().getUserSubscribedCourses(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_USER_SUBSCRIBED_COURSES_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getEnrollCourseByUser(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<EnrollDataModel> responseCall = getWebRequestInterface().getEnrollCourseByUser(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_ENROLL_COURSES_BY_USER_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getEnrollSubCourseByUser(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<EnrollDataModel> responseCall = getWebRequestInterface().getEnrollSubCourseByUser(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_ENROLL_SUB_COURSES_BY_USER_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getUpdateProfileImage(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<EnrollDataModel> responseCall = getWebRequestInterface().getUpdateProfileImage(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_UPDATE_PROFILE_IMAGE_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getTopicWiseQuiz(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<QuizModel> responseCall = getWebRequestInterface().getTopicWiseQuiz(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_GET_TOPIC_WISE_QUIZ_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getSaveAndNextQuizResult(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<SaveAndNextQuizResultModel> responseCall = getWebRequestInterface().getSaveAndNextQuizResult(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_SAVE_AND_NEXT_QUIZ_RESULT_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getSubmitFinalQuizTest(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<SubmitFinalQuizTestModel> responseCall = getWebRequestInterface().getSubmitFinalQuizTest(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_SUBMIT_FINAL_QUIZ_TEST_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getMockTestCategories(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<MockTestCategoriesModel> responseCall = getWebRequestInterface().getMockTestCategories(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_GET_MOCK_TEST_CATEGORIES_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getMockTestCategoryDetail(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<MockTestCategoryDetailModel> responseCall = getWebRequestInterface().getMockTestCategoryDetail(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_GET_MOCK_TEST_CATEGORY_DETAIL_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getMockTestCategoryWiseSeries(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<MockQuizesListModel> responseCall = getWebRequestInterface().getMockTestCategoryWiseSeries(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_GET_MOCK_TEST_CATEGORY_WISE_SERIES_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getMockTestSeriesQuestions(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<QuizModel> responseCall = getWebRequestInterface().getMockTestSeriesQuestions(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_GET_MOCK_TEST_SERIES_QUESTIONS_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getSaveAndNextMockTestResult(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<SaveAndNextQuizResultModel> responseCall = getWebRequestInterface().getSaveAndNextMockTestResult(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_SAVE_AND_NEXT_MOCK_TEST_RESULT_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }

    public void getSubmitFinalMockTestSeries(JsonObject parameterObject, WebListener webListener) {
        if (parameterObject != null) {
            Call<SubmitFinalQuizTestModel> responseCall = getWebRequestInterface().getSubmitFinalMockTestSeries(parameterObject);
            WebRequest webRequest = new WebRequest(context);
            webRequest.setWebRequestID(WEB_SUBMIT_FINAL_MOCK_TEST_SERIES_CODE);
            callApis(context, responseCall, webRequest, webListener);
        }
    }
}
