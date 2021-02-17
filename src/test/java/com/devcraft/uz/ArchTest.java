package com.devcraft.uz;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.devcraft.uz");

        noClasses()
            .that()
                .resideInAnyPackage("com.devcraft.uz.service..")
            .or()
                .resideInAnyPackage("com.devcraft.uz.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.devcraft.uz.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
