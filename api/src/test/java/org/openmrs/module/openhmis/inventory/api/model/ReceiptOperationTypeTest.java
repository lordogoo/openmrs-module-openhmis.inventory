package org.openmrs.module.openhmis.inventory.api.model;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.openhmis.inventory.api.IItemDataServiceTest;
import org.openmrs.module.openhmis.inventory.api.IStockOperationDataService;
import org.openmrs.module.openhmis.inventory.api.IStockOperationTypeDataService;
import org.openmrs.module.openhmis.inventory.api.IStockroomDataServiceTest;

public class ReceiptOperationTypeTest extends BaseOperationTypeTest {
	IStockOperationTypeDataService stockOperationTypeDataService;
	IStockOperationDataService stockOperationDataService;

	@Before
	public void before() throws Exception {
		executeDataSet(IItemDataServiceTest.ITEM_DATASET);
		executeDataSet(IStockroomDataServiceTest.DATASET);
		executeDataSet(DATASET);

		stockOperationTypeDataService = Context.getService(IStockOperationTypeDataService.class);
		stockOperationDataService = Context.getService(IStockOperationDataService.class);
	}

	@Test
	public void onCancelled_shouldClearReservedTransactions() throws Exception {
		ReceiptOperationType receiptOperationType = (ReceiptOperationType)stockOperationTypeDataService.getById(7);
		StockOperation stockOperation = stockOperationDataService.getById(4);

		assertTrue(stockOperation.getReserved().size() == 1);
		receiptOperationType.onCancelled(stockOperation);
		assertTrue(stockOperation.getReserved().size() == 0);
	}

	@Test
	public void onCompleted_shouldSetDestinationStockroom() throws Exception {
		ReceiptOperationType receiptOperationType = (ReceiptOperationType)stockOperationTypeDataService.getById(7);
		StockOperation stockOperation = stockOperationDataService.getById(4);

		receiptOperationType.onCompleted(stockOperation);
		Set<StockOperationTransaction> transactions = stockOperation.getTransactions();
		assertTrue(transactions.size() == 1);
		for (StockOperationTransaction transaction : transactions) {
			assertTrue(transaction.getStockroom().getId() == 4);
		}
	}

}
