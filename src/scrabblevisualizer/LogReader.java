/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabblevisualizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TeMPOraL
 */
public class LogReader implements Runnable {
    
    File logFile;
    BufferedReader in;
    
    boolean shouldRun = true;
    
    private long SLEEP_ON_SEQUENCE_POINT = 1000;
    
    private boolean waitingForStep = false;
    private boolean paused = false;
    
    private long SLEEP_ON_INTERPRET = 0;
    
    private boolean reachedSequencePoint = false;
    
    public void setFile(File file) throws FileNotFoundException, UnsupportedEncodingException {
        logFile = file;
        in = new BufferedReader(new InputStreamReader(new FileInputStream(logFile), "cp1250"));
    }
    
    public void play() {
        paused = false;
        waitingForStep = false;
    }
    
    public void pause() {
        paused = true;
        waitingForStep = true;
    }
    
    public void step() {
        waitingForStep = false;
    }

    @Override
    public void run() {
        System.out.println("Log Reader is running!");
        shouldRun = true;
        while(shouldRun) {
                interpretLine();
                if(sequencePoint()) {
                    do {
                        safeSleep(SLEEP_ON_SEQUENCE_POINT);
                    } while(waitingForStep);
                    waitingForStep = false;
                    if(inStepMode()) {
                        //restart step if in step mode
                        waitingForStep = true;
                    }
                    endSequencePoint();
                }
                safeSleep(SLEEP_ON_INTERPRET);
        }
    }

    private void interpretLine() {
        try {
            String line = in.readLine();
            if(line == null) {
                quit();
                return;
            }
            LogInterpreter.interpretLogLine(line);
        } catch (IOException ex) {
            Logger.getLogger(LogReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean sequencePoint() {
        return reachedSequencePoint == true;
    }
    
    public void markSequencePoint() {
        reachedSequencePoint = true;
    }

    private boolean inStepMode() {
        return paused;
    }

    private void quit() {
        shouldRun = false;
    }

    private void safeSleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            Logger.getLogger(LogReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void endSequencePoint() {
        reachedSequencePoint = false;
    }
}