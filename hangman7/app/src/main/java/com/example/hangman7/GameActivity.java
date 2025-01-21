package com.example.hangman7;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvWord, tvBoard, tvMistake;
    private LinearLayout hangmanLayout;
    private HangmanView hangmanView;
    private String word, board, show, mode;
    private char currentLetter;
    private char[] wordArray, boardArray;
    private int correctSound, incorrectSound, mistakes = 0;
    private SoundPool soundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init_Game();
        init_Sound();
        init_Buttons();
    }

    private void init_Game() {
        tvWord = findViewById(R.id.tvWord);
        tvBoard = findViewById(R.id.tvBoard);
        tvMistake = findViewById(R.id.tvMistake);
        hangmanLayout = findViewById(R.id.ll);

        mode = getIntent().getStringExtra("SAVE_MODE");
        word = getIntent().getStringExtra("EXTRA_WORD");

        board = word.replaceAll("\\S", "_");
        show = String.join(" ", board.split(""));//check what does
        wordArray = word.toLowerCase().toCharArray();
        boardArray = board.toCharArray();

        tvWord.setText(word);
        tvBoard.setText(show);
        tvMistake.setText(String.valueOf(mistakes));

        // Setup hangman view
        hangmanView = new HangmanView(this);
        hangmanLayout.addView(hangmanView);
    }

    private void init_Sound() {
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);

        correctSound = soundPool.load(this, R.raw.correctsound, 1);
        incorrectSound = soundPool.load(this, R.raw.incorrectsound, 1);
    }

    private void init_Buttons() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            String buttonID = "btn" + letter;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button button = findViewById(resID);
            if (button != null) {
                button.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        currentLetter = button.getText().toString().toLowerCase().charAt(0);
        button.setVisibility(View.INVISIBLE);
        the_Game();
    }

    private void the_Game() {
        if (check_Letter(currentLetter)) {
            addLetter(currentLetter);
            soundPool.play(correctSound, 1, 1, 0, 0, 1);
            if (check_win()) {
                endGame("win");
            }
        } else {
            mistakes++;
            tvMistake.setText(String.valueOf(mistakes));
            hangmanView.setWrongGuesses(mistakes);
            soundPool.play(incorrectSound, 1, 1, 0, 0, 1);
            if (mistakes == 7) {
                endGame("loss");
            }
        }
    }
    private boolean check_Letter(char letter) {
        for (char c : wordArray) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    private void addLetter(char letter) {
        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i] == letter) {
                boardArray[i] = letter;
            }
        }
        board = new String(boardArray);
        show = String.join(" ", board.split(""));
        tvBoard.setText(show);
    }
    private boolean check_win() {
        for (char c : boardArray) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private void endGame(String result) {
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra("EXTRA_WORD", word);
        intent.putExtra("SAVE_MODE", mode);
        intent.putExtra("WIN_OR_LOSS", result);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}
