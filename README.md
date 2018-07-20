# OS-Project
Java codes for FCFS, SJF, SRT and Round Robin algorithms.

## How The Inputs Should Be: (For All Codes uploaded.)

- The first line of input must be _the name of the processes_, each name separated by a space from the other. Names consist of an upper case English letter and a number.

##### Example:
```
A1 B1 C1
```

- The second line of input must be _the arrival times of the processes_, each number separated by a space from the other. 

##### Example:
```
2 0 3
```

- Finally, the third line of the input must be _the burst times of the processes_, again each separated by a space from the other. 

##### Example: 
```
4 5 3
```
## Outputs:

The first three lines are for (in order) FCFS, SJF and SRT. The 4 following lines belong to RR, with quantums (in order) 2, 4, 8 and 20 time units.

## Output Notes:
If the processes are in the same condition, (Same arrival time in FCFS and RR, same burst time in SJF and same remaining time in SRT) the process whose name is lexicographically less comes first.

##### Example:

```
B1B1B1B1B1A1A1A1A1C1C1C1
B1B1B1B1B1C1C1C1A1A1A1A1
B1B1B1B1B1C1C1C1A1A1A1A1
B1B1A1A1C1C1B1B1A1A1C1B1
B1B1B1B1A1A1A1A1C1C1C1B1
B1B1B1B1B1A1A1A1A1C1C1C1
B1B1B1B1B1A1A1A1A1C1C1C1
```
