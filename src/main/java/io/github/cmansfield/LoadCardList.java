package io.github.cmansfield;

import java.util.Scanner;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class LoadCardList {
  private LoadCardList() {}

  public static List<String> loadCards() throws IOException {
    List<String> cards = new ArrayList<String>();

    ZipFile zip = new ZipFile(CardListConstants.CARD_LIST_FILE_NAME);
    InputStream inputStream = zip.getInputStream(zip.getEntry(CardListConstants.ALL_CARDS_FILE_NAME));
    Scanner scanner = new Scanner(inputStream);

    // Sample code to check the stream
    int i = 0;
    while(scanner.hasNextLine()) {
      if(i >= 30) {
        break;
      }

      System.out.println(scanner.nextLine());
      ++i;
    }

    return cards;
  }
}
