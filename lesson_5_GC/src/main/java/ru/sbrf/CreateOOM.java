package ru.sbrf;

import java.util.*;

public class CreateOOM {

    /***
     -Xms512m
     -Xmx512m
     -Xlog:gc=debug:file=./logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
     -XX:+HeapDumpOnOutOfMemoryError
     -XX:HeapDumpPath=./logs/dump

     +

     Serial Collector -XX:+UseSerialGC
     Parallel Collector -XX:+UseParallelGC
     CMS -XX:+UseConcMarkSweepGC
     G1 -XX:+UseG1GC
     ZGC -XX:+UnlockExperimentalVMOptions -XX:+UseZGC
     **/

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        var r = new Random();
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            list.add(r.nextInt());
            System.out.println("Всего памяти JVM: [" + Runtime.getRuntime().maxMemory()
                    +"] Осталось: [" +Runtime.getRuntime().freeMemory()+"]");
            if (i % 2 == 0 || i % 3 == 0) {
                list.remove(list.size() - 1);
            }
        }
    }
}
