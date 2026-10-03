# TypingRaceSimulator

Object Oriented Programming Project — ECS414U

## Project Structure

```
TypingRaceSimulator/
├── Part1/    # Textual simulation (Java, command-line)
└── Part2/    # GUI simulation (to be completed)
```

## Part 1 — Textual Simulation

### How to compile

```bash
cd Part1
javac Typist.java TypingRace.java
```

### How to run

The race is started by calling `startRace()` on a `TypingRace` object.
A simple way to test this is to add a `main` method to `TypingRace`, for example:

```java
public static void main(String[] args) {
    TypingRace race = new TypingRace(40);
    race.addTypist(new Typist('①', "TURBOFINGERS", 0.85), 1);
    race.addTypist(new Typist('②', "QWERTY_QUEEN",  0.60), 2);
    race.addTypist(new Typist('③', "HUNT_N_PECK",   0.30), 3);
    race.startRace();
}
```

Then run:

```bash
java TypingRace
```

## Part 2 — GUI Simulation

### How to compile

```bash
cd Part2
javac Typist.java TypingRace.java DrawPanel.java RacerConfigurationScreen.java SetupScreen.java TypingRaceGUI.java TypistConfigurationPanel.java
```

### How to run

The race is started by calling `startRaceGUI()` on a `TypingRace` object.

Run:

```bash
java TypingRace
```

## Technologies

- Java
- Java Swing
