package io.github.cmansfield.card;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.stream.Collectors;
import java.util.*;


@JsonDeserialize(using = CardDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Card {
  private static final Logger LOGGER = LoggerFactory.getLogger(Card.class);

  private String name;
  private List<String> names;
  private String layout;
  private String manaCost;
  private Double cmc;
  private List<String> colors;
  private String type;
  private List<String> supertypes;
  private List<String> types;
  private List<String> subtypes;
  private String text;
  private String power;
  private String toughness;
  private Integer loyalty;
  private String imageName;
  private List<Map<String,String>> rulings;
  private Integer hand;
  private Integer life;
  private Boolean starter;
  private List<String> printings;
  private String source;
  private List<Map<String,String>> legalities;
  private List<String> colorIdentity;


  public Card() {}

  public Card(Card card) {
    this.name = card.getName();
    this.names = new ArrayList<>(card.getNames());
    this.layout = card.getLayout();
    this.manaCost = card.getManaCost();
    this.cmc = card.getCmc();
    this.colors = new ArrayList<>(card.getColors());
    this.type = card.getType();
    this.supertypes = new ArrayList<>(card.getSupertypes());
    this.types = new ArrayList<>(card.getTypes());
    this.subtypes = new ArrayList<>(card.getSubtypes());
    this.text = card.getText();
    this.power = card.getPower();
    this.toughness = card.getToughness();
    this.loyalty = card.getLoyalty();
    this.imageName = card.getImageName();
    this.rulings = card.getRulings().stream().map(HashMap::new).collect(Collectors.toList());
    this.hand = card.getHand();
    this.life = card.getLife();
    this.starter = card.getStarter();
    this.printings = new ArrayList<>(card.getPrintings());
    this.source = card.getSource();
    this.legalities = card.getLegalities().stream().map(HashMap::new).collect(Collectors.toList());
    this.colorIdentity = new ArrayList<>(card.getColorIdentity());
  }

  public String getName() {
    return this.name;
  }

  public List<String> getNames() {
    if(this.names == null) {
      return Collections.emptyList();
    }
    return this.names;
  }

  public String getLayout() {
    return this.layout;
  }

  public String getManaCost() {
    return manaCost;
  }

  public Double getCmc() {
    return this.cmc;
  }

  public List<String> getColors() {
    if(this.colors == null) {
      return Collections.emptyList();
    }
    return this.colors;
  }

  public String getType() {
    return this.type;
  }

  public List<String> getSupertypes() {
    if(this.supertypes == null) {
      return Collections.emptyList();
    }
    return this.supertypes;
  }

  public List<String> getTypes() {
    if(this.types == null) {
      return Collections.emptyList();
    }
    return this.types;
  }

  public List<String> getSubtypes() {
    if(this.subtypes == null) {
      return Collections.emptyList();
    }
    return this.subtypes;
  }

  public String getText() {
    return this.text;
  }

  public String getPower() {
    return this.power;
  }

  public String getToughness() {
    return this.toughness;
  }

  public Integer getLoyalty() {
    return this.loyalty;
  }

  public String getImageName() {
    return imageName;
  }

  public List<Map<String,String>> getRulings() {
    if(this.rulings == null) {
      return Collections.emptyList();
    }
    return this.rulings;
  }

  public Integer getHand() {
    return this.hand;
  }

  public Integer getLife() {
    return this.life;
  }

  public Boolean getStarter() {
    return this.starter;
  }

  public List<String> getPrintings() {
    if(this.printings == null) {
      return Collections.emptyList();
    }
    return this.printings;
  }

  public String getSource() {
    return this.source;
  }

  public List<Map<String,String>> getLegalities() {
    if(this.legalities == null) {
      return Collections.emptyList();
    }
    return this.legalities;
  }

  public List<String> getColorIdentity() {
    if(this.colorIdentity == null) {
      return Collections.emptyList();
    }
    return this.colorIdentity;
  }

  /**
   * This method will compare the two supplied object against each other, would use
   * equals by itself but there's a chance the objects could be null. 
   * 
   * @param lhs     Left hand side object
   * @param rhs     Right hand side object
   * @return        Boolean true if the two objects are equal
   */
  private <T> boolean compare(T lhs, T rhs) {
    if(lhs == null && rhs == null) {
      return true;
    }
    if(lhs == null) {
      return false;
    }
    return lhs.equals(rhs);
  }

  @Override
  public boolean equals(Object obj) {       // NOSONAR
    if(obj == null) {
      return false;
    }
    if(this == obj) {
      return true;
    }
    if(!(obj instanceof Card)) {
      return false;
    }
    
    Card card = (Card)obj;
    if(!compare(name, card.name)) {
      return false;  
    }
    if(!compare(layout, card.layout)) {
      return false;
    }
    if(!compare(manaCost, card.manaCost)) {
      return false;
    }
    if(!compare(type, card.type)) {
      return false;
    }
    if(!compare(text, card.text)) {
      return false;
    }
    if(!compare(power, card.power)) {
      return false;
    }
    if(!compare(toughness, card.toughness)) {
      return false;
    }
    if(!compare(imageName, card.imageName)) {
      return false;
    }
    if(!compare(source, card.source)) {
      return false;
    }
    if(!compare(cmc, card.cmc)) {
      return false;
    }
    if(!compare(hand, card.hand)) {
      return false;
    }
    if(!compare(life, card.life)) {
      return false;
    }
    if(!compare(loyalty, card.loyalty)) {
      return false;
    }
    if(!compare(starter, card.starter)) {
      return false;
    }
    if(!compare(names, card.names)) {
      return false;
    }
    if(!compare(types, card.types)) {
      return false;
    }
    if(!compare(colors, card.colors)) {
      return false;
    }
    if(!compare(subtypes, card.subtypes)) {
      return false;
    }
    if(!compare(printings, card.printings)) {
      return false;
    }
    if(!compare(supertypes, card.supertypes)) {
      return false;
    }
    if(!compare(colorIdentity, card.colorIdentity)) {
      return false;
    }
    if(!compare(rulings, card.rulings)) {
      return false;
    }
    return compare(starter, card.starter);
  }
  
  @Override
  public int hashCode() {
    // Not including the symbolId in this check
    return new HashCodeBuilder(17, 37)
            .append(name)
            .append(names.toString())
            .append(layout)
            .append(manaCost)
            .append(cmc)
            .append(colors.toString())
            .append(type)
            .append(supertypes.toString())
            .append(types.toString())
            .append(subtypes.toString())
            .append(text)
            .append(power)
            .append(toughness)
            .append(loyalty)
            .append(imageName)
            .append(rulings.toString())
            .append(hand)
            .append(life)
            .append(starter)
            .append(printings.toString())
            .append(source)
            .append(legalities.toString())
            .append(colorIdentity.toString())
            .toHashCode();
  }
  
  @Override
  public String toString() {
    return Arrays.stream(Card.class.getDeclaredFields())
            .filter(field -> {
              boolean isNull = false;
              try {
                isNull = field.get(this) != null;
              }
              catch (Exception e) {
                LOGGER.warn("Unable to get Card field");
              }
              return isNull;})
            .map(field -> {
              String fieldValue = "";
              try {
                fieldValue = String.format("%s: %s", field.getName(), field.get(this).toString());
              }
              catch (Exception e) {
                LOGGER.warn("Unable to get Card field");
              }
              return fieldValue;})
            .collect(Collectors.joining("\n"));
  }


  public static class CardBuilder {
    private String name;
    private List<String> names;
    private String layout;
    private String manaCost;
    private Double cmc;
    private List<String> colors;
    private String type;
    private List<String> supertypes;
    private List<String> types;
    private List<String> subtypes;
    private String text;
    private String power;
    private String toughness;
    private Integer loyalty;
    private String imageName;
    private List<Map<String,String>> rulings;
    private Integer hand;
    private Integer life;
    private Boolean starter;
    private List<String> printings;
    private String source;
    private List<Map<String,String>> legalities;
    private List<String> colorIdentity;

    public CardBuilder() {
      // Empty constructor for builder pattern
    }

    public Card.CardBuilder name(String key) {
      this.name = key;
      return this;
    }

    public Card.CardBuilder layout(String layout) {
      this.layout = layout;
      return this;
    }

    public Card.CardBuilder names(List<String> names) {
      this.names = names;
      return this;
    }

    public Card.CardBuilder manaCost(String manaCost) {
      this.manaCost = manaCost;
      return this;
    }

    public Card.CardBuilder cmc(Double cmc) {
      this.cmc = cmc;
      return this;
    }

    public Card.CardBuilder colors(List<String> colors) {
      this.colors = colors;
      return this;
    }

    public Card.CardBuilder type(String type) {
      this.type = type;
      return this;
    }

    public Card.CardBuilder superTypes(List<String> superTypes) {
      this.supertypes = superTypes;
      return this;
    }

    public Card.CardBuilder types(List<String > types) {
      this.types = types;
      return this;
    }

    public Card.CardBuilder subTypes(List<String> subTypes) {
      this.subtypes = subTypes;
      return this;
    }

    public Card.CardBuilder text(String text) {
      this.text = text;
      return this;
    }

    public Card.CardBuilder power(String power) {
      this.power = power;
      return this;
    }

    public Card.CardBuilder toughness(String toughness) {
      this.toughness = toughness;
      return this;
    }

    public Card.CardBuilder loyalty(Integer loyalty) {
      this.loyalty = loyalty;
      return this;
    }

    public Card.CardBuilder imageName(String imageName) {
      this.imageName = imageName;
      return this;
    }

    public Card.CardBuilder rulings(List<Map<String,String>> rulings) {
      this.rulings = rulings;
      return this;
    }

    public Card.CardBuilder hand(Integer hand) {
      this.hand = hand;
      return this;
    }

    public Card.CardBuilder life(Integer life) {
      this.life = life;
      return this;
    }

    public Card.CardBuilder starter(Boolean starter) {
      this.starter = starter;
      return this;
    }

    public Card.CardBuilder printings(List<String> printings) {
      this.printings = printings;
      return this;
    }

    public Card.CardBuilder source(String source) {
      this.source = source;
      return this;
    }

    public Card.CardBuilder legalities(List<Map<String,String>> legalities) {
      this.legalities = legalities;
      return this;
    }

    public Card.CardBuilder colorIdentity(List<String> colorIdentity) {
      this.colorIdentity = colorIdentity;
      return this;
    }

    public Card build() {
      Card card = new Card();

      card.name = this.name;
      card.names = this.names;
      card.layout = this.layout;
      card.manaCost = this.manaCost;
      card.cmc = this.cmc;
      card.colors = this.colors;
      card.type = this.type;
      card.supertypes = this.supertypes;
      card.types = this.types;
      card.subtypes = this.subtypes;
      card.text = this.text;
      card.power = this.power;
      card.toughness = this.toughness;
      card.loyalty = this.loyalty;
      card.imageName = this.imageName;
      card.rulings = this.rulings;
      card.hand = this.hand;
      card.life = this.life;
      card.starter = this.starter;
      card.printings = this.printings;
      card.source = this.source;
      card.legalities = this.legalities;
      card.colorIdentity = this.colorIdentity;

      return card;
    }
  }
}
