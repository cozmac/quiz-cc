package ro.ase.rocket.rocketquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

import ro.ase.rocket.rocketquiz.model.Questions;

public class quiz extends AppCompatActivity {

    Button answer1, answer2, answer3, answer4;

    TextView score, question;

    private Questions mQuestions = new Questions();

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionLenght = mQuestions.mQuestions.length;


    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        r = new Random();

        answer1=(Button)findViewById(R.id.answer1);
        answer2=(Button)findViewById(R.id.answer2);
        answer3=(Button)findViewById(R.id.answer3);
        answer4=(Button)findViewById(R.id.answer4);

        score= (TextView)findViewById(R.id.score);
        question=(TextView)findViewById(R.id.question);


        score.setText(getString(R.string.actualScore, mScore));
        updateQuestion(r.nextInt(mQuestionLenght));

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer1.getText() == mAnswer)
                {
                    mScore++;
                    score.setText(getString(R.string.actualScore, mScore));
                    updateQuestion(r.nextInt(mQuestionLenght));
                }
                else
                {
                    gameOver();

                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer2.getText() == mAnswer)
                {
                    mScore++;
                    score.setText(getString(R.string.actualScore, mScore));
                    updateQuestion(r.nextInt(mQuestionLenght));
                }
                else
                {
                    gameOver();
                }
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer3.getText() == mAnswer)
                {
                    mScore++;
                    score.setText(getString(R.string.actualScore, mScore));
                    updateQuestion(r.nextInt(mQuestionLenght));
                }
                else
                {
                    gameOver();
                }
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer4.getText() == mAnswer)
                {
                    mScore++;
                    score.setText(getString(R.string.actualScore, mScore));
                    updateQuestion(r.nextInt(mQuestionLenght));
                }
                else
                {
                    gameOver();
                }
            }
        });
    }

    private void updateQuestion(int num){
        question.setText(mQuestions.getQuestion(num));
        answer1.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));
        answer4.setText(mQuestions.getChoice4(num));


        mAnswer = mQuestions.getCorrectAnswer(num);
    }

    private void gameOver(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(quiz.this);
        alertDialogBuilder
                .setMessage(getString(R.string.gameOver_score, mAnswer, mScore))
                .setCancelable(false)
                .setPositiveButton(R.string.new_game,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), quiz.class));
                            }
                        }
                )
                .setNegativeButton(R.string.exit,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        SharedPreferences sp =
                getApplication().getSharedPreferences("rocket_quiz",
                        MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int shrdScore = sp.getInt("Score", 0);
        if (mScore > shrdScore)
        {
            editor.putInt("Score", mScore);
            editor.apply();

        }
    }
}