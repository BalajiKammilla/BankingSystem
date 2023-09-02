fun main(args: Array<String>) {

    println("Program arguments: ${args.joinToString()}")



    val bank =Bank()

    while (true){
        println("\n Welcome To The BankingSystem\n")
        println("1. Create a new Bank Account")
        println("2. Deposit Money into Target Account")
        println("3. WithDraw Money from an Account")
        println("4. Transfer Money Between Accounts ")
        println("5. Display Account Information")
        println("6. Quit")
        println("Enter your Choice : ")

        val choice= readLine()?.toIntOrNull()?:continue

        when(choice){
            1->{
                println("Enter AccountNumber :")
                val AccountNumber= readLine() ?:continue
                println("Enter AccountHolder's FullName :")
                val AccountHolder= readLine() ?:continue
                bank.createAccount(AccountNumber,AccountHolder)
            }
            2->{
                println("Enter AccountNumber :")
                val AccountNumber= readLine() ?:continue
                val ac=bank.findAccount(AccountNumber)
                if (ac != null){
                    println("Enter Deposit Amount :")
                    val amount= readLine() ?.toDoubleOrNull() ?:continue
                    ac.deposite(amount)
                }
                else{
                    println("Account Not Found")
                }
            }
            3->{
                println("Enter AccountNumber :")
                val AccountNumber= readLine()?:continue
                val ac =bank.findAccount(AccountNumber)
                if (ac != null){
                    println("Please Enter WithDrawl Amount :")
                    val amount= readLine()?.toDoubleOrNull()?:continue
                    ac.withdraw(amount)
                }
                else{
                    println("Account Not Found")
                }
            }
            4->{
                print("Enter source account number: ")
                val sourceAccountNumber = readLine() ?: continue
                val sourceAccount = bank.findAccount(sourceAccountNumber)
                if (sourceAccount != null) {
                    print("Enter target account number: ")
                    val targetAccountNumber = readLine() ?: continue
                    val targetAccount = bank.findAccount(targetAccountNumber)
                    if (targetAccount != null) {
                        print("Enter transfer amount: ")
                        val amount = readLine()?.toDoubleOrNull() ?: continue
                        sourceAccount.transfer(amount, targetAccount)
                    } else {
                        println("Target account not found.")
                    }
                } else {
                    println("Source account not found.")
                }
            }
            5 -> {
                print("Enter account number: ")
                val accountNumber = readLine() ?: continue
                val account = bank.findAccount(accountNumber)
                if (account != null) {
                    account.DisplayAccountInfo()
                } else {
                    println("Account not found.")
                }
            }
            6 -> {
                println("Exiting the program.")
                return
            }
            else->{
                println("Invalid choice. Please try again.")
            }
        }
    }
}






class BankAccount(

    var AccountNumber:String,
    var AccountHolder:String,
    var balance:Double=0.0){

    fun deposite( amount:Double){
        if (amount>0){
            balance+=amount
            println("Deposited Amount $amount \n to the Account $AccountNumber \n Total Balance $balance")
        }
        else{
            println("Please Enter Valid Amount")
        }
    }


    fun withdraw(amount: Double){
        if(amount>0 && amount<=balance){
            balance-=amount
            println("WithDrawn Amount $amount \n From Account $AccountNumber \n Remaining Balance $balance")
        }
        else{
            println("Insufficient Balance")
        }
    }

    fun transfer(amount: Double,TargetAccont:BankAccount){

        if (amount>0 && amount<=balance){
            balance-=amount
            TargetAccont.deposite(amount)
            println("transferred Amount $amount from Account Number $AccountNumber to AccountNumber ${TargetAccont.AccountNumber} ")
        }
        else{
            println("Please Enter Valid AccountNumber or  Enter Valid Balance")
        }
    }

    fun DisplayAccountInfo(){
        println("AccontHolder : $AccountHolder")
        println("AccontNumber : $AccountNumber")
        println("Balance : $balance")
    }

}






class Bank{
    private val accounts = mutableListOf<BankAccount>()

    fun createAccount(AccountNumber: String, AccountHolder: String) {
        val account = BankAccount(AccountNumber,AccountHolder)
        accounts.add(account)
        println("Created a new account: $AccountNumber")
    }

    fun findAccount(AccountNumber: String):BankAccount?{
        return accounts.find { it.AccountNumber==AccountNumber }
    }


}

