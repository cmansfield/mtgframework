package io.github.cmansfield.io.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.deck.constants.Format;
import io.github.cmansfield.io.IoConstants;
import io.github.cmansfield.io.LoadCards;
import io.github.cmansfield.card.Card;
import io.github.cmansfield.deck.Deck;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.util.*;


public final class TappedImporter {

  public static List<Deck> importFilesFromTappedOut(String directory) throws IOException {
    File folder = new File(directory);
    File[] fileArray = folder.listFiles();
    List<Deck> decks = new ArrayList<>();

    if(fileArray == null) {
      return null;
    }

    List<File> files = Arrays.asList(fileArray);
    ObjectMapper mapper = new ObjectMapper();

    files.forEach(file -> {
      if(!file.isFile()) {
        return;
      }
      try(InputStream inputStream = new FileInputStream(file.getAbsolutePath())) {
        Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
        Deck deck = createDeckFromJson(jsonMap);
        if(deck != null) {
          decks.add(deck);
        }
      }
      catch(Exception e) {
        System.out.printf("Unable to load file %s%n", file.getName());
        throw new RuntimeException(e);
      }

    });

    return decks;
  }


  private static Deck createDeckFromJson(Map<String, Object> jsonMap) {
    Card featuredCard = null;
    List inventory = (List)jsonMap.get(IoConstants.INVENTORY_KEY);

    if(jsonMap.containsKey(IoConstants.FEATURED_KEY)) {
      featuredCard = LoadCards.lookupCard((String)jsonMap.get(IoConstants.FEATURED_KEY));
    }

    Map<String,Integer> cardMap = new HashMap<>();

    inventory.forEach(list -> {
      String name;
      int qty;

      Object obj0 = ((List)list).get(0);
      Object obj1 = ((List)list).get(1);

      if(obj0 instanceof String) {
        name = (String)obj0;
        qty = (int)((Map)(obj1)).get(IoConstants.QUANTITY_KEY);
      }
      else {
        name = (String)obj1;
        qty = (int)((Map)(obj0)).get(IoConstants.QUANTITY_KEY);
      }

      cardMap.put(name, qty);
    });

    List<Card> cards = new ArrayList<>();
    cardMap.entrySet().forEach(entry -> {
      for(int i = 0; i < entry.getValue(); ++i) {
        Card foundCard = LoadCards.lookupCard(entry.getKey());
        if(foundCard != null) {
          cards.add(foundCard);
        }
      }
    });

    Deck deck = new Deck(cards);
    if(featuredCard != null) {
      deck.setFeaturedCard(featuredCard);
      deck.setFormat(Format.COMMANDER);
    }

    return deck;
  }


}
