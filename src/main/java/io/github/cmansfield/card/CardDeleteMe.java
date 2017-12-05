package io.github.cmansfield.card;

import io.github.cmansfield.card.constants.CardConstants;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@JsonDeserialize(using = CardDeserializer.class)
public class CardDeleteMe {
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


  public CardDeleteMe() {}

  public void setName(String name) {
    this.name = name;
  }

  public void setNames(List<String> names) {
    this.names = names;
  }

  public void setLayout(String layout) {
    this.layout = layout;
  }

  public void setManaCost(String manaCost) {
    this.manaCost = manaCost;
  }

  public void setCmc(Double cmc) {
    this.cmc = cmc;
  }

  public void setColors(List<String> colors) {
    this.colors = colors;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setSupertypes(List<String> supertypes) {
    this.supertypes = supertypes;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

  public void setSubtypes(List<String> subtypes) {
    this.subtypes = subtypes;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setPower(String power) {
    this.power = power;
  }

  public void setToughness(String toughness) {
    this.toughness = toughness;
  }

  public void setLoyalty(Integer loyalty) {
    this.loyalty = loyalty;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public void setRulings(List<Map<String, String>> rulings) {
    this.rulings = rulings;
  }

  public void setHand(Integer hand) {
    this.hand = hand;
  }

  public void setLife(Integer life) {
    this.life = life;
  }

  public void setStarter(Boolean starter) {
    this.starter = starter;
  }

  public void setPrintings(List<String> printings) {
    this.printings = printings;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public void setLegalities(List<Map<String, String>> legalities) {
    this.legalities = legalities;
  }

  public void setColorIdentity(List<String> colorIdentity) {
    this.colorIdentity = colorIdentity;
  }

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


    public CardBuilder() {}

    public CardDeleteMe.CardBuilder name(String key) {
      this.name = key;
      return this;
    }

    public CardDeleteMe.CardBuilder layout(String layout) {
      this.layout = layout;
      return this;
    }

    public CardDeleteMe.CardBuilder names(List<String> names) {
      this.names = names;
      return this;
    }

    public CardDeleteMe.CardBuilder manaCost(String manaCost) {
      this.manaCost = manaCost;
      return this;
    }

    public CardDeleteMe.CardBuilder cmc(Double cmc) {
      this.cmc = cmc;
      return this;
    }

    public CardDeleteMe.CardBuilder colors(List<String> colors) {
      this.colors = colors;
      return this;
    }

    public CardDeleteMe.CardBuilder type(String type) {
      this.type = type;
      return this;
    }

    public CardDeleteMe.CardBuilder superTypes(List<String> superTypes) {
      this.supertypes = superTypes;
      return this;
    }

    public CardDeleteMe.CardBuilder types(List<String > types) {
      this.types = types;
      return this;
    }

    public CardDeleteMe.CardBuilder subTypes(List<String> subTypes) {
      this.subtypes = subTypes;
      return this;
    }

    public CardDeleteMe.CardBuilder text(String text) {
      this.text = text;
      return this;
    }

    public CardDeleteMe.CardBuilder power(String power) {
      this.power = power;
      return this;
    }

    public CardDeleteMe.CardBuilder toughness(String toughness) {
      this.toughness = toughness;
      return this;
    }

    public CardDeleteMe.CardBuilder loyalty(Integer loyalty) {
      this.loyalty = loyalty;
      return this;
    }

    public CardDeleteMe.CardBuilder imageName(String imageName) {
      this.imageName = imageName;
      return this;
    }

    public CardDeleteMe.CardBuilder rulings(List<Map<String,String>> rulings) {
      this.rulings = rulings;
      return this;
    }

    public CardDeleteMe.CardBuilder hand(Integer hand) {
      this.hand = hand;
      return this;
    }

    public CardDeleteMe.CardBuilder life(Integer life) {
      this.life = life;
      return this;
    }

    public CardDeleteMe.CardBuilder starter(Boolean starter) {
      this.starter = starter;
      return this;
    }

    public CardDeleteMe.CardBuilder printings(List<String> printings) {
      this.printings = printings;
      return this;
    }

    public CardDeleteMe.CardBuilder source(String source) {
      this.source = source;
      return this;
    }

    public CardDeleteMe.CardBuilder legalities(List<Map<String,String>> legalities) {
      this.legalities = legalities;
      return this;
    }

    public CardDeleteMe.CardBuilder colorIdentity(List<String> colorIdentity) {
      this.colorIdentity = colorIdentity;
      return this;
    }

    public CardDeleteMe build() {
      CardDeleteMe card = new CardDeleteMe();

      card.setName(this.name);
      card.setNames(this.names);
      card.setLayout(this.layout);
      card.setManaCost(this.manaCost);


      this.cardcmc;
      this.cardcolors;
      this.cardtype;
      this.cardsupertypes;
      this.cardtypes;
      this.cardsubtypes;
      this.cardtext;
      this.cardpower;
      this.cardtoughness;
      this.cardloyalty;
      this.cardimageName;
      this.cardrulings;
      this.cardhand;
      this.cardlife;
      this.cardstarter;
      this.cardprintings;
      this.cardsource;
      this.cardlegalities;
      this.cardcolorIdentity;

      return card;
    }
  }
}
