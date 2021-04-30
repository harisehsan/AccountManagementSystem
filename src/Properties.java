public class Properties {
    private static String selectedAccountNumber;
    private static int Account_Row=0;
    private static String phoneNumber;
    private static int logedInUser;
    private static String address;
    private static int trasactionRow=0;
    private static int deductionRow=0;

    public int getTrasactionRow() {
        return trasactionRow;
    }

    public void setTrasactionRow(int trasactionRow) {
        Properties.trasactionRow = trasactionRow;
    }

    public int getDeductionRow() {
        return deductionRow;
    }

    public void setDeductionRow(int deductionRow) {
        Properties.deductionRow = deductionRow;
    }





    public int getLogedInUser() {
        return logedInUser;
    }

    public void setLogedInUser(int logedInUser) {
        Properties.logedInUser = logedInUser;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    private int accountNumber;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSelectedAccountNumber() {
        return selectedAccountNumber;
    }

    public void setSelectedAccountNumber(String selectedAccountNumber) {
        this.selectedAccountNumber = selectedAccountNumber;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private static String Name;
    public String getAccount_type() {
        return Account_type;
    }

    public void setAccount_type(String account_type) {
        Account_type = account_type;
    }

   private static String Account_type;

    public int getAccount_Row() {
        return Account_Row;
    }

    public  void setAccount_Row(int account_Row) {
        Account_Row = account_Row;
    }


}
