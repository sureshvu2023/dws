package com.dws.challenge;

import com.dws.challenge.model.Account;
import com.dws.challenge.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class ChallengeApplicationTests {


	@InjectMocks
	private AccountService accountService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		//accountService.initSampleData();
	}

	@Test
	public void testTransferMoney_SuccessfulTransfer() {
		Long accountFromId = 1L;
		Long accountToId = 2L;
		double initialBalanceAccountFrom = 1000.0;
		double initialBalanceAccountTo = 500.0;
		double transferAmount = 200.0;

		// Set initial balances and add accounts manually
		Account accountFrom = new Account(accountFromId, initialBalanceAccountFrom);
		Account accountTo = new Account(accountToId, initialBalanceAccountTo);

		accountService.addAccount(accountFrom);
		accountService.addAccount(accountTo);

		// Perform the transfer
		accountService.transferMoney(accountFromId, accountToId, transferAmount);

		// Check the updated balances
		assertEquals(initialBalanceAccountFrom - transferAmount, accountFrom.getBalance(), 0.001);
		assertEquals(initialBalanceAccountTo + transferAmount, accountTo.getBalance(), 0.001);

	}

	@Test
	public void testTransferMoney_InvalidAccountIds() {
		Long accountFromId = 1L;
		Long accountToId = 3L;
		double transferAmount = 100.0;

		// Attempting to transfer money from/to non-existing accounts
		assertThrows(IllegalArgumentException.class, () -> accountService.transferMoney(accountFromId, accountToId, transferAmount));


	}

	@Test
	public void testTransferMoney_InsufficientFunds() {
		Long accountFromId = 1L;
		Long accountToId = 2L;
		double initialBalanceAccountFrom = 100.0;
		double initialBalanceAccountTo = 200.0;
		double transferAmount = 150.0;

		// Set initial balances
		Account accountFrom = new Account(accountFromId, initialBalanceAccountFrom);
		Account accountTo = new Account(accountToId, initialBalanceAccountTo);

		// Attempting to transfer more money than the account has
		assertThrows(IllegalArgumentException.class, () -> accountService.transferMoney(accountFromId, accountToId, transferAmount));

		// Verify no notifications are sent in this case

	}
	@Test
	public void testTransferMoney_SuccessfulTransferconcurrency() {
		// Test for concurrent transfers
		int numThreads = 10;
		Long accountFromId = 1L;
		Long accountToId = 2L;
		double initialBalanceAccountFrom = 1000.0;
		double initialBalanceAccountTo = 500.0;
		double transferAmount = 100.0;

		// Set initial balances and add accounts manually
		Account accountFrom = new Account(accountFromId, initialBalanceAccountFrom);
		Account accountTo = new Account(accountToId, initialBalanceAccountTo);

		accountService.addAccount(accountFrom);
		accountService.addAccount(accountTo);

		// Create an ExecutorService to run concurrent transfers
		ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

		// Submit multiple tasks for concurrent transfers
		for (int i = 0; i < numThreads; i++) {
			executorService.submit(() -> accountService.transferMoney(accountFromId, accountToId, transferAmount));
		}

		// Shutdown the executor and wait for all tasks to complete
		executorService.shutdown();
		try {
			executorService.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Check the updated balances
		double expectedBalanceAccountFrom = initialBalanceAccountFrom - (numThreads * transferAmount);
		double expectedBalanceAccountTo = initialBalanceAccountTo + (numThreads * transferAmount);

		assertEquals(expectedBalanceAccountFrom, accountFrom.getBalance(), 0.001);
		assertEquals(expectedBalanceAccountTo, accountTo.getBalance(), 0.001);


	}

}
