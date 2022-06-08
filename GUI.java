import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class GUI extends keyboard implements ActionListener{
    public static JFrame frame_main;
    public static JPanel panel_main;
    public static JButton keyboardButton;
    public static JButton ddButton;


	public static void main(String[] args) {
        panel_main = new JPanel();
        frame_main = new JFrame();
        frame_main.setSize(600, 200);
        frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_main.setTitle("SELECT AMK");
        frame_main.setLocationRelativeTo(null);
        frame_main.add(panel_main); 

        panel_main.setLayout(null);
        Title = new JLabel("SEÇ MQ ");
        Title.setBounds(10, 20, 80, 25);
        panel_main.add(Title);

        JButton button_klavye = new JButton("KLAVYE MQ");
        JButton button_dd = new JButton("YOK MQ ");

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

