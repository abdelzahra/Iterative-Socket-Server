import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask for server IP address and port number
        System.out.print("Enter server IP address: ");
        String address = scanner.nextLine();
        System.out.print("Enter port number: ");
        int port = scanner.nextInt();

        // Start loop 
        while (true) {
            // menu 
            System.out.println("\nSelect a command to run:");
            System.out.println("1. Date and Time");
            System.out.println("2. Uptime");
            System.out.println("3. Memory Use");
            System.out.println("4. Netstat");
            System.out.println("5. Current Users");
            System.out.println("6. Running Processes");
            System.out.println("7. Exit");
            System.out.print("Enter command number: ");
            String command = scanner.next();

            // Exit
            if (command.equals("7")) {
                System.out.println("Exiting client.");
                break;
            }

            // times to run it
            System.out.print("Enter the number of times to run the command: [1, 5, 10, 15, 20, 25]");
            int count = scanner.nextInt();

            // fix for issuue
            ExecutorService executor = Executors.newFixedThreadPool(count);
            final LongWrapper totalTurnAroundTime = new LongWrapper(0);

            
            for (int i = 0; i < count; i++) {
                int requestId = i + 1;
                executor.execute(() -> {
                    try {
                        long startTime = System.currentTimeMillis();

                        //socket connectio
                        try (Socket socket = new Socket(address, port)) {
                            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            output.println(command);

                            String result = input.readLine();
                            System.out.println("Response for Request " + requestId + ": " + result);
							// fix from stack
                            long endTime = System.currentTimeMillis();
                            long turnAroundTime = endTime - startTime;

                            synchronized (totalTurnAroundTime) {
                                totalTurnAroundTime.value += turnAroundTime;
                            }

                            System.out.println("Turn-around time for Request " + requestId + ": " + turnAroundTime + " ms");
                        }
                    } catch (IOException e) {
                        System.out.println("Request " + requestId + " failed: " + e.getMessage());
                    }
                });
            }

            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }

            double avgTurnAroundTime = (double) totalTurnAroundTime.value / count;
            System.out.println("Total Turn-around Time: " + totalTurnAroundTime.value + " ms");
            System.out.println("Average Turn-around Time: " + avgTurnAroundTime + " ms");
        }

        scanner.close();
    }

    // Wrapper class fix stackoverfloow
    static class LongWrapper {
        long value;

        LongWrapper(long value) {
            this.value = value;
        }
    }
}