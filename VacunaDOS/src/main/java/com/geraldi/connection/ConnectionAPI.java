/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geraldi.connection;

import com.geraldi.domain.ErrorMessageAPI;
import com.geraldi.domain.Game;
import com.geraldi.domain.GameHeader;
import com.geraldi.domain.MessageAPI;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author admin
 */
public class ConnectionAPI {

    public Object getAllGames(String urlAPI) throws IOException {
        Object retorno = null;
        urlAPI += "/game";
        URL url = new URL(urlAPI);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("GET");
        try {
            int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            InputStream responseStream = connection.getInputStream();

            String json = StreamToString(responseStream);
            System.out.println("Solicitud /game/ Response Code:" + responseCode);

            Gson gson = new Gson();
            GameHeader[] gamesHeaders = gson.fromJson(json, GameHeader[].class);
            retorno = gamesHeaders;
        } 
        } catch (Exception e) {
            ErrorMessageAPI error = new ErrorMessageAPI("Servidor No Disponible");
            retorno=error;
        }
         
            
        

        return retorno;
    }
    
    public Object getFilteredGames(String urlAPI,String filter,String value) throws IOException {
        Object retorno = null;
        urlAPI += "/game/?filter="+filter+"&filterValue="+value;
        URL url = new URL(urlAPI);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("accept", "application/json");
        connection.setRequestMethod("GET");
        try {
            int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            InputStream responseStream = connection.getInputStream();

            String json = StreamToString(responseStream);
            System.out.println("Solicitud /game/filtered Response Code:" + responseCode);

            Gson gson = new Gson();
            GameHeader[] gamesHeaders = gson.fromJson(json, GameHeader[].class);
            retorno = gamesHeaders;
        } 
        } catch (Exception e) {
            ErrorMessageAPI error = new ErrorMessageAPI("Servidor No Disponible");
            retorno=error;
        }
         
            
        

        return retorno;
    }

    public Object createGame(String urlAPI, String OwnerName, String gameName, String gamePassword) throws MalformedURLException, IOException {
        Object retorno = null;
        int responseCode = 0;
        String json = "";

        try {

            //define la conexion
            urlAPI += "/game/create";
            URL url = new URL(urlAPI);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //define los parametros del metodo post
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("name", OwnerName);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            //crea el json a enviar en el body
            String jsonSend = "{\"name\":\"" + gameName + "\",\"password\":\"" + gamePassword + "\"}";

            try ( OutputStream os = connection.getOutputStream()) {//envia el body
                byte[] input = jsonSend.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            //lee el codigo de respuesta
            responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                //lee la respuesta
                InputStream responseStream = connection.getInputStream();

                //Convierte la respuesta en string
                json = StreamToString(responseStream);
                Gson gson = new Gson();

                Game gameData = gson.fromJson(json, Game.class);
                retorno = gameData;
                System.out.println(gameData.toString());
            } else {
                InputStream responseStream = connection.getErrorStream();

                //Convierte la respuesta en string
                json = StreamToString(responseStream);
                Gson gson = new Gson();
                ErrorMessageAPI error = gson.fromJson(json, ErrorMessageAPI.class);
                retorno = error;
                System.out.println(error.toString());
            }

        } catch (Exception e) {

        }

        System.out.println("Solicitud /game/create Response Code:" + responseCode);
        return retorno;
    }

    public Object getGame(String urlAPI, String playerName, String gameId, String gamePassword) throws IOException {
        Object retorno = null;

        urlAPI += "/game/" + gameId;
        URL url = new URL(urlAPI);
        int responseCode = 0;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("name", playerName);
            connection.setRequestProperty("password", gamePassword);
            connection.setRequestMethod("GET");

            responseCode = connection.getResponseCode();

            System.out.println("Solicitud /game/id Response Code:" + responseCode);
            if (responseCode == 200) {
                InputStream responseStream = connection.getInputStream();

                String json = StreamToString(responseStream);
                Gson gson = new Gson();
                Game gameData = gson.fromJson(json, Game.class);
                System.out.println(gameData.toString());
                retorno = gameData;
            } else {
                InputStream responseStream = connection.getErrorStream();

                String json = StreamToString(responseStream);
                Gson gson = new Gson();
                ErrorMessageAPI error = gson.fromJson(json, ErrorMessageAPI.class);
                System.out.println(error.toString());
                retorno = error;
                System.out.println(error.toString());
            }
        } catch (Exception e) {

        }

        return retorno;
    }

    public Object joinGame(String urlAPI, String playerName, String gameId, String gamePassword) throws IOException {
        Object retorno = null;

        urlAPI += "/game/" + gameId + "/join";
        URL url = new URL(urlAPI);
        int responseCode = 0;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("name", playerName);
            connection.setRequestProperty("password", gamePassword);
            connection.setRequestMethod("PUT");

            responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                InputStream responseStream = connection.getInputStream();
                String json = StreamToString(responseStream);

                Gson gson = new Gson();
                MessageAPI error = gson.fromJson(json, MessageAPI.class);
//                System.out.println(error.toString());
                retorno = error;
            } else {
                InputStream responseStream = connection.getErrorStream();
                String json = StreamToString(responseStream);

                Gson gson = new Gson();
                ErrorMessageAPI error = gson.fromJson(json, ErrorMessageAPI.class);
//                System.out.println(error);
                retorno = error;
            }

        } catch (Exception e) {

        }
        System.out.println("Solicitud /game/id/join Response Code:" + responseCode);

        return retorno;
    }

    public Object startGame(String urlAPI, String playerName, String gameId, String gamePassword) throws MalformedURLException {
        Object retorno = null;
        urlAPI += "/game/" + gameId + "/start";
        URL url = new URL(urlAPI);
        int responseCode = 0;

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("name", playerName);
            connection.setRequestProperty("password", gamePassword);
            connection.setRequestMethod("HEAD");

            responseCode = connection.getResponseCode();
            System.out.println("");

            ErrorMessageAPI errorA = null;
            switch (responseCode) {
                case 200:
                    MessageAPI error = new MessageAPI("Game has Started");
                    System.out.println(error.toString());
                    retorno = error;
                    break;
                case 401:
                    errorA = new ErrorMessageAPI("You are not the game's owner");
                    System.out.println(errorA.toString());
                    retorno = errorA;
                    break;
                case 403:
                    errorA = new ErrorMessageAPI("You are not part of the players list");
                    System.out.println(errorA.toString());
                    retorno = errorA;
                    break;
                case 404:
                    errorA = new ErrorMessageAPI("Invalid Game's id");
                    System.out.println(errorA.toString());
                    retorno = errorA;
                    break;
                default:
                    errorA = new ErrorMessageAPI("Can't start the game");
                    System.out.println(errorA.toString());
                    retorno = errorA;
                    break;
            }

        } catch (Exception e) {

        }
        System.out.println("Solicitud /game/id/start Response Code:" + responseCode);

        return retorno;
    }

    public Object sendGroup(String urlAPI, String playerName, String gameId, String gamePassword, String[] nombres) {
        Object retorno = null;
        int responseCode = 0;
        String json = "";

        try {

            //define la conexion
            urlAPI += "/game/" + gameId + "/group";
            URL url = new URL(urlAPI);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //define los parametros del metodo post
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("name", playerName);
            connection.setRequestProperty("password", gamePassword);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            //crea el json a enviar en el body
            String jsonSend = "{\"group\":[";
            for (int i = 0; i < nombres.length; i++) {
                String nombre = nombres[i];
                jsonSend += "\"" + nombre + "\"";
                if (i != nombres.length - 1) {
                    jsonSend += ",";
                }

            }
            jsonSend += "]}";

            try ( OutputStream os = connection.getOutputStream()) {//envia el body
                byte[] input = jsonSend.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            //lee el codigo de respuesta
            responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                //lee la respuesta
                InputStream responseStream = connection.getInputStream();

                //Convierte la respuesta en string
                json = StreamToString(responseStream);
                Gson gson = new Gson();

                MessageAPI message = gson.fromJson(json, MessageAPI.class);
                retorno = message;
                System.out.println(message.toString());
            } else {
                InputStream responseStream = connection.getErrorStream();

                //Convierte la respuesta en string
                json = StreamToString(responseStream);
                Gson gson = new Gson();
                ErrorMessageAPI error = gson.fromJson(json, ErrorMessageAPI.class);
                retorno = error;
                System.out.println(error.toString());
            }

        } catch (Exception e) {

        }

        System.out.println("Solicitud /game/group Response Code:" + responseCode);
        return retorno;
    }

    public Object go(String urlAPI, String playerName, String gameId, String gamePassword, boolean psycho) {
        Object retorno = null;
        int responseCode = 0;
        String json = "";

        try {

            //define la conexion
            urlAPI += "/game/" + gameId + "/go";
            URL url = new URL(urlAPI);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //define los parametros del metodo post
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("name", playerName);
            connection.setRequestProperty("password", gamePassword);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            //crea el json a enviar en el body
            String jsonSend = "{\"psycho\":" + psycho + "}";

            try ( OutputStream os = connection.getOutputStream()) {//envia el body
                byte[] input = jsonSend.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            //lee el codigo de respuesta
            responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                //lee la respuesta
                InputStream responseStream = connection.getInputStream();

                //Convierte la respuesta en string
                json = StreamToString(responseStream);
                Gson gson = new Gson();

                MessageAPI message = gson.fromJson(json, MessageAPI.class);
                retorno = message;
                System.out.println(message.toString());
            } else {
                InputStream responseStream = connection.getErrorStream();

                //Convierte la respuesta en string
                json = StreamToString(responseStream);
                Gson gson = new Gson();
                ErrorMessageAPI error = gson.fromJson(json, ErrorMessageAPI.class);
                retorno = error;
                System.out.println(error.toString());
            }

        } catch (Exception e) {

        }

        System.out.println("Solicitud /game/go Response Code:" + responseCode);
        return retorno;
    }

    private String StreamToString(InputStream responseStream) {

        InputStreamReader inputStreamReader = new InputStreamReader(responseStream);
        Stream<String> streamOfString = new BufferedReader(inputStreamReader).lines();
        String streamToString = streamOfString.collect(Collectors.joining());

        return streamToString;
    }

}
