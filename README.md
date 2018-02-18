# Epicture

## Table des matières

1. Introduction
2. Comment faire marcher Epicture
2.1. Epicture qu'est-ce que c'est?
2.2. Comment l'installer.
2.3. Connection.
2.4. Page principale.
2.5. Recherches.
3. Licences.

##  1. Introduction

Epicture est un projet réalisé au sein d'Epitech, il a été réalisé en groupe de 3 personnes et écrie en android natif(java).

Groupe :
Tom LENORMAND
Alexis LAVENU
Samy FICHENICK

## 2. Comment faire marcher Epicture ?
### 2.1. Epicture qu'est-ce que c'est ?

Epicture est une application mobile permettant de ce connecter a son compte instagram, de voir son profil, faire des recherche par nom de personnes ou par hashtags. L'application permet aussi de classer des postes et de les notés comment étant des favoris, que vous pourrez retrouver plus tard et revoir les postes qui vous ont marqués.

### 2.2 Comment l'installer ?

Il y a deux façons d'installer Epicture sur votre téléphone :
* Via AndroidStudio :

Vous pouvez utiliser AndroidStudio pour compiler directement Epicture sur votre téléphone. Pour ceci ouvré Epicture sur AndroidStudio, Clicker ensuite sur le flèche verte, il vous sera demander sur quel support lancer Epicture, vous devriez voir votre téléphone. Laisser compiler et vous aurez Epicture sur votre téléphone.

* Via gradlew :

Vous pouvez utiliser Gradlew (il est présent dans le projet téléchargeable sur la page Github d'Epicture), il vous suffira d'ouvrire un terminale a la recine d'Epicture est de lancer la commande suivante :

`./gradlew assembleDebug `

Vous pourez ensuite trouver dans le dossier debug d'Epicture le .apk du projet.
Il vous suffire de transferer sur votre téléphone le Epicture.apk et de le lancer ainsi l'installation commencera.

### 2.3. Connection

Une fois l'installation terminé vous pourrez lancer l'application Epicture. En la démarrant vous arriverez sur une page avec un bouton ressemblant a celui d'instagram.
Clicker dessus pour vous connecter a votre compte instagram, vous serez alors rediriger sur la page de connection instagram depuis l'application.
Une fois remplie vous serez amené sur la page principale d'Epicture.

### 2.4. Page principale

La page principale est la page permettant de ce ballader dans les différentes sections proposer par Epicture ainsi que l'affichage de votre profil.
En haut a droite vous pourrez voir un boutton orange, il permet de ce déconnecter de l'application.
En bas de la page vous trouverez quatres icones :

* une icone en forme de personne :

Il permet de voir toutes vos photos postées.

* une icone en forme d'étoile :

Il permet de voir tous les postes que vous avez marqué en tant que favoris.

* une icone en forme d'appareille photo :

Il permet de choisir une photo sur votre téléphone de l'uploader sur n'importe quel réseau social présent sur votre téléphone.

* une icone en forme de loupe :

Il vous redirige vers le page de recherche.

### 2.5. Recherches

Sur cette page, en haut ce trouve un champ de text, il permet de faire des recherche par tag ou par pseudo, vous pouvez le choix entre l'une ou l'autre facon graçes aux boutons radios présent juste en dessous du champ de text.

Une fois une recherche effectuer sur chaque poste affichés vous trouverez un bouton en forme d'étoile permmetant de définir un poste comme favoris et un bouton en forme de bulle de text permettant de poster un commentaire sur le poste choisit.

## Licences

>Copyright © 2000 Tom Lenormand <tom.lenormand@epitech.eu>
>This work is free. You can redistribute it and/or modify it under the
>terms of the Do What The Fuck You Want To Public License, Version 2,
>as published by Sam Hocevar. See the COPYING file for more details.