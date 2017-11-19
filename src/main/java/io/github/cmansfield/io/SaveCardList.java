package io.github.cmansfield.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cmansfield.card.Card;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class SaveCardList {
  private SaveCardList() {}

  public static String saveCards(List<Card> cards) throws IOException {
    final String CARD_LIST_SAVE_NAME = "CardList%d.json";
    File saveFolder = new File(IoConstants.SAVE_DIR);
    ObjectMapper mapper = new ObjectMapper();


    String saveFileName = IoConstants.SAVE_DIR + "/" + String.format(CARD_LIST_SAVE_NAME, saveFolder.listFiles().length);

    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFileName), StandardCharsets.UTF_8);) {
      // Try to create the save directory
      saveFolder.mkdir();

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