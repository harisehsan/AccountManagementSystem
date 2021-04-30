public class Transactions extends Properties {

   static String [][] transactionsTable = new String[50][5];
    static String [][] deductionsTable = new String[50][4];

    AccountsOperations accountsOperations = new AccountsOperations();
    public void displayAllAccountsDeductions()
    {
        try {
            System.out.println("\nAccount Number\tDate & Time\tAccount Type\tDeduction Amount");
            for (int i=0; i<50;i++)
            {
                if (!(Transactions.deductionsTable[i][0].equalsIgnoreCase(null))) {
                    for (int j=0;j<4;j++)
                    {
                        System.out.print(Transactions.deductionsTable[i][j]+"\t\t");
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {

        }
    }
    }
