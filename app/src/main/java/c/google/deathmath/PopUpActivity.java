package c.google.deathmath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PopUpActivity extends AppCompatActivity {

    int value;
    String display_score;
    TextView tvScore;
    Button btnHome, btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        tvScore = (TextView) findViewById(R.id.tv_score_pop);
        btnHome = (Button) findViewById(R.id.btn_home);
        btnRetry =(Button) findViewById(R.id.btn_retry);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Intent intent = getIntent();
        display_score = intent.getStringExtra("point");
        value = intent.getIntExtra("result", 0);
        tvScore.setText(display_score);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopUpActivity.this, DifficultyActivity.class);
                startActivity(intent);
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PopUpActivity.this, GameActivity.class);
                intent.putExtra("result", value);
                startActivity(intent);
            }
        });
    }


}
