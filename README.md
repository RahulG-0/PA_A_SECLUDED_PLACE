# A Secluded Place

An audio-driven horror game written in Java with Swing. You can't see the monster: you hear which direction it is coming from, turn to face it, and defend through a quick-time event. It is best played with headphones.

Built as a high-school team project with [AnirudhBharadwaj](https://github.com/AnirudhBharadwaj).

## Gameplay

- **Move** through each floor with the keyboard, choosing from the directions the game offers.
- **Listen** for the monster. Warning sounds play from the front, back, left or right.
- **Defend** by facing the right direction and pressing defend, then clicking the sequence of buttons before the timer runs out.
- **Smoke bombs** stop the monster gaining health when a defence fails.
- **Clear the floor** by draining the monster's health to collect the key and move up.

There is a demo mode with one floor, a slower quick-time event and extra smoke bombs for learning the controls. Keybinds and volume can be changed from the options menu (Escape). The full manual is in `src/TextFiles/GameManual`.

## How it is built

The game follows a model/view/controller split:

| Part | Files |
|---|---|
| Models | `GameModel`, `TitleModel`, `TotalModel` |
| Views | `GameView`, `TitleView`, `TotalView` |
| Controllers | `MouseController`, `keyboardInput`, `buttonGameController`, `TextFieldController`, `TitleController`, `VolumeController` |
| Audio | `MusicPlayer` with directional warning, footstep and ambience tracks in `src/Music` |

Game state such as keybinds and results is saved to text files in `src/TextFiles`.

## Run it

Requires a JDK (11 or newer).

```bash
cd src
javac *.java
java Main
```
