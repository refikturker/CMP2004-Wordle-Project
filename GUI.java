import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class GUI extends keyboard implements ActionListener{
    public static JFrame frame_main;
    public static JPanel panel_main;
    public static JButton keyboardButton;
    public static JButton ddButton;




    public static void main(String[] args) throws IOException {
        panel_main = new JPanel();
        frame_main = new JFrame();
        frame_main.setSize(600, 200);
        frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_main.setTitle("GUI");
        frame_main.setLocationRelativeTo(null);
        frame_main.add(panel_main); 
        panel_main.setLayout(null);
        //Title = new JLabel("Keyboard or Dragn and Drop");
        //Title.setBounds(10, 20, 80, 25);
        //panel_main.add(Title);

        JButton button_klavye = new JButton("Keyboard");
        JButton button_dd = new JButton("Drag and Drop");

        button_klavye.setBounds(100, 20, 140, 25);
        button_dd.setBounds(400, 20, 140, 25);

        panel_main.add(button_klavye);
        panel_main.add(button_dd);
        button_dd.setVisible(true);
        button_klavye.setVisible(true);

        frame_main.setVisible(true);
        panel_main.setVisible(true);

        button_klavye.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e )
            {
                keyboard start = new keyboard();
            }

        });
        button_dd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e )
            {
                dragAndDrop start2 = new dragAndDrop();
            }

        });


}


}