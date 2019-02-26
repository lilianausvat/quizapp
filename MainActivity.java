package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.security.cert.Extension;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView picture1ImageView = (ImageView) findViewById(R.id.picture1);
        picture1ImageView.setImageResource(R.drawable.belvedere_viena);

        ImageView picture2ImageView = (ImageView) findViewById(R.id.picture2);
        picture2ImageView.setImageResource(R.drawable.karls_kirche_viena);

        ImageView picture3ImageView = (ImageView) findViewById(R.id.picture3);
        picture3ImageView.setImageResource(R.drawable.toronto_cn_tower);

        ImageView picture4ImageView = (ImageView) findViewById(R.id.picture4);
        picture4ImageView.setImageResource(R.drawable.government_building_new_york);

        ImageView picture5ImageView = (ImageView) findViewById(R.id.picture5);
        picture5ImageView.setImageResource(R.drawable.sibiu_romania);

    }

    //evaluate the user answers
    public void startEvaluation(View view) {
        //keep the answer in an array of data
        String[] answers = evaluateGui();
        //evaluate the correct answers
        int result = evaluateQuiz(answers);
        toastResult(result);
    }

    public String[] evaluateGui() {
        //@ret has 5 inputs of the 5 questions
        String[] ret = new String[5];

//retrieve the value of the check boxes for the question 1
        CheckBox checkBoxQuestion1Austria = findViewById(R.id.question_1_AustriaCheckBox);
        CheckBox checkBoxQuestion1France = findViewById(R.id.question_1FranceCheckBox);
        CheckBox checkBoxQuestion1Belvedere = findViewById(R.id.question_1_BelvedereCheckBox);
//retrieve if the question 1 is correct or not and
// initiate the boolean value of @answerQuestion1 to false
        Boolean answerQuestion1 = false;

        if (checkBoxQuestion1Austria.isChecked() == true && checkBoxQuestion1France.isChecked() == false && checkBoxQuestion1Belvedere.isChecked() == true) {
            answerQuestion1 = true;
            Log.v("MainActivity", "the if else answer to the first question1 is " + answerQuestion1);
        }
//retrieve the question 3 answers in the bookean value @answerQuestion3
        //set the initial value of @answerQuestion3 to false and chage it
        // to tru if the user offer a correct answer
        CheckBox checkBoxQuestion3chicago = findViewById(R.id.question_3_chicago);
        CheckBox checkBoxQuestion3Toronto = findViewById(R.id.question_3_toronto);
        CheckBox checkBoxQuestion3Dresden = findViewById(R.id.question_3_dresden);

        //check the solutions for question 3 if the response is Toronto returne true
        Boolean answerQuestion3 = false;

        if (checkBoxQuestion3chicago.isChecked() == false && checkBoxQuestion3Toronto.isChecked() == true && checkBoxQuestion3Dresden.isChecked() == false) {
            answerQuestion3 = true;
            Log.v("MainActivity", "the answer to the 3 question" + answerQuestion3);
        }
//retrieve the value for the @EditText and compare it with the right value given in the array
        EditText editTextQuestion5 = findViewById(R.id.question_5);
        Log.v("MainActivity", "the answer to the 5 question" + editTextQuestion5);

        //return a string of 5 elements corresponsing to the 5 questions
        ret[0] = Boolean.toString(answerQuestion1);
        Log.v("MainActivity", "the answer to the 1 question" + ret[0]);
        ret[1] = evaluateRadioGroup(R.id.radio_group_question_2).toLowerCase();
        Log.v("MainActivity", "the answer to the 2 question" + ret[1]);
        ret[2] = Boolean.toString(answerQuestion3);
        Log.v("MainActivity", "the answer to the 3 question" + ret[2]);
        ret[3] = evaluateRadioGroup(R.id.radio_group_question_4).toLowerCase();
        Log.v("MainActivity", "the answer to the 4 question" + ret[3]);
        ret[4] = editTextQuestion5.getText().toString().toLowerCase();
        Log.v("MainActivity", "the answer to the 5 question" + ret[4]);
        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
//this is the array of correct answers that the statment is comparring the user answerrs against
        String[] correctAnswers = {"true", "viena", "true", "government building new york", "romania"};

        //while statement that compare the user answer with the array given in the array of @correctAnswers above
        int i = 0;
        while (i < 5) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
                Log.v("MainActivity", "result " + result);
            }
            i++;
            Log.v("MainActivity", "i the string position " + i);
        }
        Log.v("MainActivity", "the final result " + i);
        return result;


    }

    //print the score and the correct solutions with the correct solutions lenght long let the message a little longer on the screen
    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "Poor luck. The correct answers are Austria Belvedere Palace, Viena, Toronto, Government Building New York, Romanaia";
        } else if (result == 1) {
            message += "You could do better.The correct answers are Austria Belvedere Palace, Viena, Toronto, Government Building New York, Romanaia";
        } else if (result == 2) {
            message += "Quite nice.The correct answers are Austria Belvedere Palace, Viena, Toronto, Government Building New York, Romanaia";
        } else if (result == 3) {
            message += "Really nice.The correct answers are Austria Belvedere Palace, Viena, Toronto, Government Building New York, Romanaia";
        } else if (result == 4) {
            message += "Great! The correct answers are Austria Belvedere Palace, Viena, Toronto, Government Building New York, Romanaia";
        } else if (result == 5) {
            message += "Absolutely awesome! The correct answers are Austria Belvedere Palace, Viena, Toronto, Government Building New York, Romanaia";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG);
        reportResult.show();
    }


    //evaluate radioGroup and return the text for the checked radio button
    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String) radioButtonQuestion.getText();
    }

    //reset check boxes radio buttons and editText to empty values
    public void reset(View view) {

        CheckBox checkBox = findViewById(R.id.question_1_AustriaCheckBox);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_1FranceCheckBox);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_1_BelvedereCheckBox);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_2);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_3_chicago);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_3_toronto);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_3_dresden);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_group_question_4);
        radioGroup.clearCheck();

        EditText editText = findViewById(R.id.question_5);
        editText.setText("");
    }
}

