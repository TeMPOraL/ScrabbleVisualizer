/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabblevisualizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author TeMPOraL
 */
public enum LogInterpreter {
    
    PLAYER_NAME("(\\d)\\..*\\.jar(.*)\\(") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.setPlayerName(m.group(1), m.group(2));
            ScrabbleVisualizer.appendLogLine(line);
        }  
    },
    
    TIME_LIMITS("Time for init is (\\d*) miliseconds, time for move (\\d*) miliseconds") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.setLimits(m.group(1), m.group(2));
            ScrabbleVisualizer.appendLogLine(line);
        }  
    },
    
    FIRST_TURN("First turn started.") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.setTurn("1");
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    OTHER_TURN("Turn (\\d*) started.") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.setTurn(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },

    BOARD_STATE_HEADER("(\\|0123456789ABCDE)|(-\\+--)") {
        @Override
        public void applyAction(String line, Matcher m) {
            //ignore
        }
    },
    
//    BOARD_STATE_UPDATE("(\\d|[A-D])\\|(.*)") {
    BOARD_STATE_UPDATE("(\\d|[A-E])\\|(.*)") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.setBoardRow(m.group(1), m.group(2));
        }
    },
    
//    BOARD_STATE_UPDATE_SEQUENCE_POINT("(E)\\|(.*)") {
//        @Override
//        public void applyAction(String line, Matcher m) {
//            ScrabbleVisualizer.setBoardRow(m.group(1), m.group(2));
//            ScrabbleVisualizer.sequencePoint();
//        }
//    },
    
    MAIN_BAG_UPDATE("Letters in the main bag: (.*)") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.setMainBag(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    PLAYER_STATS_UPDATE("(\\d) - (\\d*) points, available letters: (.*)") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.updatePlayerStats(m.group(1), m.group(2), m.group(3));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    PLAYER_MOVE_TIME_UPDATE("Player (\\d) was thinking: (.*)") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.updatePlayerThinkingTime(m.group(1), m.group(2));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    PLAYER_ACTION_UPDATE("Player (\\d) attempts an action of type struct (.*)") {
        @Override
        public void applyAction(String line, Matcher m) {
               ScrabbleVisualizer.updatePlayerAction(m.group(1), m.group(2));
               ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    DEFINE_CURRENT_PLAYER_SEQUENCE_POINT("Current player: (\\d)") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.setCurrentPlayer(m.group(1));
            ScrabbleVisualizer.sequencePoint();
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    PLAYER_AWARDED_POINTS_FOR_WORD("Awarding player with (\\d*) points for .* word: \"(.*)\"") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.awardCurrentPlayerWithPointsForWord(m.group(1), m.group(2));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    PLAYER_AWARDED_POINTS_FOR_USING_ALL_LETTERS("Awarding player with (\\d*) points .* letters.") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.awardCurrentPlayerWithPointsForUsingUpLetters(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    ERROR_ESTABLISHING_TCP_CONNECTION("Cannot run game. Ending...") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.markGameNotStartedDueToConnectionError();
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    PLAYER_EXCHANGES_LETTERS("Player (\\d) exchanges (\\d) letters: (.*)") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.playerExchangesLetters(m.group(2), m.group(3));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    PLAYER_LETTERS_BEFORE_EXCHANGE("before exchange: (.*)") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.playerExchangesLetters_before(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    PLAYER_LETTERS_AFTER_EXCHANGE("after exchange: (.*)") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.playerExchangesLetters_after(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    OTHER_CONNECTION_ERROR("Encountered an exception when listening") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.markGameNotStartedDueToConnectionError();
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    REMOVE_BONUS("The bonus on tile (.*) has been used up.") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.removeBonusFromField(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    LIGHT_TIMEOUT("Player (.*) violated time limit") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.markPlayerViolatedTimeLimit(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    DISQUALIFICATION("Player (.*) is disqualified") {
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.disqualifyPlayer(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    ENDGAME("Finishing game\\. The winner is player (.*)\\. ") {
        @Override
        public void applyAction(String line, Matcher m) {
            System.out.println(m.group(1));
            ScrabbleVisualizer.endgame(m.group(1));
            ScrabbleVisualizer.appendLogLine(line);
        }
    },
    
    DEFAULT(".*") { 
        @Override
        public void applyAction(String line, Matcher m) {
            ScrabbleVisualizer.appendLogLine(line);
        }
    };
    
    
    private String pattern;
    private Pattern compiledPattern;
    
    private LogInterpreter(String regexp) {
        this.pattern = regexp;
        this.compiledPattern = Pattern.compile(this.pattern);
    }
    
    public void applyAction(String line, Matcher m) {
        //Default action - just print
        System.out.println(line);
    }
    
    public static void interpretLogLine(String line) {
        Matcher matcher;
        for(LogInterpreter interpreter : LogInterpreter.values()) {
            matcher = interpreter.compiledPattern.matcher(line);
            if(matcher.find()) {
                interpreter.applyAction(line, matcher);
                return;
            }
        }
    }    
}
