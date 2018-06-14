import java.util.Scanner;

public class ShortestJobFirst {
    
    public static String SJF(String[] process, int[] arrival, int[] burst){
        
        String res = "";
        Process[] processes = new Process[process.length];
        for (int i = 0; i < process.length; i++) {
            processes[i] = new Process(process[i],arrival[i],burst[i]);
        }
        sortBasedOnArrivalTime(processes);
        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < processes[i].getBurst(); j++) {
                res += processes[i].getProcessName();
            }
        }
        return res;
    }

    private static int[] parse(String[] s){
        int[] res = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            res[i] = Integer.parseInt(s[i]);
        }
        return res;
    }

    //Sorts based on arrival time. If two processes arrive at the same time, the one with shorter burst has more priority.
    public static void sortBasedOnArrivalTime(Process[] processes){
        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < processes.length - i - 1; j++) {
                if (processes[j].getArrival()>processes[j+1].getArrival()){
                    Process temp = null;
                    temp = processes[j+1];
                    processes[j+1] = processes[j];
                    processes[j] = temp;
                } else if (processes[j].getArrival()==processes[j+1].getArrival()){
                    if (processes[j].getBurst()>processes[j+1].getBurst()){
                        Process temp = null;
                        temp = processes[j+1];
                        processes[j+1] = processes[j];
                        processes[j] = temp;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);
        String process = read.nextLine();
        String arrival = read.nextLine();
        String burst = read.nextLine();
        String[] p = process.split(" ");
        int[] a = parse(arrival.split(" "));
        int[] b = parse(burst.split(" "));
        System.out.println(SJF(p,a,b));
    }
}