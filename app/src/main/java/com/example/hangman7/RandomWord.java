package com.example.hangman7;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RandomWord {

    private static final String RANDOM_WORD_URL = "https://random-word-api.herokuapp.com/word";

    /**
     * Fetches a random word from the API.
     * @return The random word as a String.
     * @throws Exception If an error occurs during fetching.
     */
    public static String GetRandomWord() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(RANDOM_WORD_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                int data = reader.read();
                while (data != -1) {
                    result.append((char) data);
                    data = reader.read();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return result.toString();
        };

        Future<String> future = executor.submit(task);
        String response = future.get(); // Wait for the result
        executor.shutdown();

        // Extract and return the word from the JSON array response
        return response.substring(2, response.length() - 2); // Remove [" and "]
    }
}
