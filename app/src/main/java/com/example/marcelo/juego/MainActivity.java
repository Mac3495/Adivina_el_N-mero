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

    //Declaramos variables nativas de Android para conectarlas con las vistas XML

    TextView resultado;
    EditText intentos, numero;
    Button botonIntentos;

    /*
    * Declaramos variables que nos servirán para la ejecución del programa
    * numIntentos: variable que almacenara el número de intentos que tendra el jugador
    * numAdivinar: variable que almacenara el número que se tiene que adivinar
    * numIngresado: variable que almacenara el numero que se ingrese para saber si se adivino
    *               o no el número.
    * limite: variable que nos indica el límite de intentos que tiene el jugador*/

    int numIntentos, numAdivinar, numIngresado, limite = 1;

    //Metodo onCreate: ejecuta la aplicación en Android

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Enlazamos las variables Java instanciadas en las lineas 15, 16, 17 con sus
        * respectivas vistas en XML en el archivo activity_main.xml*/

        resultado = (TextView) findViewById(R.id.texto_resultado);
        intentos = (EditText) findViewById(R.id.editText_intentos);
        numero = (EditText) findViewById(R.id.editText_numero);
        botonIntentos = (Button) findViewById(R.id.boton_intento);

    }

    /*Metodo agregarIntentos: Este método nos permite leer el número de intentos que tendrá
    * el jugador para adivinar el número*/

    public void agregarIntentos(View view){

        //Validamos que se haya ingresado un número
        if(intentos.getText().toString().length() > 0){

            /*Obtenemos el valor ingresado en la vista intentos y la transfomamos a un número entero
            * y lo almacenamos en la variable numIntentos*/
            numIntentos = Integer.parseInt(intentos.getText().toString());

            //Deshabilitamos el boton para ingresar intentos
            botonIntentos.invalidate();

            //Generamos el número aleatorio entre 0 y 10 el cual debe ser adivinado
            numAdivinar = (int) (Math.random()*10);

            //Mostramos el mensaje "Intentos ingresados"
            Toast.makeText(this, "Intentos ingresados ", Toast.LENGTH_SHORT).show();
        }
        else{
            /*En caso de que no se haya ingresado ningún número se mostrara el mensaje :
            * "Ingrese el número de intentos"*/
            Toast.makeText(this, "Ingresa el número de intentos", Toast.LENGTH_SHORT).show();
        }
    }

    /*Metodo jugar: Este método contiene toda la lógica para que nuestra apolicación funcione
    * como queremos*/
    public void jugar (View view){

        //Validamos que se haya ingresado un número
        if(intentos.getText().toString().length() > 0){

            /*Obtenemos el valor ingresado en la vista intentos y la transfomamos a un número entero
             * y lo almacenamos en la variable numIngresado*/
            numIngresado = Integer.parseInt(numero.getText().toString());

            //Validamos que el número ingresado sea 10 o menor
            if(numIngresado > 10){

                /*En caso de que se haya ingresado un número mayor a 10 se mostrara el mensaje :
                 * "Solo numeros del 0 a 10" y se limpiara el campo para ingresar un nuevo número*/
                Toast.makeText(this, "Solo numeros del 0 a 10", Toast.LENGTH_SHORT).show();
                numero.setText("");
            }
            else {

                //Revisamos si el límite es menor al numero de intentos
                if (limite < numIntentos) {

                    //Revisamos si el número ingresado es igual número que se tiene que adivinar
                    if (numIngresado == numAdivinar) {

                        /*En caso de que sea verdadero el jugador gana y se imprime el mensaje:
                        * "Gano en + La cantidad de intentos que le tomó adivinar el número + intentos"*/
                        resultado.setText("Ganó en " + limite + " intentos");

                        //Se establece que el límite es mayor al número de intentos para termina el juego
                        limite = numIntentos + 1;
                    }
                        /*En caso de que el numero ingresado no sea igual al número que se tiene que
                        * adivinar revisamos si el numero ingresado es menor al númeor que se tiene
                        * que adivinar*/
                        else if(numIngresado < numAdivinar){

                            /*En caso de que el número ingresado sea menor al número que se tiene que
                            * adivinar se mostrará el mensaje: Fallaste, limpiando el campo donde
                            * se ingresara un nuevo número*/
                            Toast.makeText(this, "Fallaste", Toast.LENGTH_SHORT).show();
                            numero.setText("");

                            /*Asi mismo se le proporcionará una pista al jugador y se incrementará
                            * el límite de intentos que le quedan*/
                            resultado.setText("El numero es mayor");
                            limite++;
                    }
                        /*En caso de que el número ingresado sea mayor al número que se tiene que
                         * adivinar se mostrará el mensaje: Fallaste, limpiando el campo donde
                         * se ingresara un nuevo número*/
                        else{
                        Toast.makeText(this, "Fallaste", Toast.LENGTH_SHORT).show();
                        numero.setText("");

                        /*Asi mismo se le proporcionará una pista al jugador y se incrementará
                         * el límite de intentos que le quedan*/
                        resultado.setText("El numero es menor");
                        limite++;
                    }
                }

                //En caso de que el límite sea mayor al númeor de intentos el jugador pierde
                else {
                    resultado.setText("PERDISTE");
                }
            }
        }
        else{
            /*En caso de que no se haya ingresado ningún número se mostrara el mensaje :
             * "Ingrese un número"*/
            Toast.makeText(this, "Ingresa un número", Toast.LENGTH_SHORT).show();
        }

    }

    /*Metodo reinicia: Nos permitira reiniciar el juego una vez haya concluido*/
    public void reinicia (View view){

        //Asignamos el valor inicial de 1 a la variable limite
        limite = 1;

        //Habilitamos el boton para ingresar el numero de intentos
        botonIntentos.isEnabled();

        //Limpiamos los campos donde se ingresara el numero de intentos y el donde se ingresaran los numeros
        intentos.setText("");
        numero.setText("");

        //Mostramos el mensaje Juega!
        resultado.setText("JUEGA!");
    }
}
