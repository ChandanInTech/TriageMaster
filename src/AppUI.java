import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AppUI extends JFrame {
  private JTextField fileNameET;
  private JButton runButton;
  private JTextPane textPane1;
  private JButton copyButton;
  private JPanel pan;
  private JButton openButton;
  private JTextField hasET;
  private JButton hasOpenButton;
  private JTextField dHasET;
  private JButton dHasOpenButton;

  String result = null;

    ScanFile2 scanFile = new ScanFile2();

  public AppUI() {
    add(pan);
    setSize(600, 800);
    setTitle("Log parser");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    openButton.addActionListener(e -> fileNameET.setText(openFiles()));

    copyButton.addActionListener(
        e -> {
          StringSelection stringSelection = new StringSelection(result);
          Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
          clpbrd.setContents(stringSelection, null);
        });

    runButton.addActionListener(e -> {
        try {
            run();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    hasOpenButton.addActionListener(e -> {
        try {
            hasET.setText(scanFile.getContentOfFile(openFiles()));
        } catch (FileNotFoundException ex) {
            textPane1.setText("File Not Found");
        }
    });

    dHasOpenButton.addActionListener(e -> {
        try {
            dHasET.setText(scanFile.getContentOfFile(openFiles()));
        } catch (FileNotFoundException ex) {
            textPane1.setText("File Not Found");
        }
    });
  }

  private String openFiles() {

    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
    jfc.addChoosableFileFilter(filter);

    int returnValue = jfc.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      return selectedFile.getAbsolutePath();
    }
    return "";
  }

  private void run() throws IOException {

      ArrayList keyWords = new ArrayList<String>();
      ArrayList dKeyWords = new ArrayList<String>();
      if (!hasET.getText().isEmpty()){
          keyWords.addAll(Arrays.asList(hasET.getText().split(",")));
      }

      if (!dHasET.getText().isEmpty()){
          dKeyWords.addAll(Arrays.asList(dHasET.getText().split(",")));
      }

      result = scanFile.scanIt(fileNameET.getText(), keyWords, dKeyWords);
    textPane1.setText(result);
    textPane1.select(0,0);
  }
}
