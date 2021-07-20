package br.com.cursoandroid.teste.gorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    public static final double PERCENTAGE_VALOR = 0.01;
    private TextInputEditText inputPrice;
    private TextView valorOfSeekbarTextView;
    private SeekBar tipPercentageSeekbar;
    private TextView tipValorTextView;
    private TextView totalToPayTextView;
    private Boolean validatorToCalculate;
    private double inputPriceTextValorDouble;
    private double tipValorTextViewDouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaCampos();
        validateSeekChangeListener();
    }

    private void validateSeekChangeListener() {
        tipPercentageSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valorOfSeekbarTextView.setText(progress + "%");
                validateInputPriceTextValorToCalculate();
                setValorToTextViewIfIsTrue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setValorToTextViewIfIsTrue(int progress) {
        if(validatorToCalculate){
            showTipValorTextView(progress);
            showTotalToPayTextView();
        }
    }

    private void showTotalToPayTextView() {
        double totalToPayTextViewDouble = inputPriceTextValorDouble + tipValorTextViewDouble;
        totalToPayTextView.setText(String.format("R$:%.2f", totalToPayTextViewDouble));
    }

    private void showTipValorTextView(int progress) {
        tipValorTextViewDouble = inputPriceTextValorDouble * progress * PERCENTAGE_VALOR;
        tipValorTextView.setText(String.format("R$:%.2f", tipValorTextViewDouble));
    }

    private void validateInputPriceTextValorToCalculate() {
        String inputPriceTextValor = inputPrice.getText().toString();
        validatorToCalculate = false;
        if(inputPriceTextValor.equals("") || inputPriceTextValor == null){
            showMessage();
        }else{
            validatorToCalculate = true;
            convertValorToString(inputPriceTextValor);
        }
    }

    private void convertValorToString(String inputPriceTextValor) {
        inputPriceTextValorDouble = Double.parseDouble(inputPriceTextValor);
    }

    private void showMessage() {
        Toast.makeText(this, "Digite um valor, por favor!!", Toast.LENGTH_SHORT).show();
    }

    private void inicializaCampos() {
        inputPrice = findViewById(R.id.activity_main_txt_input_valor);
        valorOfSeekbarTextView = findViewById(R.id.activity_main_txt_resultado_porcentagem_seekbar);
        tipPercentageSeekbar = findViewById(R.id.activity_main_seekbar);
        tipValorTextView = findViewById(R.id.activity_main_txt_gorjeta);
        totalToPayTextView = findViewById(R.id.activity_main_txt_total);
    }
}