package pl.robert.project

import org.junit.runner.RunWith
import org.junit.runners.Suite
import pl.robert.project.bank.account.BankAccountFacadeTest
import pl.robert.project.transactions.TransactionFacadeTest
import pl.robert.project.user.domain.UserFacadeTest

@RunWith(Suite::class)
@Suite.SuiteClasses(value = [
    BankAccountFacadeTest::class,
    TransactionFacadeTest::class,
    UserFacadeTest::class
])
class FeatureTestSuite
