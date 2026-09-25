package com.burst.launcher;

import android.app.*;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    static final int BG=Color.rgb(8,8,8), CARD=Color.rgb(20,20,20), YELLOW=Color.rgb(255,210,0), MUTED=Color.rgb(155,155,155);
    String selectedVersion="MCPE 0.15.10"; TextView versionButton;
    int dp(float v){return (int)(v*getResources().getDisplayMetrics().density+0.5f);}
    GradientDrawable bg(int c,float r){GradientDrawable d=new GradientDrawable();d.setColor(c);d.setCornerRadius(dp(r));return d;}
    TextView tv(String s,float z,int c){TextView t=new TextView(this);t.setText(s);t.setTextSize(z);t.setTextColor(c);return t;}
    void margin(View v,int l,int top,int r,int bottom){LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2);p.setMargins(dp(l),dp(top),dp(r),dp(bottom));v.setLayoutParams(p);}
    @Override public void onCreate(Bundle b){super.onCreate(b);getWindow().setStatusBarColor(BG);getWindow().setNavigationBarColor(BG);showHome();}
    void showHome(){
        LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setBackgroundColor(BG);
        ScrollView scroll=new ScrollView(this);LinearLayout content=new LinearLayout(this);content.setOrientation(LinearLayout.VERTICAL);content.setPadding(dp(18),dp(16),dp(18),dp(18));
        TextView logo=tv("BURST",36,YELLOW);logo.setTypeface(Typeface.DEFAULT,Typeface.BOLD);logo.setGravity(Gravity.CENTER);content.addView(logo);
        TextView sub=tv("L A U N C H E R",13,Color.LTGRAY);sub.setGravity(Gravity.CENTER);margin(sub,0,0,0,18);content.addView(sub);
        TextView icon=tv("▰\n▰\n▰",38,YELLOW);icon.setGravity(Gravity.CENTER);margin(icon,0,4,0,18);content.addView(icon);
        versionButton=tv(selectedVersion+"    ▼",18,YELLOW);versionButton.setGravity(Gravity.CENTER);versionButton.setPadding(dp(12),dp(18),dp(12),dp(18));versionButton.setBackground(bg(Color.rgb(52,43,4),28));versionButton.setOnClickListener(v->chooseVersion());margin(versionButton,0,0,0,12);content.addView(versionButton);
        TextView play=tv("▶  JOGAR",23,Color.BLACK);play.setTypeface(Typeface.DEFAULT,Typeface.BOLD);play.setGravity(Gravity.CENTER);play.setPadding(dp(10),dp(17),dp(10),dp(17));play.setBackground(bg(YELLOW,28));play.setOnClickListener(v->Toast.makeText(this,selectedVersion+" selecionado. A execução do jogo será adicionada na próxima etapa.",Toast.LENGTH_LONG).show());margin(play,0,0,0,14);content.addView(play);
        TextView account=tv("👤   Steve_BR\n       Local • Sem autenticação",17,Color.WHITE);account.setPadding(dp(18),dp(17),dp(18),dp(17));account.setBackground(bg(CARD,22));account.setOnClickListener(v->startActivity(new Intent(this,AccountsActivity.class)));margin(account,0,0,0,14);content.addView(account);
        LinearLayout stats=new LinearLayout(this);stats.setOrientation(LinearLayout.HORIZONTAL);stats.setWeightSum(3);addStat(stats,"🎨","RENDERIZADOR","OpenGL ES 2");addStat(stats,"💾","ARMAZENAMENTO","1.2 GB livre");addStat(stats,"📡","REDE","Online");margin(stats,0,0,0,14);content.addView(stats);
        TextView warning=tv("⚠  Esta é uma versão não-oficial do MCPE.\nCompre o jogo original na Microsoft Store.",14,Color.rgb(255,110,110));warning.setPadding(dp(16),dp(15),dp(16),dp(15));warning.setBackground(bg(Color.rgb(45,10,12),20));margin(warning,0,0,0,18);content.addView(warning);
        scroll.addView(content);root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));root.addView(bottomBar(),new LinearLayout.LayoutParams(-1,dp(76)));setContentView(root);
    }
    void addStat(LinearLayout row,String icon,String title,String value){LinearLayout box=new LinearLayout(this);box.setOrientation(LinearLayout.VERTICAL);box.setPadding(dp(10),dp(13),dp(10),dp(13));box.setBackground(bg(CARD,20));box.addView(tv(icon,16,Color.WHITE));TextView b=tv(title,12,Color.WHITE);b.setTypeface(Typeface.DEFAULT,Typeface.BOLD);box.addView(b);box.addView(tv(value,14,Color.WHITE));LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,-2,1);p.setMargins(dp(3),0,dp(3),0);row.addView(box,p);}
    View bottomBar(){LinearLayout bar=new LinearLayout(this);bar.setOrientation(LinearLayout.HORIZONTAL);bar.setGravity(Gravity.CENTER);bar.setBackgroundColor(Color.rgb(10,10,10));TextView home=nav("▶\nINÍCIO",true),news=nav("▣\nNOTÍCIAS",false),settings=nav("⚙\nCONFIG",false);home.setOnClickListener(v->showHome());news.setOnClickListener(v->startActivity(new Intent(this,NewsActivity.class)));settings.setOnClickListener(v->startActivity(new Intent(this,SettingsActivity.class)));bar.addView(home,new LinearLayout.LayoutParams(0,-1,1));bar.addView(news,new LinearLayout.LayoutParams(0,-1,1));bar.addView(settings,new LinearLayout.LayoutParams(0,-1,1));return bar;}
    TextView nav(String s,boolean active){TextView t=tv(s,12,active?YELLOW:MUTED);t.setGravity(Gravity.CENTER);return t;}
    void chooseVersion(){String[] versions={"MCPE 0.15.10","MCPE 0.14.3"};int checked=selectedVersion.equals(versions[0])?0:1;new AlertDialog.Builder(this).setTitle("Selecionar versão").setSingleChoiceItems(versions,checked,(d,w)->{selectedVersion=versions[w];versionButton.setText(selectedVersion+"    ▼");d.dismiss();}).setNegativeButton("Cancelar",null).show();}
}
