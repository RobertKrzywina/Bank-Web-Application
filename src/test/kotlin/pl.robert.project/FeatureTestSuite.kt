package pl.robert.project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.robert.project.bank.account.BankAccountFacadeTest;
import pl.robert.project.transactions.TransactionFacadeTest;

@RunWith(Suite::class)
@Suite.SuiteClasses(value = [
    BankAccountFacadeTest::class,
    TransactionFacadeTest::class
])
class FeatureTestSuite
