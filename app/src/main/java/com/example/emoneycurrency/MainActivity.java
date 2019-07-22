package com.example.emoneycurrency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private String coinSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                // atribui id dos componentes de tela à variáveis - view holder!
        this.mViewHolder.editText = findViewById(R.id.text_valor);
        this.mViewHolder.buttonCalculate = findViewById(R.id.button_calculate);
        this.mViewHolder.textResult = findViewById(R.id.text_result);
        this.mViewHolder.spinner = findViewById(R.id.spinner_coin_select);

                // configura o modelo(adapter) do spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.coins, R.layout.textview_font_change);    //coin é a Array de String de valores do spinner (res>values>strings) //textview_font_change é o modelo do spinner (res>layout>textview_font_change)
        adapter.setDropDownViewResource(R.layout.textview_font_change);         //o dropdown também recebe o modelo
        this.mViewHolder.spinner.setAdapter(adapter);
        this.mViewHolder.spinner.setOnItemSelectedListener(this);

                // configura o click do botão
        this.mViewHolder.buttonCalculate.setOnClickListener(this);

        this.clearField();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_calculate){
            String BRLvalue = this.mViewHolder.editText.getText().toString();   //recebe o valor do campo de texto
            this.mViewHolder.spinner.getOnItemSelectedListener();   //força o valor da opção selecionada no spinner
            if ("".equals(BRLvalue)){
                Toast.makeText(this,"Informe um valor!", Toast.LENGTH_LONG).show(); //Se não houver valor, exibe uma notificação
            } else{

                String valueToSearch = "R$ " + BRLvalue + " in " + getCoinSelected();   //torna todos os valores em uma unica string

                //Intent intent = new Intent(this, CalculateActivity.class); //Não utiliza outra tela(CalculateActivity) pois ainda não consegui pegar os dados do resultado da perquisa.

                    // passa os valores para a API de pesquisa
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);   //Intent da Activity será uma ação de pesquisa na web (NECESSÁRIO ADICIONAR PERMISSÃO AO MANIFEST! >manifests>Android>Manifests.xml)
                intent.putExtra(SearchManager.QUERY, valueToSearch);    //passa para a Intent a query e o valor
                startActivity(intent);                                  //inicializa a Activity com a ação da Intent
            }
        }

    }

    public String getCoinSelected() {
        return coinSelected;
    }

    public void setCoinSelected(String coinSelected) {
        this.coinSelected = coinSelected;
    }

    private void clearField(){  //limpa o campo do valor
        this.mViewHolder.editText.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {    //obtém o valor selecionado no spinner e chama o set da coinSelected
        setCoinSelected(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { //não dá pra deixar o spinner vazio, então não precisou implementar
        //nothing
    }

    private static class ViewHolder{    //view holder para indexação de componentes de tela em variáveis
        EditText editText;
        Button buttonCalculate;
        Spinner spinner;
        TextView textResult;
    }
}