package interfaces;

import java.io.Serializable;
import java.util.*;

public interface StatementInterface extends Serializable {

	public int getAccountNum();  // returns account number associated with this statement

	public Date getStartDate(); // returns start Date of Statement

	public Date getEndDate(); // returns end Date of Statement

	public String getAccountName(); // returns name of account holder

	public List  getTransactions(); // returns list of Transaction objects that encapsulate details about each transaction

}