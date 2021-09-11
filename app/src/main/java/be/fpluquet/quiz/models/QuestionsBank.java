package be.fpluquet.quiz.models;

import java.io.Serializable;

import be.fpluquet.quiz.R;

public class QuestionsBank implements Serializable {
    private Question[] mQuestions = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
    };

    private int mCurrentIndex = 0;

    public Question getCurrentQuestion() {
        return mQuestions[mCurrentIndex];
    }

    public void next() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
    }

    public int getCurrentQuestionTextResId() {
        return this.getCurrentQuestion().getTextResId();
    }

    public boolean isCurrentQuestionAnswerTrue() {
        return this.getCurrentQuestion().isAnswerTrue();
    }

    public int getCurrentIndex() {
        return this.mCurrentIndex;
    }

    public void setCurrentIndex(int index) {
        this.mCurrentIndex = index;
    }
}
