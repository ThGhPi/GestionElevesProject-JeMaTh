# Gestion des branches, commit et merge

## Créer son projet sur son unité locale

Mettez-vous dans un dossier de votre convenance ! Puis :
```bash
git clone https://github.com/ThGhPi/GestionElevesProject-JeMaTh.git
cd GestionElevesProject-JeMaTh
```

## Créer sa branche

Nom de la branche : prénomdev-sp(numéro du sprint), exemple pour Thomas et le sprint 1 :
```bash
git branch thomasdev-sp1
git switch thomasdev-sp1
```

## Faire son commit

```bash
git add .
git commit -m "v0.4 composant fonctionnel"
git push -u origin ma_branche
```

## Faire un merge sur la branche main

```bash
git fetch branche_a_merger
git fetch main
git switch main
git merge branche_a_merger
```