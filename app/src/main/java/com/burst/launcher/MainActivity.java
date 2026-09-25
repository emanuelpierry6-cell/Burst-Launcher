package com.burst.launcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    private final int AMARELO = Color.rgb(255, 210, 0);
    private final int FUNDO = Color.rgb(7, 7, 9);
    private final int CARD = Color.rgb(19, 19, 22);
    private final int CARD2 = Color.rgb(28, 28, 32);
    private final int BRANCO = Color.WHITE;
    private final int CINZA = Color.rgb(155, 155, 160);
    private final int VERMELHO = Color.rgb(255, 90, 90);

    private LinearLayout principal;
    private LinearLayout conteudo;
    private LinearLayout barra;

    private TextView inicio;
    private TextView noticias;
    private TextView config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(FUNDO);
        getWindow().setNavigationBarColor(FUNDO);

        criarAplicativo();
        mostrarInicio();
    }

    // =========================
    // COMPONENTES
    // =========================

    private TextView texto(String valor, float tamanho) {
        TextView t = new TextView(this);
        t.setText(valor);
        t.setTextColor(BRANCO);
        t.setTextSize(tamanho);
        t.setGravity(Gravity.CENTER_VERTICAL);
        return t;
    }

    private TextView titulo(String valor) {
        TextView t = texto(valor, 25);
        t.setTextColor(AMARELO);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setGravity(Gravity.CENTER);
        return t;
    }

    private GradientDrawable fundo(int cor, int raio) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(cor);
        g.setCornerRadius(raio);
        return g;
    }

    private TextView botao(String valor) {
        TextView b = texto(valor, 18);
        b.setTextColor(Color.BLACK);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setGravity(Gravity.CENTER);
        b.setPadding(20, 22, 20, 22);
        b.setBackground(fundo(AMARELO, 40));
        return b;
    }

    private TextView card(String valor, float tamanho) {
        TextView t = texto(valor, tamanho);
        t.setPadding(20, 20, 20, 20);
        t.setBackground(fundo(CARD, 25));
        return t;
    }

    private void adicionar(LinearLayout pai, View view, int margem) {
        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        p.setMargins(margem, margem, margem, margem);
        pai.addView(view, p);
    }

    // =========================
    // ESTRUTURA PRINCIPAL
    // =========================

    private void criarAplicativo() {

        principal = new LinearLayout(this);
        principal.setOrientation(LinearLayout.VERTICAL);
        principal.setBackgroundColor(FUNDO);

        conteudo = new LinearLayout(this);
        conteudo.setOrientation(LinearLayout.VERTICAL);
        conteudo.setPadding(22, 25, 22, 20);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(FUNDO);
        scroll.addView(conteudo);

        barra = new LinearLayout(this);
        barra.setOrientation(LinearLayout.HORIZONTAL);
        barra.setGravity(Gravity.CENTER);
        barra.setPadding(8, 8, 8, 8);
        barra.setBackgroundColor(Color.rgb(11, 11, 13));

        inicio = texto("▶\nINÍCIO", 13);
        noticias = texto("▣\nNOTÍCIAS", 13);
        config = texto("⚙\nCONFIG", 13);

        inicio.setGravity(Gravity.CENTER);
        noticias.setGravity(Gravity.CENTER);
        config.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams item =
                new LinearLayout.LayoutParams(
                        0, 90, 1
                );

        barra.addView(inicio, item);
        barra.addView(noticias, item);
        barra.addView(config, item);

        inicio.setOnClickListener(v -> mostrarInicio());
        noticias.setOnClickListener(v -> mostrarNoticias());
        config.setOnClickListener(v -> mostrarConfig());

        principal.addView(
                scroll,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        
