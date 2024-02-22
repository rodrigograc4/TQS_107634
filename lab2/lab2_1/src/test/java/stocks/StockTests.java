package stocks;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class StockTests {
    @Mock
    IStockmarketService stockmarket;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    public void testGetTotalValue() {
        when(stockmarket.lookUpPrice("AMAZON")).thenReturn(3.0);
        when(stockmarket.lookUpPrice("NIKE")).thenReturn(5.0);
        //when(stockmarket.lookUpPrice("NOTUSED")).thenReturn(1.5));

        portfolio.addStock(new Stock("AMAZON", 2));
        portfolio.addStock(new Stock("NIKE", 4));

        double result = portfolio.getTotalValue();

        assertEquals(26, result);

        verify(stockmarket, times(2)).lookUpPrice(anyString());
    }
}