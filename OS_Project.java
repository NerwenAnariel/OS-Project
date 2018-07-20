package Phase1;

import java.util.Scanner;

public class OS_Project {

    public static String FCFS(String[] process, int[] arrival, int[] burst){

        String res = "";
        int n = process.length;

        Process minProcess = null;
        int minArrival = Integer.MAX_VALUE;
        int index = 0;
        boolean[] done = new boolean[n];
        Process[] processes = new Process[n];

        for (int i = 0; i < process.length; i++) {
            processes[i] = new Process(process[i], arrival[i], burst[i]);
        }

        for (int i = 0; i < n; i++) {
            minArrival = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (processes[j].getArrival()< minArrival && !done[j]){
                    minProcess = processes[j];
                    minArrival = processes[j].getArrival();
                    index = j;
                }else if (minArrival == processes[j].getArrival() && !done[i]){
                    if (processes[j].getProcessName().compareTo(processes[index].getProcessName())<0){
                        minProcess = processes[j];
                        minArrival = processes[j].getArrival();
                        index = j;
                    }
                }
            }

            for (int k = 0; k < processes[index].getBurst(); k++) {
                res += processes[index].getProcessName();
            }
            done[index] = true;
        }

        return res;
    }

    public static String SJF(String[] process, int[] arrival, int[] burst){

        String res = "";
        int n = process.length;

        Process minProcess = null;
        int minBurst = Integer.MAX_VALUE;
        int index = 0;
        boolean[] done = new boolean[n];
        Process[] processes = new Process[n];
        int time = 0;

        for (int i = 0; i < process.length; i++) {
            processes[i] = new Process(process[i], arrival[i], burst[i]);
        }

        for (int i = 0; i < n; i++) {
            minBurst = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (processes[j].getBurst()< minBurst && !done[j] && processes[j].getArrival() <= time){
                    minProcess = processes[j];
                    minBurst = processes[j].getBurst();
                    index = j;
                }else if (processes[j].getBurst() == minBurst && !done[j] && processes[j].getArrival() <= time){
                    if (processes[j].getProcessName().compareTo(processes[index].getProcessName())<0){
                        minProcess = processes[j];
                        minBurst = processes[j].getBurst();
                        index = j;
                    }
                }
            }

            for (int k = 0; k < processes[index].getBurst(); k++) {
                res += processes[index].getProcessName();
            }

            done[index] = true;
            time += processes[index].getBurst();
        }

        return res;
    }

    public static String SRT(String[] process, int[] arrival, int[] burst){

        String res = "";
        int n = process.length;

        Process minProcess = null;
        int minRemainingTime = Integer.MAX_VALUE;
        int time = 0;
        boolean[] done = new boolean[n];
        int complete = 0;
        Process[] processes = new Process[n];
        int index = 0;

        for (int i = 0; i < process.length; i++) {
            processes[i] = new Process(process[i], arrival[i], burst[i]);
        }

        while (complete<n){
            minRemainingTime = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (processes[i].getBurst()<minRemainingTime && !done[i] && processes[i].getArrival()<=time){
                    minProcess = processes[i];
                    minRemainingTime = processes[i].getBurst();
                    index = i;
                }else if (processes[i].getBurst()==minRemainingTime && !done[i] && processes[i].getArrival()<=time){
                    if (processes[i].getProcessName().compareTo(processes[index].getProcessName())<0){
                        minProcess = processes[i];
                        minRemainingTime = processes[i].getBurst();
                        index = i;
                    }
                }
            }
            int temp = processes[index].getBurst();
            temp--;
            processes[index].setBurst(temp);
            res += processes[index].getProcessName();
            time++;
            if (processes[index].getBurst()==0){
                complete++;
                done[index] = true;
            }
        }

        return res;
    }

    public static String RR(String[] process, int[] arrival, int[] burst, int quantum){

        String res = "";
        int n = process.length;

        Process[] processes = new Process[process.length];

        for (int i = 0; i < process.length; i++) {
            processes[i] = new Process(process[i],arrival[i],burst[i]);
        }

        sortBasedOnArrivalTime(processes);

        boolean[] done = new boolean[n];
        int complete = 0;
        int time = 0;

        while (true){

            for (int i = 0; i < n; i++) {
                int a1 = processes[i].getArrival();
                int b1 = processes[i].getBurst();
                if (!done[i] && a1<=time){
                    if (b1<=quantum){
                        for (int j = 0; j < b1; j++) {
                            res += processes[i].getProcessName();
                        }
                        time += b1;
                        done[i] = true;
                        complete += 1;
                        int temp = 0;
                        processes[i].setBurst(temp);
                    }else {
                        for (int j = 0; j < quantum; j++) {
                            res += processes[i].getProcessName();
                        }
                        time += quantum;
                        int temp =b1;
                        temp -= quantum;
                        processes[i].setBurst(temp);
                    }
                }
            }

            if (complete==n)
                break;
        }

        return res;
    }

    private static void sortBasedOnArrivalTime(Process[] processes){
        for (int i = 0; i < processes.length; i++) {
            for (int j = 0; j < processes.length - i - 1; j++) {
                if (processes[j].getArrival()>processes[j+1].getArrival()){
                    Process temp = null;
                    temp = processes[j+1];
                    processes[j+1] = processes[j];
                    processes[j] = temp;
                }else if (processes[j].getArrival()==processes[j+1].getArrival()){
                    if (processes[j].getProcessName().compareTo(processes[j+1].getProcessName())>0){
                        Process temp = null;
                        temp = processes[j+1];
                        processes[j+1] = processes[j];
                        processes[j] = temp;
                    }
                }
            }
        }
    }

    private static int[] parse(String[] s){
        int[] res = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            res[i] = Integer.parseInt(s[i]);
        }
        return res;
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
        System.out.println(SJF(p,a,b));
        System.out.println(SRT(p,a,b));
        System.out.println(RR(p,a,b,2));
        System.out.println(RR(p,a,b,4));
        System.out.println(RR(p,a,b,8));
        System.out.println(RR(p,a,b,20));
    }

}

