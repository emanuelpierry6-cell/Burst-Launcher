package com.burst.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tela = new TextView(this);

        tela.setText("BURST\nLAUNCHER\n\nMCPE 0.15.10\n\n▶ JOGAR");
        tela.setTextColor(Color.YELLOW);
        tela.setTextSize(28);
        tela.setGravity(Gravity.CENTER);
        tela.setBackgroundColor(Color.rgb(8, 8, 8));

        setContentView(tela);
    }
}
