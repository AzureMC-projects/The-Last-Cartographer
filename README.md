# The Last Cartographer

The Last Cartographer is a professional-quality Fabric mod for Minecraft 26.3 centered on exploration, discovery, documentation, clues, and environmental storytelling.

> The world doesn't tell you what happened. You discover it yourself.

## Project status

The repository is being developed phase by phase for eventual public distribution through Modrinth.

Current phase: Phase 1 — Foundation.

The current build is a development build, not a stable Modrinth release. Features are added only after their APIs and multiplayer behavior can be implemented correctly.

## Supported toolchain

- Minecraft Java Edition 26.3
- Fabric Loader 0.19.5
- Fabric API 0.160.7+26.3
- Fabric Loom 1.17
- Gradle 9.6
- Java 25

Minecraft 26.3 is the explicit target. Earlier or later Minecraft versions are not claimed to be compatible.

## Architecture

The project deliberately separates common and client-only code:

- src/main/java — server/common gameplay and data systems
- src/client/java — client initialization, rendering, screens, input, and other client-only systems
- src/main/resources — shared assets and data
- src/client/resources — client-only resources

The mod is designed around server-authoritative gameplay, persistent player/world data, normal Minecraft world-generation APIs, minimal packet traffic, and bounded work around relevant chunks and players.

## Phase 1 foundation

Implemented:

- Fabric 26.3 Gradle project
- Split common/client source sets
- Mod metadata and dependency declarations
- MIT source-code license
- Defensive JSON configuration foundation
- Cartographer's Journal item registration
- Creative inventory integration
- Journal item model/client-item resources
- Initial changelog and project documentation

The following systems are intentionally not represented by fake placeholders yet: the journal UI, discovery registry, persistent discovery data, world generation, artifacts, civilizations, mystery events, NPCs, dimensions, multiplayer synchronization, advancements, and release automation. They will be implemented in their dedicated phases.

## Configuration

The mod creates the file config/the_last_cartographer.json with safe defaults.

Current configuration keys:

- discoveryFrequencySeconds — base interval available to future discovery scheduling systems.
- landmarkRarityMultiplier — multiplier for future landmark rarity configuration.
- mysteryEventFrequencyMultiplier — multiplier for future mystery event frequency.
- npcSpawning — enables future explorer NPC spawning.
- artifactGeneration — enables future artifact generation.
- structureGeneration — enables future structure generation.
- dimensionAccess — enables future hidden-dimension access.
- discoveryRewards — enables future discovery rewards.
- sharedDiscoveries — controls future multiplayer discovery sharing.

Values are normalized defensively on load. Invalid JSON falls back to safe defaults and is logged without crashing the game.

As gameplay systems become active, configuration controls will be exposed through an appropriate server/in-game workflow rather than requiring normal players to edit JSON manually.

## Development setup

1. Install Java 25.
2. Install a Java 25-compatible IDE. IntelliJ IDEA 2025.3 or newer is recommended for the current Fabric toolchain.
3. Clone the repository.
4. Import the project as a Gradle project.
5. Generate or use the Gradle wrapper for the pinned Gradle 9.6 toolchain.
6. Run the generated Fabric client or server configuration.
7. Build with ./gradlew build.

Before a release, the project will be tested in a clean client, a dedicated server, and multiplayer scenarios.

## Release policy

A build is not considered a stable release merely because it compiles.

Stable releases will require:

- clean client launch
- dedicated-server launch
- multiplayer testing
- world-generation testing
- persistence testing across restart
- configuration validation
- log/error review
- dependency verification
- changelog update
- supported-version verification
- clean-install testing

## Modrinth

The eventual Modrinth project page will document exact Minecraft versions, Fabric Loader/Fabric API requirements, installation, configuration, multiplayer behavior, screenshots, known issues, and release notes.

Compatibility will never be claimed for a Minecraft version that has not been tested.

## Licensing and assets

Source code is MIT licensed.

Custom textures, models, sounds, music, written lore, and other non-code assets will be original or separately licensed. Third-party assets will not be bundled without appropriate permission.

Minecraft assets will only be referenced or used in ways consistent with the applicable Minecraft/Mojang terms.

## Contributing

Before adding a system:

1. Verify the Minecraft 26.3/Fabric API.
2. Keep common and client-only code separated.
3. Prefer registries, data-driven content, and normal world-generation APIs.
4. Avoid unnecessary per-tick work and large-area scans.
5. Keep authoritative decisions on the server.
6. Persist data defensively.
7. Add tests or a documented manual test plan for gameplay changes.
8. Update documentation and the changelog when behavior changes.

## Design principle

The player should finish an expedition feeling that they discovered something that existed long before they arrived.

The world is the story. The player is the cartographer.
