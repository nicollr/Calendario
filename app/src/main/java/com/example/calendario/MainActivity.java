package com.example.calendario;

import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button b,agregar;
    int diaint,mesint,añoint;
    EditText texto,dia,mes,año,nota;
    Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendario= Calendar.getInstance();
        b=(Button)findViewById(R.id.button);
        agregar=(Button)findViewById(R.id.btnagregar);
        texto=(EditText)findViewById(R.id.titulo);
        dia=(EditText)findViewById(R.id.dia);
        nota=(EditText)findViewById(R.id.nota);
        mes=(EditText)findViewById(R.id.mes);
        año=(EditText)findViewById(R.id.año);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,"nicollr433@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT,"Hola mundo");
                intent.putExtra(Intent.EXTRA_TEXT,"Este es mi mensaje");
                if(intent.resolveActivity(getPackageManager())!=null)
                    startActivity(intent);
            }
        });
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir();
                calendario.set(Calendar.DAY_OF_MONTH,diaint );
                calendario.set(Calendar.MONTH, mesint);
                calendario.set(Calendar.YEAR, añoint);
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendario.getTimeInMillis());

                intent.putExtra(CalendarContract.Events.ALL_DAY, false);
                intent.putExtra(CalendarContract.Events.TITLE, texto.getText().toString());
                intent.putExtra(CalendarContract.Events.DESCRIPTION, nota.getText().toString());

                startActivity(intent);

            }
        });

    }
    public void convertir()
    {
        diaint= Integer.parseInt(dia.getText().toString());
        mesint=Integer.parseInt(mes.getText().toString());
        añoint=Integer.parseInt(año.getText().toString());
    }
}
