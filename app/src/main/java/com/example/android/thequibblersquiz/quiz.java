package com.example.android.thequibblersquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class quiz extends AppCompatActivity {
    int numQuestions = 6;

    // if any new per-game state is added, be sure to reset it in resetQuiz()
    int answerValues[] = {0, 0, 0, 0, 0, 0};
    boolean questionsAnswered[] = {false, false, false, false, false, false};
    int numRight = 0;         // number of question answered correctly


    public void showToast(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), "" + msg,
                Toast.LENGTH_SHORT);
        TextView toastMessage = toast.getView().findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.CYAN);
        toast.show();
    }

    /**
     * Resets quiz back to intro activity
     **/

    /*public void backToIntro(View view) {
        resetTheQuiz();
        setContentView(R.layout.activity_title);
    }

    // Reset quiz to start over

    public void resetTheQuiz() {
        //currentAnswer = null;
        for (int i = 0; i < numQuestions; i++) {
            answerValues[i] = 0;
            questionsAnswered[i] = false;
        }
        numRight = 0;
    }

    // updates the number of correct answers
    */void quizAnswers() {
        for (int i = 0; i < numQuestions; i++) {
            if (i == 7) {
                if (answerValues[i] == 4)
                    numRight++;
            } else {
                if (answerValues[i] == 1)
                    numRight++;
            }
        }
    }

    // see if the user has answered all questions
    boolean allQuestionsAnswered() {
        for (int i = 0; i < numQuestions; i++)
            if (!questionsAnswered[i])
                return false;

        return true;
    }

    // display a message with number correct and some witty teaser text to go with it
    void displayResults() {
        TextView summaryTextView = findViewById(R.id.summaryText);
        TextView teaserTextView = findViewById(R.id.teaserText);
        String tmpStr;

        tmpStr = "You got\n" + " " + numRight + "\nout of " + numQuestions + "\n";
        summaryTextView.setText(tmpStr);

        switch (numRight) {
            case 0:
                tmpStr = "You're a Muggle,\ntime to brush up.\n";
                break;
            case 1:
                tmpStr = "Who is Harry Potter?\n";
                break;
            case 2:
                tmpStr = "It's ok,\nYou have a real life.\n";
                break;
            case 3:
                tmpStr = "Time to brush up, but not bad.\n";
                break;
            case 4:
                tmpStr = "Not to shabby.\n";
                break;
            case 5:
                tmpStr = "Almost there. Harry would be proud.\n";
                break;
            case 6:
                tmpStr = "Here is your Felix Felicis\n";
                break;
        }

        teaserTextView.setText(tmpStr);
    }

    // Calculate score and load the results activity.
    public void getResults(View view) {

        if (!allQuestionsAnswered()) {
            showToast("Please answer all questions");
            return;
        }

        quizAnswers();
        displayResults();
    }

    public String idName(int resId) {
        return getResources().getResourceEntryName(resId);
    }


    // Logs the value of question

    public void answers(View view) {
        int answer = Integer.parseInt(view.getTag().toString());
        int qNum = (Integer.parseInt(idName(view.getId()).replaceAll("[\\D]", ""))) - 1;
        CheckBox chkbx1 = (CheckBox) findViewById(R.id.answer6a), chkbx2 = (CheckBox) findViewById(R.id.answer6b),chkbx3 = (CheckBox) findViewById(R.id.answer6c),chkbx4 = (CheckBox) findViewById(R.id.answer6d);

        if (qNum == 5) {
            if(chkbx2.isChecked() && chkbx3.isChecked() && chkbx4.isChecked()) answerValues[qNum] = 1;
            else answerValues[qNum] = 0;
            if (chkbx1.isChecked()) answerValues[qNum] = 0;
        }
        else answerValues[qNum] = answer;
        questionsAnswered[qNum] = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }
}
