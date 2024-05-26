package com.amitesh.payload;

import java.util.Objects;
import java.util.StringJoiner;

public class EmailMessage {

  private String fromEmail;
  private String toEmail;
  private String subject;
  private String body;

  public EmailMessage() {
  }

  public EmailMessage(String fromEmail, String toEmail, String subject, String body) {
    this.fromEmail = fromEmail;
    this.toEmail = toEmail;
    this.subject = subject;
    this.body = body;
  }

  public String getFromEmail() {
    return fromEmail;
  }

  public void setFromEmail(String fromEmail) {
    this.fromEmail = fromEmail;
  }

  public String getToEmail() {
    return toEmail;
  }

  public void setToEmail(String toEmail) {
    this.toEmail = toEmail;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailMessage that = (EmailMessage) o;
    return Objects.equals(fromEmail, that.fromEmail) && Objects.equals(toEmail,
        that.toEmail) && Objects.equals(subject, that.subject) && Objects.equals(
        body, that.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromEmail, toEmail, subject, body);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", EmailMessage.class.getSimpleName() + "[", "]")
        .add("fromEmail='" + fromEmail + "'")
        .add("toEmail='" + toEmail + "'")
        .add("subject='" + subject + "'")
        .add("body='" + body + "'")
        .toString();
  }
}
