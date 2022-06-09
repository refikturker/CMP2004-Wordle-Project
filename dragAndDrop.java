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

import javax.sql.rowset.spi.SyncResolver;
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

import java.nio.file.Files;


public class dragAndDrop {


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
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        public TestPane() {
            setLayout(new GridLayout(2, 1));
            add(createBottomPanel());
            add(createTopPanel());
        }

        protected JPanel createTopPanel() {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridy = 0;
            gbc.weightx = 0;
            String firstRow[] = {"Q","W","E","R","T","Y","U","I","O","P"};
            String secondRow[] = {"A","S","D","F","G","H","J","K","L"};
            String thirdRow[] = {"Z","X","C","V","B","N","M"};

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
        

        protected JPanel createBottomPanel() {
            GridBagConstraints gbc = new GridBagConstraints();
            JPanel panel = new JPanel(new GridBagLayout());
            ArrayList <JLabel> labels = new ArrayList<JLabel>();
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.gridy = 0;
            JButton button = new JButton("Enter");
            
        
           for(int i = 0; i < 25; i++){
                if(i % 5 == 0)
                {
                    gbc.gridy += 1;
                }
                //JTextField field = new JTextField(5);
                JLabel label = new JLabel();   
                label.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(20, 20, 20, 20)));
                label.setTransferHandler(new ValueImportTransferHandler());
                label.setSize(20, 20); 
                labels.add(label);
                panel.add(label,gbc);
                }

                    button.addActionListener(new ActionListener()
                    {
                        String selected = "start";

                        public void actionPerformed(ActionEvent e){
                            for(int i = 0; i < 25; i++){
                                String str[] = new String[25];
                                

                                if(labels.get(4).getText().length() == 1)
                                {
                                    str[i] = labels.get(i).getText();
                                    if(str[i].contains("S")){
                                    labels.get(0).setForeground(Color.MAGENTA);
                                    System.out.println("amcik");
                                    }
                                    
                                    System.out.print(str[i]);
                                   continue;
                                }
                            
                            
                                //if()
                               // {
                                  //  
                                    
                                //} 
                            
                            }
                        }
                    });

                

              
                

                
                panel.add(button);
                
        
           /*  label.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(20, 20, 20, 20)));
            label.setTransferHandler(new ValueImportTransferHandler());
            panel.add(label);
            */
    
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