Télécorn Viking
===

Télécorn Viking vous propose son application de randonnée, permettant de sauvegarder et partager des parcours de randonnée.

Comment lancer
---
`java -jar %nom_de_lexecutable%.jar`

les exécutables se trouvent dans le dossier `out/artifacts`. Vous pouvez choisir quel version utiliser ici.
Le rendu final se trouve dans le dossier `RELEASE_FINAL`.

Les features ★
---
Lister les parcours enregistrés
Faire une recherche dans les parcours avec les zones de recherches en haut à droite. Il n'est pas obligé de remplir un champ. Pour lancer la recherche vous pouvez cliquer sur la loupe ou appuyer sur la touche Entrée.

Création d'un parcours grâce au bouton à droite de la fenêtre d'accueil.
Modification et suppression d'un parcours grâce aux boutons dans la liste.

Affichage des détails d'un parcours dans sa fenêtre en cliquant sur son titre
Affichage sur une carte du tracé d'un parcours
Affichage du dénivelé du parcours dans un graphique

Import des parcours au format gpx, ils sont lu et stocké dans le dossier GPX à coté de l'exécutable

Definir un parcours comme un favoris, il est aussi possible de n'afficher queles favoris grâce au bouton dans la colonne de droite.

Apres une recherche, ou avoir choisi de n'afficher que les favoris, pour revenir à la premiere page, cliquer sur le logo licorne en haut à gauche.

Il est possible de sauvegarder les parcours avec le bouton 'Sauvegarde' à droite de la fenêtre.


Carte interactive
---
Lors de la création d'un parcours, il est possible de définir son tracé en utilisant la carte.
Le sélecteur à droite permet de choisir entre l'ajout d'un point du chemin et l'ajout d'un point d’intérêt. Ensuite faire un clique gauche sur la carte permet d'ajouter ce point. Pour voir le premier trait il faut ajouter 2 points.

Un clique gauche sur label permet de changer son nom. Écrire dans la zone de texte qui s'est ouvert à droite puis appuyez sur entrée.

Un clique droit sur un marker le supprime ainsi que son label.

Video de présentation
---
[Telecorn Viking présentation v2](https://www.youtube.com/watch?v=2GcBMklRor4)

[version 1](https://www.youtube.com/watch?v=3vlqXBYZ1iE)

Bug connus et features manquantes
---

Il n'y a pas d'Images associés aux parcours.

Bug : Les Marker perdent leurs accent après une sauvegarde, c'est dû au chargement des données, qui n'accepte pas les charatères spéciaux.

Bug : L'application affiche une erreur si le fichier GPX n'est pas présent au lancement. Bien penser à garder le fichier (même vide).

Bug : Un chemin créé manuellement aura un dénivelé nul sur le graph, c'est par ce que la carte est plate et qu'on ne peut pas récupéré l'élévation du terrain en un point.


> fait avec le visualiseur de [stackedit](https://stackedit.io)
