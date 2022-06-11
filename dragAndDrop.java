import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.transform.Templates;

import java.io.*;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.transform.stax.StAXResult;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javax.swing.text.*;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import java.nio.file.Files;
import java.text.BreakIterator;


public class dragAndDrop {
    static long startTime;
    FileWriter scoreBoard;
    FileReader getHighScore;
    public dragAndDrop() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("TAŞAK");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setSize(1000,1000);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new GridLayout(2, 5));
            add(createBottomPanel());
            add(createTopPanel());
        }

        protected JPanel createTopPanel() {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridy = 0;
            gbc.weightx = 0;
            String firstRow[] = {"q","w","e","r","t","y","u","i","o","p"};
            String secondRow[] = {"a","s","d","f","g","h","j","k","l"};
            String thirdRow[] = {"z","x","c","v","b","n","m"};

            for (int i = 0; i < 10; i++) {
                JButton btn = new JButton(firstRow[i]);
                panel.add(btn, gbc);
                btn.setTransferHandler(new ValueExportTransferHandler(firstRow[i]));

                btn.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        JButton button = (JButton) e.getSource();
                        TransferHandler handle = button.getTransferHandler();
                        handle.exportAsDrag(button, e, TransferHandler.COPY);
                    }
                });
            }
       
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridy = 1;
            gbc.weightx = 0;
            startTime = System.currentTimeMillis();

            for (int i = 0; i < 9; i++) {
                JButton btn = new JButton(secondRow[i]);
                panel.add(btn, gbc);
                btn.setTransferHandler(new ValueExportTransferHandler(secondRow[i]));

                btn.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        JButton button = (JButton) e.getSource();
                        TransferHandler handle = button.getTransferHandler();
                        handle.exportAsDrag(button, e, TransferHandler.COPY);
                    }
                
                });
            }

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridy = 2;
            gbc.weightx = 0;

            for (int i = 0; i < 7; i++) {
                JButton btn = new JButton(thirdRow[i]);
                panel.add(btn, gbc);
                btn.setTransferHandler(new ValueExportTransferHandler(thirdRow[i]));

                btn.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        JButton button = (JButton) e.getSource();
                        TransferHandler handle = button.getTransferHandler();
                        handle.exportAsDrag(button, e, TransferHandler.COPY);
                    }
                });
            }
            return panel;
        }
        String selected = ReturnRandomWord();

        char [] stringToChar(char arr [], String selected)
        {   
            for(int i = 0; i < selected.length(); i++)
            {   
                arr[i] = selected.charAt(i);
            }
            return arr;
        }
    

        String ReturnRandomWord(){

            String[] answerList = new String[2315];
            try {
                File myObj = new File("wordleAnswers.txt");
                Scanner myReader = new Scanner(myObj);
                int indexCounter = 0;
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    //add data to the array
                    answerList[indexCounter] = data;
                    indexCounter++;
                }
                myReader.close();   
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        
            return answerList[(int)(Math.random() * (answerList.length - 1))]; //returns a random word from this large list
            }



            boolean IsAValidWord(String input, String[] possibleWords) {
                if (input.length() < 5) {
                    System.out.println("Wordle: The Word You Entered Was Not Long Enough");
                    return false;
                }
                for (String string : possibleWords) {
                    if (string.equals(input)) {
                        return true;
                    }
                }
                return false;
            }
      
        


        protected JPanel createBottomPanel() {
            GridBagConstraints gbc = new GridBagConstraints();
            JPanel panel = new JPanel(new GridBagLayout());
            ArrayList <JLabel> labels = new ArrayList<JLabel>();

            gbc.anchor = GridBagConstraints.SOUTHEAST;
            gbc.gridy = 0;
            System.out.println(selected);

            JButton button = new JButton("Enter");
            
            panel.add(button,gbc);
        
             char [] answerChar = new char[5];
              stringToChar(answerChar, selected);
             
    
            for(int i = 0; i < 25; i++){
                if(i % 5 == 0)
                    gbc.gridy += 1;



                JLabel label = new JLabel();   
                gbc.gridwidth = 1000;
             

                label.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(30, 30, 30, 30)));
                label.setTransferHandler(new ValueImportTransferHandler());
                
		        label.setMinimumSize(new Dimension(74, 72));
                label.setMaximumSize(new Dimension(74, 72));
                label.setPreferredSize(new Dimension(74, 72));
                labels.add(label);
                panel.add(label,gbc);
               
                }

            
                    button.addActionListener(new ActionListener()
                    {
                        String  inputString;
                        String abc = "";
                        int c = 0;
                        char [] inputChar = new char[5];
                        char [] gercekChar = new char[5];
                    
                        
                        
                        public void actionPerformed(ActionEvent e){
                          if(c == 0){  
                            for(int i = 0; i < 5; i++){
                                inputString = labels.get(i).getText();
                                abc+=inputString;
                                
                            }
                        }
                        else if(c == 1){  
                            for(int i = 5; i < 10; i++){
                                inputString = labels.get(i).getText();
                                abc+=inputString;
                            }
                        }
                      else if(c == 2){  
                            for(int i = 10; i < 15; i++){
                                inputString = labels.get(i).getText();
                                abc+=inputString;
                            }
                        }
                       else if(c == 3){  
                            for(int i = 15; i < 20; i++){
                                inputString = labels.get(i).getText();
                                abc+=inputString;
                            }
                        }
                        else if(c == 4){  
                            for(int i = 20; i < 25; i++){
                                inputString = labels.get(i).getText();
                                abc+=inputString;
                            }
                        }
                            for(int i = 0; i < 5; i++)
                            {
                                inputChar[i] = abc.charAt(i);
                                
                                System.out.println(inputChar[i]);
                            }
                           
                            String possibleWords[] = new String[12947];
                            try {
                                File myObj = new File("wordleWords.txt");
                                Scanner myReader = new Scanner(myObj);
                                int indexCounter = 0;
                                while(myReader.hasNextLine())
                                {
                                    String data = myReader.nextLine();
                                    possibleWords[indexCounter] = data;
                                    indexCounter++;
                                }
                            } catch (FileNotFoundException f) {
                                System.err.println("An error occured.");
                                f.printStackTrace();
                            }
                                                    
                                for(int i = 0; i < 5; i++){
                                if(inputChar[i] == answerChar[i]){
                                      if(c == 0){
                                        labels.get(i).setOpaque(true);
                                        labels.get(i).setBackground(Color.GREEN);
                                    
                                      }
                                     else if(c == 1){
                                        labels.get(5+i).setOpaque(true);
                                        labels.get(5+i).setBackground(Color.GREEN);
                                      }

                                      else if(c == 2){
                                        labels.get(10+i).setOpaque(true);
                                        labels.get(10+i).setBackground(Color.GREEN);
                                      }
                                      else if(c == 3){
                                        labels.get(15+i).setOpaque(true);
                                        labels.get(15+i).setBackground(Color.GREEN);
                                      }

                                      else if(c == 4){
                                        labels.get(20+i).setOpaque(true);
                                        labels.get(20+i).setBackground(Color.GREEN);
                                      }
                                        
                                }
                             
                                 else if(inputChar[i] == answerChar[0] || inputChar[i] == answerChar[1] || inputChar[i] == answerChar[2] || inputChar[i]  == answerChar[3] || inputChar[i]  == answerChar[4])    
                                 {
                                      
                                    if(c == 0){
                                        labels.get(i).setOpaque(true);
                                        labels.get(i).setBackground(Color.YELLOW);
                                    
                                      }
                                     else if(c == 1){
                                        labels.get(5+i).setOpaque(true);
                                        labels.get(5+i).setBackground(Color.YELLOW);
                                      }

                                      else if(c == 2){
                                        labels.get(10+i).setOpaque(true);
                                        labels.get(10+i).setBackground(Color.YELLOW);
                                      }
                                      else if(c == 3){
                                        labels.get(15+i).setOpaque(true);
                                        labels.get(15+i).setBackground(Color.YELLOW);
                                      }

                                      else if(c == 4){
                                        labels.get(20+i).setOpaque(true);
                                        labels.get(20+i).setBackground(Color.YELLOW);
                                      }
                                        


                                 } 
                              
                                }
                                if(inputChar[0] == answerChar[0] && inputChar[1] == answerChar[1] && inputChar[2] == answerChar[2] && inputChar[3]  == answerChar[3] && inputChar[4]  == answerChar[4]){
                                    long score = (System.currentTimeMillis() - startTime);
                                    try {
                                        FileWriter deneme = new FileWriter("deneme.txt", true);
                                        try (BufferedWriter yazici = new BufferedWriter(deneme)) {
                                            yazici.write((int) score + "");
                                            yazici.newLine();
                                            yazici.close();
                                        }
                                    } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                    }
                                    int highScore = (int) score;
                                    try {
                                        BufferedReader reader = new BufferedReader(new FileReader("deneme.txt"));
                                        String line = reader.readLine();
                                        while (line != null)                 // read the score file line by line
                                        {
                                            try {
                                                int readValue = Integer.parseInt(line.trim());   // parse each line as an int
                                                if (readValue < highScore)                       // and keep track of the largest
                                                { 
                                                    highScore = readValue; 
                                                }
                                            } catch (NumberFormatException e1) {
                                                // ignore invalid scores
                                                //System.err.println("ignoring invalid score: " + line);
                                            }
                                            line = reader.readLine();
                                        }
                                        reader.close();

                                    } catch (IOException ex) {
                                        System.err.println("ERROR reading scores from file");
                                    }
                               
                                    
                                    System.out.println(highScore);
                                    JLabel highScoreLabel = new JLabel("Highest score is " + highScore + " miliseconds");
                                    JLabel win = new JLabel("Congratulations you have won in " + score + " miliseconds");
                                    JFrame winFrame = new JFrame();
                                    winFrame.setSize(400,300);
                                    win.setAlignmentX(30);
                                    win.setAlignmentY(30);
                                    win.setBounds(0,0, 30, 30);
                                    highScoreLabel.setBounds(0,40, 300, 30);

                                    highScoreLabel.setAlignmentX(30);
                                    highScoreLabel.setAlignmentY(100);
                                    winFrame.add(highScoreLabel);
                                    winFrame.add(win);

                                    winFrame.setVisible(true);
                        
                                 } 
                            
                            for(int j = 0; j < 5; j++)
                            {
                                inputChar[j] = ' ';
                            }
                            abc = "";
                             c++;
                             System.out.println(c);
                             System.out.print(selected);
                            
                        }
                    
                    });

                panel.add(button);
                
            return panel;    
            
        }

    }

    public static class ValueExportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
        private String value;

        public ValueExportTransferHandler(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = new StringSelection(getValue());
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            super.exportDone(source, data, action);

        }

    }

    public static class ValueImportTransferHandler extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;

        public ValueImportTransferHandler() {
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof String) {
                        Component component = support.getComponent();
                        if (component instanceof JLabel) {
                            ((JLabel) component).setText(value.toString());
                            accept = true;
                        }
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }
    
    }
    
}