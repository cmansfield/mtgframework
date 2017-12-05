package io.github.cmansfield.card;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import io.github.cmansfield.card.constants.CardConstants;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;


public class CardDeserializer extends JsonDeserializer<CardDeleteMe> {

  private static Map<String,BiFunction> CARD_METHODS = new HashMap<String,BiFunction>();
  static {
    CARD_METHODS.put("layout", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("name", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("manaCost", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("cmc", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("type", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("types", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("text", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("imageName", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("printings", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("legalities", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("layout", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("name", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("manaCost", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("cmc", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("type", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("types", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("subtypes", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("text", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("power", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("toughness", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("imageName", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("printings", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("legalities", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("layout", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("name", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("manaCost", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("cmc", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("type", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("types", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("subtypes", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("text", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("power", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("toughness", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("imageName", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("printings", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
    CARD_METHODS.put("legalities", (cb, str) -> { return ((CardDeleteMe.CardBuilder)cb).layout((String)str); });
  }


  public CardDeserializer() {}

  @Override
  public CardDeleteMe deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    CardDeleteMe card = new CardDeleteMe();
    JsonNode node = jp.getCodec().readTree(jp);

    Iterator<String> fieldNames = node.fieldNames();
    while(fieldNames.hasNext()) {
      String field = fieldNames.next();
      JsonNode childNode = node.get(field);
      System.out.println(field);

//      if(childNode.getNodeType().toString().equals(JsonNodeType.STRING.toString())) {
//        System.out.print("Found a String!");
//      }
    }
//    Map map = objectMapper.convertValue(node, Map.class);

//    card.setName(node.get(CardConstants.NAME.toString()).asText());
    card.setName("Test");
    return card;
  }
}
