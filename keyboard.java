import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Scanner;
import java.util.Timer;
import java.io.FileNotFoundException;
import java.io.*;

class keyboard extends JFrame implements ActionListener {
    static int a = 0;
    static JPanel panel;
    static JFrame frame;
    static JLabel Title;
    static JLabel stats;
    static JTextField userText1;
    static JLabel[] labels;
    static JButton button = new JButton("Enter");
    static Image kupa;
   

    static double x = 0,y = 0,velX = 2,velY = 2;

    public void paint (Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(kupa, 25,50,25,25, this);
    
    }

   





   public static Scanner s = new Scanner(System.in);
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_YELLOW = "\u001B[33m";
   public static final String ANSI_GREEN = "\u001B[32m";

   static String[] possibleWords;
   static int tries;
   static char[] input;
   static long startTime;
   static char[] answer;
   static String answerChoosen;
   static boolean done;
   FileWriter scoreBoard;
   FileReader getHighScore;

   keyboard(){
    while(a<=0){
    panel = new JPanel();
    frame = new JFrame();
    frame.setSize(800, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("WORDLE");
    frame.setLocationRelativeTo(null);
    frame.add(panel);

    panel.setLayout(null);
    stats = new JLabel("Type a five letter word");
    stats.setBounds(300, 100, 600, 25);
    panel.add(stats);

    userText1 = new JTextField();
    userText1.setBounds(300, 150, 150, 40);
    panel.add(userText1);

    button.setBounds(450, 150, 80, 40);
    panel.add(button);

    labels = new JLabel[5];
    for (int i = 0; i < 5; i++) {
        labels[i] = new JLabel("<html><font size='30' color=blue> [][][][][][] </font> <font");
        labels[i].setBounds(300, 170 + (i * 50), 250, 100);
        panel.add(labels[i]);
    }
    frame.setVisible(true);
    StartWordle();
    }
   }


   
   void StartWordle() {
    a++;    
    userText1.addActionListener(new keyboard());
    button.addActionListener(new keyboard());
    possibleWords = new String[12947];
    try {
        File myObj = new File("wordleWords.txt");
        Scanner myReader = new Scanner(myObj);
        int indexCounter = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            possibleWords[indexCounter] = data;
            indexCounter++;
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

    startTime = System.currentTimeMillis();
    tries = 0;
    answerChoosen = ReturnRandomWord();
    System.out.println(answerChoosen);
    answer = new char[5];
    for (int i = 0; i < 5; i++ ) answer[i] = answerChoosen.charAt(i);

    input = new char[5];
}






public static void EndWordle() {


    userText1.setEnabled(false);
    userText1.setVisible(false);

    if(done){
    long score = System.currentTimeMillis() - startTime;
    try {
        FileWriter deneme = new FileWriter("scoreboard.txt", true);
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
        BufferedReader reader = new BufferedReader(new FileReader("scoreboard.txt"));
        String line = reader.readLine();
        while (line != null)                 
        {
            try {
                int readValue = Integer.parseInt(line.trim());   
                if (readValue < highScore)                       
                { 
                    highScore = readValue; 
                }
            } catch (NumberFormatException e1) {
            }
            line = reader.readLine();
        }
        reader.close();

    } catch (IOException ex) {
        System.err.println("ERROR reading scores from file");
    }
    stats.setText("You Found The Answer in " + ((System.currentTimeMillis() - startTime) + " miliseconds.") + " Highest score is " + highScore);
    JFrame winFrame = new JFrame();
    winFrame.setSize(500,500);
    winFrame.setVisible(true);
    stats.setAlignmentY(0);
    stats.setVisible(true);
    
    MyPanel panel;
    panel = new MyPanel();
    panel.setAlignmentY(20);
    panel.add(stats);
    winFrame.add(panel);
    panel.setVisible(true);

    }
}

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub // if the button is pressed
    EnterWord();

}

void EnterWord(){ 
    if ( IsAValidWord(userText1.getText(), possibleWords) ) ButtonPressed();
    else System.out.println("Not a valid word");
}

void ButtonPressed(){
    userText1.setBounds(300, 150, 150, 40);

    String userInput = userText1.getText();
    int[] colorOfLetters = PlayWordle(userInput);

    done = true;
    for (int i : colorOfLetters) {
        if (i != 2) done = false;
    }
    if (done || tries >= 5) EndWordle();

    String[] numsToColors = new String[5];
    for (int i = 0; i < 5; i++) {
        if (colorOfLetters[i] == 0) numsToColors[i] = "black";
        else if (colorOfLetters[i] == 1) numsToColors[i] = "orange";
        else if (colorOfLetters[i] == 2) numsToColors[i] = "green";
    }

    
    String finalString = (
    "<html><font size='10' color=" + numsToColors[0] + "> " + userInput.charAt(0) + "</font> <font            " + 
    "<html><font size='10' color=" + numsToColors[1] + "> " + userInput.charAt(1) + "</font> <font            " + 
    "<html><font size='10' color=" + numsToColors[2] + "> " + userInput.charAt(2) + "</font> <font            " + 
    "<html><font size='10' color=" + numsToColors[3] + "> " + userInput.charAt(3) + "</font> <font            " + 
    "<html><font size='10' color=" + numsToColors[4] + "> " + userInput.charAt(4) + "</font> <font            ");
    setNextLabel(finalString);

    userText1.setText("");
}

int[] PlayWordle(String InputWordleWord) {
    done = false;
    tries++;

    String R1 = InputWordleWord.toLowerCase();


    if (!IsAValidWord(R1, possibleWords)) {
        System.out.println("wasnt a good word");
    } else {
        for (int i = 0; i < 5; i++ ) {
            input[i] = R1.charAt(i);
        }
    }
    for (int i = 0; i < 5; i++ ) answer[i] = answerChoosen.charAt(i);
    return ReturnColorOfLeters(input, answer);
}

void setNextLabel(String string){
    labels[tries - 1].setText(string);
}

int[] ReturnColorOfLeters(char[] inputWord, char[] correctWord) {
    char[] answerTemp = correctWord;
    int[] colorForLetter = new int[5]; 

    for (int i = 0; i < 5; i++) { 
        if (inputWord[i] == answerTemp[i]) {
            answerTemp[i] = '-';
            colorForLetter[i] = 2;
        }
    }

    for (int j = 0; j < 5; j++) {
        for (int k = 0; k < 5; k++){
            if (inputWord[j] == answerTemp[k] && colorForLetter[j] != 2) {
                colorForLetter[j] = 1;
                answerTemp[k] = '-';
            }
        }
    }

    for (int m = 0; m < 5; m++) {
        if (colorForLetter[m] == 0) System.out.print(inputWord[m]);
        if (colorForLetter[m] == 1) System.out.print(ANSI_YELLOW + inputWord[m] + ANSI_RESET);
        if (colorForLetter[m] == 2) System.out.print(ANSI_GREEN + inputWord[m] + ANSI_RESET);
    }

    System.out.println("");
    return colorForLetter;
}

boolean IsAValidWord(String input, String[] possibleWords) {
    if (input.length() < 5) {
        System.out.println("Word not long enough.");
        return false;
    }
    for (String string : possibleWords) {
        if (string.equals(input)) {
            return true;
        }
    }
    return false;
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

    return answerList[(int)(Math.random() * (answerList.length - 1))];

    }
    
}


    




 
