package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    // bad practice, but MAYBE it's the only way to make it work
//    @Autowired
//    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
//        this.beerInventoryService = beerInventoryService;
//    }
//
//    @Autowired
//    public void setMapper(BeerMapper mapper) {
//        this.mapper = mapper;
//    }

    // this works along with @RequiredArgsConstructor and fields NOT final, but at least we can mock in testing
    public BeerMapperDecorator(BeerInventoryService beerInventoryService, BeerMapper mapper) {
        this.beerInventoryService = beerInventoryService;
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.beerToBeerDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }
}
