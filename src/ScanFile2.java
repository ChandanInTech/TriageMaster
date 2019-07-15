import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScanFile2 {

  public String scanIt(String filepath, ArrayList<String> keywords, ArrayList<String> dKeywords) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    Scanner sc;

    try {
      sc = new Scanner(new File(filepath));
    } catch (FileNotFoundException e) {
      return "File Not Found";
    }

    if (keywords.size() != 0 && dKeywords.size() != 0) {
      while (sc.hasNext()) {
        String line = sc.nextLine();
        for (String keyword : keywords) {
          if (line.contains(keyword)) {
            int n = 0;
            for (String dKeyword : dKeywords) {
              if (line.contains(dKeyword)) {
                n++;
                break;
              }
            }
            if (n == 0) {
              stringBuilder.append(line).append("\n");
            }
          }
        }
      }
      return stringBuilder.toString();
    }

    if (keywords.size() != 0 && dKeywords.size() == 0) {
      while (sc.hasNext()) {
        String line = sc.nextLine();
        for (String keyword : keywords) {
          if (line.contains(keyword)) {
            stringBuilder.append(line).append("\n");
            break;
          }
        }
      }
      return stringBuilder.toString();
    }

    if (keywords.size() == 0 && dKeywords.size() != 0) {
      while (sc.hasNext()) {
        String line = sc.nextLine();
        int n = 0;
        for (String keyword : dKeywords) {
          if (line.contains(keyword)) {
            n++;
            break;
          }
        }
        if (n == 0) {
          stringBuilder.append(line).append("\n");
        }
      }
      return stringBuilder.toString();
    }

    if (keywords.size() == 0 && dKeywords.size() == 0) {
      while (sc.hasNext()) {
        String line = sc.nextLine();
        stringBuilder.append(line).append("\n");
      }

//        sc.useDelimiter("\\Z");



//        return sc.next();
        return stringBuilder.toString();
//        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

    return stringBuilder.toString();
  }

  public String getContentOfFile(String filepath) throws FileNotFoundException {
    StringBuilder stringBuilder = new StringBuilder();
    Scanner sc = new Scanner(new File(filepath));
    while (sc.hasNext()) stringBuilder.append(sc.nextLine()).append("\n");
    return stringBuilder.toString();
  }
}
