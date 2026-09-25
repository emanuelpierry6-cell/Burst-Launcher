package com.burst.launcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int YELLOW=Color.rgb(255,208,0), BG=Color.rgb(7,7,7), CARD=Color.rgb(19,19,19), WHITE=Color.WHITE, GRAY=Color.rgb(145,145,145);
    private LinearLayout content;
    private TextView homeTab, newsTab, configTab;

    @Override public void onCreate(Bundle state){
        super.onCreate(state);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        buildShell();
        showHome();
    }

    private GradientDrawable rounded(int color,int radius){
        GradientDrawable d=new GradientDrawable(); d.setColor(color); d.setCornerRadius(radius); return d;
    }
    private TextView tv(String s,float size){
        TextView v=new TextView(this); v.setText(s); v.setTextSize(size); v.setTextColor(WHITE); v.setGravity(Gravity.CENTER_VERTICAL); return v;
    }
    private TextView card(String s,float size){
        TextView v=tv(s,size); v.setPadding(20,18,20,18); v.setBackground(rounded(CARD,24)); return v;
    }
    private TextView button(String s){
        TextView v=tv(s,20); v.setTextColor(Color.BLACK); v.setTypeface(Typeface.DEFAULT,Typeface.BOLD); v.setGravity(Gravity.CENTER); v.setPadding(18,20,18,20); v.setBackground(rounded(YELLOW,50)); return v;
    }
    private void add(View v,int m){
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(m,m,m,m); content.addView(v,p);
    }
    private void buildShell(){
        LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(BG);
        ScrollView scroll=new ScrollView(this);
        content=new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL); content.setPadding(18,18,18,26); scroll.addView(content);
        LinearLayout bar=new LinearLayout(this); bar.setOrientation(LinearLayout.HORIZONTAL); bar.setGravity(Gravity.CENTER); bar.setPadding(4,4,4,4); bar.setBackgroundColor(Color.rgb(12,12,12));
        homeTab=tv("⌂\nINÍCIO",12); newsTab=tv("▣\nNOTÍCIAS",12); configTab=tv("⚙\nCONFIG",12);
        homeTab.setGravity(Gravity.CENTER); newsTab.setGravity(Gravity.CENTER); configTab.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams nav=new LinearLayout.LayoutParams(0,84,1);
        bar.addView(homeTab,nav); bar.addView(newsTab,nav); bar.addView(configTab,nav);
        homeTab.setOnClickListener(v->showHome()); newsTab.setOnClickListener(v->showNews()); configTab.setOnClickListener(v->showConfig());
        root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1)); root.addView(bar); setContentView(root);
    }
    private void nav(String selected){
        homeTab.setTextColor(GRAY); newsTab.setTextColor(GRAY); configTab.setTextColor(GRAY);
        if(selected.equals("home")) homeTab.setTextColor(YELLOW);
        if(selected.equals("news")) newsTab.setTextColor(YELLOW);
        if(selected.equals("config")) configTab.setTextColor(YELLOW);
    }
    private void clear(){ content.removeAllViews(); }

    private void showHome(){
        clear(); nav("home");
        TextView burst=tv("BURST",40); burst.setTextColor(YELLOW); burst.setTypeface(Typeface.DEFAULT,Typeface.BOLD); burst.setGravity(Gravity.CENTER); add(burst,0);
        TextView launcher=tv("L A U N C H E R",12); launcher.setTextColor(GRAY); launcher.setGravity(Gravity.CENTER); add(launcher,0);
        TextView emblem=tv("▰\n▰\n▰",38); emblem.setTextColor(YELLOW); emblem.setGravity(Gravity.CENTER); add(emblem,8);

        TextView version=card("MCPE 0.15.10                         ▼",17); version.setTextColor(YELLOW); version.setGravity(Gravity.CENTER); version.setBackground(rounded(Color.rgb(48,40,5),30)); version.setOnClickListener(v->chooseVersion()); add(version,7);
        TextView play=button("▶   JOGAR"); play.setTextSize(23); play.setOnClickListener(v->new AlertDialog.Builder(this).setTitle("Burst Launcher").setMessage("Versão selecionada: MCPE 0.15.10\n\nO launcher está pronto para trabalhar com arquivos do jogo que o usuário possui legalmente.").setPositiveButton("OK",null).show()); add(play,14);

        TextView account=card("👤   Steve_BR\n\n       LOCAL • SEM AUTENTICAÇÃO",15); account.setOnClickListener(v->accounts()); add(account,9);

        LinearLayout stats=new LinearLayout(this); stats.setOrientation(LinearLayout.HORIZONTAL);
        TextView r=card("🎨\n\nRENDERIZADOR\n\nOpenGL ES 2.0",11), s=card("💾\n\nARMAZENAMENTO\n\n1.2 GB LIVRE",11), n=card("📡\n\nREDE\n\nONLINE",11);
        LinearLayout.LayoutParams col=new LinearLayout.LayoutParams(0,-2,1); col.setMargins(3,3,3,3);
        stats.addView(r,col); stats.addView(s,col); stats.addView(n,col); add(stats,3);

        TextView warning=card("⚠  VERSÃO NÃO-OFICIAL DO MCPE\n\nCompre o jogo original na Microsoft Store.",13); warning.setTextColor(Color.rgb(255,105,105)); warning.setBackground(rounded(Color.rgb(40,12,12),22)); add(warning,13);
        TextView about=card("BURST LAUNCHER\n\nLauncher experimental para versões antigas.\n\nVersões planejadas:\n• MCPE 0.15.10\n• MCPE 0.14.3\n\nFEITO POR PIERRY, O ORIGINAL",13); about.setTextColor(GRAY); add(about,8);
    }

    private void chooseVersion(){
        String[] versions={"MCPE 0.15.10","MCPE 0.14.3"};
        new AlertDialog.Builder(this).setTitle("SELECIONAR VERSÃO").setItems(versions,(d,w)->Toast.makeText(this,"Selecionado: "+versions[w],Toast.LENGTH_SHORT).show()).show();
    }
    private void accounts(){
        LinearLayout box=new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL); box.setPadding(8,4,8,4);
        box.addView(card("👤  Steve_BR\n\nConta local • Sem autenticação",15));
        box.addView(card("Ⓜ  Conta Microsoft\n\nNão conectada",15));
        new AlertDialog.Builder(this).setTitle("CONTAS").setView(box).setNegativeButton("FECHAR",null).show();
    }
    private void showNews(){
        clear(); nav("news");
        TextView title=tv("NOTÍCIAS",29); title.setTextColor(YELLOW); title.setTypeface(Typeface.DEFAULT,Typeface.BOLD); title.setGravity(Gravity.CENTER); add(title,5);
        news("BURST LAUNCHER","Nova versão do launcher disponível.");
        news("MCPE 0.15.10","Suporte planejado para a versão 0.15.10.");
        news("MCPE 0.14.3","Suporte planejado para a versão 0.14.3.");
        news("DESENVOLVIMENTO","Novos recursos serão adicionados ao projeto.");
        news("PIERRY","FEITO POR PIERRY, O ORIGINAL");
    }
    private void news(String title,String desc){ add(card(title+"\n\n"+desc,14),8); }

    private void showConfig(){
        clear(); nav("config");
        TextView title=tv("CONFIGURAÇÕES",27); title.setTextColor(YELLOW); title.setTypeface(Typeface.DEFAULT,Typeface.BOLD); title.setGravity(Gravity.CENTER); add(title,5);
        TextView renderer=card("🎨  RENDERIZADOR\n\nOpenGL ES 2.0\n\nToque para selecionar.",14); renderer.setOnClickListener(v->chooseRenderer()); add(renderer,8);
        add(card("🧠  MEMÓRIA RAM\n\n2048 MB",14),8);
        add(card("🐞  DEBUG\n\nDesativado",14),8);
        add(card("⚗  MODO EXPERIMENTAL\n\nDesativado",14),8);
        add(card("📁  ARQUIVOS DO JOGO\n\nGerenciar arquivos",14),8);
        add(card("💾  ARMAZENAMENTO\n\n1.2 GB livre",14),8);
        add(card("ℹ  SOBRE O BURST LAUNCHER\n\nVersão 1.0\n\nFEITO POR PIERRY, O ORIGINAL",14),8);
        TextView github=button("GITHUB"); github.setTextSize(15); github.setOnClickListener(v->Toast.makeText(this,"Burst Launcher • GitHub",Toast.LENGTH_SHORT).show()); add(github,15);
    }
    private void chooseRenderer(){
        String[] items={"OpenGL ES 2.0","OpenGL ES 3.0","Vulkan"};
        new AlertDialog.Builder(this).setTitle("RENDERIZADOR").setItems(items,(d,w)->Toast.makeText(this,"Selecionado: "+items[w],Toast.LENGTH_SHORT).show()).show();
    }
}
