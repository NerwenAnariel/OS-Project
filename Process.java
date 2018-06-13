public class Process {

    private String processName;
    private int arrival; //process' arrival time
    private int burst; //process' burst time

    public Process(){}

    public Process(String processName, int arrival, int burst){
        this.processName = processName;
        this.arrival = arrival;
        this.burst = burst;
    }

    public String getProcessName() {
        return processName;
    }

    public int getArrival(){
        return arrival;
    }

    public int getBurst(){
        return burst;
    }
}
