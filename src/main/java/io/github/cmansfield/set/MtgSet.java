package io.github.cmansfield.set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@JsonDeserialize(using = SetDeserializer.class)
public class MtgSet {
  private String name;
  private String code;
  private String infoCode;
  private String releaseDate;
  private String boarder;
  private String type;
  private String mkmName;
  private String mkmId;
  private List<Map<String,Object>> cards;
  private String block;


  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getInfoCode() {
    return infoCode;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public String getBoarder() {
    return boarder;
  }

  public String getType() {
    return type;
  }

  public String getMkmName() {
    return mkmName;
  }

  public String getMkmId() {
    return mkmId;
  }

  public List<Map<String, Object>> getCards() {
    return new ArrayList<>(cards);
  }

  public String getBlock() {
    return block;
  }


  public static class MtgSetBuilder {
    private String name;
    private String code;
    private String infoCode;
    private String releaseDate;
    private String boarder;
    private String type;
    private String mkmName;
    private String mkmId;
    private List<Map<String,Object>> cards;
    private String block;

    public MtgSet.MtgSetBuilder name(String name) {
      this.name = name;
      return this;
    }

    public MtgSet.MtgSetBuilder code(String code) {
      this.code = code;
      return this;
    }

    public MtgSet.MtgSetBuilder infoCode(String infoCode) {
      this.infoCode = infoCode;
      return this;
    }

    public MtgSet.MtgSetBuilder releaseDate(String releaseDate) {
      this.releaseDate = releaseDate;
      return this;
    }

    public MtgSet.MtgSetBuilder boarder(String boarder) {
      this.boarder = boarder;
      return this;
    }

    public MtgSet.MtgSetBuilder type(String type) {
      this.type = type;
      return this;
    }

    public MtgSet.MtgSetBuilder mkmName(String mkmName) {
      this.mkmName = mkmName;
      return this;
    }

    public MtgSet.MtgSetBuilder mkmId(String mkmId) {
      this.mkmId = mkmId;
      return this;
    }

    public MtgSet.MtgSetBuilder cards(List<Map<String,Object>> cards) {
      this.cards = cards;
      return this;
    }

    public MtgSet.MtgSetBuilder block(String block) {
      this.block = block;
      return this;
    }

    public MtgSet build() {
      MtgSet mtgSet = new MtgSet();
      mtgSet.name = this.name;
      mtgSet.code = this.code;
      mtgSet.infoCode = this.infoCode;
      mtgSet.releaseDate = this.releaseDate;
      mtgSet.boarder = this.boarder;
      mtgSet.type = this.type;
      mtgSet.mkmName = this.mkmName;
      mtgSet.mkmId = this.mkmId;
      mtgSet.cards = this.cards;
      mtgSet.block = this.block;

      return mtgSet;
    }
  }
}
