package io.github.cmansfield.Card;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.List;


public class Card implements Serializable {
  private String layout;
  private String name;
  private String manaCost;
  private int cmc;
  private List<String> colors;
  private String type;
  private List<String> types;
  private List<String> subTypes;
  private String text;
  private String power;
  private String toughness;
  private String imageName;
  private List<String> colorIdentity;


  public Card() {}

  public void setName(String key) {
    this.name = key;
  }

  public void setLayout(String layout) {
    this.layout = layout;
  }

  public void setManaCost(String manaCost) {
    this.manaCost = manaCost;
  }

  public void setCmc(int cmc) {
    this.cmc = cmc;
  }

  public void setColors(List<String> colors) {
    this.colors = colors;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

  public void setSubTypes(List<String> subTypes) {
    this.subTypes = subTypes;
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

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public void setColorIdentity(List<String> colorIdentity) {
    this.colorIdentity = colorIdentity;
  }

  public String getName() {
    return this.name;
  }

  public String getLayout() {
    return this.layout;
  }

  public String getManaCost() {
    return this.manaCost;
  }

  public int getCmc() {
    return this.cmc;
  }

  public List<String> getColors() {
    return this.colors;
  }

  public String getType() {
    return this.type;
  }

  public List<String> getTypes() {
    return this.types;
  }

  public List<String> getSubTypes() {
    return this.subTypes;
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

  public String getImageName() {
    return this.imageName;
  }

  public List<String> getColorIdentity() {
    return this.colorIdentity;
  }
}
