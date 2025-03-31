package kz.baymukach.test0912;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView questionText;
    private RadioGroup answersGroup;
    private Button submitButton;
    private int currentQuestionIndex = 0;
    private String[] questions = {"Suraq 1", "Suraq 2", "Suraq 3"};
    private String[][] answerChoices = {
            {"1","2","3","4"},
            {"5","6","7","8"},
            {"9","1","2","3"}
    };
    private int[] correctAnswers = {0, 1, 2};
    private int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        submitButton = findViewById(R.id.submitButton);
        answersGroup = findViewById(R.id.answersGroup);

        setQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answersGroup.getCheckedRadioButtonId();
                if(selectedId == -1){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }else{
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String answerText = selectedRadioButton.getText().toString();
                    if(answerText.equals(answerChoices[currentQuestionIndex] [correctAnswers[currentQuestionIndex]])){
                        Toast.makeText(getApplicationContext(),"Durys!", Toast.LENGTH_SHORT).show();
                        score++;
                    }else{
                        Toast.makeText(getApplicationContext(), "Kate!", Toast.LENGTH_SHORT).show();
                    }
                }
                currentQuestionIndex++;
                if(currentQuestionIndex < questions.length){
                    setQuestion();
                }else{
                    showResults();
//                    Toast.makeText(getApplicationContext(), "Aiaktaldy", Toast.LENGTH_SHORT).show();
//                    submitButton.setEnabled(false);
                }
            }

            private void showResults() {
                setContentView(R.layout.result);
                TextView scoreTv = findViewById(R.id.scoreTv);
                Button btnRestart = findViewById(R.id.btnRestart);
                scoreTv.setText("Вы набрали " + questions.length + "/" + score);
                btnRestart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score = 0;
                        currentQuestionIndex = 0;
                        setContentView(R.layout.activity_main);
                        setQuestion();
                    }
                });

            }
        });
    }

    private void setQuestion() {
        questionText.setText(questions[currentQuestionIndex]);

        for(int i=0; i<answersGroup.getChildCount(); i++){
            RadioButton radioButton = (RadioButton) answersGroup.getChildAt(i);
            radioButton.setText(answerChoices[currentQuestionIndex][i]);
        }
        answersGroup.clearCheck();
    }
}