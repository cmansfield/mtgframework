package io.github.cmansfield.set;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.github.cmansfield.set.constants.SetConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonParser;

import java.util.function.BiFunction;
import java.io.IOException;
import java.util.*;


public class SetDeserializer extends JsonDeserializer<MtgSet> {
  private static Map<String,BiFunction<MtgSet.MtgSetBuilder, Object, MtgSet.MtgSetBuilder>> SET_METHODS = new HashMap<>();   // NOSONAR
  static {
    SET_METHODS.put(SetConstants.NAME.toString(), (cb, value) -> cb.name((String)value));
    SET_METHODS.put(SetConstants.CODE.toString(), (cb, value) -> cb.code((String)value));
    SET_METHODS.put(SetConstants.INFO_CODE.toString(), (cb, value) -> cb.infoCode((String)value));
    SET_METHODS.put(SetConstants.RELEASE_DATE.toString(), (cb, value) -> cb.releaseDate((String)value));
    SET_METHODS.put(SetConstants.BOARDER.toString(), (cb, value) -> cb.boarder((String)value));
    SET_METHODS.put(SetConstants.TYPE.toString(), (cb, value) -> cb.type((String)value));
    SET_METHODS.put(SetConstants.MKM_NAME.toString(), (cb, value) -> cb.mkmName((String)value));
    SET_METHODS.put(SetConstants.MKM_ID.toString(), (cb, value) -> cb.mkmId((String)value));
    SET_METHODS.put(SetConstants.CARDS.toString(), (cb, value) -> cb.cards((List<Map<String,Object>>)value));
    SET_METHODS.put(SetConstants.BLOCK.toString(), (cb, value) -> cb.block((String)value));
  }

  @Override
  public MtgSet deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    MtgSet.MtgSetBuilder mtgSetBuilder = new MtgSet.MtgSetBuilder();
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode node = jp.getCodec().readTree(jp);

    Iterator<String> fieldNames = node.fieldNames();
    while(fieldNames.hasNext()) {
      String field = fieldNames.next();

      if(!SET_METHODS.containsKey(field)) {
        continue;
      }

      JsonNode childNode = node.get(field);

      if(childNode.getNodeType().toString().equals(JsonNodeType.ARRAY.toString())) {
        mtgSetBuilder = SET_METHODS.get(field).apply(mtgSetBuilder, objectMapper.convertValue(childNode, List.class));
      }
      else if(childNode.getNodeType().toString().equals(JsonNodeType.STRING.toString())) {
        mtgSetBuilder = SET_METHODS.get(field).apply(mtgSetBuilder, childNode.asText());
      }
    }

    return mtgSetBuilder.build();
  }
}
