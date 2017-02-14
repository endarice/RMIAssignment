package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import interfaces.StatementInterface;

public class Statement implements StatementInterface, Serializable {

	/*This is for generating a statement on specific Account*/

	private Account account;
	private Date start;
	private Date end;
	private List<Transaction> theTransactions;

	public Statement (Account acc, Date from, Date to) {

		this.account = acc;
		this.start = from;
		this.end = to;
		this.theTransactions = new ArrayList<>();
		sortTransactions();
	}
	
	@Override
	public int getAccountNum() {
		return account.getAccountNum();
	}

	public Account getAccount(){
		return this.getAccount();
	}

	@Override
	public Date getStartDate() {
		return start;
	}

	@Override
	public Date getEndDate() {
		return end;
	}

	@Override
	public String getAccountName() {
		return account.getUsername();
	}

	@Override
	public List<Transaction> getTransactions() {
		return theTransactions;
	}

	public void sortTransactions(){
		for (Transaction t: getAccount().getTransactions()){

			if(!t.getDatecreated().before(getStartDate()) && !t.getDatecreated().after(getEndDate()) ){
				theTransactions.add(t);
			}
		}
	}



	public String toString(){

        StringBuilder sb = new StringBuilder();
        for (Transaction t : theTransactions){
            sb.append(t.toString());
        }

        return sb.toString();

    }
}
