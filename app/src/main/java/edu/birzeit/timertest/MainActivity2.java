package edu.birzeit.timertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Get the TextView from the layout
        TextView textView = findViewById(R.id.textView);

        // Read the text file from the assets folder
        try {
            InputStream inputStream = getAssets().open("my_text_file.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Read each line of the text file and save the words and their meanings in a HashMap
            HashMap<String, String> wordsMap = new HashMap<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    String word = parts[0];
                    String meaning = parts[1];
                    wordsMap.put(word, meaning);
                }
            }

            // Display the words and their meanings in the TextView
            StringBuilder stringBuilder = new StringBuilder();
            for (String word : wordsMap.keySet()) {
                String meaning = wordsMap.get(word);
                stringBuilder.append(word);
                stringBuilder.append(": ");
                stringBuilder.append(meaning);
                stringBuilder.append("\n");
            }
            textView.setText(stringBuilder.toString());

            // Close the streams
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
