package io.github.cmansfield.card;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.cmansfield.card.constants.CardConstants;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonParser;

import java.util.function.BiFunction;
import java.io.IOException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CardDeserializer extends JsonDeserializer<CardDeleteMe> {

  private static Map<String,BiFunction> CARD_METHODS = new HashMap<>();
  static {
    CARD_METHODS.put(CardConstants.LAYOUT.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).layout((String)value));
    CARD_METHODS.put(CardConstants.NAME.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).name((String)value));
    CARD_METHODS.put(CardConstants.NAMES.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).names((List)value));
    CARD_METHODS.put(CardConstants.MANA_COST.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).manaCost((String)value));
    CARD_METHODS.put(CardConstants.CMC.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).cmc((Double)value));
    CARD_METHODS.put(CardConstants.COLORS.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).colors((List)value));
    CARD_METHODS.put(CardConstants.TYPE.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).type((String)value));
    CARD_METHODS.put(CardConstants.SUPER_TYPES.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).superTypes((List)value));
    CARD_METHODS.put(CardConstants.TYPES.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).types((List)value));
    CARD_METHODS.put(CardConstants.SUB_TYPES.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).subTypes((List)value));
    CARD_METHODS.put(CardConstants.TEXT.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).text((String)value));
    CARD_METHODS.put(CardConstants.POWER.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).power((String)value));
    CARD_METHODS.put(CardConstants.TOUGHNESS.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).toughness((String)value));
    CARD_METHODS.put(CardConstants.LOYALTY.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).loyalty(((Double)value).intValue()));
    CARD_METHODS.put(CardConstants.IMAGE_NAME.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).imageName((String)value));
    CARD_METHODS.put(CardConstants.RULINGS.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).rulings((List)value));
    CARD_METHODS.put(CardConstants.HAND.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).hand(((Double)value).intValue()));
    CARD_METHODS.put(CardConstants.LIFE.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).life(((Double)value).intValue()));
    CARD_METHODS.put(CardConstants.STARTER.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).starter((Boolean) value));
    CARD_METHODS.put(CardConstants.PRINTINGS.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).printings((List)value));
    CARD_METHODS.put(CardConstants.SOURCE.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).source((String)value));
    CARD_METHODS.put(CardConstants.LEGALITIES.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).legalities((List)value));
    CARD_METHODS.put(CardConstants.COLOR_IDENTITY.toString(), (cb, value) -> ((CardDeleteMe.CardBuilder)cb).colorIdentity((List)value));
  }


  @Override
  public CardDeleteMe deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    CardDeleteMe.CardBuilder cardBuilder = new CardDeleteMe.CardBuilder();
    JsonNode node = jp.getCodec().readTree(jp);

    Iterator<String> fieldNames = node.fieldNames();
    while(fieldNames.hasNext()) {
      String field = fieldNames.next();

      if(!CARD_METHODS.containsKey(field)) {
        throw new IllegalStateException(String.format("Not familiar with field name: %s", field));
      }

      JsonNode childNode = node.get(field);

      if(childNode.getNodeType().toString().equals(JsonNodeType.ARRAY.toString())) {
        cardBuilder = (CardDeleteMe.CardBuilder)CARD_METHODS.get(field).apply(cardBuilder, objectMapper.convertValue(childNode, List.class));
      }
      else if(childNode.getNodeType().toString().equals(JsonNodeType.NUMBER.toString())) {
        cardBuilder = (CardDeleteMe.CardBuilder)CARD_METHODS.get(field).apply(cardBuilder, objectMapper.convertValue(childNode, Double.class));
      }
      else if(childNode.getNodeType().toString().equals(JsonNodeType.BOOLEAN.toString())) {
        cardBuilder = (CardDeleteMe.CardBuilder)CARD_METHODS.get(field).apply(cardBuilder, objectMapper.convertValue(childNode, Boolean.class));
      }
      else if(childNode.getNodeType().toString().equals(JsonNodeType.STRING.toString())) {
        cardBuilder = (CardDeleteMe.CardBuilder)CARD_METHODS.get(field).apply(cardBuilder, childNode.asText());
      }
    }

    return cardBuilder.build();
  }
}
