package com.amitesh.payload;

import java.util.Objects;
import java.util.StringJoiner;

public class SmsMessage {

  private String fromNum;
  private String toNum;
  private String message;

  public SmsMessage() {
  }

  public SmsMessage(String fromNum, String toNum, String message) {
    this.fromNum = fromNum;
    this.toNum = toNum;
    this.message = message;
  }

  public String getFromNum() {
    return fromNum;
  }

  public void setFromNum(String fromNum) {
    this.fromNum = fromNum;
  }

  public String getToNum() {
    return toNum;
  }

  public void setToNum(String toNum) {
    this.toNum = toNum;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SmsMessage that = (SmsMessage) o;
    return Objects.equals(fromNum, that.fromNum) && Objects.equals(toNum,
        that.toNum) && Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromNum, toNum, message);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SmsMessage.class.getSimpleName() + "[", "]")
        .add("fromNum='" + fromNum + "'")
        .add("toNum='" + toNum + "'")
        .add("message='" + message + "'")
        .toString();
  }
}
