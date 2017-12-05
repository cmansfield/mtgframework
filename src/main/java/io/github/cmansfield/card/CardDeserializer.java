package io.github.cmansfield.card;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.cmansfield.card.constants.CardConstants;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;


public class CardDeserializer extends JsonDeserializer<CardDeleteMe> {

  public CardDeserializer() {}

  @Override
  public CardDeleteMe deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    CardDeleteMe card = new CardDeleteMe();
    JsonNode node = jp.getCodec().readTree(jp);

//    card.setName(node.get(CardConstants.NAME.toString()).asText());
    card.setName("Test");
    return card;
  }
}
