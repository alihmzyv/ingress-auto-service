package az.ingress.ingressautoservice.service.impl;

import az.ingress.ingressautoservice.constant.MileageType;
import az.ingress.ingressautoservice.dto.CarDetailsDto;
import az.ingress.ingressautoservice.dto.ad.AdResponseDto;
import az.ingress.ingressautoservice.entity.Account;
import az.ingress.ingressautoservice.entity.Ad;
import az.ingress.ingressautoservice.entity.addetails.City;
import az.ingress.ingressautoservice.entity.addetails.Currency;
import az.ingress.ingressautoservice.entity.cardetails.*;
import az.ingress.ingressautoservice.entity.helper.CarDetails;
import az.ingress.ingressautoservice.exception.NotFoundException;
import az.ingress.ingressautoservice.repository.AdCustomRepository;
import az.ingress.ingressautoservice.service.AdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdServiceImplTest {
    @MockBean
    AdCustomRepository adCustomRepository;

    @Autowired
    AdService adService;

    @Test
    void whenAdDoesNotExistByIdThenItShouldFail() {
        Long id = 1L;
        when(adCustomRepository.findById(id))
                .thenReturn(Optional.empty());
        assertThrowsExactly(NotFoundException.class,
                () -> adService.findById(id),
                String.format("Ad not found by id: %s", 1L));
    }

    @Test
    void whenAddExistsByIdThenShouldReturnCorrectly() {
        String phoneNumberOfSeller = "0559994650";
        Account account = Account.builder()
                .id(1L)
                .phoneNumber(phoneNumberOfSeller)
                .password("12345")
                .emailAddress("alihmzyv@gmail.com")
                .build();
        Long id = 1L;
        String brandName = "Bentley";
        Brand brand = Brand.builder()
                .id(1L)
                .name(brandName)
                .build();
        String modelName = "Bentayga";
        Model model = Model.builder()
                .id(1L)
                .name(modelName)
                .brand(brand)
                .build();
        String fuelTypeName = "benzene";
        FuelType fuelType = FuelType.builder()
                .id(1L)
                .name(fuelTypeName)
                .build();
        String driveUnitTypeName = "awd";
        DriveUnitType driveUnitType = DriveUnitType.builder()
                .id(1L)
                .name(driveUnitTypeName)
                .build();
        String bodyStyleName = "Bus";
        BodyStyle bodyStyle = BodyStyle.builder()
                .id(1L)
                .name(bodyStyleName)
                .build();
        Long mileage = 10000L;
        Short year = 2010;
        String colorName = "grey";
        Color color = Color.builder()
                .id(1L)
                .name(colorName)
                .build();
        EngineCapacity engineCapacity = EngineCapacity.builder()
                .capacityInSm3((short) 50)
                .id(1L)
                .build();
        Long enginePowerInHp = 750L;
        String ownershipHistoryName = "first-hand";
        OwnershipHistory ownershipHistory = OwnershipHistory.builder()
                .name(ownershipHistoryName)
                .id(1L)
                .build();
        String marketVersioName = "America";
        MarketVersion marketVersion = MarketVersion.builder()
                .name(marketVersioName)
                .id(1L)
                .build();
        String transmissionTypeName = "mechanical";
        TransmissionType transmissionType = TransmissionType.builder()
                .id(1L)
                .name(transmissionTypeName)
                .build();
        boolean damaged = true;
        boolean painted = false;
        Short numOfSeats = 5;
        String vinCode = "1212121";
        String extraInfo = "please buy this";
        Long priceVal = 10000L;
        String currencyName = "AZN";
        Currency currency = Currency.builder()
                .id(1L)
                .name(currencyName)
                .build();
        boolean eligibleForLoan = true;
        boolean eligibleForBarter = false;
        String nameOfSeller = "Ali";
        String cityName = "Baku";
        City city = City.builder()
                .id(1L)
                .name(cityName)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand)
                .model(model)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage)
                .yearOfCar(year)
                .color(color)
                .engineCapacity(engineCapacity)
                .enginePowerInHp(enginePowerInHp)
                .ownershipHistory(ownershipHistory)
                .marketVersion(marketVersion)
                .transmissionType(transmissionType)
                .damaged(damaged)
                .painted(painted)
                .numOfSeats(numOfSeats)
                .vinCode(vinCode)
                .extraInfo(extraInfo)
                .build();
        Ad ad = Ad.builder()
                .id(id)
                .carDetails(carDetails)
                .priceVal(priceVal)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForBarter)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        when(adCustomRepository.findById(id))
                .thenReturn(Optional.of(ad));
        CarDetailsDto carDetailsDto = CarDetailsDto.builder()
                .brandName(brandName)
                .modelName(modelName)
                .fuelTypeName(fuelTypeName)
                .driveUnitTypeName(driveUnitTypeName)
                .bodyStyleName(bodyStyleName)
                .mileage(mileage)
                .yearOfCar(year)
                .colorName(colorName)
                .engineCapacityInLitres(BigDecimal.valueOf(0.05))
                .enginePowerInHp(enginePowerInHp)
                .ownershipHistoryName(ownershipHistoryName)
                .marketVersionName(marketVersion.getName())
                .transmissionTypeName(transmissionTypeName)
                .damaged(damaged)
                .painted(painted)
                .numOfSeats(numOfSeats)
                .extraInfo(extraInfo)
                .mileageType(MileageType.WITH)
                .build();
        AdResponseDto expectedAd = AdResponseDto.builder()
                .id(id)
                .carDetails(carDetailsDto)
                .priceVal(priceVal)
                .priceCurrencyName(currencyName)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForBarter)
                .nameOfSeller(nameOfSeller)
                .cityName(cityName)
                .accountPhoneNumber(phoneNumberOfSeller)
                .build();
        AdResponseDto actualAd = adService.findById(id);
        assertEquals(expectedAd, actualAd);
    }
}