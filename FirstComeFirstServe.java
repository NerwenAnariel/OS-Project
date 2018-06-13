import java.util.Scanner;

public class FirstComeFirstServe{

    public static String FCFS(String[] process, int[] arrival, int[] burst){

        String res = ""; //Each process name is repeated as many as its burst time here.

        Process[] processes = new Process[process.length];

        for (int i = 0; i < process.length; i++) { //creates the processes out of the information given.
            processes[i] = new Process(process[i], arrival[i], burst[i]);
        }

        sortBasedOnArrivalTime(processes);

        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < processes[i].getBurst(); j++) {
                res += processes[i].getProcessName();
            }
        }

        return res;
    }

    private static int[] parse(String[] s){ //parses the strings into numbers, if possible.
        int[] res = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            res[i] = Integer.parseInt(s[i]);
        }
        return res;
    }

    private static void sortBasedOnArrivalTime(Process[] processes){ //sorts the processes based on their arrival times, with bubble sort algorithm.
        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < processes.length - i - 1; j++) {
                if (processes[j].getArrival()>processes[j+1].getArrival()){
                    Process temp = null;
                    temp = processes[j+1];
                    processes[j+1] = processes[j];
                    processes[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in); //defining a Scanner to read inputs from console
        String process = read.nextLine(); // Reads the processes' names. The user enters the processes names as a string, the names separated by a space.
        String arrival = read.nextLine(); // Reads the processes' arrival time, as a string with the numbers being separated by a space.
        String burst = read.nextLine(); // Reads the processes' burst times, as a string with the numbers being separated by a space.
        String[] p = process.split(" "); // Turns the names' string into an array of names, preparing it to create processes out of it.
        int[] b = parse(burst.split(" ")); // Turns the arrivals' string into an array of numbers, preparing it to create processes out of it.
        int[] a = parse(arrival.split(" ")); // Turns the burst times into an array of numbers, preparing it to create processes out of it.
        System.out.println(FCFS(p,a,b)); //the FCFS method being executed.
    }
}