package scrabblevisualizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author TeMPOraL
 */
public class ScrabbleVisualizer {
    public static VisualizerUI ui;
    public static LogReader lr;
    
    public static Thread readerThread;
    private static int activePlayer;
    /**
     * @param args the command line arguments
     */
    
    public static void loadLogFile(File file) throws FileNotFoundException, IOException {
        System.out.println("Loading file: " + file.getName());
        //TODO stop everything
        ui.resetUI();
        ui.setLoadedLogFileName(file.getName());
        ui.enablePlaybackControls();
        
        lr = new LogReader();
        lr.setFile(file);
        readerThread = new Thread(lr);
        lr.pause();
        readerThread.start();
    }
    
    public static void startPlayback() {
        lr.play();
    }
    
    public static void pausePlayback() {
        lr.pause();
    }
    
    public static void stepOneTurn() {
        lr.step();
    }
    
    /**
     * controls for interaction between interpreter and UI
     */
    
    public static void setPlayerName(String player, String name) {
        int playerNum = Integer.parseInt(player);
        if(playerNum == 0) {
            ui.setPlayer1Name(name);
        }
        else {
            ui.setPlayer2Name(name);
        }
    }
    
    public static void appendLogLine(String line) {
        ui.appendLogLine(line);
    }
    
    public static void setBoardRow(String rowNum, String row) {
        int rowNumber = Integer.parseInt(rowNum, 16);
        for(int i = 0 ; i < 15 ; ++i) {
            ui.setBoardElement(rowNumber, i, String.valueOf(row.charAt(i)));
        }
    }
    
    public static void setMainBag(String letters) {
        if(letters.compareTo("No letters") == 0) {
            ui.lockOutMainBag();
        }
        else {
            ui.setMainBag(letters);
        }
    }
    
    public static void sequencePoint() {
        lr.markSequencePoint();
    }
    
    public static void updatePlayerStats(String player, String points, String letters) {
        int playerNum = Integer.parseInt(player);
        if(playerNum == 0) {
            ui.setPlayer1Points(points);
            ui.setPlayer1Letters(letters);
        }
        else {
            ui.setPlayer2Points(points);
            ui.setPlayer2Letters(letters);
        }
    }
    
    public static void updatePlayerThinkingTime(String player, String time) {
        int playerNum = Integer.parseInt(player);
        if(playerNum == 0) {
            ui.setPlayer1ThinkingTime(time);
        }
        else {
            ui.setPlayer2ThinkingTime(time);
        }
    }
    
    public static void updatePlayerAction(String player, String action) {
        int playerNum = Integer.parseInt(player);
        if(action.compareTo("SkipTurn") == 0) {
            action = "Pomiń turę";
        }
        else if(action.compareTo("PutLetters") == 0) {
            action = "Połóż litery";
        }
        else if(action.compareTo("ExchangeLetters") ==0) {
            action = "Zamień litery";
        }
        
        if(playerNum == 0) {
            ui.setPlayer1Action(action);
        }
        else {
            ui.setPlayer2Action(action);
        }
    }
    
    public static void setLimits(String init, String move) {
        ui.setLimits(init, move);
    }
    
    public static void setTurn(String turn) {
        ui.setTurn(turn);
    }
    
    public static void setCurrentPlayer(String player) {
        int playerNum = Integer.parseInt(player);
        ui.highlightPlayer(playerNum);
        activePlayer = playerNum;
    }
    
    public static void awardCurrentPlayerWithPointsForWord(String points, String word) {
        int nPoints = Integer.parseInt(points);
        ui.addPointsForPlayer(activePlayer, nPoints);
        ui.addAdditionalInfoForPlayer(activePlayer, "Gracz otrzymał " + points + " punktów za słowo: " + word + ".");
    }
    
    public static void awardCurrentPlayerWithPointsForUsingUpLetters(String points) {
        int nPoints = Integer.parseInt(points);
        ui.addPointsForPlayer(activePlayer, nPoints);
        ui.addAdditionalInfoForPlayer(activePlayer, "Gracz otrzymał " + points + " punktów za wykorzystanie wszystkich swoich liter.");
    }
    
    public static void markGameNotStartedDueToConnectionError() {
        ui.endGameWithConnectionError();
    }
    
    public static void playerExchangesLetters(String howMany, String letters) {
        ui.addAdditionalInfoForPlayer(activePlayer, "Gracz zamienia " + howMany + " liter(y): " + letters + ".");
    }
    
    public static void playerExchangesLetters_before(String letters) {
        ui.addAdditionalInfoForPlayer(activePlayer, "Litery gracza przed wymianą: " + letters + ".");
    }
    
    public static void playerExchangesLetters_after(String letters) {
        ui.addAdditionalInfoForPlayer(activePlayer, "Litery gracza po wymianie: " + letters + ".");
    }
    
    public static void removeBonusFromField(String bonus) {
        
    }
    
    public static void markPlayerViolatedTimeLimit(String player) {
        ui.addAdditionalInfoForPlayer(activePlayer, "Gracz naruszył limit czasowy.");
    }
    
    public static void disqualifyPlayer(String player) {
        ui.disqualifyPlayer(Integer.parseInt(player));
    }
    
    public static void endgame(String winner) {
        ui.endgame(Integer.parseInt(winner));
    }
    
    public static void main(String[] args) {
        ui = new VisualizerUI();
        ui.setVisible(true);
        ui.resetUI();
        ui.disablePlaybackControls();

        System.out.println("DONE");
    }

 

}
