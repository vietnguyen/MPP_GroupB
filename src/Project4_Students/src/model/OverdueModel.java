package model;

import business.BookCopy;
import business.CheckoutRecordEntry;
import business.LibraryMember;

public class OverdueModel {
  private LibraryMember member;
  private BookCopy bookCopy;
  private CheckoutRecordEntry checkoutRecordEntry;
  
  public OverdueModel(LibraryMember member, BookCopy bookCopy, CheckoutRecordEntry checkoutRecordEntry) {
    this.member = member;
    this.bookCopy = bookCopy;
    this.checkoutRecordEntry = checkoutRecordEntry;
  }
  
  public void setMember(LibraryMember member) {
    this.member = member;
  }
  
  public void setBookCopy(BookCopy bookCopy) {
    this.bookCopy = bookCopy;
  }
  
  public void setCheckoutRecordEntry(CheckoutRecordEntry checkoutRecordEntry) {
    this.checkoutRecordEntry = checkoutRecordEntry;
  }
  
  public LibraryMember getMember() {
    return member;
  }
  
  public BookCopy getBookCopy() {
    return bookCopy;
  }
  
  public CheckoutRecordEntry getCheckoutRecordEntry() {
    return checkoutRecordEntry;
  }
}
