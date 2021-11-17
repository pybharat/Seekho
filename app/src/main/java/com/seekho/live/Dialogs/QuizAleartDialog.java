package com.seekho.live.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.seekho.live.AppBase.AppBaseDialogFragment;
import com.seekho.live.R;

public class QuizAleartDialog extends AppBaseDialogFragment {

    Context context;
    TextView start_test_btn_tv;
    ImageView dismiss_iv;
    CheckBox read_verify_cb;

    TextView title_tv;
    String title = "";
    String topic_id = "";

    public static QuizAleartDialog getInstance(Context context){
        QuizAleartDialog dialog = new QuizAleartDialog(context);
        return dialog;
    }

    public QuizAleartDialog(Context context) {
        this.context = context;
    }

    @Override
    public int layoutResourceID() {
        return R.layout.dialog_quiz_aleart;
    }

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        if (getView() == null)return;
        start_test_btn_tv = getView().findViewById(R.id.start_test_btn_tv);
        dismiss_iv = getView().findViewById(R.id.dismiss_iv);
        read_verify_cb = getView().findViewById(R.id.read_verify_cb);
        title_tv = getView().findViewById(R.id.title_tv);

        if (getArguments() != null){
            title = getArguments().getString(KEY_TITLE);
            topic_id = getArguments().getString(KEY_TOPIC_ID);

            title_tv.setText(title);
        }

        start_test_btn_tv.setOnClickListener(this);
        dismiss_iv.setOnClickListener(this);
    }

    public void showDialog(){
        getInstance(context).show(getFragManager(),getClass().getSimpleName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_test_btn_tv:
                if (read_verify_cb.isChecked()){
                    Bundle bundle = new Bundle();
                    if (topic_id != null && !topic_id.equals("")){
                        bundle.putString(KEY_TITLE,title);
                        bundle.putString(KEY_TOPIC_ID,topic_id);
                        bundle.putInt(KEY_OTP_TYPE,KEY_COURSE_QUIZ_TYPE);
                        goToTestsActivity(bundle);
                    }
                    closeDialog();
                } else {
                    showSimpleToast("Please read the instructions and check okay.");
                }

                break;
            case R.id.dismiss_iv:
                closeDialog();
                break;
        }
    }
}
