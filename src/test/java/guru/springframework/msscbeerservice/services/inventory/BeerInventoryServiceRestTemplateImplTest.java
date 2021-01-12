package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnhandInventory() {
        var qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_1_UUID);

        System.out.println(qoh);
    }
}