package server;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DJ on 09/02/2017.
 */
public class Transaction implements Serializable {

    // So each transaction that happens needs a date that it was made, so initialize a date object for every one
    // now we need to be able to sort the transaction on the date

    private Date dateCreated;
    private String type;
    private int accNum;

    public Transaction(int acc, String type){

        this.accNum = acc;
        this.type = type;
        this.dateCreated = new Date();
    }

    public Date getDatecreated() {
        return dateCreated;
    }

    public String getType() {
        return type;
    }

    public int getAccNum() {
        return accNum;
    }

    public String toString(){
        return "Account number : " + getAccNum() + " | Transaction :" + getType() +
                "Date Created " + dateCreated.toString() ;
    }






}
