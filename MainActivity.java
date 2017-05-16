package application.example.com.jogovelha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String quadrado = "quadrado";//constante do nome da tag

    private final String o = "O";//bola
    private final String x = "X";//xis
    private String ultimo;//variavel para guardar o ultimo a ter jogado
    private String jogador;

    View view;

    int qtdeMovimentos = 0;

    int[][] EstadoFinal= new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9},

            {1,4,7},
            {2,5,8},
            {3,6,9},

            {1,5,9},
            {3,5,7}
    };//possiveis jogadas para vencer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(getLayoutInflater().inflate(R.layout.activity_main, null));
        setContentView(getView());
    }

    public void novoJogo(View v){
        //((Button)findViewById(R.id.quadrado1)).setText("");
        limparCampos();//limpa todos os campos preenchidos
        setCorPreta();//deixa os textos dos botoes em preto para o jogo começar
        qtdeMovimentos = 0;
    }

    public void limparCampos(){//limpa com campos
        for(int i=1;i<=9;i++){//quadrados de 1 a 9
            if(getQuadrado(i)!=null){
                getQuadrado(i).setText("");//seta o quadrado como vazio
            }
        }
    }

    public void setColor(int btn, int colorX){
        getQuadrado(btn).setTextColor(getResources().getColor(colorX));//seta a cor da letra
    }

    public boolean isFim(){//verifica o ganhador
        for(int i=0;i<=7;i++){
            String s1 = getQuadrado(EstadoFinal[i][0]).getText().toString();
            String s2 = getQuadrado(EstadoFinal[i][1]).getText().toString();
            String s3 = getQuadrado(EstadoFinal[i][2]).getText().toString();

            if(!s1.equals("")&&!s2.equals("")&&!s3.equals("")){//se for vazio não vai validar como fim do jogo
                if(s1.equals(s2)&&s2.equals(s3)){//
                    setColor(EstadoFinal[i][0], R.color.colorAccent);
                    setColor(EstadoFinal[i][1], R.color.colorAccent);
                    setColor(EstadoFinal[i][2], R.color.colorAccent);
                    Toast.makeText(this,"Fim de jogo",Toast.LENGTH_LONG).show();

                    ganhador(s1);
                    ((TextView)findViewById(R.id.movimentos)).setText(qtdeMovimentos);
                    return true;
                }


            }
        }
        return false;
    }

    public void deuVelha(){
        if(!isFim()){
            if(qtdeMovimentos==9){
                Toast.makeText(this, "Ops, deu velha!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void iniciar(View v){
        EnableQuadrado(true);


        RadioButton rbX = (RadioButton)findViewById(R.id.x);
        RadioButton rbO = (RadioButton)findViewById(R.id.o);

        if(rbX.isChecked()){
            ultimo = o;
        }else if(rbO.isChecked()){
            ultimo = x;
        }
    }

    public void EnableQuadrado(boolean b){
        for(int i=1;i<=9;i++){
            if(getQuadrado(i)!=null){
                getQuadrado(i).setEnabled(b);
            }
        }
    }

    public void setCorPreta(){
        for(int i=0;i<=9;i++){
            if(getQuadrado(i)!=null){
                setColor(i,R.color.black);
            }
        }
    }

    public void movimentos(){
        for(int i=0;i<=9;i++){
            if(getQuadrado(i)!=null){

            }
        }

    }

    public void ganhador(String ganhador){
        if(ganhador.equals(x)){
            ((TextView)findViewById(R.id.vencedor)).setText(x);
        }else{
            ((TextView)findViewById(R.id.vencedor)).setText(o);
        }
    }

    public void onclickQuadrado(View v){
        if(!isFim()) {
            if (((Button) v).getText().equals("")) {
                if (getUltimo().equals(x)) {
                    ((Button) v).setText(o);
                    setUltimo(o);
                } else {
                    ((Button) v).setText(x);
                    setUltimo(x);
                }
                qtdeMovimentos++;
                computador();
            } else {
                Toast.makeText(this, "Ops! Este quadrado já esta preenchido, escolha outro.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Jogo acabou!", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(getView().getContext(), v.getTag().toString(), Toast.LENGTH_LONG).show();
    }

    public Button getQuadrado(int tagNum){
        return (Button)getView().findViewWithTag(quadrado+tagNum);
    }

    public void Escolher_jogador(View v){
        jogador = "computador";
    }

    public void computador(){
        if(jogador.equals("computador")){
            for(int i=0;i<=7;i++) {
                String s1 = getQuadrado(EstadoFinal[i][0]).getText().toString();
                String s2 = getQuadrado(EstadoFinal[i][1]).getText().toString();
                String s3 = getQuadrado(EstadoFinal[i][2]).getText().toString();

                if(ultimo.equals(x)){
                    if(s1.equals("")&&s2.equals("")){
                        ((Button)findViewById(R.id.quadrado3)).setText(o);
                    }else if(s2.equals("")&&s3.equals("")){
                        ((Button)findViewById(R.id.quadrado1)).setText(o);
                    }else if(s1.equals("")&&s3.equals("")){
                        ((Button)findViewById(R.id.quadrado2)).setText(o);
                    }else{
                        ((Button)findViewById(R.id.quadrado5)).setText(o);
                    }
                }else{
                    if(s1.equals("")&&s2.equals("")){
                        ((Button)findViewById(R.id.quadrado3)).setText(x);
                    }else if(s2.equals("")&&s3.equals("")){
                        ((Button)findViewById(R.id.quadrado1)).setText(x);
                    }else if(s1.equals("")&&s3.equals("")){
                        ((Button)findViewById(R.id.quadrado2)).setText(x);
                    }else{
                        ((Button)findViewById(R.id.quadrado5)).setText(x);
                    }
                }
            }
        }
    }

    public View getView(){
        return view;
    }

    public void setView(View view){
        this.view = view;
    }

    public String getUltimo(){
        return ultimo;
    }

    public void setUltimo(String ultimo){
        this.ultimo = ultimo;
    }
}
