# BankAccount Kata (Java - Maven)

Ce projet est une implémentation simple du **Kata BankAccount**, en Java, avec Maven comme système de build.

## Fonctionnalités

- Dépôt et retrait d’argent
- Suivi des opérations (date, montant, solde)
- Impression du relevé bancaire
- Manipulation sécurisée des montants financiers (`BigDecimal`)
- Code simple, lisible et maintenable

## User Stories

### US 1
En tant que client de la banque  
Je veux pouvoir déposer de l'argent  
Afin d’épargner


### US 2
En tant que client de la banque  
Je veux pouvoir retirer de l'argent  
Afin d’utiliser mes économies



## Design

- BankAccount : gère les dépôts, retraits et état du compte.
- Transaction : représente une ligne d'opération avec date, montant et solde courant.
- Test acceptation : vérifie le bon enchaînement des opérations et l’affichage.
- Tests unitaires (JUnit 5) pour valider la logique métier.


## Lancer le projet

### Prérequis

- Java 17+
- Maven 3.8+

### Compilation & tests

mvn clean install


### Exécuter le test d’acceptation

mvn exec:java -Dexec.mainClass="com.bank.AcceptanceTest"




