package com.example.marcelo.juego;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView resultado;
    EditText intentos, numero;
    Button botonIntentos;
    int numIntentos, numAdivinar, numIngresado, limite = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = (TextView) findViewById(R.id.texto_resultado);
        intentos = (EditText) findViewById(R.id.editText_intentos);
        numero = (EditText) findViewById(R.id.editText_numero);
        botonIntentos = (Button) findViewById(R.id.boton_intento);

    }

    public void agregarIntentos(View view){
        if(intentos.getText().toString().length() > 0){
            numIntentos = Integer.parseInt(intentos.getText().toString());
            botonIntentos.invalidate();
            numAdivinar = (int) (Math.random()*10);
            Log.i("Numero-------------", numAdivinar + "");
            Toast.makeText(this, "Intentos ingresados ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Ingresa el número de intentos", Toast.LENGTH_SHORT).show();
        }
    }

    public void jugar (View view){
        if(intentos.getText().toString().length() > 0){
            numIngresado = Integer.parseInt(numero.getText().toString());
            if(numIngresado > 10){
                Toast.makeText(this, "Solo numeros del 0 a 10", Toast.LENGTH_SHORT).show();
                numero.setText("");
            }
            else {
                if (limite < numIntentos) {
                    if (numIngresado == numAdivinar) {
                        resultado.setText("Ganó en " + limite + " intentos");
                        limite = numIntentos + 1;
                    } else if(numIngresado<numAdivinar){
                        Toast.makeText(this, "Fallaste", Toast.LENGTH_SHORT).show();
                        numero.setText("");
                        resultado.setText("El numero es mayor");
                        limite++;
                    }else{
                        Toast.makeText(this, "Fallaste", Toast.LENGTH_SHORT).show();
                        numero.setText("");
                        resultado.setText("El numero es menor");
                        limite++;
                    }
                }
                else {
                    resultado.setText("PERDISTE");
                }
            }
        }
        else{
            Toast.makeText(this, "Ingresa el número de intentos", Toast.LENGTH_SHORT).show();
        }

    }

    public void reinicia (View view){
        limite = 1;
        botonIntentos.isEnabled();
        intentos.setText("");
        numero.setText("");
        resultado.setText("JUEGA!");
    }
}
