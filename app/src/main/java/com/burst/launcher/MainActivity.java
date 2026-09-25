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

    private final int AMARELO = Color.rgb(255, 210, 0);
    private final int FUNDO = Color.rgb(8, 8, 8);
    private final int CARD = Color.rgb(19, 19, 19);
    private final int CINZA = Color.rgb(150, 150, 150);
    private final int BRANCO = Color.WHITE;

    private LinearLayout conteudo;

    private TextView navInicio;
    private TextView navNoticias;
    private TextView navConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(FUNDO);
        getWindow().setNavigationBarColor(FUNDO);

        criarEstrutura();
        mostrarInicio();
    }

    // =========================================================
    // COMPONENTES
    // =========================================================

    private TextView texto(String valor, float tamanho) {
        TextView t = new TextView(this);

        t.setText(valor);
        t.setTextSize(tamanho);
        t.setTextColor(BRANCO);
        t.setGravity(Gravity.CENTER_VERTICAL);

        return t;
    }

    private GradientDrawable arredondado(int cor, int raio) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(cor);
        g.setCornerRadius(raio);

        return g;
    }

    private TextView titulo(String valor) {
        TextView t = texto(valor, 26);

        t.setTextColor(AMARELO);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setGravity(Gravity.CENTER);

        return t;
    }

    private TextView botao(String valor) {
        TextView b = texto(valor, 18);

        b.setTextColor(Color.BLACK);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setGravity(Gravity.CENTER);

        b.setPadding(20, 20, 20, 20);
        b.setBackground(arredondado(AMARELO, 40));

        return b;
    }

    private TextView cartao(String valor, float tamanho) {
        TextView t = texto(valor, tamanho);

        t.setPadding(20, 20, 20, 20);
        t.setBackground(arredondado(CARD, 25));

        return t;
    }

    private void adicionar(View view, int margem) {

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        p.setMargins(margem, margem, margem, margem);

        conteudo.addView(view, p);
    }

    // =========================================================
    // ESTRUTURA
    // =========================================================

    private void criarEstrutura() {

        LinearLayout principal = new LinearLayout(this);

        principal.setOrientation(LinearLayout.VERTICAL);
        principal.setBackgroundColor(FUNDO);

        ScrollView scroll = new ScrollView(this);

        scroll.setBackgroundColor(FUNDO);

        conteudo = new LinearLayout(this);

        conteudo.setOrientation(LinearLayout.VERTICAL);
        conteudo.setPadding(22, 22, 22, 25);

        scroll.addView(conteudo);

        // -------------------------
        // BARRA INFERIOR
        // -------------------------

        LinearLayout barra = new LinearLayout(this);

        barra.setOrientation(LinearLayout.HORIZONTAL);
        barra.setGravity(Gravity.CENTER);
        barra.setPadding(5, 5, 5, 5);
        barra.setBackgroundColor(Color.rgb(12, 12, 12));

        navInicio = texto("▶\nINÍCIO", 13);
        navNoticias = texto("▣\nNOTÍCIAS", 13);
        navConfig = texto("⚙\nCONFIG", 13);

        navInicio.setGravity(Gravity.CENTER);
        navNoticias.setGravity(Gravity.CENTER);
        navConfig.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams item =
                new LinearLayout.LayoutParams(
                        0,
                        90,
                        1
                );

        barra.addView(navInicio, item);
        barra.addView(navNoticias, item);
        barra.addView(navConfig, item);

        navInicio.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mostrarInicio();
                    }
                }
        );

        navNoticias.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mostrarNoticias();
                    }
                }
        );

        navConfig.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mostrarConfig();
                    }
                }
        );

        principal.addView(
                scroll,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        1
                )
        );

        principal.addView(barra);

        setContentView(principal);
    }

    private void limpar() {
        conteudo.removeAllViews();
    }

    private void selecionar(String tela) {

        navInicio.setTextColor(CINZA);
        navNoticias.setTextColor(CINZA);
        navConfig.setTextColor(CINZA);

        if (tela.equals("inicio")) {
            navInicio.setTextColor(AMARELO);
        }

        if (tela.equals("noticias")) {
            navNoticias.setTextColor(AMARELO);
        }

        if (tela.equals("config")) {
            navConfig.setTextColor(AMARELO);
        }
    }

    // =========================================================
    // INÍCIO
    // =========================================================

    private void mostrarInicio() {

        limpar();
        selecionar("inicio");

        TextView logo = titulo("BURST");

        logo.setTextSize(38);

        adicionar(logo, 3);

        TextView launcher = texto(
                "L A U N C H E R",
                13
        );

        launcher.setTextColor(CINZA);
        launcher.setGravity(Gravity.CENTER);

        adicionar(launcher, 0);

        TextView icone = texto(
                "▰\n▰\n▰",
                40
        );

        icone.setTextColor(AMARELO);
        icone.setGravity(Gravity.CENTER);

        adicionar(icone, 15);

        // VERSÃO

        TextView versao = texto(
                "MCPE 0.15.10        ▼",
                18
        );

        versao.setTextColor(AMARELO);
        versao.setGravity(Gravity.CENTER);

        versao.setPadding(20, 24, 20, 24);

        versao.setBackground(
                arredondado(
                        Color.rgb(45, 38, 5),
                        30
                )
        );

        versao.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        escolherVersao();
                    }
                }
        );

        adicionar(versao, 8);

        // JOGAR

        TextView jogar = botao(
                "▶   JOGAR"
        );

        jogar.setTextSize(22);

        jogar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Burst Launcher")
                                .setMessage(
                                        "Versão selecionada:\n\n" +
                                        "MCPE 0.15.10\n\n" +
                                        "O launcher está pronto."
                                )
                                .setPositiveButton(
                                        "OK",
                                        null
                                )
                                .show();
                    }
                }
        );

        adicionar(jogar, 15);

        // CONTA

        TextView conta = cartao(
                "👤   Steve_BR\n\n" +
                "       Local • Sem autenticação",
                16
        );

        conta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        abrirContas();
                    }
                }
        );

        adicionar(conta, 12);

        // STATUS

        LinearLayout status = new LinearLayout(this);

        status.setOrientation(
                LinearLayout.HORIZONTAL
        );

        TextView render = cartao(
                "🎨\n\nRENDERIZADOR\n\nOpenGL ES 2.0",
                12
        );

        TextView armazenamento = cartao(
                "💾\n\nARMAZENAMENTO\n\n1.2 GB livre",
                12
        );

        TextView rede = cartao(
                "📡\n\nREDE\n\nOnline",
                12
        );

        LinearLayout.LayoutParams coluna =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                );

        coluna.setMargins(
                4,
                4,
                4,
                4
        );

        status.addView(render, coluna);
        status.addView(armazenamento, coluna);
        status.addView(rede, coluna);

        adicionar(status, 5);

        // AVISO

        TextView aviso = cartao(
                "⚠  Esta é uma versão não-oficial do MCPE.\n\n" +
                "Compre o jogo original na Microsoft Store.",
                14
        );

        aviso.setTextColor(
                Color.rgb(255, 110, 110)
        );

        aviso.setBackground(
                arredondado(
                        Color.rgb(40, 12, 12),
                        25
                )
        );

        adicionar(aviso, 15);

        // INFORMAÇÕES

        TextView informacao = cartao(
                "BURST LAUNCHER\n\n" +
                "Launcher experimental para versões antigas.\n\n" +
                "Versão: 1.2.0",
                14
        );

        informacao.setTextColor(CINZA);

        adicionar(informacao, 10);
    }

    // =========================================================
    // VERSÕES
    // =========================================================

    private void escolherVersao() {

        final String[] versoes = {
                "MCPE 0.15.10",
                "MCPE 0.14.3"
        };

        new AlertDialog.Builder(this)
                .setTitle("Selecionar versão")
                .setItems(
                        versoes,
                        new android.content.DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(
                                    android.content.DialogInterface dialog,
                                    int which) {

                                Toast.makeText(
                                        MainActivity.this,
                                        "Selecionado: " +
                                                versoes[which],
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                )
                .show();
    }

    // =========================================================
    // CONTAS
    // =========================================================

    private void abrirContas() {

        LinearLayout layout = new LinearLayout(this);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        layout.setPadding(
                20,
                10,
                20,
                10
        );

        TextView local = cartao(
                "👤  Steve_BR\n\n" +
                "Local • Sem autenticação",
                16
        );

        TextView microsoft = cartao(
                "Ⓜ  Conta Microsoft\n\n" +
                "Não conectada",
                16
        );

        TextView adicionarConta =
                botao("+  ADICIONAR CONTA");

        adicionarConta.setTextSize(14);

        layout.addView(local);

        layout.addView(microsoft);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        p.setMargins(
                0,
                15,
                0,
                0
        );

        layout.addView(
                adicionarConta,
                p
        );

        adicionarConta.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(
                                MainActivity.this,
                                "Login Microsoft será adicionado futuramente.",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );

        new AlertDialog.Builder(this)
                .setTitle("Contas")
                .setView(layout)
                .setNegativeButton(
                        "FECHAR",
                        null
                )
                .show();
    }

    // =========================================================
    // NOTÍCIAS
    // =========================================================

    private void mostrarNoticias() {

        limpar();
        selecionar("noticias");

        TextView titulo = titulo(
                "NOTÍCIAS"
        );

        titulo.setTextSize(29);

        adicionar(titulo, 5);

        noticia(
                "Burst Launcher v1.4.2 Released",
                "Nova atualização do launcher com melhorias de desempenho e estabilidade."
        );

        noticia(
                "MCPE 0.15.10 — Patch Notes",
                "Informações e alterações relacionadas à versão 0.15.10."
        );

        noticia(
                "Suporte a shaders experimentais",
                "Novos recursos gráficos estão sendo estudados para futuras versões."
        );

        noticia(
                "Burst Launcher no GitHub",
                "Acompanhe o desenvolvimento do projeto no GitHub."
        );

        noticia(
                "Novas versões antigas",
                "O launcher foi preparado para trabalhar com diferentes versões."
        );
    }

    private void noticia(
            String tituloTexto,
            String descricao
    ) {

        LinearLayout bloco =
                new LinearLayout(this);

        bloco.setOrientation(
                LinearLayout.VERTICAL
        );

        bloco.setPadding(
                20,
                20,
                20,
                20
        );

        bloco.setBackground(
                arredondado(
                        CARD,
                        25
                )
        );

        TextView tituloNoticia =
                texto(
                        tituloTexto,
                        18
                );

        tituloNoticia.setTextColor(
                AMARELO
        );

        tituloNoticia.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        TextView textoNoticia =
                texto(
                        descricao,
                        14
                );

        textoNoticia.setTextColor(
                CINZA
        );

        textoNoticia.setPadding(
                0,
                12,
                0,
                0
        );

        bloco.addView(
                tituloNoticia
        );

        bloco.addView(
                textoNoticia
        );

        adicionar(
                bloco,
                10
        );
    }

    // =========================================================
    // CONFIGURAÇÕES
    // =========================================================

    private void mostrarConfig() {

        limpar();
        selecionar("config");

        TextView titulo = titulo(
                "CONFIGURAÇÕES"
        );

        titulo.setTextSize(27);

        adicionar(titulo, 5);

        TextView renderizador = cartao(
                "🎨  RENDERIZADOR\n\n" +
                "OpenGL ES 2.0\n\n" +
                "Toque para selecionar",
                15
        );

        renderizador.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        escolherRenderizador();
                    }
                }
        );

        adicionar(renderizador, 10);

        TextView ram = cartao(
                "🧠  MEMÓRIA RAM\n\n" +
                "RAM atual: 2048 MB\n\n" +
                "Recomendado: 1024–2048 MB",
                15
        );

        adicionar(ram, 10);

        TextView debug = cartao(
                "🐞  LOG DE DEBUG\n\n" +
                "Desativado\n\n" +
                "Toque para alterar",
                15
        );

        debug.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(
                                MainActivity.this,
                                "Opção de debug alterada.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        adicionar(debug, 10);

        TextView experimental = cartao(
                "⚗  MODO EXPERIMENTAL\n\n" +
                "Desativado\n\n" +
                "Recursos experimentais",
                15
        );

        adicionar(experimental, 10);

        TextView mundos = cartao(
                "🌎  MUNDOS SALVOS\n\n" +
                "Gerenciar mundos do jogo",
                15
        );

        adicionar(mundos, 10);

        TextView packs = cartao(
                "📦  RESOURCE PACKS\n\n" +
                "Gerenciar pacotes de recursos",
                15
        );

        adicionar(packs, 10);

        TextView backups = cartao(
                "💾  BACKUPS\n\n" +
                "Gerenciar cópias de segurança",
                15
        );

        adicionar(backups, 10);

        TextView github = botao(
                "GITHUB"
        );

        github.setTextSize(15);

        github.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(
                                MainActivity.this,
                                "GitHub do Burst Launcher",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        adicionar(github, 15);
    }

    // =========================================================
    // RENDERIZADOR
    // =========================================================

    private void escolherRenderizador() {

        final String[] opcoes = {
                "OpenGL ES 2.0",
                "OpenGL ES 3.0",
                "Vulkan Beta"
        };

      
