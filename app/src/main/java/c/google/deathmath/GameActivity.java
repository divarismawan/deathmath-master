package c.google.deathmath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Boolean.FALSE;

public class GameActivity extends AppCompatActivity {

    MediaPlayer mc_false;
    MediaPlayer mc_true;
    Random random = new Random();
    int pointA, pointB;
    int pointC, pointD;
    int pointDiff = 0;
    int randMath;
    int result;
    int newResult;
    int point = 0;
    int value;

    TextView tv_math;
    TextView tv_result;
    TextView tv_point;
    TextView tv_pointA;
    TextView tv_pointB;
    TextView tv_timer;


    Button btn_zero;
    Button btn_one;
    Button btn_two;
    Button btn_three;
    Button btn_four;
    Button btn_five;
    Button btn_six;
    Button btn_seven;
    Button btn_eight;
    Button btn_nine;
    Button btn_submit;
    Button btn_change;
    Button btn_del;
    Button btn_reset;
    boolean timerrunning = FALSE;
    long sisawaktuMilisecond = 15000;//15 detik
    CountDownTimer myTimer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent myIntent = getIntent();
        value = myIntent.getIntExtra("result",0 );

        mc_false = MediaPlayer.create(GameActivity.this, R.raw.m_false);
        mc_true = MediaPlayer.create(GameActivity.this, R.raw.m_true);

        tv_math   = (TextView)findViewById(R.id.tv_math);
        tv_result = (TextView)findViewById(R.id.tv_result);
        tv_pointA = (TextView)findViewById(R.id.tv_pointA);
        tv_pointB = (TextView)findViewById(R.id.tv_pointB);
        tv_point  = (TextView)findViewById(R.id.tv_point);
        tv_timer  = (TextView) findViewById(R.id.tv_timer);

        btn_zero   = (Button)findViewById(R.id.btn_zero);
        btn_one    = (Button)findViewById(R.id.btn_one);
        btn_two    = (Button)findViewById(R.id.btn_two);
        btn_three  = (Button)findViewById(R.id.btn_three);
        btn_four   = (Button)findViewById(R.id.btn_four);
        btn_five   = (Button)findViewById(R.id.btn_five);
        btn_six    = (Button)findViewById(R.id.btn_six);
        btn_seven  = (Button)findViewById(R.id.btn_seven);
        btn_eight  = (Button)findViewById(R.id.btn_eight);
        btn_nine   = (Button)findViewById(R.id.btn_nine);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_change = (Button)findViewById(R.id.btn_change);
        btn_del    = (Button)findViewById(R.id.btn_del);
        btn_reset  = (Button)findViewById(R.id.btn_reset);

        setValue(btn_zero,0);
        setValue(btn_one,1);
        setValue(btn_two,2);
        setValue(btn_three,3);
        setValue(btn_four,4);
        setValue(btn_five,5);
        setValue(btn_six,6);
        setValue(btn_seven,7);
        setValue(btn_eight,8);
        setValue(btn_nine,9);
        tv_point.setText("Point : "+point);

        getDataIntent();
        setBtn_change();
        setBtn_reset();
        setBtn_del();

       restartTimer(0);
    }

    public void getDataIntent(){
        Intent myIntent = getIntent();
        value = myIntent.getIntExtra("result", 0);
        int setValue;
        if (value==0){
            setValue = diffEasy();
            setBtn_submit(String.valueOf(setValue));
            pointDiff = 1;
        }else if (value==1){
            setValue = diffNormal();
            setBtn_submit(String.valueOf(setValue));
            pointDiff = 3;
        }else if (value==2){
            setValue = diffHard();
            setBtn_submit(String.valueOf(setValue));
            pointDiff = 5;
        }
    }

    public int diffEasy(){
        pointA = random.nextInt(30); // random pertama
        pointB = random.nextInt(20); // random kedua
        pointC = random.nextInt(15); // random perkalian
        pointC = random.nextInt(10); // random perkalian
        newResult = mathType(pointA,pointB,pointC,pointD);
        return newResult;
       }
    public int diffNormal(){
        pointA = random.nextInt(80)+20; // random pertama
        pointB = random.nextInt(80)+20; // random kedua
        pointC = random.nextInt(20)+10;  // random perkalian
        pointD = random.nextInt(15)+5;   // random perkalian
        newResult = mathType(pointA,pointB,pointC,pointD);
        return newResult;
    }
    public int diffHard(){
        pointA = random.nextInt(250)+30; // random pertama
        pointB = random.nextInt(200)+30; // random kedua
        pointC = random.nextInt(25)+15;  // random perkalian
        pointD = random.nextInt(25)+5;   // random perkalian
        newResult = mathType(pointA,pointB,pointC,pointD);
        return newResult;
    }


    public int mathType(int pointA, int pointB, int pointC, int pointD){
        randMath = random.nextInt(3); //random jenis perhitungan
        if (randMath==0){
            tv_math.setText("x");
            tv_pointA.setText(""+pointC);
            tv_pointB.setText(""+pointD);
            result = pointC*pointD;
        }else if (randMath==1){
            tv_math.setText("+");
            tv_pointA.setText(""+pointA);
            tv_pointB.setText(""+pointB);
            result = pointA+pointB;
        }else  if (randMath==2){
            tv_math.setText("-");
            tv_pointA.setText(""+pointA);
            tv_pointB.setText(""+pointB);
            result = pointA-pointB;
        }
        return result;
    }


//    set value on tv_result
    public void setValue(Button button, final int val){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_result.setText(tv_result.getText()+""+val);
            }
        });
    }

//    button validate quest
    public void setBtn_submit(final String result){
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_result.getText().equals(result)){
                    mc_true.start();
                    String myval = tv_timer.getText().toString();
                    int timevalue = Integer.parseInt(myval);
                    point +=setPointWin(timevalue);
                    tv_point.setText("Point : "+point);
                    myTimer.cancel();
                    restartTimer(5000);
                    tv_result.setText("");
                    setUpdateDiff(point);
                }else {
                    mc_false.start();
                    Toast.makeText(GameActivity.this, "Jawaban Salah",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    set point
    public int setPointWin(int time){
        int winPoint = 0;
        if (time<=5){
            winPoint = 5*pointDiff;
        }else if (time>6 && time<=10){
            winPoint = 10*pointDiff;
        }else if (time>11 && time<=15){
            winPoint = 15*pointDiff;
        }else if (time>16){
            winPoint = 20*pointDiff;
        }
        return winPoint;
    }

// update difficulty
    public void setUpdateDiff(int pointWin){
        int hasil;
        if (pointWin>=150 && pointWin<300){
            if (pointWin >= 150 && pointWin < 165){
                Toast.makeText(this, "Level Up", Toast.LENGTH_SHORT).show();
            }
            hasil = diffNormal();
            setBtn_submit(String.valueOf(hasil));
            pointDiff = 3;
        }else if (pointWin>=300){
            if (pointWin >= 300 && pointWin < 315){
                Toast.makeText(this, "Level Up", Toast.LENGTH_SHORT).show();
            }
            hasil = diffHard();
            setBtn_submit(String.valueOf(hasil));
            pointDiff = 5;
        }else {
            getDataIntent();
        }
    }

//    button +/-
    public void setBtn_change(){
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String st = tv_result.getText().toString();
                String newVal;
                if(st.isEmpty()){
                    tv_result.setText("-");
                }else{
                    if(st.substring(0,1).equals("-")){
                        newVal = st.replace("-","");
                        tv_result.setText(""+newVal);
                    }else if (!st.equals("")){
                        tv_result.setText("-"+st);
                    }
                }
            }
        });
    }

//    button pass quest
    public void setBtn_reset(){
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataIntent();
                tv_result.setText("");
            }
        });
    }

//    button delete answer
    public void setBtn_del(){
        if (tv_result.equals("")){
            btn_del.setEnabled(false);
        }else {
            btn_del.setEnabled(true);
            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String str = tv_result.getText().toString();
                    if (!str.equals("")) {
                        String newValue = str.substring(0, str.length() - 1);
                        tv_result.setText("" + newValue);
                    }
                }
            });
        }
    }


    public void restartTimer(int addtime){
        myTimer = new CountDownTimer(sisawaktuMilisecond+addtime,1000){
            public void onTick(long l) {
                sisawaktuMilisecond = l;
                updateTimer();
            }
            public void onFinish() {
                tv_timer.setText("0");
                btn_submit.setEnabled(false);
                Intent intent = new Intent(getApplicationContext(), PopUpActivity.class);
                intent.putExtra("point", point);
                if (value==0){
                    intent.putExtra("result",0);
                }else if (value==1){
                    intent.putExtra("result",1);
                }else if (value==2) {
                    intent.putExtra("result", 2);
                }
                startActivity(intent);
                Toast.makeText(GameActivity.this, "Waktu Habis", Toast.LENGTH_SHORT).show();
                sisawaktuMilisecond = 15000;
            }
        }.start();
        timerrunning = true;
    }

    public void updateTimer(){
        int second = (int) (sisawaktuMilisecond /1000);
        tv_timer.setText("" +second);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameActivity.this, PopUpBackActivity.class);
        startActivity(intent);

    }

}
