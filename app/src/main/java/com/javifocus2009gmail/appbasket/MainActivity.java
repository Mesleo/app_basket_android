package com.javifocus2009gmail.appbasket;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {
    Button button1Local;
    Button button2Local;
    Button button3Local;
    ImageButton restarLocal;
    TextView resultadoLocal;
    int rLocal;
    ImageButton restarVisitante;
    Button button1Visitante;
    Button button2Visitante;
    Button button3Visitante;
    TextView resultadoVisitante;
    int rVisitante;
    Button resetear;
    AlertDialog.Builder dialogo1;
    TextView nombreLocal;
    TextView nombreVisitante;
    Intent intent;
    Bundle bundle;
    public final static int MI_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        nombreLocal = (TextView) findViewById(R.id.eLocal);
        nombreVisitante = (TextView) findViewById(R.id.eVisitante);

        /**
         * Equipo Local
         */
        resultadoLocal = (TextView) findViewById(R.id.resultado1);
        rLocal = Integer.parseInt(resultadoLocal.getText().toString());
        button2Local = (Button) findViewById(R.id.button2local);
        button3Local = (Button) findViewById(R.id.button3local);
        button1Local = (Button) findViewById(R.id.button1local);
        restarLocal = (ImageButton) findViewById(R.id.buttonRestarLocal);

        /**
         * Equipo visitante
         */
        resultadoVisitante = (TextView) findViewById(R.id.resultado2);
        rVisitante = Integer.parseInt(resultadoVisitante.getText().toString());
        button2Visitante = (Button) findViewById(R.id.button2visitante);
        button3Visitante = (Button) findViewById(R.id.button3visitante);
        button1Visitante = (Button) findViewById(R.id.button1visitante);
        restarVisitante = (ImageButton) findViewById(R.id.buttonRestarVisitante);

        resetear = (Button) findViewById(R.id.reset);

        dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("¿Estás seguro?");

        /**
         * Funcionalidad equipo local
         */
        button2Local.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                rLocal +=2;
                resultadoLocal.setText(String.valueOf(rLocal));
            }
        });
        button3Local.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                rLocal +=3;
                resultadoLocal.setText(String.valueOf(rLocal));
            }
        });
        button1Local.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                rLocal +=1;
                resultadoLocal.setText(String.valueOf(rLocal));
            }
        });
        restarLocal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rLocal == 0){
                    return;
                }
                else{
                    rLocal -=1;
                    resultadoLocal.setText(String.valueOf(rLocal));
                }
            }
        });

        /**
         * Funcionalidad equipo visitante
         */
        button2Visitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                rVisitante +=2;
                resultadoVisitante.setText(String.valueOf(rVisitante));
            }
        });
        button3Visitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                rVisitante +=3;
                resultadoVisitante.setText(String.valueOf(rVisitante));
            }
        });
        button1Visitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                rVisitante +=1;
                resultadoVisitante.setText(String.valueOf(rVisitante));
            }
        });

        restarVisitante.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rVisitante == 0){
                    return;
                }
                else{
                    rVisitante -=1;
                    resultadoVisitante.setText(String.valueOf(rVisitante));
                }
            }
        });
    }

    private void deseaSalir(){
        if(rVisitante != 0 || rLocal != 0) {
            dialogo1.setMessage("¿Estás seguro?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    finish();
                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    return;
                }
            });
            dialogo1.show();
        }else{
            finish();
        }
    }

    private void resetear() {
        if(rVisitante != 0 || rLocal != 0) {
            dialogo1.setMessage("¿Poner marcadores a cero?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    rVisitante = 0;
                    rLocal = 0;
                    resultadoLocal.setText(String.valueOf(rLocal));
                    resultadoVisitante.setText(String.valueOf(rVisitante));
                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    return;
                }
            });
            dialogo1.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MI_REQUEST_CODE) {
            String local = data.getStringExtra(NombrarEquipos.EQUIPO_LOCAL);
            String visitante = data.getStringExtra(NombrarEquipos.EQUIPO_VISITANTE);
            nombreLocal.setText(String.valueOf(local));
            nombreVisitante.setText(String.valueOf(visitante));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.exit:
                deseaSalir();
                return true;
            case R.id.rename:
                Intent intent = new Intent(this, NombrarEquipos.class);
                intent.putExtra("EQUIPOLOCAL", String.valueOf(nombreLocal));
                intent.putExtra("EQUIPOVISITANTE", String.valueOf(nombreVisitante));
                startActivityForResult(intent, MI_REQUEST_CODE);
                return true;
            case R.id.reset:
                resetear();
            case R.id.action_settings:
                //metodoSettings()
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
