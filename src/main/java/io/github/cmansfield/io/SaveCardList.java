package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.card.Card;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.io.*;


public final class SaveCardList {
  private SaveCardList() {}

  public static String saveCards(List<Card> cards) throws IOException {
    final String CARD_LIST_SAVE_NAME = "CardList%d.json";
    File saveFolder = new File(IoConstants.SAVE_DIR);
    ObjectMapper mapper = new ObjectMapper();

    try {
      // Try to create the save directory
      saveFolder.mkdir();
    }
    catch (Exception e) {
      System.out.printf("Unable to create directory '%s' at this time%n", IoConstants.SAVE_DIR);
    }

    String saveFileName = IoConstants.SAVE_DIR + "/" + String.format(CARD_LIST_SAVE_NAME, saveFolder.listFiles().length);

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFileName), StandardCharsets.UTF_8);) {

      // TODO - Make it so multiple copies of a card can be added to this map
      Map<String, Object> cardMap = cards.stream()
              .collect(Collectors.toMap(Card::getName, card -> card.getCardPojo()));

      String jsonCards = mapper.writerWithDefaultPrettyPrinter()
              .writeValueAsString(cardMap);

      writer.write(jsonCards);
    }
    catch (Exception e) {
      System.out.printf("Unable to save the card list at this time%n%s%n", e.getMessage());
      throw new IOException(e);
    }

    return saveFileName;
  }
}
