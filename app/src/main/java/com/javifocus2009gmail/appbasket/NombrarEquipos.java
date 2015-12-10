package com.javifocus2009gmail.appbasket;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NombrarEquipos extends MainActivity {

    public  static String EQUIPO_LOCAL = "EQUIPOLOCAL";
    public  static String EQUIPO_VISITANTE = "EQUIPOVISITANTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nombrarequipos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button aceptar = (Button)findViewById(R.id.buttonAceptar);
        final EditText tvl = (EditText) findViewById(R.id.nomELocal);
        final EditText tvv = (EditText) findViewById(R.id.nomEVisitante);

        aceptar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                tvl.getText().toString().trim();
                tvv.getText().toString().trim();
                if(tvl.getText().toString().length()==0){
                    tvl.setText("Equipo local");
                }
                if(tvv.getText().toString().length()==0){
                    tvv.setText("Equipo visitante");
                }
                intent.putExtra("EQUIPOLOCAL", tvl.getText().toString());
                intent.putExtra("EQUIPOVISITANTE", tvv.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
