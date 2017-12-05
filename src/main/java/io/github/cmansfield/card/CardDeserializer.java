package io.github.cmansfield.card;

import org.codehaus.jackson.map.deser.std.StdDeserializer;
import io.github.cmansfield.card.constants.CardConstants;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonNode;

import java.io.IOException;


public class CardDeserializer extends StdDeserializer<CardDeleteMe>{

  public CardDeserializer() {
    this(null);
  }

  public CardDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public CardDeleteMe deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    CardDeleteMe card = new CardDeleteMe();
    JsonNode productNode = jp.getCodec().readTree(jp);
    card.setName(productNode.get(CardConstants.NAME.toString()).getTextValue());
    return card;
  }
}
