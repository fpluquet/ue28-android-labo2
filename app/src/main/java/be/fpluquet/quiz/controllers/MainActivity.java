package be.fpluquet.quiz.controllers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.fpluquet.quiz.R;
import be.fpluquet.quiz.models.Question;
import be.fpluquet.quiz.models.QuestionsBank;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QUIZ_APP";
    public static final String KEY_INDEX = "KEY_INDEX";
    public static final String KEY_QUESTION_BANK = "KEY_QUESTION_BANK";
    private static final int REQUEST_CODE = 1;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private QuestionsBank mQuestionBank = new QuestionsBank();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate method");
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
//            int index = savedInstanceState.getInt(KEY_INDEX);
//            mQuestionBank.setCurrentIndex(index);
//            Log.d(TAG, "Changement de l'index en " + index);
            mQuestionBank = (QuestionsBank) savedInstanceState.getSerializable(KEY_QUESTION_BANK);
        }
        setContentView(R.layout.activity_main);
        mTrueButton = this.findViewById(R.id.true_button);
        mFalseButton = this.findViewById(R.id.false_button);
        mNextButton = this.findViewById(R.id.next_button);
        mCheatButton = this.findViewById(R.id.cheat_button);
        mQuestionTextView = this.findViewById(R.id.question_text_view);
        setQuestionText();
        setButtonsListeners();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
//        outState.putInt(KEY_INDEX, mQuestionBank.getCurrentIndex());
        outState.putSerializable(KEY_QUESTION_BANK, mQuestionBank);
    }

    private void setButtonsListeners() {
        mTrueButton.setOnClickListener(event -> {
            if (mQuestionBank.isCurrentQuestionAnswerTrue()) {
                Toast.makeText(this, R.string.good_answer, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
            }
//            questionText.setText("Cliqué !");
//            Log.d("MonQuiz/MainActivity", "Clic sur le bouton Vrai");
        });
        mFalseButton.setOnClickListener(event -> {
            if (mQuestionBank.isCurrentQuestionAnswerTrue() == false) {
                Toast.makeText(this, R.string.good_answer, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();
            }
        });

        mNextButton.setOnClickListener(event -> {
//            mCurrentIndex++;
//            if (mCurrentIndex == mQuestionBank.length) {
//                mCurrentIndex = 0;
//            }
//            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            mQuestionBank.next();
            setQuestionText();

        });
        mCheatButton.setOnClickListener(event -> {
            Intent intent = new Intent(this, CheatActivity.class);
            intent.putExtra(CheatActivity.ANSWER_EXTRA, mQuestionBank.isCurrentQuestionAnswerTrue());
            this.startActivityForResult(intent, REQUEST_CODE);
        });
    }

    private void setQuestionText() {
        mQuestionTextView.setText(mQuestionBank.getCurrentQuestionTextResId());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false)) {
                Toast.makeText(this, getString(R.string.answer_has_been_shown) + " > La réponse est " +
                        getString(mQuestionBank.isCurrentQuestionAnswerTrue() ? R.string.true_button : R.string.false_button), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart method");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart method");
    }
}