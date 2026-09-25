package com.burst.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    int amarelo = Color.rgb(255, 210, 0);
    int fundo = Color.rgb(8, 8, 8);
    int card = Color.rgb(18, 18, 18);
    int texto = Color.WHITE;
    int cinza = Color.rgb(150, 150, 150);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(fundo);
        getWindow().setNavigationBarColor(fundo);

        criarTelaInicial();
    }

    private TextView texto(String valor, float tamanho) {
        TextView t = new TextView(this);
        t.setText(valor);
        t.setTextColor(texto);
        t.setTextSize(tamanho);
        t.setGravity(Gravity.CENTER_VERTICAL);
        return t;
    }

    private GradientDrawable fundoArredondado(int cor, int raio) {
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
        b.setBackground(fundoArredondado(amarelo, 35));
        b.setPadding(20, 20, 20, 20);

        return b;
    }

    private TextView titulo(String valor) {
        TextView t = texto(valor, 24);
        t.setTextColor(amarelo);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setGravity(Gravity.CENTER);
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

    private void criarTelaInicial() {

        LinearLayout principal = new LinearLayout(this);
        principal.setOrientation(LinearLayout.VERTICAL);
        principal.setBackgroundColor(fundo);

        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(fundo);

        LinearLayout conteudo = new LinearLayout(this);
        conteudo.setOrientation(LinearLayout.VERTICAL);
        conteudo.setPadding(25, 25, 25, 20);

        // LOGO
        TextView logo = titulo("BURST");
        logo.setTextSize(38);
        adicionar(conteudo, logo, 5);

        TextView launcher = titulo("L A U N C H E R");
        launcher.setTextSize(13);
        launcher.setTextColor(Color.LTGRAY);
        launcher.setGravity(Gravity.CENTER);
        adicionar(conteudo, launcher, 0);

        // ÍCONE
        TextView icone = texto("▰\n▰\n▰", 45);
        icone.setTextColor(amarelo);
        icone.setGravity(Gravity.CENTER);
        adicionar(conteudo, icone, 25);

        // VERSÃO
        TextView versao = texto("MCPE 0.15.10     ▼", 18);
        versao.setTextColor(amarelo);
        versao.setGravity(Gravity.CENTER);
        versao.setPadding(20, 25, 20, 25);
        versao.setBackground(fundoArredondado(Color.rgb(45, 38, 5), 30));
        adicionar(conteudo, versao, 10);

        // JOGAR
        TextView jogar = botao("▶   JOGAR");
        jogar.setTextSize(23);

        jogar.setOnClickListener(v -> {
            jogar.setText("CARREGANDO...");
            jogar.setTextColor(Color.BLACK);
        });

        adicionar(conteudo, jogar, 15);

        // CONTA
        TextView conta = texto("👤   Steve_BR\n       Local • Sem autenticação", 16);
        conta.setPadding(20, 20, 20, 20);
        conta.setBackground(fundoArredondado(card, 25));
        adicionar(conteudo, conta, 15);

        // STATUS
        LinearLayout status = new LinearLayout(this);
        status.setOrientation(LinearLayout.HORIZONTAL);

        TextView render = texto("🎨\nRENDERIZADOR\n\nOpenGL ES 2", 14);
        render.setPadding(15, 20, 15, 20);
        render.setBackground(fundoArredondado(card, 25));

        TextView armazenamento =
                texto("💾\nARMAZENAMENTO\n\n1.2 GB livre", 14);
        armazenamento.setPadding(15, 20, 15, 20);
        armazenamento.setBackground(fundoArredondado(card, 25));

        TextView rede =
                texto("📡\nREDE\n\nOnline", 14);
        rede.setPadding(15, 20, 15, 20);
        rede.setBackground(fundoArredondado(card, 25));

        LinearLayout.LayoutParams metade =
                new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1);

        metade.setMargins(5, 5, 5, 5);

        status.addView(render, metade);
        status.addView(armazenamento, metade);
        status.addView(rede, metade);

        adicionar(conteudo, status, 5);

        // AVISO
        TextView aviso =
                texto("⚠  Esta é uma versão não-oficial do MCPE.\n" +
                      "Compre o jogo original na Microsoft Store.", 14);

        aviso.setTextColor(Color.rgb(255, 100, 100));
        aviso.setPadding(20, 20, 20, 20);
        aviso.setBackground(
                fundoArredondado(Color.rgb(35, 10, 10), 25)
        );

        adicionar(conteudo, aviso, 15);

        scroll.addView(conteudo);

        // BARRA INFERIOR
        LinearLayout barra = new LinearLayout(this);
        barra.setOrientation(LinearLayout.HORIZONTAL);
        barra.setGravity(Gravity.CENTER);
        barra.setPadding(10, 10, 10, 10);
        barra.setBackgroundColor(Color.rgb(10, 10, 10));

        TextView inicio = texto("▶\nINÍCIO", 13);
        inicio.setTextColor(amarelo);
        inicio.setGravity(Gravity.CENTER);

        TextView noticias = texto("▣\nNOTÍCIAS", 13);
        noticias.setTextColor(cinza);
        noticias.setGravity(Gravity.CENTER);

        TextView config = texto("⚙\nCONFIG", 13);
        config.setTextColor(cinza);
        config.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams item =
                new LinearLayout.LayoutParams(0, 90, 1);

        barra.addView(inicio, item);
        barra.addView(noticias, item);
        barra.addView(config, item);

        principal.addView(scroll,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0, 1));

        principal.addView(barra);

        setContentView(principal);
    }
            }
