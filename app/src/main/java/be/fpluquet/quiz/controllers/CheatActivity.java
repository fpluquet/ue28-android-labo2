package be.fpluquet.quiz.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import be.fpluquet.quiz.R;

public class CheatActivity extends AppCompatActivity {

    public static final String ANSWER_EXTRA = "ANSWER_EXTRA";
    public static final String EXTRA_ANSWER_SHOWN = "EXTRA_ANSWER_SHOWN";
    private Button mShowAnswerButton;
    private TextView mAnswerTextView;
    private boolean mAnswerIsTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mShowAnswerButton = this.findViewById(R.id.show_answer_button);
        mAnswerTextView = this.findViewById(R.id.answer_text_view);
        mAnswerIsTrue = this.getIntent().getBooleanExtra(ANSWER_EXTRA, false);
        mShowAnswerButton.setOnClickListener(event -> {
            if (mAnswerIsTrue)
                mAnswerTextView.setText(R.string.true_button);
            else
                mAnswerTextView.setText(R.string.false_button);
            Intent data = new Intent();
            data.putExtra(EXTRA_ANSWER_SHOWN, true);
            this.setResult(RESULT_OK, data);
        });
    }
}