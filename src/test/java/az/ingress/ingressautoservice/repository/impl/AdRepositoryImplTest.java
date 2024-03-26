package az.ingress.ingressautoservice.repository.impl;

import az.ingress.ingressautoservice.constant.MileageType;
import az.ingress.ingressautoservice.dto.ad.AdShortResponseDto;
import az.ingress.ingressautoservice.dto.ad.FindAdsRequestParams;
import az.ingress.ingressautoservice.entity.Account;
import az.ingress.ingressautoservice.entity.Ad;
import az.ingress.ingressautoservice.entity.Ad_;
import az.ingress.ingressautoservice.entity.addetails.City;
import az.ingress.ingressautoservice.entity.addetails.Currency;
import az.ingress.ingressautoservice.entity.cardetails.*;
import az.ingress.ingressautoservice.entity.helper.CarDetails;
import az.ingress.ingressautoservice.entity.helper.CarDetails_;
import az.ingress.ingressautoservice.repository.AdRepository;
import jakarta.persistence.PersistenceUnit;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase
class AdRepositoryImplTest {
    @PersistenceUnit
    private SessionFactory sessionFactory;
    @Autowired
    private AdRepository adRepository;

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
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
                .brand(brand1)
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
        Ad adSaved = adRepository.save(ad);
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
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
                .brand(brand1)
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
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .brand(brand2)
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

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
                .brand(brand3)
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
        List<AdShortResponseDto> ads = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(ads);
        assertEquals(1L, ads.size());
        AdShortResponseDto expectedAd = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal)
                .currencyName("AZE")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad2.getCreatedAt())
                .build();
        assertEquals(expectedAd, ads.get(0));
    }

    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByModel() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
                .brand(brand1)
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
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
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

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
                .modelIds(Set.of(3L))
                .build();
        List<AdShortResponseDto> ads = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(ads);
        assertEquals(1L, ads.size());
        AdShortResponseDto expectedAd = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal)
                .currencyName("AZE")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad2.getCreatedAt())
                .build();
        assertEquals(expectedAd, ads.get(0));
    }

    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByCity() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
                .brand(brand1)
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
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
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
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
            session.persist(ad2);
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .cityIds(Set.of(2L))
                .build();
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(actualAds);
        assertEquals(2, actualAds.size());
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal)
                .currencyName("AZE")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal)
                .currencyName("AZE")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd1, expectedAd2);
        assertThat(expectedAds).hasSameElementsAs(actualAds);
    }

    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByCityTest2() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        City city1 = City.builder()
                .id(2L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
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
                .city(city1)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
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
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
            session.persist(ad2);
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .cityIds(Set.of(2L))
                .build();
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(actualAds);
        assertEquals(3, actualAds.size());
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal)
                .currencyName("AZE")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal)
                .currencyName("AZE")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd1, expectedAd2, expectedAd3);
        assertThat(expectedAds).hasSameElementsAs(actualAds);
    }

    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByMinPrice() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
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
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
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
        Long priceVal2 = 5000L;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
                .priceVal(priceVal1)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
            session.persist(ad2);
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .minPrice(10000L)
                .build();
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(actualAds);
        assertEquals(2, actualAds.size());
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd1, expectedAd3);
        assertThat(expectedAds).hasSameElementsAs(actualAds);
    }


    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByMaxPrice() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
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
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
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
        Long priceVal2 = 5000L;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
                .priceVal(priceVal1)
                .priceCurrency(currency)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
            session.persist(ad2);
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .maxPrice(5000L)
                .build();
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("AZE")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd2);
        assertEquals(expectedAds.size(), actualAds.size());
        assertThat(expectedAds).hasSameElementsAs(actualAds);
    }

    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByCurrency() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan = true;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal1)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan)
                .eligibleForBarter(eligibleForLoan)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
            session.persist(ad2);
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .currencyId(2L)
                .build();
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal1)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd2, expectedAd3);
        assertThat(expectedAds).hasSameElementsAs(actualAds);
    }

    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByLoanAndBarterEligibility() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal1)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
            session.persist(ad2);
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .eligibleForLoan(true)
                .eligibleForBarter(false)
                .build();
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal1)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd1, expectedAd2);
        assertThat(expectedAds).hasSameElementsAs(actualAds);
    }

    @Test
    void whenThereAreAdsThenAdsShouldBeAbleToBeFoundByMileageType() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        Long mileage2 = 0L;
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .model(model2)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal1)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
            session.persist(ad2);
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .mileageType(MileageType.WITHOUT)
                .build();
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage2)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal1)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd2);
        assertThat(expectedAds).hasSameElementsAs(actualAds);
    }

    @Test
    void whenThereAreAdsThenDefaultOrderShouldBeByAdCreationDateDesc() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(10)))
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        Long mileage2 = 0L;
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .model(model2)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(5)))
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal1)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(1)))
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad2);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .build();
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, PageRequest.of(0, 20));
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad1.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage2)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal1)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad3.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd3, expectedAd2, expectedAd1);
        assertEquals(expectedAds, actualAds);
    }

    @Test
    void whenThereAreAdsAndOrderIsGivenAsCreationDateDesc() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(10)))
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        Long mileage2 = 0L;
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .model(model2)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(5)))
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal1)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(1)))
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad2);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .build();
        PageRequest pageRequest = PageRequest.of(0,
                20,
                Sort.by(Sort.Order.desc(Ad_.CREATED_AT)));
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, pageRequest);
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad1.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage2)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal1)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad3.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd3, expectedAd2, expectedAd1);
        assertEquals(expectedAds, actualAds);
    }

    @Test
    void whenThereAreAdsAndOrderIsGivenPriceAsc() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(10)))
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        Long mileage2 = 0L;
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .model(model2)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(5)))
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Long priceVal3 = 30000L;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal3)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(1)))
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad2);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .build();
        PageRequest pageRequest = PageRequest.of(0,
                20,
                Sort.by(Sort.Order.asc(Ad_.PRICE_VAL)));
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, pageRequest);
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad1.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage2)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal3)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad3.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd2, expectedAd1, expectedAd3);
        assertEquals(expectedAds, actualAds);
    }

    @Test
    void whenThereAreAdsAndOrderIsGivenAsPriceDesc() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(10)))
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        Long mileage2 = 0L;
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .model(model2)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(5)))
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Long priceVal3 = 30000L;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal3)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(1)))
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad2);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .build();
        PageRequest pageRequest = PageRequest.of(0,
                20,
                Sort.by(Sort.Order.desc(Ad_.PRICE_VAL)));
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, pageRequest);
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Baku")
                .createdAt(ad1.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage2)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal3)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage)
                .cityName("Absheron")
                .createdAt(ad3.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd3, expectedAd1, expectedAd2);
        assertEquals(expectedAds, actualAds);
    }

    @Test
    void whenThereAreAdsAndOrderIsGivenAsMileageDesc() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long mileage1 = 10000L;
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
                .model(model)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(10)))
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        Long mileage2 = 20000L;
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .model(model2)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(5)))
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        Long mileage3 = 15000L;
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
                .model(model3)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Long priceVal3 = 30000L;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal3)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(1)))
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad2);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .build();
        PageRequest pageRequest = PageRequest.of(0,
                20,
                Sort.by(Sort.Order.asc(String.join(".", Ad_.CAR_DETAILS, CarDetails_.MILEAGE))));
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, pageRequest);
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage1)
                .cityName("Baku")
                .createdAt(ad1.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage2)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal3)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage3)
                .cityName("Absheron")
                .createdAt(ad3.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd1, expectedAd3, expectedAd2);
        assertEquals(expectedAds, actualAds);
    }

    @Test
    void whenThereAreAdsAndOrderIsGivenAsYearOfCarDesc() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long mileage1 = 10000L;
        Short year1 = 2010;
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
                .model(model)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage1)
                .yearOfCar(year1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(10)))
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        Long mileage2 = 20000L;
        Short year2 = 2024;
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .model(model2)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage2)
                .yearOfCar(year2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(5)))
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        Long mileage3 = 15000L;
        Short year3 = 2023;
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
                .model(model3)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage3)
                .yearOfCar(year3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Long priceVal3 = 30000L;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal3)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .createdAt(Instant.now().minus(Duration.ofSeconds(1)))
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad2);
        });
        sessionFactory.inTransaction(session -> {
            session.persist(ad3);
        });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .build();
        PageRequest pageRequest = PageRequest.of(0,
                20,
                Sort.by(Sort.Order.desc(String.join(".", Ad_.CAR_DETAILS, CarDetails_.YEAR_OF_CAR))));
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, pageRequest);
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year1)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage1)
                .cityName("Baku")
                .createdAt(ad1.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year2)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage2)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal3)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year3)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage3)
                .cityName("Absheron")
                .createdAt(ad3.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = List.of(expectedAd2, expectedAd3, expectedAd1);
        assertEquals(expectedAds, actualAds);
    }

    @Test
    void whenThereAreAdsThenPaginationShouldBeWorking() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long mileage1 = 10000L;
        Short year1 = 2010;
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
                .model(model)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage1)
                .yearOfCar(year1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .build();
        Model model2 = Model.builder()
                .id(3L)
                .build();
        City city2 = City.builder()
                .id(2L)
                .build();
        Long mileage2 = 20000L;
        Short year2 = 2024;
        CarDetails carDetails2 = CarDetails.builder()
                .brand(brand2)
                .model(model2)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage2)
                .yearOfCar(year2)
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
        Long priceVal2 = 5000L;
        Currency currency2 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan2 = true;
        boolean eligibleForBarter2 = false;
        Ad ad2 = Ad.builder()
                .carDetails(carDetails2)
                .priceVal(priceVal2)
                .priceCurrency(currency2)
                .eligibleForLoan(eligibleForLoan2)
                .eligibleForBarter(eligibleForBarter2)
                .nameOfSeller(nameOfSeller)
                .city(city2)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();

        Brand brand3 = Brand.builder()
                .id(3L)
                .build();
        Model model3 = Model.builder()
                .id(8L)
                .build();
        City city3 = City.builder()
                .id(2L)
                .build();
        Long mileage3 = 15000L;
        Short year3 = 2023;
        CarDetails carDetails3 = CarDetails.builder()
                .brand(brand3)
                .model(model3)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage3)
                .yearOfCar(year3)
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
        Currency currency3 = Currency.builder()
                .id(2L)
                .build();
        boolean eligibleForLoan3 = true;
        boolean eligibleForBarter3 = true;
        Long priceVal3 = 30000L;
        Ad ad3 = Ad.builder()
                .carDetails(carDetails3)
                .priceVal(priceVal3)
                .priceCurrency(currency3)
                .eligibleForLoan(eligibleForLoan3)
                .eligibleForBarter(eligibleForBarter3)
                .nameOfSeller(nameOfSeller)
                .city(city3)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        IntStream.rangeClosed(1, 10)
                .forEach(val -> {
                    ad1.setId(null);
                    sessionFactory.inTransaction(
                            session -> session.persist(ad1)
                    );
                });
        IntStream.rangeClosed(1, 10)
                .forEach(val -> {
                    ad2.setId(null);
                    sessionFactory.inTransaction(
                            session -> session.persist(ad2)
                    );
                });
        IntStream.rangeClosed(1, 10)
                .forEach(val -> {
                    ad3.setId(null);
                    sessionFactory.inTransaction(
                            session -> session.persist(ad3)
                    );
                });
        FindAdsRequestParams requestParams = FindAdsRequestParams.builder()
                .build();
        PageRequest pageRequest = PageRequest.of(0,
                24,
                Sort.by(Sort.Order.desc(String.join(".", Ad_.CREATED_AT))));
        List<AdShortResponseDto> actualAds = adRepository.find(requestParams, pageRequest);
        assertNotNull(actualAds);
        AdShortResponseDto expectedAd1 = AdShortResponseDto.builder()
                .id(ad1.getId())
                .price(priceVal1)
                .currencyName("AZE")
                .brandName("Aston Martin")
                .modelName("DB9")
                .year(year1)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage1)
                .cityName("Baku")
                .createdAt(ad1.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd2 = AdShortResponseDto.builder()
                .id(ad2.getId())
                .price(priceVal2)
                .currencyName("USD")
                .brandName("Audi")
                .modelName("100")
                .year(year2)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage2)
                .cityName("Absheron")
                .createdAt(ad2.getCreatedAt())
                .build();
        AdShortResponseDto expectedAd3 = AdShortResponseDto.builder()
                .id(ad3.getId())
                .price(priceVal3)
                .currencyName("USD")
                .brandName("Bentley")
                .modelName("Bentayga")
                .year(year3)
                .capacityInLitres(BigDecimal.valueOf(0.05))
                .mileage(mileage3)
                .cityName("Absheron")
                .createdAt(ad3.getCreatedAt())
                .build();
        List<AdShortResponseDto> expectedAds = Stream.of(Stream.generate(() -> expectedAd3)
                                .limit(10),
                        Stream.generate(() -> expectedAd2)
                                .limit(10),
                        Stream.generate(() -> expectedAd1)
                                .limit(4))
                .flatMap(stream -> stream)
                .toList();
        assertThat(actualAds)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id", "createdAt", "updatedAt")
                .isEqualTo(expectedAds);
    }

    @Test
    void whenAdExistsThenItShouldBeAbleToBeFoundById() {
        Account account = sessionFactory.fromTransaction(session -> {
            Account accountToPersist = Account.builder()
                    .phoneNumber("0559994650")
                    .password("12345")
                    .emailAddress("alihmzyv@gmail.com")
                    .build();
            session.persist(accountToPersist);
            return accountToPersist;
        });
        Brand brand1 = Brand.builder()
                .id(1L)
                .build();
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
        Long mileage1 = 10000L;
        Short year1 = 2010;
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
        Long priceVal1 = 10000L;
        Currency currency1 = Currency.builder()
                .id(1L)
                .build();
        boolean eligibleForLoan1 = true;
        boolean eligibleForBarter1 = false;
        String nameOfSeller = "Ali";
        City city = City.builder()
                .id(1L)
                .build();
        String emailAddressOfSeller = "alihmzyv@gmail.com";
        CarDetails carDetails = CarDetails.builder()
                .brand(brand1)
                .model(model)
                .fuelType(fuelType)
                .driveUnitType(driveUnitType)
                .bodyStyle(bodyStyle)
                .mileage(mileage1)
                .yearOfCar(year1)
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
                .priceVal(priceVal1)
                .priceCurrency(currency1)
                .eligibleForLoan(eligibleForLoan1)
                .eligibleForBarter(eligibleForBarter1)
                .nameOfSeller(nameOfSeller)
                .city(city)
                .emailAddressOfSeller(emailAddressOfSeller)
                .account(account)
                .build();
        sessionFactory.inTransaction(session -> {
            session.persist(ad1);
        });
        assertNotNull(ad1.getId());
        Optional<Ad> adOpt = adRepository.findById(ad1.getId());
        assertTrue(adOpt.isPresent());
        Ad ad = adOpt.get();
        assertEquals(ad.getCity().getName(), "Baku");
    }
}