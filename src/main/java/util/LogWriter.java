package util;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author：markrujun
 * @create：2018-12-31 11:25
 */
public  class LogWriter implements Runnable {
    private static LogWriter obj;
    private static File logFile;
    public static BufferedWriter out;
    public static ArrayList<String> taskWrite = new ArrayList();
    public static boolean treadEnd = false;
    public Thread t = new Thread(this);

    private static void createFile(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String name = df.format(new Date());
        logFile = new File(name + ".txt");
    }

    public static void write(String msg) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String time = df.format(new Date());
        taskWrite.add(time + "     " +msg);
    }
    public static LogWriter getInstance() {
        if (obj==null) {
            synchronized (LogWriter.class){
                if (obj == null)  {
                    obj = new LogWriter();
                }
            }
        }
        return obj;
    }

    @Override
    public void run() {
        createFile();
        int tempSize = taskWrite.size();
        try {
            out  = new BufferedWriter(new FileWriter(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!treadEnd) {
            if (tempSize < taskWrite.size()) {
                for (int i=tempSize; i< taskWrite.size(); i++) {
                    try {
                        System.out.println(taskWrite.get(i) + "\n");
                        out.write(taskWrite.get(i) + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void start() {
        if (t == null) {
            t = new Thread(this);
        }
        t.start();
    }
}
