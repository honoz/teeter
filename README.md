# Teeter - Remake du jeu classique HTC

Un remake moderne du jeu Teeter original de HTC, recréé à partir des ressources extraites.

## Description

Teeter est un jeu de labyrinthe basé sur l'accéléromètre où vous devez guider une balle à travers 32 niveaux en évitant les trous et en atteignant l'objectif.

## Fonctionnalités

- ✅ 32 niveaux originaux
- ✅ Contrôles par accéléromètre
- ✅ Effets sonores et vibrations
- ✅ Graphismes originaux du jeu HTC
- ✅ Suivi du temps et des tentatives
- ✅ Compatible avec les appareils Android modernes (API 24+)

## Technologies

- **Langage**: Kotlin
- **Minimum SDK**: Android 7.0 (API 24)
- **Target SDK**: Android 14 (API 34)
- **Architecture**: Custom Game Engine avec SurfaceView

## Structure du projet

```
TeeterGame/
├── app/
│   ├── src/main/
│   │   ├── java/com/htc/android/teeter/
│   │   │   ├── game/
│   │   │   │   └── GameView.kt          # Moteur de jeu principal
│   │   │   ├── models/
│   │   │   │   ├── Level.kt             # Modèle de niveau
│   │   │   │   └── GameState.kt         # État du jeu
│   │   │   ├── utils/
│   │   │   │   └── LevelParser.kt       # Parseur XML des niveaux
│   │   │   ├── SplashActivity.kt        # Écran de démarrage
│   │   │   ├── GameActivity.kt          # Activité principale du jeu
│   │   │   └── ScoreActivity.kt         # Écran des scores
│   │   └── res/
│   │       ├── drawable/                # Images du jeu original
│   │       ├── raw/                     # Sons (.ogg)
│   │       └── xml/                     # Définitions des 32 niveaux
│   └── build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## Comment compiler

### Prérequis
- Android Studio Hedgehog ou plus récent
- JDK 8 ou plus récent
- SDK Android avec API 34

### Étapes

1. Ouvrir le projet dans Android Studio:
   ```
   File > Open > Sélectionner le dossier TeeterGame
   ```

2. Synchroniser Gradle:
   ```
   File > Sync Project with Gradle Files
   ```

3. Compiler et installer:
   ```
   Run > Run 'app'
   ```
   Ou via ligne de commande:
   ```bash
   cd TeeterGame
   ./gradlew assembleDebug
   # L'APK sera dans app/build/outputs/apk/debug/
   ```

## Comment jouer

1. Lancez l'application
2. Inclinez votre appareil pour contrôler la balle
3. Évitez les trous noirs
4. Atteignez la zone verte (goal) pour terminer le niveau
5. Complétez les 32 niveaux !

## Ressources extraites utilisées

- **Images**: Balle, trous, murs, maze background, animations
- **Sons**: hole.ogg, level_complete.ogg, game_complete.ogg
- **Niveaux**: 32 fichiers XML définissant les positions des murs, trous et objectifs

## Notes techniques

- Le jeu utilise le SensorManager pour détecter les mouvements de l'accéléromètre
- La physique de la balle inclut la vélocité, la friction et les collisions
- Les niveaux sont chargés dynamiquement depuis les fichiers XML
- Le rendu est fait avec Canvas sur SurfaceView pour de meilleures performances

## Licence

Ce projet est un remake non officiel du jeu original Teeter de HTC, créé à des fins éducatives et de préservation.

## Crédits

- Jeu original: HTC Corporation
- Remake: Créé à partir des ressources extraites de l'application originale
