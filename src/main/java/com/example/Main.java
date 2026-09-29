package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("[Server] Server online");

        ServerSocket ss = new ServerSocket(3000);
        System.out.println("[Server] Server in ascolto sulla porta " + ss.getLocalPort());
        Socket sock = ss.accept();

        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
        
        do{
            String word = in.readLine();
            if(word.equals("--exit")){
                System.out.println("[Server] Ricevuto --exit. Termino la connessione...");
                out.println("--confirmexit");
                sock.close();
                break;
            }else{
                System.out.println("[Client] Parola ricevuta: \"" + word + "\"");
                System.out.println("[Server] Trasmissione parola uppercase...");
                out.println(word.toUpperCase());
            }
        }while(true);
    }
}