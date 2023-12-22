package twelve;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Twelve {
    List<HotSpring> hotSpringList=new ArrayList<>();
    private long sumOfAllHotSpringMap=0;
    public Twelve(String path) {
        try {
            List<String> strings = Files.readAllLines(Paths.get(path));
            for (int i = 0; i <strings.size() ; i++) {

                String s = strings.get(i);
                String[] hotSpringMapAndindex = s.split(" ");
                String[] indexes = hotSpringMapAndindex[1].split(",");
                int[] arr = getHotSpringIndexes(indexes);
                hotSpringList.add(new HotSpring(hotSpringMapAndindex[0],arr));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long Sum5WithThreads(int numberOfThreads) throws InterruptedException {
        BlockingQueue<HotSpring> queue = new LinkedBlockingQueue<>(hotSpringList);
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    HotSpring hotSpring = queue.poll();
                    if (hotSpring == null) {
                        break; // No more items in the queue
                    }
                    long springSum = hotSpring.sum5();
                    this.addToSum(springSum);
                }
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        return this.sumOfAllHotSpringMap;
    }

    public long Sum5()
    {
        long total=0;
        for (int i = 0; i < hotSpringList.size(); i++) {
            HotSpring hotSpring = hotSpringList.get(i);
            long springSum = hotSpring.sum5();
            total+= springSum;

        }
        return total;
    }

    public long Sum()
    {
        long total=0;
        for (HotSpring hotSpring:hotSpringList) {
            long springSum = hotSpring.sum();
            total+= springSum;

        }
        return total;
    }


    private synchronized void addToSum(long res)
    {
        this.sumOfAllHotSpringMap+=res;
    }
    private  int[] getHotSpringIndexes(String[] split) {
        int[] arr=new int[split.length];


        for (int j = 0; j < split.length ; j++) {
            arr[j]= Integer.parseInt(split[j]);
        }
        return arr;
    }
}
