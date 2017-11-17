package io.github.cmansfield;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.Card.Card;

import java.util.*;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.InputStream;


public class LoadCardList {
  private LoadCardList() {}

  public static List<String> loadCards() throws IOException {
    List<String> cards = new ArrayList<String>();

    ZipFile zip = new ZipFile(CardListConstants.CARD_LIST_FILE_NAME);
    InputStream inputStream = zip.getInputStream(zip.getEntry(CardListConstants.ALL_CARDS_FILE_NAME));
    Scanner scanner = new Scanner(inputStream);


    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);

    System.out.println(jsonMap.get("AWOL"));


//    Card[] pojos = mapper.readValue(inputStream, Card[].class);
//    List<Card> mcList = new ArrayList<Card>(Arrays.asList(pojos));

    // Sample code to check the stream
//    int i = 0;
//    while(scanner.hasNextLine()) {
//      if(i >= 30) {
//        break;
//      }
//
//      System.out.println(scanner.nextLine());
//      ++i;
//    }

    return cards;
  }
}
