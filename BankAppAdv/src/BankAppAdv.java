import java.util.*;
import java.util.Map.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BankAppAdv {
	public static void main(String[] args) throws ParseException {
		Scanner in = new Scanner(System.in);
		int number;
		String name;
		int bal = 0;
		// Create a hashmap to store account info.
		HashMap<Integer, Account> map = new HashMap<Integer, Account>();
		HashMap<Integer, Transaction> map2 = new HashMap<Integer, Transaction>();

		// Adding accounts to Hashmap.
		int choice;
		System.out.println("Welcome to Evil Corp Savings and Loan");
		System.out.println("Please create the user account(s)");
		System.out
				.println("Enter an account # or 0 to stop entering accounts : ");
		choice = in.nextInt();
		while (choice != 0) {
			number = choice;
			System.out.println("Enter the name for account# " + number);
			name = in.next();
			System.out.println("Enter the balance for account# " + number);
			bal = in.nextInt();
			Account acct = new Account();
			acct.setAccount_num(number);
			acct.setAccount_name(name);
			acct.setAccount_bal(bal);
			map.put(number, acct);
			System.out
					.println("Enter another account # or 0 to stop entering accounts :");
			choice = in.nextInt();
		}
// Operating on accounts.1
		String type = null;
		String choice2;
		int number2;
		double amount = 0;
		int i = 0;
		System.out.println("Enter a transaction type (Check, Debit card, Deposit or Withdrawal) or q to finish :");
		choice2 = in.next();
		
		while (!choice2.equalsIgnoreCase("q")) {
			if (choice2.equalsIgnoreCase("c")) {
				type = "Check";
			} else if (choice2.equalsIgnoreCase("dc")) {
				type = "Debit Card";
			} else if (choice2.equalsIgnoreCase("d")) {
				type = "Deposit";
			} else if (choice2.equalsIgnoreCase("w")) {
				type = "Withdrawal";
			}
			//
			System.out.println("Enter the account # :");
			number2 = in.nextInt();
			//
			System.out.println("Enter the amount of the " + type + " :");
			amount = in.nextInt();
			//if (type.equalsIgnoreCase("Deposit")) {
			//	map.get(number2).calbal_p(amount);
			//} else {
			//	map.get(number2).calbal_m(amount);
			//}

			System.out.println("Enter the date of the " + type + " :");
			String aaa = in.next();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String cunvertCurrentDate = aaa;
			Date date = new Date();
			date = df.parse(cunvertCurrentDate);
			// System.out.println(date);

			// Add to transaction
			Transaction trac = new Transaction();
			trac.setAcct_num(number2);
			trac.setTransaction_type(type);
			trac.setAmount(amount);
			trac.setDate(date);
			map2.put(i, trac);
			i++;
			System.out.println("Enter a transaction type (Check, Debit card, Deposit or Withdrawal) or q to finish :");
			choice2 = in.next();
			
		}
		
		
	
//sort transaction map.
		int j;
		Transaction temp=new Transaction();
		for(j=0;j<i;j++){
                 for(int z=1; z < (i-j); z++){
                	 double aa=getElapsedDay(map2.get(z-1).getDate());
                	 double bb=getElapsedDay(map2.get(z).getDate());
                         if(aa>bb){
                                 //swap the elements!
                                temp=map2.get(z-1); 
                        	 	map2.put(z-1,map2.get(z));
                        	 	map2.put(z,temp);
                         }   
                 }
		}

		// Operating on accounts.2		
		int x,y,acctnum;
		double acctbal=0;
		for(x=0;x<i;x++){
			if(map2.get(x).getTransaction_type().equalsIgnoreCase("Deposit")){
				acctnum=map2.get(x).getAcct_num();
				acctbal=map.get(acctnum).getAccount_bal();
				map.get(acctnum).calbal_p(map2.get(x).getAmount());
			}else{
				acctnum=map2.get(x).getAcct_num();
				acctbal=map.get(acctnum).getAccount_bal();
				map.get(acctnum).calbal_m(map2.get(x).getAmount());
			}
		}

//print account and transaction.		
		System.out.println("********************************************");
		for (Entry<Integer, Account> entry : map.entrySet()) {
			Integer key = entry.getKey();
			double value = entry.getValue().getAccount_bal();
			System.out.println("The balance of account# " + key + " is "
					+ value);
			System.out.println();
		}
		System.out.println("********************************************");
		for (Entry<Integer, Transaction> entry : map2.entrySet()) {
			Integer key = entry.getKey();
			Integer num = entry.getValue().getAcct_num();
			String type1 = entry.getValue().getTransaction_type();
			double amount1 = entry.getValue().getAmount();
			Date date = entry.getValue().getDate();

			System.out.println("Date: " + date);
			System.out.println("Transaction type:  " + type1);
			System.out.println("Account# " + num);
			System.out.println("Amount: " + amount1);
			System.out.println();
		}
	}
	
	public static double getElapsedDay(Date aaa){

		double elapsedDay;

		Calendar cal = Calendar.getInstance();
		cal.setTime(aaa);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		// System.out.println(year +" "+ (month)+" "+day );
		GregorianCalendar d = new GregorianCalendar(year, month, day);
		GregorianCalendar s= new GregorianCalendar(1970,1,1);
		Date start=s.getTime();
		long startDateMS=start.getTime();
		Date end=d.getTime();
		long endDateMS=end.getTime();

		long elapsedMS=endDateMS-startDateMS;
		elapsedDay=elapsedMS/(24*60*60*1000);
		return elapsedDay;
	}
	
	
	
	
}
