package com.kowsercse.experiment.unicode;

public class ছাত্র {
  private String নাম;
  private String শ্রেণী;

  public ছাত্র() {
  }

  public ছাত্র(String নাম, String শ্রেণী) {
    this.নাম = নাম;
    this.শ্রেণী = শ্রেণী;
  }

  public String getনাম() {
    return নাম;
  }

  public void setনাম(String নাম) {
    this.নাম = নাম;
  }

  public String getশ্রেণী() {
    return শ্রেণী;
  }

  public void setশ্রেণী(String শ্রেণী) {
    this.শ্রেণী = শ্রেণী;
  }

  @Override
  public String toString() {
    return "ছাত্র [নাম=" + নাম + ", শ্রেণী=" + শ্রেণী + "]";
  }
}
