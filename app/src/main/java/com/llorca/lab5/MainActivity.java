package com.llorca.lab5;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] names, andlevel, andversion, andreleased, anddescription;
    ListView list;
    private File file;
    private File folder;
    int[] cLogo = {R.drawable.and1, R.drawable.and2,R.drawable.and3,R.drawable.and4, R.drawable.and5,R.drawable.and6,
            R.drawable.and7, R.drawable.and8, R.drawable.and9,R.drawable.and10,R.drawable.and11,
            R.drawable.and12, R.drawable.and13, R.drawable.and14, R.drawable.and15};

    ArrayList<Android> androidList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = getResources().getStringArray(R.array.androidnames);
        andlevel = getResources().getStringArray(R.array.level);
        andversion = getResources().getStringArray(R.array.version);
        andreleased = getResources().getStringArray(R.array.released);
        anddescription = getResources().getStringArray(R.array.description);

        list = findViewById(R.id.lvColleges);
        for(int i = 0; i < names.length; i++){
            androidList.add(new Android(cLogo[i], names[i], andlevel[i], andversion[i], andreleased[i], anddescription[i]));
        }
        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.items ,androidList);
        list.setAdapter(adapter);
        // list.position
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        // Toast.makeText(this, names[i], Toast.LENGTH_LONG).show();
        folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        file = new File(folder, "AndroidVersions.txt");
        try {

            FileOutputStream fos = new FileOutputStream(file);
            String choiceApi = androidList.get(i).getName();
            fos.write(choiceApi.getBytes());
            String choiceReleaseDate = androidList.get(i).getReleased();
            fos.write(choiceReleaseDate.getBytes());

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(androidList.get(i).getName());
            dialog.setIcon(androidList.get(i).getLogo());

            final String name = androidList.get(i).getName();
            final String ver = androidList.get(i).getVersion();
            final String date = androidList.get(i).getReleased();

            dialog.setMessage(androidList.get(i).getDescription());
            dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
//                dialog.dismiss();
//                try{
//                    BufferedReader br = new BufferedReader(new FileReader(file));
//                    String line;
//
//                    while ((line = br.readLine()) != null){
//                        text.append(line);
//                        text.append('\n');
//                    }
//                    br.close();
//                    Toast.makeText(MainActivity.this, text.toString(), Toast.LENGTH_LONG).show();
//                } catch(FileNotFoundException e){
//                    e.printS
//                }
                @Override
                public void onClick (DialogInterface dialog,int which){
                    dialog.dismiss();
                    String readData = readInput();
                    Toast.makeText(MainActivity.this, readData, Toast.LENGTH_LONG).show();
                }
            });
            dialog.create().show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String readInput() {
        FileInputStream stream = null;
        file = new File(folder, "AndroidVersions.txt");
        StringBuilder sb = new StringBuilder();

        try {
            stream = new FileInputStream(file);
            int i;
            while ((i = stream.read()) != -1) {
                sb.append((char) i);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found");
        } catch (IOException e) {
            Log.d("error", "IO error");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}