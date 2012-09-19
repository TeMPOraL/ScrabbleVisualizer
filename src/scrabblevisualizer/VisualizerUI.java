/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabblevisualizer;

import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author TeMPOraL
 */
public class VisualizerUI extends javax.swing.JFrame {

    public JFileChooser logChooser = new JFileChooser();
    private String FAILED_TO_LOAD_LOG_ERROR_TEXT = "Nie udało się załadować loga, spróbuj ponownie.";
    private String PLAYER_IS_MAKING_A_MOVE = "Gracz wykonuje ruch.";
    private String NO_MORE_LETTERS = "Brak liter w worku.";
    private String DISQUALIFIED = "Dyskwalifikacja!";
    private String ENDGAME_CONNECTION_ERROR = "Koniec gry! - błąd połączenia.";
    private String ENDGAME = "Koniec gry!";
    private String TIE = "REMIS";
    private String WINNER = "ZWYCIĘZCA";
    private String LOSER = "PRZEGRANY";

    /**
     * Creates new form VisualizerUI
     */
    public VisualizerUI() {
        initComponents();
        doAdditionalInitializations();
    }

    /**
     * Scrabble Visualizer UI op functions.
     */
    public void resetUI() {
        labelP1Name.setText("");
        labelP2Name.setText("");

        labelP1LastMove.setText("");
        labelP2LastMove.setText("");

        labelP1LastMoveTime.setText("");
        labelP2LastMoveTime.setText("");

        labelP1Points.setText("");
        labelP2Points.setText("");
        
        labelP1LastMovePoints.setText("");
        labelP2LastMovePoints.setText("");
        
        labelP1Status.setText("");
        labelP2Status.setText("");
        labelP1Status.setForeground(Color.black);
        labelP2Status.setForeground(Color.black);

        labelLogName.setText("Załaduj log...");
        labelTurn.setText("");
        labelLimits.setText("");
        labelEndGame.setText("");

        textLog.setText("");
        textMainLettersBag.setEnabled(true);
        textMainLettersBag.setText("");
        textP1AvailableLetters.setText("");
        textP2AvailableLetters.setText("");
        
        buttonPlay.setSelected(false);

        tableBoard.clearSelection();

        resetBoard();
    }
    
    public void disablePlaybackControls() {
        buttonPlay.setEnabled(false);
        buttonStep.setEnabled(false);
    }
    
    public void enablePlaybackControls() {
        buttonPlay.setEnabled(true);
        buttonStep.setEnabled(true);
    }
    
    public void setLoadedLogFileName(String name) {
        labelLogName.setText(name);
    }
    
    public void setPlayer1Name(String name) {
        labelP1Name.setText(name);
    }
    
    public void setPlayer2Name(String name) {
        labelP2Name.setText(name);
    }
    
    public void appendLogLine(String line) {
        textLog.append(line);
        textLog.append("\n");
        textLog.setCaretPosition(textLog.getDocument().getLength());

    }
    
    public void setBoardElement(int row, int col, String val) {
        if(val.compareTo(".") != 0) {
            tableBoard.setValueAt(val, row, col);
        }
    }
    
    public void setMainBag(String letters) {
        textMainLettersBag.setText(letters);
    }
    
    public void setPlayer1Points(String points) {
         labelP1Points.setText(points);
    }
    public void setPlayer1Letters(String letters) {
         textP1AvailableLetters.setText(letters);
    }
    
    public void setPlayer2Points(String points) {
         labelP2Points.setText(points);
    }
    public void setPlayer2Letters(String letters) {
         textP2AvailableLetters.setText(letters);
    }
    
    public void setPlayer1ThinkingTime(String time) {
        labelP1LastMoveTime.setText(time);
                    
        //clear P1 points & additional info
        labelP1LastMovePoints.setText("0");
        textP1AdditionalInfo.setText("");
    }
    
    public void setPlayer2ThinkingTime(String time) {
        labelP2LastMoveTime.setText(time);
        
        //clear P2 points & additional info
        labelP2LastMovePoints.setText("0");
        textP2AdditionalInfo.setText("");
    }

    public void setPlayer1Action(String action) {
        labelP1LastMove.setText(action);
    }
    
    public void setPlayer2Action(String action) {
        labelP2LastMove.setText(action);
    }
    
    public void setLimits(String init, String move) {
        labelLimits.setText("Limity: inicjalizacja - " + init + " milisekund, ruch - " + move + " milisekund.");
    }
    
    public void setTurn(String turn) {
        labelTurn.setText(turn);
    }
    
    public void highlightPlayer(int player) {
        if(player == 0) {
            if(labelP1Status.getText().compareTo(DISQUALIFIED) != 0) {
                labelP1Status.setText(PLAYER_IS_MAKING_A_MOVE);
                labelP1Status.setForeground(Color.black);
            }
            if(labelP2Status.getText().compareTo(DISQUALIFIED) != 0) {
                labelP2Status.setText("");
            }
        }
        else {
            if(labelP2Status.getText().compareTo(DISQUALIFIED) != 0) {
                labelP2Status.setText(PLAYER_IS_MAKING_A_MOVE);
                labelP2Status.setForeground(Color.black);
            }
            if(labelP1Status.getText().compareTo(DISQUALIFIED) != 0) {
                labelP1Status.setText("");
            }
        }
        //TODO handle disq cases
    }
    
    public void addPointsForPlayer(int player, int points) {
        if(player == 0) {
            labelP1LastMovePoints.setText(String.valueOf(Integer.parseInt(labelP1LastMovePoints.getText()) + points));
        }
        else {
            labelP2LastMovePoints.setText(String.valueOf(Integer.parseInt(labelP2LastMovePoints.getText()) + points));
        }
    }
    
    public void addAdditionalInfoForPlayer(int player, String info) {
        if(player == 0) {
            textP1AdditionalInfo.append(info);
            textP1AdditionalInfo.append("\n");
        }
        else {
            textP2AdditionalInfo.append(info);
            textP2AdditionalInfo.append("\n");
        }
    }
    
    public void lockOutMainBag() {
        textMainLettersBag.setText(NO_MORE_LETTERS);
        textMainLettersBag.setEnabled(false);
    }
    
    public void endGameWithConnectionError() {
        labelEndGame.setText(ENDGAME_CONNECTION_ERROR);
    }
    
    public void disqualifyPlayer(int player) {
        if(player == 0) {
            labelP1Name.setForeground(Color.red);
            labelP1Status.setForeground(Color.red);
            labelP1Status.setText(DISQUALIFIED);
        }
        else {
            labelP2Name.setForeground(Color.red);
            labelP2Status.setForeground(Color.red);
            labelP2Status.setText(DISQUALIFIED);
        }
    }
    
    public void endgame(int winner) {
        labelEndGame.setText(ENDGAME);
        if(winner == -1) {
            labelP1Status.setForeground(Color.orange);
            labelP1Status.setText(TIE);
            labelP2Status.setForeground(Color.orange);
            labelP2Status.setText(TIE);
        }
        else if(winner == 0) {
            labelP1Status.setForeground(Color.green);
            labelP1Status.setText(WINNER);
            labelP2Status.setForeground(Color.red);
            labelP2Status.setText(LOSER);
        }
        else {
            labelP1Status.setForeground(Color.red);
            labelP1Status.setText(LOSER);
            labelP2Status.setForeground(Color.green);
            labelP2Status.setText(WINNER);
        }
        System.out.println(winner);
    }

    private void doAdditionalInitializations() {
        logChooser.setFileFilter(new FileNameExtensionFilter("Scrabble log files (*.log)", "log"));
        tableBoard.setDefaultRenderer(String.class, new ScrabbleBoardRenderer());
    }

    private void resetBoard() {
        for (int i = 0; i < tableBoard.getRowCount(); ++i) {
            for (int j = 0; j < tableBoard.getColumnCount(); ++j) {
                tableBoard.setValueAt(" ", i, j);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneBoard = new javax.swing.JScrollPane();
        tableBoard = new javax.swing.JTable();
        buttonStep = new javax.swing.JButton();
        scrollPaneLog = new javax.swing.JScrollPane();
        textLog = new javax.swing.JTextArea();
        scrollPaneMainLettersBag = new javax.swing.JScrollPane();
        textMainLettersBag = new javax.swing.JTextArea();
        labelP1Caption = new javax.swing.JLabel();
        labelP2Caption = new javax.swing.JLabel();
        textP2AvailableLetters = new javax.swing.JTextField();
        textP1AvailableLetters = new javax.swing.JTextField();
        labelP1Name = new javax.swing.JLabel();
        labelP2Name = new javax.swing.JLabel();
        labelP1LastMoveCaption = new javax.swing.JLabel();
        labelP1LastMove = new javax.swing.JLabel();
        labelP1PointsCaption = new javax.swing.JLabel();
        labelP1Points = new javax.swing.JLabel();
        labelP1LastMoveTimeCaption = new javax.swing.JLabel();
        labelP1LastMoveTime = new javax.swing.JLabel();
        labelP1AvailableLettersCaption = new javax.swing.JLabel();
        labelP2PointsCaption = new javax.swing.JLabel();
        labelP2LastMoveCaption = new javax.swing.JLabel();
        labelP2Points = new javax.swing.JLabel();
        labelP2LastMove = new javax.swing.JLabel();
        labelP2LastMoveTime = new javax.swing.JLabel();
        labelP2LastMoveTimeCaption = new javax.swing.JLabel();
        labelP2AvailableLettersCaption = new javax.swing.JLabel();
        labelMainLettersBagCaption = new javax.swing.JLabel();
        labelTurnCaption = new javax.swing.JLabel();
        labelTurn = new javax.swing.JLabel();
        labelLogNameCaption = new javax.swing.JLabel();
        labelLogName = new javax.swing.JLabel();
        buttonLoadLogFile = new javax.swing.JButton();
        buttonPlay = new javax.swing.JToggleButton();
        labelP1LastMovePointsCaption = new javax.swing.JLabel();
        labelP1LastMovePoints = new javax.swing.JLabel();
        labelP2LastMovePointsCaption = new javax.swing.JLabel();
        labelP2LastMovePoints = new javax.swing.JLabel();
        labelLimits = new javax.swing.JLabel();
        labelP1AdditionalInfoCaption = new javax.swing.JLabel();
        scrollP1AdditionalInfo = new javax.swing.JScrollPane();
        textP1AdditionalInfo = new javax.swing.JTextArea();
        labelP2AdditionalInfoCaption = new javax.swing.JLabel();
        scrollP2AdditionalInfo = new javax.swing.JScrollPane();
        textP2AdditionalInfo = new javax.swing.JTextArea();
        labelP1Status = new javax.swing.JLabel();
        labelP2Status = new javax.swing.JLabel();
        labelEndGame = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 810));
        setResizable(false);

        tableBoard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBoard.setRowHeight(32);
        scrollPaneBoard.setViewportView(tableBoard);

        buttonStep.setText("Step >");
        buttonStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStepActionPerformed(evt);
            }
        });

        textLog.setEditable(false);
        textLog.setColumns(20);
        textLog.setRows(5);
        scrollPaneLog.setViewportView(textLog);

        textMainLettersBag.setEditable(false);
        textMainLettersBag.setColumns(20);
        textMainLettersBag.setRows(5);
        scrollPaneMainLettersBag.setViewportView(textMainLettersBag);

        labelP1Caption.setText("Gracz 1:");
        labelP1Caption.setToolTipText("");

        labelP2Caption.setText("Gracz 2:");

        textP2AvailableLetters.setEditable(false);
        textP2AvailableLetters.setText("jTextField1");

        textP1AvailableLetters.setEditable(false);
        textP1AvailableLetters.setText("jTextField2");
        textP1AvailableLetters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textP1AvailableLettersActionPerformed(evt);
            }
        });

        labelP1Name.setText("jLabel3");

        labelP2Name.setText("jLabel4");

        labelP1LastMoveCaption.setText("Ostatni ruch:");

        labelP1LastMove.setText("jLabel1");

        labelP1PointsCaption.setText("Punkty");

        labelP1Points.setText("jLabel2");

        labelP1LastMoveTimeCaption.setText("Czas ruchu:");

        labelP1LastMoveTime.setText("jLabel4");

        labelP1AvailableLettersCaption.setText("Litery w posiadaniu:");

        labelP2PointsCaption.setText("Punkty:");

        labelP2LastMoveCaption.setText("Ostatni ruch:");

        labelP2Points.setText("jLabel2");

        labelP2LastMove.setText("jLabel1");

        labelP2LastMoveTime.setText("jLabel4");

        labelP2LastMoveTimeCaption.setText("Czas ruchu:");

        labelP2AvailableLettersCaption.setText("Litery w posiadaniu:");

        labelMainLettersBagCaption.setText("Worek z literami:");

        labelTurnCaption.setText("Tura:");

        labelTurn.setText("jLabel13");

        labelLogNameCaption.setText("Log:");
        labelLogNameCaption.setToolTipText("");

        labelLogName.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        labelLogName.setText("jLabel2");

        buttonLoadLogFile.setText("Załaduj log...");
        buttonLoadLogFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoadLogFileActionPerformed(evt);
            }
        });

        buttonPlay.setText("Play");
        buttonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPlayActionPerformed(evt);
            }
        });

        labelP1LastMovePointsCaption.setText("Punkty zdobyte w ostatnim ruchu:");

        labelP1LastMovePoints.setText("jLabel4");

        labelP2LastMovePointsCaption.setText("Punkty zdobyte w ostatnim ruchu:");

        labelP2LastMovePoints.setText("jLabel4");

        labelLimits.setText("jLabel1");

        labelP1AdditionalInfoCaption.setText("Dodatkowe informacje o ruchu:");

        textP1AdditionalInfo.setEditable(false);
        textP1AdditionalInfo.setColumns(20);
        textP1AdditionalInfo.setLineWrap(true);
        textP1AdditionalInfo.setRows(5);
        scrollP1AdditionalInfo.setViewportView(textP1AdditionalInfo);

        labelP2AdditionalInfoCaption.setText("Dodatkowe informacje o ruchu:");
        labelP2AdditionalInfoCaption.setToolTipText("");

        textP2AdditionalInfo.setEditable(false);
        textP2AdditionalInfo.setColumns(20);
        textP2AdditionalInfo.setLineWrap(true);
        textP2AdditionalInfo.setRows(5);
        scrollP2AdditionalInfo.setViewportView(textP2AdditionalInfo);

        labelP1Status.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelP1Status.setText("jLabel1");

        labelP2Status.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelP2Status.setText("jLabel1");

        labelEndGame.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelEndGame.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneLog)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneMainLettersBag, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelTurnCaption, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelTurn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(164, 164, 164))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(labelLogNameCaption, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonLoadLogFile, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelEndGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(labelLogName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelMainLettersBagCaption)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textP1AvailableLetters)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(labelP1Caption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelP1LastMoveCaption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelP1LastMoveTimeCaption, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelP1PointsCaption, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelP1Points, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelP1LastMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelP1LastMoveTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelP1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 6, Short.MAX_VALUE))))
                                    .addComponent(labelP1LastMovePoints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelP1LastMovePointsCaption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(scrollP1AdditionalInfo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelP1AvailableLettersCaption)
                                            .addComponent(labelP1AdditionalInfoCaption))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(labelP1Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelLimits)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(scrollPaneBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textP2AvailableLetters)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelP2PointsCaption)
                                            .addComponent(labelP2LastMoveCaption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelP2LastMoveTimeCaption)
                                            .addComponent(labelP2Caption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelP2Points, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labelP2LastMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labelP2LastMoveTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(1, 1, 1))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelP2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 5, Short.MAX_VALUE))))
                                    .addComponent(labelP2LastMovePoints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelP2LastMovePointsCaption, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelP2AvailableLettersCaption)
                                            .addComponent(labelP2AdditionalInfoCaption))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(scrollP2AdditionalInfo)
                                    .addComponent(labelP2Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelP1Caption, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelP1Name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelP1PointsCaption)
                            .addComponent(labelP1Points))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelP1LastMoveCaption)
                            .addComponent(labelP1LastMove))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelP1LastMoveTimeCaption)
                            .addComponent(labelP1LastMoveTime))
                        .addGap(25, 25, 25)
                        .addComponent(labelP1LastMovePointsCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelP1LastMovePoints)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelP1AvailableLettersCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textP1AvailableLetters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelP1AdditionalInfoCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollP1AdditionalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelP1Status, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelP2Caption, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelP2Name))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelP2PointsCaption)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelP2LastMoveCaption)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelP2LastMoveTimeCaption))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelP2Points)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelP2LastMove)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelP2LastMoveTime)))
                        .addGap(23, 23, 23)
                        .addComponent(labelP2LastMovePointsCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelP2LastMovePoints)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelP2AvailableLettersCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textP2AvailableLetters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelP2AdditionalInfoCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollP2AdditionalInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelP2Status, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMainLettersBagCaption)
                    .addComponent(labelLimits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTurnCaption)
                            .addComponent(labelTurn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelEndGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonStep)
                                    .addComponent(buttonLoadLogFile)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelLogNameCaption))))
                    .addComponent(scrollPaneMainLettersBag))
                .addGap(1, 1, 1)
                .addComponent(labelLogName)
                .addGap(3, 3, 3)
                .addComponent(scrollPaneLog, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        labelP1Name.getAccessibleContext().setAccessibleName("labelPlayer1Name");
        labelP1Name.getAccessibleContext().setAccessibleDescription("");
        labelP2Name.getAccessibleContext().setAccessibleName("labelPlayer2Name");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textP1AvailableLettersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textP1AvailableLettersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textP1AvailableLettersActionPerformed

    private void buttonLoadLogFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoadLogFileActionPerformed
        //TODO pause any kind of execution
        int retVal = logChooser.showOpenDialog(this);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            try {
                ScrabbleVisualizer.loadLogFile(logChooser.getSelectedFile());
            }
            catch(Exception e) {
                labelLogName.setText(FAILED_TO_LOAD_LOG_ERROR_TEXT);
            }
        }
    }//GEN-LAST:event_buttonLoadLogFileActionPerformed

    private void buttonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPlayActionPerformed
        if(buttonPlay.isSelected()) {
            ScrabbleVisualizer.startPlayback();
            buttonStep.setEnabled(false);
        }
        else {
            //do pause stuff
            ScrabbleVisualizer.pausePlayback();
            buttonStep.setEnabled(true);
        }
    }//GEN-LAST:event_buttonPlayActionPerformed

    private void buttonStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStepActionPerformed
        ScrabbleVisualizer.stepOneTurn();
    }//GEN-LAST:event_buttonStepActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualizerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizerUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLoadLogFile;
    private javax.swing.JToggleButton buttonPlay;
    private javax.swing.JButton buttonStep;
    private javax.swing.JLabel labelEndGame;
    private javax.swing.JLabel labelLimits;
    private javax.swing.JLabel labelLogName;
    private javax.swing.JLabel labelLogNameCaption;
    private javax.swing.JLabel labelMainLettersBagCaption;
    private javax.swing.JLabel labelP1AdditionalInfoCaption;
    private javax.swing.JLabel labelP1AvailableLettersCaption;
    private javax.swing.JLabel labelP1Caption;
    private javax.swing.JLabel labelP1LastMove;
    private javax.swing.JLabel labelP1LastMoveCaption;
    private javax.swing.JLabel labelP1LastMovePoints;
    private javax.swing.JLabel labelP1LastMovePointsCaption;
    private javax.swing.JLabel labelP1LastMoveTime;
    private javax.swing.JLabel labelP1LastMoveTimeCaption;
    private javax.swing.JLabel labelP1Name;
    private javax.swing.JLabel labelP1Points;
    private javax.swing.JLabel labelP1PointsCaption;
    private javax.swing.JLabel labelP1Status;
    private javax.swing.JLabel labelP2AdditionalInfoCaption;
    private javax.swing.JLabel labelP2AvailableLettersCaption;
    private javax.swing.JLabel labelP2Caption;
    private javax.swing.JLabel labelP2LastMove;
    private javax.swing.JLabel labelP2LastMoveCaption;
    private javax.swing.JLabel labelP2LastMovePoints;
    private javax.swing.JLabel labelP2LastMovePointsCaption;
    private javax.swing.JLabel labelP2LastMoveTime;
    private javax.swing.JLabel labelP2LastMoveTimeCaption;
    private javax.swing.JLabel labelP2Name;
    private javax.swing.JLabel labelP2Points;
    private javax.swing.JLabel labelP2PointsCaption;
    private javax.swing.JLabel labelP2Status;
    private javax.swing.JLabel labelTurn;
    private javax.swing.JLabel labelTurnCaption;
    private javax.swing.JScrollPane scrollP1AdditionalInfo;
    private javax.swing.JScrollPane scrollP2AdditionalInfo;
    private javax.swing.JScrollPane scrollPaneBoard;
    private javax.swing.JScrollPane scrollPaneLog;
    private javax.swing.JScrollPane scrollPaneMainLettersBag;
    private javax.swing.JTable tableBoard;
    private javax.swing.JTextArea textLog;
    private javax.swing.JTextArea textMainLettersBag;
    private javax.swing.JTextArea textP1AdditionalInfo;
    private javax.swing.JTextField textP1AvailableLetters;
    private javax.swing.JTextArea textP2AdditionalInfo;
    private javax.swing.JTextField textP2AvailableLetters;
    // End of variables declaration//GEN-END:variables
}
