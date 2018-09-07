package com.parse.starter;

public class Borrower {
    private String loan_amt , interst_rate , email_id , type_of_loan;

    public Borrower(String loan_amt, String interst_rate, String email_id, String type_of_loan) {
        this.loan_amt = loan_amt;
        this.interst_rate = interst_rate;
        this.email_id = email_id;
        this.type_of_loan = type_of_loan;
    }

    public String getLoan_amt() {
        return loan_amt;
    }

    public String getInterst_rate() {
        return interst_rate;
    }

    public String getEmail_id() {
        return email_id;
    }

    public String getType_of_loan() {
        return type_of_loan;
    }
}

