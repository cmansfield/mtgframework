package io.github.cmansfield.card;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonDeserialize(using = CardDeserializer.class)
public class Card {
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
  public Integer loyalty;
  public String imageName;
  public List<Map<String,String>> rulings;
  public Integer hand;
  public Integer life;
  public Boolean starter;
  public List<String> printings;
  public String source;
  public List<Map<String,String>> legalities;
  public List<String> colorIdentity;


  public Card() {}

  public Card(Card card) {
    this.name = card.getName();
    this.names = card.getNames();
    this.layout = card.getLayout();
    this.manaCost = card.getManaCost();
    this.cmc = card.getCmc();
    this.colors = card.getColors();
    this.type = card.getType();
    this.supertypes = card.getSuperTypes();
    this.types = card.getTypes();
    this.subtypes = card.getSubTypes();
    this.text = card.getText();
    this.power = card.getPower();
    this.toughness = card.getToughness();
    this.loyalty = card.getLoyalty();
    this.imageName = card.getImageName();
    this.rulings = card.getRulings();
    this.hand = card.getHand();
    this.life = card.getLife();
    this.starter = card.getStarter();
    this.printings = card.getPrintings();
    this.source = card.getSource();
    this.legalities = card.getLegalities();
    this.colorIdentity = card.getColorIdentity();
  }

//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public void setNames(List<String> names) {
//    this.names = names;
//  }
//
//  public void setLayout(String layout) {
//    this.layout = layout;
//  }
//
//  public void setManaCost(String manaCost) {
//    this.manaCost = manaCost;
//  }
//
//  public void setCmc(Double cmc) {
//    this.cmc = cmc;
//  }
//
//  public void setColors(List<String> colors) {
//    this.colors = colors;
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }
//
//  public void setSupertypes(List<String> supertypes) {
//    this.supertypes = supertypes;
//  }
//
//  public void setTypes(List<String> types) {
//    this.types = types;
//  }
//
//  public void setSubtypes(List<String> subtypes) {
//    this.subtypes = subtypes;
//  }
//
//  public void setText(String text) {
//    this.text = text;
//  }
//
//  public void setPower(String power) {
//    this.power = power;
//  }
//
//  public void setToughness(String toughness) {
//    this.toughness = toughness;
//  }
//
//  public void setLoyalty(Integer loyalty) {
//    this.loyalty = loyalty;
//  }
//
//  public void setImageName(String imageName) {
//    this.imageName = imageName;
//  }
//
//  public void setRulings(List<Map<String, String>> rulings) {
//    this.rulings = rulings;
//  }
//
//  public void setHand(Integer hand) {
//    this.hand = hand;
//  }
//
//  public void setLife(Integer life) {
//    this.life = life;
//  }
//
//  public void setStarter(Boolean starter) {
//    this.starter = starter;
//  }
//
//  public void setPrintings(List<String> printings) {
//    this.printings = printings;
//  }
//
//  public void setSource(String source) {
//    this.source = source;
//  }
//
//  public void setLegalities(List<Map<String, String>> legalities) {
//    this.legalities = legalities;
//  }
//
//  public void setColorIdentity(List<String> colorIdentity) {
//    this.colorIdentity = colorIdentity;
//  }

  public String getName() {
    return this.name;
  }

  public List<String> getNames() {
    return new ArrayList<>(this.names);
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
    return new ArrayList<>(this.colors);
  }

  public String getType() {
    return this.type;
  }

  public List<String> getSuperTypes() {
    return new ArrayList<>(this.supertypes);
  }

  public List<String> getTypes() {
    return new ArrayList<>(this.types);
  }

  public List<String> getSubTypes() {
    return new ArrayList<>(this.subtypes);
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

  // TODO - Deep copy this
  public List<Map<String,String>> getRulings() {
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
    return new ArrayList<>(this.printings);
  }

  public String getSource() {
    return this.source;
  }

  // TODO - Deep copy this
  public List<Map<String,String>> getLegalities() {
    return this.legalities;
  }

  public List<String> getColorIdentity() {
    return new ArrayList<>(this.colorIdentity);
  }

  // TODO - finish this method
  @Override
  public String toString() {
    return super.toString();
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


    public CardBuilder() {}

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
