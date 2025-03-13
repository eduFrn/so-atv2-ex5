package controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ThreadPing extends Thread {

	private int servidor;

	public ThreadPing(int servidor) {
		super();
		this.servidor = servidor;
	}

	@Override
	public void run() {
		ping();
	}

	private String os() {
		return System.getProperty("os.name");
	}

	public void ping() {

		String nomeOs = os();

		if (nomeOs.contains("Linux")) {

			String linkServidor;
			String nomeServidor;

			if (servidor == 1) {
				linkServidor = "www.uol.com.br";
				nomeServidor = "UOL";
			} else if (servidor == 2) {
				linkServidor = "www.terra.com.br";
				nomeServidor = "Terra";
			} else if (servidor == 3) {
				linkServidor = "www.google.com.br";
				nomeServidor = "Google";
			} else {
				System.out.println("Valor inválido inserido. Saindo da aplicação...");
				return;
			}

			try {
				Process p = Runtime.getRuntime().exec("ping -4 -c 10 " + linkServidor);
				InputStream stream = p.getInputStream();
				InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
				BufferedReader buffer = new BufferedReader(reader);

				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("avg")) {
						String values = linha.split(" = ")[1];
						String avg = values.split("/")[1];
						System.out.println("\nPING PARA O SERVIDOR " + nomeServidor + " FINALIZADO\nServidor: " + nomeServidor + " | Média: " + avg + "ms\n");
					} else if (linha.contains("time=")) {
						String time = linha.split("time=")[1];
						System.out.println("Servidor: " + nomeServidor + " | Tempo: " + time);
					}

					linha = buffer.readLine();
				}

				buffer.close();
				reader.close();
				stream.close();
			} catch (IOException e) {
				String err = e.getMessage();
				System.err.println(err);
			}
		} else {
			System.out.println("SO não é Linux.");
		}

	}

}
