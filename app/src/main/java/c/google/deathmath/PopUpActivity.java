package c.google.deathmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PopUpActivity extends AppCompatActivity {

    int value;
    int score;

    String display_score;
    TextView tvScore;
    Button btnHome, btnRetry;
    ImageView img_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        tvScore = (TextView) findViewById(R.id.tv_score_pop);
        btnHome = (Button) findViewById(R.id.btn_home);
        btnRetry =(Button) findViewById(R.id.btn_retry);
        img_score = findViewById(R.id.img_score);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Intent intent = getIntent();

        score = intent.getIntExtra("point", 0);
        value = intent.getIntExtra("result", 0); //to retry the game
        setImg_score(score);
        tvScore.setText(""+score);

        setBtnHome();
        setBtnRetry();
    }

    public void setBtnRetry(){
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopUpActivity.this, GameActivity.class);
                intent.putExtra("result", value);
                startActivity(intent);
            }
        });
    }

    public void setBtnHome(){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopUpActivity.this, DifficultyActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setImg_score(int sc){
        if (sc<=50){
            img_score.setImageResource(R.drawable.minus);
        }else{
            img_score.setImageResource(R.drawable.add);
        }
    }
}
