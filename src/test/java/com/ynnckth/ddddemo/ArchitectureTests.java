package com.ynnckth.ddddemo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

public class ArchitectureTests {

    private static final String BASE_PACKAGE = "com.ynnckth.ddddemo";
    private static final String DOMAIN_PACKAGE = "..domain..";
    private static final String USE_CASE_PACKAGE = "..use_cases..";

    @Test
    void implementsOnionArchitecture() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(BASE_PACKAGE);

        ArchRule rule = onionArchitecture()
                .domainModels(DOMAIN_PACKAGE)
                .domainServices(DOMAIN_PACKAGE)
                .applicationServices(USE_CASE_PACKAGE)
                .adapter("controller", "..adapter.controller..")
                .adapter("clients", "..adapter.clients..")
                .adapter("exchange_rates", "..adapter.exchange_rates..");

        rule.check(importedClasses);
    }

    @Test
    void domainModelsHaveNoFrameworkDependencies() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages(BASE_PACKAGE);

        ArchRule rule = noClasses()
                .that()
                .resideInAPackage(DOMAIN_PACKAGE)
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(DOMAIN_PACKAGE,
                        "java..",
                        "lombok..",
                        "org.springframework.lang" //exception for nullable annotation
                );

        rule.check(importedClasses);
    }
}
