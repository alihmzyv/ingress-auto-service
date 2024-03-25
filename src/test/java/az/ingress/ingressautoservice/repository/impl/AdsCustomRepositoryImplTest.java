package az.ingress.ingressautoservice.repository.impl;

import az.ingress.ingressautoservice.dto.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Account;
import az.ingress.ingressautoservice.entity.Ad;
import az.ingress.ingressautoservice.entity.addetails.City;
import az.ingress.ingressautoservice.entity.addetails.Currency;
import az.ingress.ingressautoservice.entity.cardetails.*;
import az.ingress.ingressautoservice.entity.helper.CarDetails;
import az.ingress.ingressautoservice.repository.AdsCustomRepository;
import jakarta.persistence.PersistenceUnit;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@AutoConfigureTestDatabase
class AdsCustomRepositoryImplTest {
    @PersistenceUnit
    private SessionFactory sessionFactory;
    @Autowired
    private AdsCustomRepository adsCustomRepository;

    @AfterEach
    void clean() {
        sessionFactory.inTransaction(session -> {
            session.createNativeMutationQuery("delete from ad").executeUpdate();
            session.createNativeMutationQuery("delete from account").executeUpdate();
        });
    }

    @Test
    void whenSavedAdsThenShouldExist() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Model model = Model.builder()
                .id(1L)
                .build();
        FuelType fuelType = FuelType.builder()
                .id(1L)
                .build();
        DriveUnitType driveUnitType = DriveUnitType.builder()
                .id(1L)
                .build();
        BodyStyle bodyStyle = BodyStyle.builder()
                .id(1L)
                .build();
        Long mileage = 10000L;
        Short year = 2010;
        Color color = Color.builder()
                .id(1L)
                .build();
        EngineCapacity engineCapacity = EngineCapacity.builder()
                .id(1L)
                .build();
        Long enginePowerInHp = 750L;
        OwnershipHistory ownershipHistory = OwnershipHistory.builder()
                .id(1L)
                .build();
        MarketVersion marketVersion = MarketVersion.builder()
                .id(1L)
                .build();
        TransmissionType transmissionType = TransmissionType.builder()
                .id(1L)
                .build();
        boolean damaged = true;
        boolean painted = false;
        Short numOfSeats = 5;
        String vinCode = "1212121";
        String extraInfo = "please buy this";
        Long priceVal = 10000L;
        Currency currency = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan = true;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
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
                .carDetails(carDetails)
                .priceVal(priceVal)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Ad adSaved = adsCustomRepository.save(ad);
        assertThat(adSaved)
                .usingRecursiveComparison(RecursiveComparisonConfiguration.builder()
                        .withIgnoreAllExpectedNullFields(true)
                        .build());
        assertNotNull(adSaved.getId());
        sessionFactory.inSession(session ->
                assertEquals(adSaved, session.find(Ad.class, adSaved.getId()))
        );
    }

    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByBrand() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Model model = Model.builder()
                .id(1L)
                .build();
        FuelType fuelType = FuelType.builder()
                .id(1L)
                .build();
        DriveUnitType driveUnitType = DriveUnitType.builder()
                .id(1L)
                .build();
        BodyStyle bodyStyle = BodyStyle.builder()
                .id(1L)
                .build();
        Long mileage = 10000L;
        Short year = 2010;
        Color color = Color.builder()
                .id(1L)
                .build();
        EngineCapacity engineCapacity = EngineCapacity.builder()
                .id(1L)
                .build();
        Long enginePowerInHp = 750L;
        OwnershipHistory ownershipHistory = OwnershipHistory.builder()
                .id(1L)
                .build();
        MarketVersion marketVersion = MarketVersion.builder()
                .id(1L)
                .build();
        TransmissionType transmissionType = TransmissionType.builder()
                .id(1L)
                .build();
        boolean damaged = true;
        boolean painted = false;
        Short numOfSeats = 5;
        String vinCode = "1212121";
        String extraInfo = "please buy this";
        Long priceVal = 10000L;
        Currency currency = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan = true;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
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
        Ad ad1 = Ad.builder()
                .carDetails(carDetails)
                .priceVal(priceVal)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .model(model2)
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
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Model model3 = Model.builder()
                .id(8L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .model(model3)
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
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
            session.persist(ad2);
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .brandId(2L)
                .build();
        List<AdShortResponseDto> ads = adsCustomRepository.find(requestParams, PageRequest.of(1, 20));
        assertNotNull(ads);
        assertEquals(1L, ads.size());
        AdShortResponseDto expectedAd = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal)
                .currencyName("AZE")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInSm3((short) 50)
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad2.getCreatedAt())
                .build();
        System.out.println("Add: " + ads.get(0));
        assertEquals(expectedAd, ads.get(0));
    }
}