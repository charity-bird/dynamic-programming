package WeightedIntervalScheduling;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class Job {
    int startTime;
    int endTime;
    int value;
    int label;
    public Job(int startTime, int finishTime, int value) {
        this.startTime = startTime;
        this.endTime = finishTime;
        this.value = value;
    }
}
public class WeightedIntervalScheduling {

    static TreeMap<Integer, Job> jobs;
    static Integer[] M;

    public static void iterativeComputeOPT() {
        M[0] = 0;
        for (int j = 1; j < jobs.size(); j++)
            M[j] = Math.max(jobs.get(j).value + M[p(j)], M[j-1]);
    }

    public static void initializeM() {
        int n = WeightedIntervalScheduling.jobs.size();
        M = new Integer[n + 1];
        for (int j = 1; j < n; j++)
            M[j] = null;
        M[0] = 0;
    }

    public static void findSolution(int j) {
        if (j == 0) {

        } else if (jobs.get(j).value + M[p(j)] > M[j-1]) {
            System.out.print(j + " ");
            findSolution(p(j));
        } else {
            findSolution(j - 1);
        }
    }
    public static int MComputeOPT(int j) {
        if (M[j] == null)
            M[j] = Math.max(jobs.get(j).value + MComputeOPT(p(j)), MComputeOPT(j-1));
        return M[j];
    }
    public static int computeOPT(int j) {
        if (j == 0)
            return 0;
        else
            return Math.max(jobs.get(j).value + computeOPT(p(j)), computeOPT(j-1));
    }

    public static int p(int label) {
        int thisJobsStartTime = jobs.get(label).startTime;
        for (Map.Entry<Integer, Job> entry : jobs.descendingMap().entrySet()) {
            if (entry.getValue().endTime <= thisJobsStartTime) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static void labelJobsByFinishTimes(Job[] jobs) {
        Arrays.sort(jobs, new Comparator<Job>() {
            public int compare(Job a, Job b) {
                return a.endTime - b.endTime;
            }
        });
        for (int i = 0; i < jobs.length; i++) {
            jobs[i].label = i;
        }
    }

    public static void main(String[] args) {
        Job j1 = new Job(0,6,4);
        Job j2 = new Job(1,4,5);
        Job j3 = new Job(3,5,4);
        Job j4 = new Job(3,8,3);
        Job j5 = new Job(4,7,1);
        Job j6 = new Job(5,9,2);
        Job j7 = new Job(6,10,3);
        Job j8 = new Job(8,11,5);
        Job[] jobsArray = {j1, j2, j3, j4, j5, j6, j7, j8};
        labelJobsByFinishTimes(jobsArray);

        WeightedIntervalScheduling.jobs = new TreeMap<Integer, Job>();
        for (int i = 0; i < jobsArray.length; i++) {
            WeightedIntervalScheduling.jobs.put(jobsArray[i].label+1, jobsArray[i]);
        }
        System.out.println("TreeMap:");
        for (Map.Entry<Integer, Job> entry : WeightedIntervalScheduling.jobs.descendingMap().entrySet()) {
            int label = entry.getValue().label;
            int startTime = entry.getValue().startTime;
            int endTime = entry.getValue().endTime;
            int value = entry.getValue().value;
            System.out.printf("%d, start:%d, end:%d, value:%d\n", label, startTime, endTime, value);
        }
        System.out.println(p(8));
        System.out.println(p(2));
        System.out.println(computeOPT(8));

        initializeM();

        System.out.println(MComputeOPT(8));

        System.out.println("Finding solution for j=8 via recursive method:");
        findSolution(8);

        System.out.println();

        System.out.println("Finding solution for j=8 via iterative method:");
        iterativeComputeOPT();
        findSolution(8);
    }
}
