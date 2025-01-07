# Projet-conception_de_logiciels


# Gym Management System

## Description

Le **Gym Management System** est une application JavaFX destinée à gérer efficacement une salle de sport. Cette application permet de gérer les clients, les employés, ainsi que les rendez-vous entre eux. Elle inclut également un système d'authentification et des options de configuration pour la base de données.

## Fonctionnalités

- **Authentification sécurisée** : Connexion via nom d'utilisateur et mot de passe.
- **Gestion des clients** :
  - Ajout de nouveaux clients.
  - Modification et suppression des clients existants.
- **Gestion des employés** :
  - Ajout de nouveaux employés.
  - Modification et suppression des employés.
- **Gestion des rendez-vous** :
  - Création de rendez-vous entre clients et employés.
  - Suppression des rendez-vous.
- **Paramètres** : Modification des informations de la base de données.
- **Interface utilisateur intuitive** : Utilisation de JavaFX pour une interface simple et conviviale.

## Structure du Projet

- **GymData** : Classe responsable de la gestion des opérations CRUD (Create, Read, Update, Delete) sur la base de données.
- **GymApplication** : Point d'entrée de l'application. Gère la navigation entre les différentes pages.
- **Login** : Gestion des informations d'authentification.
- **LoginPage** : Interface de la page de connexion.
- **SettingPage** : Interface permettant de modifier les paramètres de la base de données.
- **Person** : Classe de base pour les entités "Personne".
- **Customer** : Classe dérivée de Person, représentant un client.
- **CustomerPage** : Interface permettant de gérer les clients (ajout, modification, suppression).
- **Employee** : Classe dérivée de Person, représentant un employé.
- **EmployeePage** : Interface permettant de gérer les employés (ajout, modification, suppression).
- **Appointment** : Classe représentant un rendez-vous entre un client et un employé.
- **AppointmentPage** : Interface permettant de gérer les rendez-vous (ajout, suppression).
- **PasswordPage** : Interface permettant de changer le mot de passe de l'administrateur.
- **HomePage** : Tableau de bord principal regroupant toutes les fonctionnalités.

## Prérequis

- **Java** version 11 ou plus.
- **JavaFX SDK**.
- **Base de données** (MySQL, PostgreSQL, ou SQLite).
- **IDE** : IntelliJ IDEA, Eclipse, ou tout autre IDE compatible avec JavaFX.

